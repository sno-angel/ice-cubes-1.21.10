package net.sno_angel.icecubes.item.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.sno_angel.icecubes.entity.BlazebombEntity;
import net.sno_angel.icecubes.item.ModItems;

public class BlazebombItem extends ThrowablePotionItem {

    public BlazebombItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    protected PotionEntity createEntity(ServerWorld world, LivingEntity user, ItemStack stack) {
        return null;
    }

    @Override
    protected PotionEntity createEntity(World world, Position pos, ItemStack stack) {
        return null;
    }
}
