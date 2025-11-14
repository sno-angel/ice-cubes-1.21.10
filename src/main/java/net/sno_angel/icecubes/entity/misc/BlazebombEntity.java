package net.sno_angel.icecubes.entity.misc;

import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sno_angel.icecubes.entity.ModEntityTypes;

import java.util.List;

public class BlazebombEntity extends PotionEntity {
    private static final int BURN_RANGE = 1;

    public BlazebombEntity(EntityType<? extends PotionEntity> entityType, World world) {
        super(entityType, world);
    }
/*
    public BlazebombEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntityTypes.BLAZEBOMB, world, owner, stack);
    }

    public BlazebombEntity(World world, double x, double y, double z, ItemStack stack) {
        super(ModEntityTypes.BLAZEBOMB, world, x, y, z, stack);
    } */

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        explode(blockHitResult);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        explode(hitResult);
    }

    private void explode(HitResult hitResult) {
        if (!this.getEntityWorld().isClient()) {
            World world = this.getEntityWorld();
            Vec3d hitPos = hitResult.getPos();
            BlockPos hitBlock = new BlockPos((int)hitResult.getPos().getX(), (int)hitResult.getPos().getY(), (int)hitResult.getPos().getZ());
            if(this.getEntityWorld() instanceof ServerWorld) {
                ((ServerWorld)world).spawnParticles(ParticleTypes.LAVA, hitPos.getX(), hitPos.getY(), hitPos.getZ(), 24, 0, 0, 0, 5);
            }
            Box burnBox = new Box(hitBlock).expand(BURN_RANGE);
            List<LivingEntity> entities = getEntityWorld().getEntitiesByClass(LivingEntity.class, burnBox, Entity::isAlive);
            for(Entity entity : entities) {
                entity.setOnFire(true);
            }
            for(int x = -BURN_RANGE; x <= BURN_RANGE; x++) {
                for(int y = -BURN_RANGE; y <= BURN_RANGE; y++) {
                    for(int z = -BURN_RANGE; z <= BURN_RANGE; z++) {
                        BlockPos burnBlock = new BlockPos(hitBlock.getX() + x, hitBlock.getY() + y, hitBlock.getZ() + z);
                        if(FireBlock.canPlaceAt(world, burnBlock, Direction.UP)) {
                            world.setBlockState(burnBlock, Blocks.FIRE.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void spawnAreaEffectCloud(ServerWorld world, ItemStack stack, HitResult hitResult) {
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }
}
