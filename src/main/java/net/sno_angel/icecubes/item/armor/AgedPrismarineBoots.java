package net.sno_angel.icecubes.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class AgedPrismarineBoots extends Item {
    public AgedPrismarineBoots(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!(entity instanceof LivingEntity) || world.isClient()) return;
        if(slot == EquipmentSlot.FEET) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE,
                    200, 0, false, false, true));
        }
    }
}
