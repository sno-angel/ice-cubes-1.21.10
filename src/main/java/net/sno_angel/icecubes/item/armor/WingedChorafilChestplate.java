package net.sno_angel.icecubes.item.armor;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;

import java.util.function.Consumer;

public class WingedChorafilChestplate extends Item {
    public WingedChorafilChestplate(Settings settings) {
        super(settings);
    }

    public static Item.Settings getSettings(){
        Item.Settings settings = new Item.Settings();
        settings.armor(ChorafilArmorMaterial.ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
                .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ChorafilArmorMaterial.ARMOR_MATERIAL.durability()));
        settings.rarity(Rarity.RARE);
        settings.component(DataComponentTypes.GLIDER, Unit.INSTANCE);
        settings.component(
                DataComponentTypes.EQUIPPABLE,
                EquippableComponent.builder(EquipmentSlot.CHEST)
                        .equipSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN)
                        .model(EquipmentAssetKeys.ELYTRA)
                        .build()
        );
        settings.enchantable(12);
        settings.repairable(ChorafilArmorMaterial.REPAIRS_CHORAFIL_ARMOR);
        return settings;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("itemTooltip.icecubes.winged_chorafil_chestplate").formatted(Formatting.GRAY));
    }
}
