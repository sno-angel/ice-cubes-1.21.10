package net.sno_angel.icecubes.item.food;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.sno_angel.icecubes.item.ModItems;


public class BlazePepper extends Item {
    public BlazePepper(Settings settings) {
        super(settings);
    }

    public static Item.Settings getSettings(){
        Item.Settings settings = new Item.Settings();
        settings.food(
                new FoodComponent.Builder()
                        .nutrition(4)
                        .saturationModifier(0.4F)
                        .alwaysEdible()
                        .build()
        );
        return settings;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.setOnFireFor(6F);
        user.giveOrDropStack(new ItemStack(ModItems.BLAZE_PEPPER_CORE,1));
        return super.finishUsing(stack,world,user);
    }
}
