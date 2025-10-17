package net.sno_angel.icecubes.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

public class StellarNetheriteChestplate extends Item {

    private boolean effectsActive;

    public StellarNetheriteChestplate(Settings settings) {
        super(settings);
        effectsActive = false;
    }

    public static Item.Settings getSettings(){
        Item.Settings settings = new Item.Settings();
        settings.armor(ArmorMaterials.NETHERITE, EquipmentType.CHESTPLATE)
                .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ArmorMaterials.NETHERITE.durability()));
        settings.rarity(Rarity.RARE);
        settings.fireproof();
        settings.enchantable(12);
        settings.repairable(Items.NETHERITE_INGOT);
        return settings;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity == null || world.isClient()) return;
        if(slot == EquipmentSlot.CHEST && entity instanceof LivingEntity) {
            if(entity.isOnFire()) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 2, false, false, true));
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 2, false, false, true));
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 60, 1, false, false, true));
                if(!effectsActive) {
                    world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS);
                    world.spawnParticles(ParticleTypes.FLAME, entity.getX(), entity.getY()+1, entity.getZ(),
                            72, 0d, 0d, 0d, Math.random() * 0.15);
                    world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, entity.getX(), entity.getY()+1, entity.getZ(),
                            24, 0d, 0d, 0d, Math.random() * 0.15);
                    effectsActive = true;
                }
                entity.extinguish();
            }
            if(effectsActive) {
                world.spawnParticles(ParticleTypes.SMOKE, entity.getX(), entity.getY()+1, entity.getZ(),
                        4, 0.25d, 0.25d, 0.25d, 0);
                if((!((LivingEntity)entity).hasStatusEffect(StatusEffects.SPEED) ||
                        !((LivingEntity)entity).hasStatusEffect(StatusEffects.REGENERATION) ||
                        !((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE))) {
                    world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS);
                    effectsActive = false;
                    entity.extinguish();
                }
            }

        }

    }


}
