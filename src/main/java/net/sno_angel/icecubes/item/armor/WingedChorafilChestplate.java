package net.sno_angel.icecubes.item.armor;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;

public class WingedChorafilChestplate extends Item {
    public WingedChorafilChestplate(Settings settings) {
        super(settings);
    }

    public static Item.Settings getSettings(){
        return new Item.Settings()
            .armor(ChorafilArmorMaterial.ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ChorafilArmorMaterial.ARMOR_MATERIAL.durability()))
            .rarity(Rarity.RARE)
            .component(DataComponentTypes.GLIDER, Unit.INSTANCE)
            .component(
                DataComponentTypes.EQUIPPABLE,
                EquippableComponent.builder(EquipmentSlot.CHEST)
                        .equipSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN)
                        .model(EquipmentAssetKeys.ELYTRA)
                        .build()
            )
            .enchantable(12)
            .repairable(ChorafilArmorMaterial.REPAIRS_CHORAFIL_ARMOR)
            .attributeModifiers(ChorafilArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.CHESTPLATE)
                .with(EntityAttributes.GRAVITY, new EntityAttributeModifier(Identifier.of(
                                "icecubes", "armor.winged_chorafil_chestplate"),
                                -0.075, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                        AttributeModifierSlot.CHEST));
    }
}
