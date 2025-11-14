package net.sno_angel.icecubes.item.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.sno_angel.icecubes.entity.misc.BlazebombEntity;

public class BlazebombItem extends ThrowablePotionItem {

    public BlazebombItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    protected PotionEntity createEntity(ServerWorld world, LivingEntity user, ItemStack stack) {
        return null; //new BlazebombEntity(world, user, stack);
    }

    @Override
    protected PotionEntity createEntity(World world, Position pos, ItemStack stack) {
        return null;//new BlazebombEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    }
}
