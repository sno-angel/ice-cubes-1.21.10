package net.sno_angel.icecubes.item.armor;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

public class OceanicAgedPrismarineChestplate extends Item {

    private boolean effectActive;

    public OceanicAgedPrismarineChestplate(Settings settings) {
        super(settings);
    }

    public static Item.Settings getSettings(){
        return new Item.Settings()
            .armor(AgedPrismarineArmorMaterial.ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(AgedPrismarineArmorMaterial.ARMOR_MATERIAL.durability()))
            .rarity(Rarity.RARE)
            .enchantable(12)
            .repairable(AgedPrismarineArmorMaterial.REPAIRS_AGED_PRISMARINE_ARMOR)
            .attributeModifiers(AgedPrismarineArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.CHESTPLATE)
                .with(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, new EntityAttributeModifier(Identifier.of(
                        "icecubes", "armor.oceanic_aged_prismarine_chestplate"),
                                0.25, EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.CHEST));
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!(entity instanceof LivingEntity) || world.isClient()) return;
        if(slot == EquipmentSlot.CHEST && entity.isTouchingWaterOrRain()) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER,
                    210, 0, false, false, true));
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,
                    210, 0, false, false, true));
        }
    }
}

