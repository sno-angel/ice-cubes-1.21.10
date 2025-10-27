package net.sno_angel.icecubes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sno_angel.icecubes.item.ModItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

//@Debug(export = true)
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    public ItemEntityMixin(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract ItemStack getStack();

    @Shadow
    private int pickupDelay;

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo ci) {
        if(getStack().isIn(ModItemTags.CHORAFIL_ITEMS)) {
            double y = getEntityPos().getY();
            double bottom = this.getEntityWorld().getBottomY() + 1;
            if(y <= bottom) {
                setVelocity(getVelocity().getX() * 0.55,
                        getVelocity().getY() + 0.01,getVelocity().getZ() * 0.55);
                setNoGravity(true);
                getEntityWorld().addParticleClient(ParticleTypes.PORTAL, false, false,
                        this.getX(), this.getY()-0.35, this.getZ(), 0, 0, 0);
            }
            else if(y < bottom + 5) {
                setVelocity(getVelocity().getX(),
                        getVelocity().getY() * 0.95, getVelocity().getZ());
                getEntityWorld().addParticleClient(ParticleTypes.PORTAL, false, false,
                        this.getX(), this.getY()-0.35, this.getZ(), 0, 0, 0);
            }
            else {
                setNoGravity(false);
            }
        }
        else if(getStack().isIn(ModItemTags.AGED_PRISMARINE_ITEMS)) {
            if(isTouchingWater() && this.pickupDelay == 0) {
                Box detectionBox = new Box(getBlockPos()).expand(8);
                List<PlayerEntity> players = getEntityWorld().getEntitiesByClass(PlayerEntity.class, detectionBox, Entity::isTouchingWater);
                if(!players.isEmpty()) {
                    PlayerEntity target = players.getFirst();
                    float distanceToTarget = 9;
                    for (PlayerEntity p : players) {
                        if(distanceToTarget < distanceTo(p)) {
                            target = p;
                            distanceToTarget = distanceTo(p);
                        }
                    }
                    if(target.isTouchingWater()) {
                        Vec3d moveDirection = new Vec3d(
                                target.getX() - this.getX(),
                                target.getY() - this.getY(),
                                target.getZ() - this.getZ()
                        );
                        this.addVelocity(moveDirection.normalize().multiply(0.05));
                        getEntityWorld().addParticleClient(ParticleTypes.BUBBLE, false, false,
                                this.getX(), this.getY()+0.35, this.getZ(), 0, 0, 0);
                    }
                }
            }
        }
    }

    /* I'm well aware that Overriding here is a bad practice. However, this is a niche enough
    scenario that's hard enough to implement through an EntityMixin that I don't really care.
    If it ends up causing major compatibility issues, I *might* fix it, but it shouldn't.
     */
    @Override
    protected void tickInVoid() {
        if(!getStack().isIn(ModItemTags.CHORAFIL_ITEMS)) {
            this.discard();
        }
    }
}
