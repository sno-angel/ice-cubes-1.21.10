package net.sno_angel.icecubes.item.armor;

import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class StellarNetheriteChestplateGeoModel extends GeoModel<StellarNetheriteChestplate> {

    private final Identifier model = Identifier.of(IceCubes.MOD_ID,  "stellar_netherite_chestplate.geo.json"); // TODO: add geckolib/models/ to this if needed
    private final Identifier animations = Identifier.of(IceCubes.MOD_ID,  "animations/stellar_netherite_chestplate.animation.json"); // TODO: add geckolib/animations/ to this if needed
    private final Identifier texture = Identifier.of(IceCubes.MOD_ID,  "textures/armor/stellar_netherite_chestplate.png");

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return model;
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return texture;
    }

    @Override
    public Identifier getAnimationResource(StellarNetheriteChestplate animatable) {
        return animations;
    }
}
