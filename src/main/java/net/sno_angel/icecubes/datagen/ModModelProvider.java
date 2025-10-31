package net.sno_angel.icecubes.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterials;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;
import net.sno_angel.icecubes.item.armor.ChorafilArmorMaterial;
import net.sno_angel.icecubes.trim.ModTrimMaterials;

import java.util.List;

public class ModModelProvider extends FabricModelProvider {
    public static final List<ItemModelGenerator.TrimMaterial> TRIM_MATERIALS_MODELS = List.of(
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.QUARTZ, ArmorTrimMaterials.QUARTZ),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.IRON, ArmorTrimMaterials.IRON),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.NETHERITE, ArmorTrimMaterials.NETHERITE),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.REDSTONE, ArmorTrimMaterials.REDSTONE),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.COPPER, ArmorTrimMaterials.COPPER),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.GOLD, ArmorTrimMaterials.GOLD),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.EMERALD, ArmorTrimMaterials.EMERALD),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.DIAMOND, ArmorTrimMaterials.DIAMOND),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.LAPIS, ArmorTrimMaterials.LAPIS),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.AMETHYST, ArmorTrimMaterials.AMETHYST),
            new ItemModelGenerator.TrimMaterial(ArmorTrimAssets.RESIN, ArmorTrimMaterials.RESIN),
            new ItemModelGenerator.TrimMaterial(ModTrimMaterials.CHORAFIL_ASSETS, ModTrimMaterials.CHORAFIL),
            new ItemModelGenerator.TrimMaterial(ModTrimMaterials.AGED_PRISMARINE_ASSETS, ModTrimMaterials.AGED_PRISMARINE)
    );

    public static final Identifier MOD_TRIM_PREFIX_HELMET = Identifier.of(IceCubes.MOD_ID, "item/helmet_trim");
    public static final Identifier MOD_TRIM_PREFIX_CHESTPLATE = Identifier.of(IceCubes.MOD_ID, "item/chestplate_trim");
    public static final Identifier MOD_TRIM_PREFIX_LEGGINGS = Identifier.of(IceCubes.MOD_ID, "item/leggings_trim");
    public static final Identifier MOD_TRIM_PREFIX_BOOTS = Identifier.of(IceCubes.MOD_ID, "item/boots_trim");

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // TODO: generate trim models for custom armors and materials
    }
}
