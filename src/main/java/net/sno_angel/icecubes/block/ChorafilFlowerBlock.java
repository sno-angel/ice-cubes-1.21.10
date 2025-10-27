package net.sno_angel.icecubes.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.sno_angel.icecubes.IceCubes;

public class ChorafilFlowerBlock extends Block {
    public static final MapCodec<ChorafilFlowerBlock> CODEC = createCodec(ChorafilFlowerBlock::new);
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE;
    private static final VoxelShape SHAPE;

    public ChorafilFlowerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    public MapCodec<ChorafilFlowerBlock> getCodec() {
        return CODEC;
    }

    protected boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE;
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos blockPos = pos.up();
        if (world.isAir(blockPos) && blockPos.getY() <= world.getTopYInclusive()) {
            int i = state.get(AGE);
            boolean aboveEndStone = canPlaceAt(state, world, pos);
            boolean aboveAir = world.getBlockState(pos.down()).isAir();
            if((i == 0 && aboveEndStone) || (i > 0 && i < MAX_AGE && aboveAir)) {
                if(canRise(world, pos)) {
                    if(random.nextInt(10) == 0) {
                        if(i < 3)
                            grow(world, blockPos, i+1);
                        else
                            mature(world, blockPos, random);
                    }
                }
            }
        }
    }

    private void grow(World world, BlockPos pos, int age) {
        world.setBlockState(pos, this.getDefaultState().with(AGE, age), 2);
        world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
        world.syncWorldEvent(1033, pos, 0); // TODO: make custom event for Chorafil Flower growth
    }

    private void mature(World world, BlockPos pos, Random random) {
        world.setBlockState(pos, this.getDefaultState().with(AGE, MAX_AGE), 2);
        world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
        for(Direction direction : DIRECTIONS) {
            if (world.isAir(pos.offset(direction))) {
                if(random.nextBoolean()) {
                    world.setBlockState(pos.offset(direction), ModBlocks.CHORAFIL_BLOOM.getDefaultState().with(ChorafilBloomBlock.FACING,direction));
                }
            }
        }
        if(world instanceof ServerWorld) {
            ((ServerWorld)world).spawnParticles(ParticleTypes.WHITE_SMOKE,
                    pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 24,0, 0, 0,
                    random.nextDouble() * 0.15);
        }

        world.syncWorldEvent(1034, pos, 0);
    }

    public void checkIfAndDie(World world, BlockPos pos) {
        boolean shouldDie = true;
        for(Direction direction : DIRECTIONS) {
            if(world.getBlockState(pos.offset(direction)).isOf(ModBlocks.CHORAFIL_BLOOM)) {
                shouldDie = false;
            }
        }
        if(shouldDie) {
            FallingBlockEntity.spawnFromBlock(world, pos, world.getBlockState(pos));
            world.syncWorldEvent(1034, pos, 0);
        }
    }

    private static boolean canRise(WorldView world, BlockPos pos) {
        return world.isAir(pos.up());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOf(Blocks.END_STONE);
    }

    protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        BlockPos blockPos = hit.getBlockPos();
        if (world instanceof ServerWorld serverWorld) {
            if (projectile.canModifyAt(serverWorld, blockPos) && projectile.canBreakBlocks(serverWorld)) {
                world.breakBlock(blockPos, true, projectile);
            }
        }
    }

    static {
        AGE = Properties.AGE_4;
        SHAPE = Block.createColumnShape(14.0, 0.0, 15.0);
    }
}
