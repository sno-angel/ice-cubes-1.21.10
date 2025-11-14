package net.sno_angel.icecubes.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.sno_angel.icecubes.IceCubes;
import net.sno_angel.icecubes.item.armor.*;
import net.sno_angel.icecubes.item.food.BlazePepper;
import net.sno_angel.icecubes.item.weapon.ChorafilCrossbowItem;
import net.sno_angel.icecubes.trim.ModTrimMaterials;

import java.util.function.Function;

public class ModItems implements ModInitializer {

    // Item registries begin here
    // Aged Prismarine
    public static final Item AGED_PRISMARINE_SHARD = register("aged_prismarine_shard", Item::new, new Item.Settings());
    public static final Item AGED_PRISMARINE_PLATE = register("aged_prismarine_plate", Item::new, new Item.Settings().trimMaterial(ModTrimMaterials.AGED_PRISMARINE));
    public static final Item AGED_PRISMARINE_HELMET = register("aged_prismarine_helmet", Item::new, new Item.Settings()
            .armor(AgedPrismarineArmorMaterial.ARMOR_MATERIAL, EquipmentType.HELMET)
            .maxDamage(EquipmentType.HELMET.getMaxDamage(ChorafilArmorMaterial.BASE_DURABILITY))
            .attributeModifiers(AgedPrismarineArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.HELMET)
                    .with(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, new EntityAttributeModifier(Identifier.of(
                                    "icecubes", "armor.aged_prismarine_helmet"),
                                    0.25, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.HEAD)));
    public static final Item AGED_PRISMARINE_CHESTPLATE = register("aged_prismarine_chestplate", Item::new, new Item.Settings()
            .armor(AgedPrismarineArmorMaterial.ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
            .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ChorafilArmorMaterial.BASE_DURABILITY))
            .attributeModifiers(AgedPrismarineArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.CHESTPLATE)
                    .with(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, new EntityAttributeModifier(Identifier.of(
                                    "icecubes", "armor.aged_prismarine_chestplate"),
                                    0.25, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.CHEST)));
    public static final Item AGED_PRISMARINE_LEGGINGS = register("aged_prismarine_leggings", Item::new, new Item.Settings()
            .armor(AgedPrismarineArmorMaterial.ARMOR_MATERIAL, EquipmentType.LEGGINGS)
            .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(ChorafilArmorMaterial.BASE_DURABILITY))
            .attributeModifiers(AgedPrismarineArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.LEGGINGS)
                    .with(EntityAttributes.WATER_MOVEMENT_EFFICIENCY, new EntityAttributeModifier(Identifier.of(
                                    "icecubes", "armor.aged_prismarine_leggings"),
                                    0.25, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.LEGS)));
    public static final Item AGED_PRISMARINE_BOOTS = register("aged_prismarine_boots", Item::new, new Item.Settings()
            .armor(AgedPrismarineArmorMaterial.ARMOR_MATERIAL, EquipmentType.BOOTS)
            .maxDamage(EquipmentType.BOOTS.getMaxDamage(ChorafilArmorMaterial.BASE_DURABILITY))
            .attributeModifiers(AttributeModifiersComponent.builder().add(EntityAttributes.WATER_MOVEMENT_EFFICIENCY,
                    new EntityAttributeModifier(Identifier.of("icecubes",
                            "armor.aged_prismarine_boots"), 0.25,
                            EntityAttributeModifier.Operation.ADD_VALUE),
                    AttributeModifierSlot.FEET).build()));

    // Chorafil
    public static final Item RAW_CHORAFIL = register("raw_chorafil", Item::new, new Item.Settings());
    public static final Item CRYSTALLIZED_CHORAFIL = register("crystallized_chorafil", Item::new, new Item.Settings().trimMaterial(ModTrimMaterials.CHORAFIL));
    public static final Item CHORAFIL_HELMET = register("chorafil_helmet", Item::new, new Item.Settings()
            .armor(ChorafilArmorMaterial.ARMOR_MATERIAL, EquipmentType.HELMET)
            .maxDamage(EquipmentType.HELMET.getMaxDamage(ChorafilArmorMaterial.BASE_DURABILITY))
            .attributeModifiers(ChorafilArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.HELMET)
                    .with(EntityAttributes.GRAVITY, new EntityAttributeModifier(Identifier.of(
                            "icecubes", "armor.chorafil_helmet"),
                                    -0.075, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            AttributeModifierSlot.HEAD)));
    public static final Item CHORAFIL_CHESTPLATE = register("chorafil_chestplate", Item::new, new Item.Settings()
            .armor(ChorafilArmorMaterial.ARMOR_MATERIAL, EquipmentType.CHESTPLATE)
            .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ChorafilArmorMaterial.BASE_DURABILITY))
            .attributeModifiers(ChorafilArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.CHESTPLATE)
                    .with(EntityAttributes.GRAVITY, new EntityAttributeModifier(Identifier.of(
                            "icecubes", "armor.chorafil_chestplate"),
                                    -0.075, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            AttributeModifierSlot.CHEST)));
    public static final Item CHORAFIL_LEGGINGS = register("chorafil_leggings", Item::new, new Item.Settings()
            .armor(ChorafilArmorMaterial.ARMOR_MATERIAL, EquipmentType.LEGGINGS)
            .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(ChorafilArmorMaterial.BASE_DURABILITY))
            .attributeModifiers(ChorafilArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.LEGGINGS)
                    .with(EntityAttributes.GRAVITY, new EntityAttributeModifier(Identifier.of(
                            "icecubes", "armor.chorafil_leggings"),
                                    -0.075, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            AttributeModifierSlot.LEGS)));
    public static final Item CHORAFIL_BOOTS = register("chorafil_boots", Item::new, new Item.Settings()
            .armor(ChorafilArmorMaterial.ARMOR_MATERIAL, EquipmentType.BOOTS)
            .maxDamage(EquipmentType.BOOTS.getMaxDamage(ChorafilArmorMaterial.BASE_DURABILITY))
            .attributeModifiers(ChorafilArmorMaterial.ARMOR_MATERIAL.createAttributeModifiers(EquipmentType.BOOTS)
                    .with(EntityAttributes.GRAVITY, new EntityAttributeModifier(Identifier.of(
                            "icecubes", "armor.chorafil_boots"),
                                    -0.075, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            AttributeModifierSlot.FEET)));
    public static final Item CHORAFIL_CROSSBOW = register("chorafil_crossbow", ChorafilCrossbowItem::new, ChorafilCrossbowItem.getSettings());

    // Upgraded Chestplates
    public static final Item OCEANIC_AGED_PRISMARINE_CHESTPLATE = register("oceanic_aged_prismarine_chestplate",
            OceanicAgedPrismarineChestplate::new, OceanicAgedPrismarineChestplate.getSettings());
    public static final Item WINGED_CHORAFIL_CHESTPLATE = register("winged_chorafil_chestplate",
            WingedChorafilChestplate::new, WingedChorafilChestplate.getSettings());
    public static final Item STELLAR_NETHERITE_CHESTPLATE = register("stellar_netherite_chestplate",
            StellarNetheriteChestplate::new, StellarNetheriteChestplate.getSettings());

    // Smithing Templates
    public static final SmithingTemplateItem CHORAFIL_UPGRADE_SMITHING_TEMPLATE = (SmithingTemplateItem) register(
            "chorafil_upgrade_smithing_template", ChorafilArmorMaterial::createChorafilUpgrade, new Item.Settings().rarity(Rarity.UNCOMMON));
    public static final SmithingTemplateItem AGED_PRISMARINE_UPGRADE_SMITHING_TEMPLATE = (SmithingTemplateItem) register(
            "aged_prismarine_upgrade_smithing_template", AgedPrismarineArmorMaterial::createAgedPrismarineUpgrade, new Item.Settings().rarity(Rarity.UNCOMMON));

    //  Blaze Pepper
    public static final Item BLAZE_PEPPER = register("blaze_pepper", BlazePepper::new, BlazePepper.getSettings());
    public static final Item BLAZE_PEPPER_CORE = register("blaze_pepper_core", Item::new, new Item.Settings());
    public static final Item PEPPER_POWDER = register("pepper_powder", Item::new, new Item.Settings());

    public static final Item ELDER_EFFIGY = register("elder_effigy", Item::new, new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1));


    // Don't touch this
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(IceCubes.MOD_ID, "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.RAW_CHORAFIL))
            .displayName(Text.translatable("itemGroup.icecubes"))
            .build();

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(IceCubes.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void registerModItems() {
        // Register the group.
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

        // Register items to the custom item group.
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.AGED_PRISMARINE_SHARD);
            itemGroup.add(ModItems.AGED_PRISMARINE_PLATE);
            itemGroup.add(ModItems.AGED_PRISMARINE_HELMET);
            itemGroup.add(ModItems.AGED_PRISMARINE_CHESTPLATE);
            itemGroup.add(ModItems.AGED_PRISMARINE_LEGGINGS);
            itemGroup.add(ModItems.AGED_PRISMARINE_BOOTS);
            itemGroup.add(ModItems.RAW_CHORAFIL);
            itemGroup.add(ModItems.CRYSTALLIZED_CHORAFIL);
            itemGroup.add(ModItems.CHORAFIL_HELMET);
            itemGroup.add(ModItems.CHORAFIL_CHESTPLATE);
            itemGroup.add(ModItems.CHORAFIL_LEGGINGS);
            itemGroup.add(ModItems.CHORAFIL_BOOTS);
            itemGroup.add(ModItems.OCEANIC_AGED_PRISMARINE_CHESTPLATE);
            registerTooltip(ModItems.OCEANIC_AGED_PRISMARINE_CHESTPLATE, "oceanic_aged_prismarine_chestplate");
            itemGroup.add(ModItems.WINGED_CHORAFIL_CHESTPLATE);
            registerTooltip(ModItems.WINGED_CHORAFIL_CHESTPLATE, "winged_chorafil_chestplate");
            itemGroup.add(ModItems.CHORAFIL_CROSSBOW);
            itemGroup.add(ModItems.STELLAR_NETHERITE_CHESTPLATE);
            registerTooltip(ModItems.STELLAR_NETHERITE_CHESTPLATE, "stellar_netherite_chestplate");
            itemGroup.add(ModItems.AGED_PRISMARINE_UPGRADE_SMITHING_TEMPLATE);
            itemGroup.add(ModItems.CHORAFIL_UPGRADE_SMITHING_TEMPLATE);
            itemGroup.add(ModItems.BLAZE_PEPPER);
            itemGroup.add(ModItems.BLAZE_PEPPER_CORE);
            itemGroup.add(ModItems.PEPPER_POWDER);
            itemGroup.add(ModItems.ELDER_EFFIGY);
        });
    }

    private static void registerTooltip(Item item, String itemId) {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (!itemStack.isOf(item)) {
                return;
            }
            list.add(1, Text.translatable("item.icecubes."+itemId+".tooltip").withColor(Colors.LIGHT_GRAY));
        });
    }

    @Override
    public void onInitialize() {
        registerModItems();
    }
}
