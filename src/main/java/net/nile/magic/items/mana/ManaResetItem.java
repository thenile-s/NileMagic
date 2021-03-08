package net.nile.magic.items.mana;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.nile.magic.players.IManaComponent;
import net.nile.magic.players.PlayerComponents;

public class ManaResetItem extends Item {

    public ManaResetItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity) {
            if (user.getItemCooldownManager().isCoolingDown(this)) {
                user.sendMessage(new LiteralText("Your not feeling strong enough to give up who you are yet..."), false);
            } else {
                user.sendMessage(new LiteralText("Your past memories are absorbed into the rock..."), false);
                IManaComponent mana = PlayerComponents.MANA.get(user);

                mana.reset();

                user.getItemCooldownManager().set(this, 20);
            }
        }
        return super.use(world, user, hand);
    }

}
