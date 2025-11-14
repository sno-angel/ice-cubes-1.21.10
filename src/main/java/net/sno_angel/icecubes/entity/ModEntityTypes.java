package net.sno_angel.icecubes.entity;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;
import net.sno_angel.icecubes.entity.misc.BlazebombEntity;

public class ModEntityTypes implements ModInitializer {

    /* public static final EntityType<BlazebombEntity> BLAZEBOMB = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(IceCubes.MOD_ID, "blazebomb"),
            EntityType.Builder.create(BlazebombEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10)); */

    @Override
    public void onInitialize() {

    }
}
