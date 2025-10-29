package net.sno_angel.icecubes.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.block.ModBlocks;
import net.sno_angel.icecubes.item.ModItems;


public class ModLootTableModifiers {

    public static void modifyLootTables() {
        // AP Upgrade Smithing Template - Big Ocean Ruin Chest
        addOneToTable(LootTables.UNDERWATER_RUIN_BIG_CHEST, ModItems.AGED_PRISMARINE_UPGRADE_SMITHING_TEMPLATE, RandomChanceLootCondition.builder(1.0F));

        // AP Upgrade Smithing Template - Small Ocean Ruin Chest
        addOneToTable(LootTables.UNDERWATER_RUIN_SMALL_CHEST, ModItems.AGED_PRISMARINE_UPGRADE_SMITHING_TEMPLATE, RandomChanceLootCondition.builder(0.05F));

        // AP Shard - Small Ocean Ruin Chest
        addOneToTable(LootTables.UNDERWATER_RUIN_SMALL_CHEST, ModItems.AGED_PRISMARINE_SHARD, RandomChanceLootCondition.builder(0.05F));

        // AP Shard - Big Ocean Ruin Chest
        addOneToTable(LootTables.UNDERWATER_RUIN_SMALL_CHEST, ModItems.AGED_PRISMARINE_SHARD, RandomChanceLootCondition.builder(0.05F));

        // AP Shard - Elder Guardian
        Identifier ELDER_GUARDIAN_ID = Identifier.of("minecraft","entities/elder_guardian");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(ELDER_GUARDIAN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_SHARD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F,5.0F)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        // Chorafil Upgrade Smithing Template - End City Treasure Chest // TODO: Remove this when adding Obsidian Watchtower
        addOneToTable(LootTables.UNDERWATER_RUIN_BIG_CHEST, ModItems.CHORAFIL_UPGRADE_SMITHING_TEMPLATE, RandomChanceLootCondition.builder(0.06F));

        // Raw Chorafil - End City Treasure Chest
        addOneToTable(LootTables.END_CITY_TREASURE_CHEST, ModItems.RAW_CHORAFIL, RandomChanceLootCondition.builder(0.1F));

        // Chorafil Bud - End City Treasure Chest
        addOneToTable(LootTables.END_CITY_TREASURE_CHEST, ModBlocks.CHORAFIL_BUD.asItem(), RandomChanceLootCondition.builder(0.04F));

        // TODO: This doesn't work. Why?
        /* LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(LootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_SHARD))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1F)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        }); */

        /*LootTableEvents.REPLACE.register((key, original, source, registries) -> {
            return original;
        });*/
    }

    private static void addOneToTable(RegistryKey<LootTable> table, Item item, LootCondition.Builder condition) {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(table.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(condition)
                        .with(ItemEntry.builder(item))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
