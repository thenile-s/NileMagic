package net.nile.magic.items.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.nile.magic.NileMagic;
import net.nile.magic.players.IManaComponent;
import net.nile.magic.players.PlayerComponents;
import net.nile.magic.spells.Spell;
import net.nile.magic.spells.Spells;

public class SpellScrollItem extends Item {

    public SpellScrollItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(!world.isClient()){
            user.getStackInHand(hand).getTag().putBoolean("test_passs", true);
        }
        NileMagic.logger.info("Client tag is " + user.getStackInHand(hand).getTag());

        if (user instanceof ServerPlayerEntity) {
            
            ServerPlayerEntity player = (ServerPlayerEntity) user;

            ItemStack stack = player.getStackInHand(hand);

            CompoundTag spellTag = stack.getTag().getCompound("nile_spell");

            if (spellTag != null) {
                String id = spellTag.getString("id");

                int power = spellTag.getInt("power");

                Spell spell = Spells.getSpell(id);

                if (player.abilities.creativeMode) {

                    spell.cast(player, power);

                } else {
                    IManaComponent mana = PlayerComponents.MANA.get(player);
                    if (mana.getCurrent() >= spell.getManaCost()) {
                        stack.setDamage(stack.getDamage() + 1);
                        if (stack.getDamage() >= getMaxDamage()) {
                            user.setStackInHand(hand, ItemStack.EMPTY);
                        }
                        mana.deplete(spell.getManaCost());
                        spell.cast(player, power);
                    }
                }

            }
        }

        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
