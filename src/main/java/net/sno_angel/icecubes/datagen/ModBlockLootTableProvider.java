package net.sno_angel.icecubes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryWrapper;
import net.sno_angel.icecubes.block.ModBlocks;
import net.sno_angel.icecubes.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CHORAFIL_BLOOM, (block) ->
                this.dropsWithSilkTouchOrShears(block,
                        this.addSurvivesExplosionCondition(block,
                                ItemEntry.builder(ModItems.RAW_CHORAFIL))));
    }
}
