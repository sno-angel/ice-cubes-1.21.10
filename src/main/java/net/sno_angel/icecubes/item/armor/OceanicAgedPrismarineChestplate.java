package net.sno_angel.icecubes.item.armor;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class OceanicAgedPrismarineChestplate extends Item {

    private boolean effectActive;

    public OceanicAgedPrismarineChestplate(Settings settings) {
        super(settings);
    }

    public static Item.Settings getSettings(){
        Item.Settings settings = new Item.Settings();
        settings.armor(AgedPrismarineArmorMaterial.ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(AgedPrismarineArmorMaterial.ARMOR_MATERIAL.durability()));
        settings.rarity(Rarity.RARE);
        settings.enchantable(12);
        settings.repairable(AgedPrismarineArmorMaterial.REPAIRS_AGED_PRISMARINE_ARMOR);
        return settings;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!(entity instanceof LivingEntity) || world.isClient()) return;
        if(slot == EquipmentSlot.CHEST) {
            if(entity.isTouchingWaterOrRain()) {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER,
                        40, 1, false, false, true));
                if(!effectActive) {
                    world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_CONDUIT_ACTIVATE, SoundCategory.PLAYERS);
                    effectActive = true;
                }
            }
            if(effectActive) {
                world.spawnParticles(ParticleTypes.NAUTILUS, entity.getX(), entity.getY()+1, entity.getZ(),
                        1, 0.25d, 0.25d, 0.25d, 0);
                if((!((LivingEntity)entity).hasStatusEffect(StatusEffects.CONDUIT_POWER))) {
                    world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.PLAYERS);
                    effectActive = false;
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("itemTooltip.icecubes.oceanic_aged_prismarine_chestplate").formatted(Formatting.GRAY));
    }
}

