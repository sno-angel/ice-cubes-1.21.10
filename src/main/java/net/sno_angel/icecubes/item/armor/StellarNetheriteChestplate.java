package net.sno_angel.icecubes.item.armor;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;
import java.util.function.Consumer;

public class StellarNetheriteChestplate extends Item /*implements GeoItem*/ {

    private boolean effectActive;

    public StellarNetheriteChestplate(Settings settings) {
        super(settings);
    }

    public boolean isEffectActive() {
        return effectActive;
    }

    public static Item.Settings getSettings(){
        Item.Settings settings = new Item.Settings();
        settings.armor(ArmorMaterials.NETHERITE, EquipmentType.CHESTPLATE)
                .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ArmorMaterials.NETHERITE.durability()));
        settings.rarity(Rarity.RARE);
        settings.fireproof();
        settings.enchantable(13);
        settings.repairable(ArmorMaterials.NETHERITE.repairIngredient());
        return settings;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!(entity instanceof LivingEntity) || world.isClient()) return;
        if(slot == EquipmentSlot.CHEST) {
            if(entity.isOnFire()) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,
                        80, 1, false, false, true));
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,
                        80, 0, false, false, false));
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,
                        80, 0, false, false, true));
                if(!effectActive) {
                    world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS);
                    world.spawnParticles(ParticleTypes.FLAME, entity.getX(), entity.getY()+1, entity.getZ(),
                            72, 0d, 0d, 0d, Math.random() * 0.15);
                    world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, entity.getX(), entity.getY()+1, entity.getZ(),
                            24, 0d, 0d, 0d, Math.random() * 0.15);
                }
                entity.extinguish();
                effectActive = true;
            }
            if(effectActive) {
                world.spawnParticles(ParticleTypes.SMOKE, entity.getX(), entity.getY()+1, entity.getZ(),
                        2, 0.25d, 0.25d, 0.25d, 0);
                if((!((LivingEntity)entity).hasStatusEffect(StatusEffects.SPEED) ||
                        !((LivingEntity)entity).hasStatusEffect(StatusEffects.ABSORPTION) ||
                        !((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE))) {
                    world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS);
                    effectActive = false;
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("itemTooltip.icecubes.stellar_netherite_chestplate").formatted(Formatting.GRAY));
    }
/*
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }
 */
}

