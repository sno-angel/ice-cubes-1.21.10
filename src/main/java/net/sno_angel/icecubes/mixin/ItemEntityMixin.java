package net.sno_angel.icecubes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.sno_angel.icecubes.IceCubes;
import net.sno_angel.icecubes.util.ModItemTagProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    public ItemEntityMixin(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract ItemStack getStack();

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V")
    private void ItemEntity(EntityType<? extends ItemEntity> entityType, World world, CallbackInfo ci) {
        IceCubes.LOGGER.info("test");
        if(this.getStack().isIn(ModItemTagProvider.CHORAFIL_ITEMS)) {
            this.setNoGravity(true);
            IceCubes.LOGGER.info("gravity");
        }
    }

}
