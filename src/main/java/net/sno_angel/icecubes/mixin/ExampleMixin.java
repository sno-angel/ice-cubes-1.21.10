package net.sno_angel.icecubes.mixin;

import net.minecraft.server.MinecraftServer;
import net.sno_angel.icecubes.IceCubes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
        IceCubes.LOGGER.debug("Mixins running!");
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}