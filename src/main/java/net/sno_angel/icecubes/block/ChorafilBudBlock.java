package net.sno_angel.icecubes.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
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
import net.minecraft.world.tick.ScheduledTickView;

import static net.sno_angel.icecubes.block.ChorafilBloomBlock.FACING;

public class ChorafilBudBlock extends Block {
    public static final MapCodec<ChorafilBudBlock> CODEC = createCodec(ChorafilBudBlock::new);
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE;
    private static final VoxelShape SHAPE;

    public ChorafilBudBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    public MapCodec<ChorafilBudBlock> getCodec() {
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
            if((i == 0 && aboveEndStone) || (i > 0 && i < MAX_AGE && isFloating(world, pos))) {
                if(canRise(world, pos)) {
                    if(random.nextInt(10) == 0) {
                        if(i < MAX_AGE - 1)
                            grow(world, blockPos, i+1);
                        else
                            mature(world, blockPos, random);
                    }
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(isFloating(world, pos)) {
            for(int i = 0; i <= state.get(AGE) / 2; ++i) {
                double particleX = pos.getX() + random.nextDouble();
                double particleZ = pos.getZ() + random.nextDouble();
                world.addParticleClient(ParticleTypes.REVERSE_PORTAL, false, false,
                        particleX, pos.getY(), particleZ, 0,  -0.1, 0);
            }
        }
        super.randomDisplayTick(state, world, pos, random);
    }

    private void grow(World world, BlockPos pos, int age) {
        world.setBlockState(pos, this.getDefaultState().with(AGE, age), 2);
        world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
        world.syncWorldEvent(1033, pos, 0); // TODO: make custom event for Chorafil Bud growth
    }

    private void mature(World world, BlockPos pos, Random random) {
        world.setBlockState(pos, this.getDefaultState().with(AGE, MAX_AGE), 2);
        world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
        boolean hasPlacedBloom = false;
        for(Direction direction : DIRECTIONS) {
            if (world.isAir(pos.offset(direction))) {
                if(random.nextInt(3) == 0) {
                    world.setBlockState(pos.offset(direction), ModBlocks.CHORAFIL_BLOOM.getDefaultState().with(FACING,direction));
                    hasPlacedBloom = true;
                }
            }
        }
        // Failsafe 1 - try to guarantee at least 1 bloom in a random direction
        if(!hasPlacedBloom) {
            Direction direction = DIRECTIONS[random.nextInt(6)];
            if (world.isAir(pos.offset(direction))) {
                world.setBlockState(pos.offset(direction), ModBlocks.CHORAFIL_BLOOM.getDefaultState().with(FACING,direction));
                hasPlacedBloom = true;
            }
        }
        // Failsafe 2 - if the random one failed, down is guaranteed to be open, so place a bloom there
        if(!hasPlacedBloom) {
            if (world.isAir(pos.offset(Direction.DOWN))) {
                world.setBlockState(pos.offset(Direction.DOWN), ModBlocks.CHORAFIL_BLOOM.getDefaultState().with(FACING,Direction.DOWN));
            }
        }

        world.syncWorldEvent(1034, pos, 0);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if(world instanceof World) {
            ((World) world).scheduleBlockTick(pos, state.getBlock(), 1);
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(state.get(AGE) == 0 && !canPlaceAt(state, world, pos)) {
            world.breakBlock(pos, true);
        }
        checkIfAndDie(state, world, pos);
        super.scheduledTick(state, world, pos, random);
    }

    public void checkIfAndDie(BlockState state, World world, BlockPos pos) {
        if(state.get(AGE) == MAX_AGE) {
            boolean shouldDie = true;
            for(Direction direction : DIRECTIONS) {
                if(world.getBlockState(pos.offset(direction)).isOf(ModBlocks.CHORAFIL_BLOOM)
                && world.getBlockState(pos.offset(direction)).get(FACING).equals(direction)) {
                    shouldDie = false;
                }
            }
            if(shouldDie) {
                world.breakBlock(pos, false);
                world.syncWorldEvent(1034, pos, 0);
            }
        }
    }

    private static boolean canRise(WorldView world, BlockPos pos) {
        return world.isAir(pos.up());
    }

    private static boolean isFloating(World world, BlockPos pos) {
        return !world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP);
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
        AGE = Properties.AGE_7;
        SHAPE = Block.createColumnShape(14.0, 0.0, 15.0);
    }
}
