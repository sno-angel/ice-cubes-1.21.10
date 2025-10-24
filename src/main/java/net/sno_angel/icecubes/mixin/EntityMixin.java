package net.sno_angel.icecubes.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(at = @At("HEAD"), method = "attemptTickInVoid")
    public void attemptTickInVoid(CallbackInfo ci) {
        // TODO: figure out how to check if this is an ItemEntity
    }
}
