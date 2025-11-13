package net.sno_angel.icecubes.item.misc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;
import net.sno_angel.icecubes.item.ModItems;

public class ModPotions implements ModInitializer {

    // Levitation
    public static final Potion LEVITATION_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(IceCubes.MOD_ID, "levitation"),
                    new Potion("levitation",
                            new StatusEffectInstance(
                                    StatusEffects.LEVITATION, 600, 0)));
    public static final Potion LONG_LEVITATION_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(IceCubes.MOD_ID, "long_levitation"),
                    new Potion("levitation",
                            new StatusEffectInstance(
                                    StatusEffects.LEVITATION, 1200, 0)));
    public static final Potion STRONG_LEVITATION_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(IceCubes.MOD_ID, "strong_levitation"),
                    new Potion("levitation",
                            new StatusEffectInstance(
                                    StatusEffects.LEVITATION, 400, 1)));

    // Mining Fatigue
    public static final Potion MINING_FATIGUE_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(IceCubes.MOD_ID, "mining_fatigue"),
                    new Potion("mining_fatigue",
                            new StatusEffectInstance(
                                    StatusEffects.MINING_FATIGUE, 1500, 0)));
    public static final Potion LONG_MINING_FATIGUE_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(IceCubes.MOD_ID, "long_mining_fatigue"),
                    new Potion("mining_fatigue",
                            new StatusEffectInstance(
                                    StatusEffects.MINING_FATIGUE, 3000, 0)));

    // Haste
    public static final Potion HASTE_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(IceCubes.MOD_ID, "haste"),
                    new Potion("haste",
                            new StatusEffectInstance(
                                    StatusEffects.HASTE, 1500, 0)));
    public static final Potion LONG_HASTE_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(IceCubes.MOD_ID, "long_haste"),
                    new Potion("haste",
                            new StatusEffectInstance(
                                    StatusEffects.HASTE, 3000, 0)));
    public static final Potion STRONG_HASTE_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(IceCubes.MOD_ID, "strong_haste"),
                    new Potion("haste",
                            new StatusEffectInstance(
                                    StatusEffects.HASTE, 1200, 1)));

    public static void registerModPotionRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            // Levitation
            builder.registerPotionRecipe(
                    // Input potion.
                    Potions.AWKWARD,
                    // Ingredient
                    ModItems.RAW_CHORAFIL,
                    // Output potion.
                    Registries.POTION.getEntry(LEVITATION_POTION)
            );
            builder.registerPotionRecipe( // Long Lev from Lev
                    Registries.POTION.getEntry(LEVITATION_POTION),
                    Items.REDSTONE,
                    Registries.POTION.getEntry(LONG_LEVITATION_POTION)
            );
            builder.registerPotionRecipe( // Strong Lev from Lev
                    Registries.POTION.getEntry(LEVITATION_POTION),
                    Items.GLOWSTONE_DUST,
                    Registries.POTION.getEntry(STRONG_LEVITATION_POTION)
            );
            // Mining Fatigue
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    ModItems.AGED_PRISMARINE_SHARD,
                    Registries.POTION.getEntry(MINING_FATIGUE_POTION)
            );
            builder.registerPotionRecipe( // Long MF from MF
                    Registries.POTION.getEntry(MINING_FATIGUE_POTION),
                    Items.REDSTONE,
                    Registries.POTION.getEntry(LONG_MINING_FATIGUE_POTION)
            );
            // Haste
            builder.registerPotionRecipe( // Haste from MF
                    Registries.POTION.getEntry(MINING_FATIGUE_POTION),
                    Items.FERMENTED_SPIDER_EYE,
                    Registries.POTION.getEntry(HASTE_POTION)
            );
            builder.registerPotionRecipe( // Long Haste from Haste
                    Registries.POTION.getEntry(HASTE_POTION),
                    Items.REDSTONE,
                    Registries.POTION.getEntry(LONG_HASTE_POTION)
            );
            builder.registerPotionRecipe( // Strong Haste from Haste
                    Registries.POTION.getEntry(HASTE_POTION),
                    Items.GLOWSTONE_DUST,
                    Registries.POTION.getEntry(STRONG_HASTE_POTION)
            );
            builder.registerPotionRecipe( // Long Haste from Long MF
                    Registries.POTION.getEntry(LONG_MINING_FATIGUE_POTION),
                    Items.FERMENTED_SPIDER_EYE,
                    Registries.POTION.getEntry(LONG_HASTE_POTION)
            );
        });
    }

    @Override
    public void onInitialize() {
        registerModPotionRecipes();
    }
}
