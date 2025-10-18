package net.sno_angel.icecubes.item.armor;


import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;

import java.util.Map;

public class AgedPrismarineArmorMaterial {
    public static final int BASE_DURABILITY = 30;

    public static final RegistryKey<EquipmentAsset> AGED_PRISMARINE_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(IceCubes.MOD_ID, "aged_prismarine"));
    public static final TagKey<Item> REPAIRS_AGED_PRISMARINE_ARMOR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(IceCubes.MOD_ID, "repairs_aged_prismarine_armor"));

    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    EquipmentType.HELMET, 3,
                    EquipmentType.CHESTPLATE, 8,
                    EquipmentType.LEGGINGS, 6,
                    EquipmentType.BOOTS, 3
            ),
            14,
            SoundEvents.ITEM_ARMOR_EQUIP_COPPER,
            2.5F,
            0.0F,
            REPAIRS_AGED_PRISMARINE_ARMOR,
            AGED_PRISMARINE_ARMOR_MATERIAL_KEY
    );
}
