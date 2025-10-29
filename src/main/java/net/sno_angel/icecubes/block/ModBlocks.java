package net.sno_angel.icecubes.block;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;
import net.sno_angel.icecubes.item.ModItems;

import java.util.function.Function;

public class ModBlocks implements ModInitializer {
    public static final Block AGED_PRISMARINE_BLOCK = register("aged_prismarine_block", Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.COPPER)
                    .requiresTool()
                    .strength(30,1200)
                    .mapColor(MapColor.TERRACOTTA_WHITE),
            true);
    public static final Block CHORAFIL_BLOCK = register("chorafil_block", Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()
                    .strength(30,1200)
                    .mapColor(MapColor.MAGENTA),
            true);

    public static final Block CHORAFIL_BUD = register("chorafil_bud", ChorafilBudBlock::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.WOOD)
                    .strength(0.6F,0.6F)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.PUSH_ONLY)
                    .mapColor(MapColor.MAGENTA),
            true);

    public static final Block CHORAFIL_BLOOM = register("chorafil_bloom", ChorafilBloomBlock::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.WEEPING_VINES)
                    .strength(0.5F,0.4F)
                    .nonOpaque()
                    .noCollision()
                    .mapColor(MapColor.MAGENTA)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true);

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(IceCubes.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(IceCubes.MOD_ID, name));
    }


    @Override
    public void onInitialize() {
        setupItemGroups();
    }

    public static void setupItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModBlocks.AGED_PRISMARINE_BLOCK.asItem());
            itemGroup.add(ModBlocks.CHORAFIL_BLOCK.asItem());
            itemGroup.add(ModBlocks.CHORAFIL_BUD.asItem());
            itemGroup.add(ModBlocks.CHORAFIL_BLOOM.asItem());
        });
    }
}
