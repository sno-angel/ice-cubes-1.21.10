package net.sno_angel.icecubes.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(EntityAttributes.class)
public class EntityAttributesMixin {

    // This is terrible and messy, but it should work
    // I couldn't figure out how to isolate it to only WME, so I have to account for the other clamps with 0, 0, 1 parameters
    @Definition(id = "ClampedEntityAttribute", type = ClampedEntityAttribute.class)
    @Expression("new ClampedEntityAttribute(?, 0.0, 0.0, 1.0)")
    @WrapOperation(method = "<clinit>", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static ClampedEntityAttribute modifyWaterMovementEfficiencyLimit(String translationKey, double fallback, double min, double max, Operation<ClampedEntityAttribute> original) {
        if(translationKey.equals("attribute.name.water_movement_efficiency"))
            return new ClampedEntityAttribute(translationKey, fallback, min, 2.0);
        return new ClampedEntityAttribute(translationKey, fallback, min, max);
    }
}
