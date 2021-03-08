package net.nile.magic.items.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.nile.magic.screenhandlers.SpellTomeScreenHandlerFactory;

public class SpellTomeItem extends Item {

    public SpellTomeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(user.world.isClient) return super.use(world, user, hand);

        ItemStack itemStack = user.getStackInHand(hand);

        user.openHandledScreen(new SpellTomeScreenHandlerFactory(itemStack));

        return super.use(world, user, hand);
    }
}
