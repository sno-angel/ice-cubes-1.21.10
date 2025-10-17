package net.sno_angel.icecubes.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;

import java.util.function.Function;

public class ModItems implements ModInitializer {

    // Item registries begin here
    // Chorafil
    public static final Item RAW_CHORAFIL = register("raw_chorafil", Item::new, new Item.Settings());
    public static final Item CRYSTALLIZED_CHORAFIL = register("crystallized_chorafil", Item::new, new Item.Settings());

    // Aged Prismarine
    public static final Item AGED_PRISMARINE_SHARD = register("aged_prismarine_shard", Item::new, new Item.Settings());
    public static final Item AGED_PRISMARINE_PLATE = register("aged_prismarine_plate", Item::new, new Item.Settings());

    // Super Chestplates
    public static final Item STELLAR_NETHERITE_CHESTPLATE = register("stellar_netherite_chestplate", Item::new,
            new Item.Settings().armor(ArmorMaterials.NETHERITE, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ArmorMaterials.NETHERITE.durability()))
    );



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

    public static void initialize() {
        // Register the group.
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

    // Register items to the custom item group.
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.RAW_CHORAFIL);
            itemGroup.add(ModItems.CRYSTALLIZED_CHORAFIL);
            itemGroup.add(ModItems.AGED_PRISMARINE_SHARD);
            itemGroup.add(ModItems.AGED_PRISMARINE_PLATE);
            itemGroup.add(ModItems.STELLAR_NETHERITE_CHESTPLATE);
        });
    }

    @Override
    public void onInitialize() {

    }
}
