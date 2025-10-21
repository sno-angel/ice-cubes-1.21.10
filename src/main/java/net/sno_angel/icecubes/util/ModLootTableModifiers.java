package net.sno_angel.icecubes.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.sno_angel.icecubes.item.ModItems;


public class ModLootTableModifiers {

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1.0F))
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_UPGRADE_SMITHING_TEMPLATE))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1F))
                        .with(ItemEntry.builder(ModItems.AGED_PRISMARINE_UPGRADE_SMITHING_TEMPLATE).weight(9))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        // TODO: figure out how to add AP Shards to both ruin chests and archaeology

        /*LootTableEvents.REPLACE.register((key, original, source, registries) -> {
            return original;
        });*/
    }
}
