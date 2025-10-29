package net.sno_angel.icecubes.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.sno_angel.icecubes.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//@Debug(export = true)
@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @ModifyExpressionValue(method = "canWalkOnPowderSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private static boolean canWalkWithAgedPrismarineBoots(boolean original, Entity entity) {
        return original || ((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.AGED_PRISMARINE_BOOTS);
    }
}
