package net.sno_angel.icecubes.item.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.sno_angel.icecubes.entity.misc.BlazebombEntity;

public class BlazebombItem extends ThrowablePotionItem {

    public BlazebombItem(Item.Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        world.playSound((Entity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        return super.use(world, user, hand);
    }

    @Override
    protected PotionEntity createEntity(ServerWorld world, LivingEntity user, ItemStack stack) {
        return BlazebombEntity.spawnFromUser(world, user, stack);
    }

    @Override
    protected PotionEntity createEntity(World world, Position pos, ItemStack stack) {
        return BlazebombEntity.spawnFromPos(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    }
}
