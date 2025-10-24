package me.kisters.item;

import me.kisters.entity.DynamiteEntity;
import me.kisters.entity.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class DynamiteItem extends Item {
    public DynamiteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient() && ModEntities.DYNAMITE_ENTITY_TYPE != null) {
            DynamiteEntity dynamite = new DynamiteEntity(world, user);
            dynamite.setItem(stack);
            dynamite.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(dynamite);
            
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}