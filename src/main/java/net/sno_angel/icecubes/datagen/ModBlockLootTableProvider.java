package net.sno_angel.icecubes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.PotatoesBlock;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.state.property.Property;
import net.sno_angel.icecubes.block.ChorafilBudBlock;
import net.sno_angel.icecubes.block.ModBlocks;
import net.sno_angel.icecubes.item.ModItems;

import java.util.Properties;
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

        // There's gotta be a better way to do this
        LootCondition.Builder chorafilBudAgeConditionBuilder = BlockStatePropertyLootCondition.builder(
                ModBlocks.CHORAFIL_BUD).properties(StatePredicate.Builder
                .create().exactMatch(ChorafilBudBlock.AGE, 0));
        addDrop(ModBlocks.CHORAFIL_BUD, (block ->
                LootTable.builder().pool(LootPool.builder()
                        .with(ItemEntry.builder(ModBlocks.CHORAFIL_BUD.asItem()))
                        .conditionally(chorafilBudAgeConditionBuilder)
                        .build())));
    }
}
