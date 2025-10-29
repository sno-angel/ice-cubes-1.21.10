package net.sno_angel.icecubes;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.sno_angel.icecubes.datagen.ModBlockLootTableProvider;
import net.sno_angel.icecubes.trim.ModTrimMaterials;

public class IceCubesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        // pack.addProvider(ModItemTagProvider::new);
        //pack.addProvider(ModRegistryDataGenerator::new);
        pack.addProvider(ModBlockLootTableProvider::new);
	}

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
    }
}
