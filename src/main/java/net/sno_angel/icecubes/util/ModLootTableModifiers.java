package net.sno_angel.icecubes.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.item.ModItems;


public class ModLootTableModifiers {

    public static void modifyLootTables() {

        // AP Upgrade Smithing Template - Big Ocean Ruin Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(1.0F))
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_UPGRADE_SMITHING_TEMPLATE))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        // AP Upgrade Smithing Template - Small Ocean Ruin Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.07F))
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_UPGRADE_SMITHING_TEMPLATE))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        // AP Shard - Small Ocean Ruin Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.15F))
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_SHARD))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        // AP Shard - Big Ocean Ruin Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.05F))
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_SHARD))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

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


        // TODO: This doesn't work. Why?
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(LootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_SHARD))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1F)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        /*LootTableEvents.REPLACE.register((key, original, source, registries) -> {
            return original;
        });*/
    }
}
