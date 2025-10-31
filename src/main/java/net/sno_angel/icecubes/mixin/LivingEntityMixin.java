package net.sno_angel.icecubes.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;
import net.sno_angel.icecubes.item.ModItems;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @ModifyExpressionValue(method = "travelInFluid", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;getAttributeValue(Lnet/minecraft/registry/entry/RegistryEntry;)D"))
    public double ifWearingAgedPrismarineBoots(double original) {
        if(this.getEquippedStack(EquipmentSlot.FEET).getItem().equals(ModItems.AGED_PRISMARINE_BOOTS)) {
            return original + 0.0D;
        }
        return original;
    }

}
