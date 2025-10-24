package net.sno_angel.icecubes.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.sno_angel.icecubes.IceCubes;

public class ModItemTags {

    public static final TagKey<Item> AGED_PRISMARINE_ITEMS = TagKey.of(Registries.ITEM.getKey(), Identifier.of(IceCubes.MOD_ID, "aged_prismarine_items"));
    public static final TagKey<Item> CHORAFIL_ITEMS = TagKey.of(Registries.ITEM.getKey(), Identifier.of(IceCubes.MOD_ID, "chorafil_items"));


    private ModItemTags() {
    }
}
