package net.sno_angel.icecubes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.sno_angel.icecubes.item.ModItemTags;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    public ItemEntityMixin(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract ItemStack getStack();

    /*@Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;DDD)V")
    public void ItemEntity(World world, double x, double y, double z, ItemStack stack, double velocityX,
                            double velocityY, double velocityZ, CallbackInfo ci) {
        if(getStack().isIn(ModItemTags.CHORAFIL_ITEMS)) {

        }
    }*/

    /* I'm well aware that this is a bad practice. However, this is a niche enough scenario
    that's hard enough to implement through custom classes that I don't really care. If it
    ends up causing major compatibility issues, I *might* fix it, but it shouldn't.
     */

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo ci) {
        if(getStack().isIn(ModItemTags.CHORAFIL_ITEMS)) {
            double y = getEntityPos().getY();
            double bottom = this.getEntityWorld().getBottomY() + 1;
            if(y <= bottom) {
                setVelocity(getVelocity().getX() * 0.55,
                        getVelocity().getY() + 0.01,getVelocity().getZ() * 0.55);
                setNoGravity(true);
            }
            /* else if(y < bottom + 1) {
                setVelocity(getVelocity().getX(),
                        getVelocity().getY() * 0.9, getVelocity().getZ());
            }*/
            else {
                setNoGravity(false);
            }
        }
    }



    @Override
    protected void tickInVoid() {
        if(!getStack().isIn(ModItemTags.CHORAFIL_ITEMS)) {
            this.discard();
        }
    }
}
