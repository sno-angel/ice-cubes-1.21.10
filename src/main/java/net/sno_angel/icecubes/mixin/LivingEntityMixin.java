package net.sno_angel.icecubes.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.sno_angel.icecubes.item.ModItems;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

// This is currently disabled, as the second ModifyExpressionValue currently prevents the game from launching
// due to a missing refmap. Unsure how to fix this, but this is not a critical feature.
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow
    @Nullable
    private DamageSource lastDamageSource;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Definition(id = "statusEffectInstance", local = @Local(type = StatusEffectInstance.class))
    @Expression("statusEffectInstance != null")
    @ModifyExpressionValue(method = "travelMidAir", at = @At("MIXINEXTRAS:EXPRESSION"))
    public boolean preventLevitation(boolean original) {
        if(this.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.CHORAFIL_BOOTS)) {
            return original && !this.isOnGround();
        }
        return original;
    }

    @Definition(id = "ProjectileEntity", type = ProjectileEntity.class)
    @Definition(id = "var15", local = @Local(name = "var15"))
    @Expression("var15 instanceof ProjectileEntity")
    @ModifyExpressionValue(method = "damage", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    public boolean preventShulkerBulletKnockback(boolean original, ServerWorld world, DamageSource source, float amount) {
        if(source.getSource() instanceof ShulkerBulletEntity)
            return !this.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.CHORAFIL_BOOTS);
        return original;
    }
}
