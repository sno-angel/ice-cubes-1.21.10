package net.sno_angel.icecubes.trim;

import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.sno_angel.icecubes.IceCubes;

import java.util.List;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> AGED_PRISMARINE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(IceCubes.MOD_ID, "aged_prismarine"));
    public static final RegistryKey<ArmorTrimMaterial> CHORAFIL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(IceCubes.MOD_ID, "chorafil"));

    public static final ArmorTrimAssets AGED_PRISMARINE_ASSETS = ArmorTrimAssets.of("aged_prismarine");
    public static final ArmorTrimAssets CHORAFIL_ASSETS = ArmorTrimAssets.of("chorafil");

    public static final List<ArmorTrimAssets> TRIM_MATERIALS = List.of(
            AGED_PRISMARINE_ASSETS,
            CHORAFIL_ASSETS
    );

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, AGED_PRISMARINE, Style.EMPTY.withColor(TextColor.parse("#CFCCBE").getOrThrow()), AGED_PRISMARINE_ASSETS);
        register(registerable, CHORAFIL, Style.EMPTY.withColor(TextColor.parse("#C52DC1").getOrThrow()), CHORAFIL_ASSETS);
    }

    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> trimKey, Style style, ArmorTrimAssets armorTrimAssets) {
        Text text = Text.translatable(Util.createTranslationKey("trim_material", trimKey.getValue())).setStyle(style);
        registerable.register(trimKey, new ArmorTrimMaterial(armorTrimAssets, text));
    }
}
