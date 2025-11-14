package net.sno_angel.icecubes.entity;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlazebombEntity extends PotionEntity {
    private static int BURN_RANGE;

    public BlazebombEntity(EntityType<? extends PotionEntity> entityType, World world) {
        super(entityType, world);
    }

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
            Vec3d hitPos = hitResult.getPos();
            //for(int i = 0; i < )
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
