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
                    .pistonBehavior(PistonBehavior.DESTROY)
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
    public static final Block CHORAFIL_LANTERN = register("chorafil_lantern", Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.TUFF)
                    .strength(0.1F,0.1F)
                    .nonOpaque()
                    .luminance((state) -> 15)
                    .mapColor(MapColor.MAGENTA),
            true);
    public static final Block GILDED_CHORAFIL_LANTERN = register("gilded_chorafil_lantern", Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.TUFF)
                    .strength(0.1F,0.1F)
                    .nonOpaque()
                    .luminance((state) -> 15)
                    .mapColor(MapColor.GOLD),
            true);
    public static final Block RED_GILDED_CHORAFIL_LANTERN = register("red_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block ORANGE_GILDED_CHORAFIL_LANTERN = register("orange_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block YELLOW_GILDED_CHORAFIL_LANTERN = register("yellow_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block LIME_GILDED_CHORAFIL_LANTERN = register("lime_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block GREEN_GILDED_CHORAFIL_LANTERN = register("green_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block CYAN_GILDED_CHORAFIL_LANTERN = register("cyan_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block LIGHT_BLUE_GILDED_CHORAFIL_LANTERN = register("light_blue_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block BLUE_GILDED_CHORAFIL_LANTERN = register("blue_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block PURPLE_GILDED_CHORAFIL_LANTERN = register("purple_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block MAGENTA_GILDED_CHORAFIL_LANTERN = register("magenta_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block PINK_GILDED_CHORAFIL_LANTERN = register("pink_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block BROWN_GILDED_CHORAFIL_LANTERN = register("brown_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block WHITE_GILDED_CHORAFIL_LANTERN = register("white_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block LIGHT_GRAY_GILDED_CHORAFIL_LANTERN = register("light_gray_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block GRAY_GILDED_CHORAFIL_LANTERN = register("gray_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);
    public static final Block BLACK_GILDED_CHORAFIL_LANTERN = register("black_gilded_chorafil_lantern", Block::new, AbstractBlock.Settings.create().sounds(BlockSoundGroup.TUFF).strength(0.1F,0.1F).nonOpaque().luminance((state) -> 15).mapColor(MapColor.GOLD), true);


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
            itemGroup.add(ModBlocks.CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.RED_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.ORANGE_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.YELLOW_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.LIME_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.GREEN_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.CYAN_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.LIGHT_BLUE_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.BLUE_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.PURPLE_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.MAGENTA_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.PINK_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.BROWN_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.WHITE_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.LIGHT_GRAY_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.GRAY_GILDED_CHORAFIL_LANTERN.asItem());
            itemGroup.add(ModBlocks.BLACK_GILDED_CHORAFIL_LANTERN.asItem());
        });
    }
}
