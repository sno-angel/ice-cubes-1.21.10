package net.sno_angel.icecubes.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.projectile.ProjectileEntity;
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
            boolean aboveEndStone = world.getBlockState(pos.down()).isOf(Blocks.END_STONE);
            boolean aboveAir = world.getBlockState(pos.down()).isAir();
            if((i == 0 && aboveEndStone) || (i > 0 && i < MAX_AGE && aboveAir)) {
                if(canRise(world, blockPos)) {
                    if(random.nextInt(10) == 0) {
                        if(i < 3)
                            grow(world, blockPos, i+1);
                        else
                            mature(world, blockPos);
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

    private void mature(World world, BlockPos pos) {
        world.setBlockState(pos, this.getDefaultState().with(AGE, 4), 2);
        world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
        world.syncWorldEvent(1034, pos, 0);
    }

    private static boolean canRise(WorldView world, BlockPos pos) {
        if (!world.isAir(pos.up())) {
            return false;
        }
        for(Direction direction : Direction.Type.HORIZONTAL) {
            if (!world.isAir(pos.offset(direction))) {
                return false;
            }
        }

        return true;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
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
