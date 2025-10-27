package net.sno_angel.icecubes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;
import net.sno_angel.icecubes.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public static final TagKey<Item> CHORAFIL_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(IceCubes.MOD_ID, "chorafil_items"));
    public static final TagKey<Item> AGED_PRISMARINE_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(IceCubes.MOD_ID, "aged_prismarine_items"));

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(CHORAFIL_ITEMS)
                .add(ModItems.RAW_CHORAFIL)
                .add(ModItems.CRYSTALLIZED_CHORAFIL)
                .add(ModItems.CHORAFIL_HELMET)
                .add(ModItems.CHORAFIL_CHESTPLATE)
                .add(ModItems.CHORAFIL_LEGGINGS)
                .add(ModItems.CHORAFIL_BOOTS)
                .add(ModItems.WINGED_CHORAFIL_CHESTPLATE)
                .setReplace(true);

        valueLookupBuilder(AGED_PRISMARINE_ITEMS)
                .add(ModItems.AGED_PRISMARINE_SHARD)
                .add(ModItems.AGED_PRISMARINE_PLATE)
                .add(ModItems.AGED_PRISMARINE_HELMET)
                .add(ModItems.AGED_PRISMARINE_CHESTPLATE)
                .add(ModItems.AGED_PRISMARINE_LEGGINGS)
                .add(ModItems.AGED_PRISMARINE_BOOTS)
                .add(ModItems.OCEANIC_AGED_PRISMARINE_CHESTPLATE)
                .setReplace(true);
        //
    }
}
