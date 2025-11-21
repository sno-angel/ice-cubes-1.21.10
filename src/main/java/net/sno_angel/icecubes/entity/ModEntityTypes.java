package net.sno_angel.icecubes.entity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;
import net.sno_angel.icecubes.entity.misc.BlazebombEntity;

public class ModEntityTypes implements ModInitializer {

    public static final EntityType<BlazebombEntity> BLAZEBOMB = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(IceCubes.MOD_ID, "blazebomb"),
            EntityType.Builder.create(BlazebombEntity::new, SpawnGroup.MISC)
                    .dimensions(0.75f, 0.75f).build(RegistryKey.of(
                            RegistryKeys.ENTITY_TYPE, Identifier.of(IceCubes.MOD_ID, "blazebomb")))
    );

    public static void initialize() {
    }

    @Override
    public void onInitialize() {

    }
}
