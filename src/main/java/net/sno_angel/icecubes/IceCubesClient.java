package net.sno_angel.icecubes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.sno_angel.icecubes.block.ModBlocks;

public class IceCubesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // To make some parts of the block transparent (like glass, saplings and doors):
        BlockRenderLayerMap.putBlock(ModBlocks.CHORAFIL_BLOOM, BlockRenderLayer.CUTOUT);

        // To make some parts of the block translucent (like ice, stained glass and portal)
        //BlockRenderLayerMap.putBlock(ModBlocks.TRANS_BLOCK, BlockRenderLayer.TRANSLUCENT);
    }
}
