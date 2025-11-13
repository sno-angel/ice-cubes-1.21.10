package net.sno_angel.icecubes.item.weapon;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sno_angel.icecubes.item.armor.ChorafilArmorMaterial;

public class ChorafilCrossbowItem extends CrossbowItem {
    private static final float DEFAULT_SPEED = 4.85F;
    private static final float FIREWORK_ROCKET_SPEED = 2.4F;

    public ChorafilCrossbowItem(Settings settings) {
        super(settings);
    }

    public static Item.Settings getSettings() {
        Item.Settings settings = new Item.Settings();
        settings.maxCount(1)
                .maxDamage(465)
                .component(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT)
                .enchantable(1)
                .repairable(ChorafilArmorMaterial.REPAIRS_CHORAFIL_ARMOR);
        return settings;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ChargedProjectilesComponent chargedProjectilesComponent = (ChargedProjectilesComponent)itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
            this.shootAll(world, user, hand, itemStack, getSpeed(chargedProjectilesComponent), 0.2F, (LivingEntity)null);
            return ActionResult.CONSUME;
        }
        return super.use(world, user, hand);
    }

    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        if (projectileStack.isOf(Items.FIREWORK_ROCKET)) {
            return new FireworkRocketEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - (double)0.15F, shooter.getZ(), true);
        } else {
            ProjectileEntity projectileEntity = super.createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
            projectileEntity.setNoGravity(true);
        }
        return super.createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
    }

    private static float getSpeed(ChargedProjectilesComponent stack) {
        return stack.contains(Items.FIREWORK_ROCKET) ? FIREWORK_ROCKET_SPEED : DEFAULT_SPEED;
    }
}
