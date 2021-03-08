package net.nile.magic.items.mana;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.nile.magic.players.IManaComponent;
import net.nile.magic.players.PlayerComponents;

public abstract class AbstractManaManipulationItem extends Item {

    public AbstractManaManipulationItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(user instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity)user;
            manipulateMana(player, PlayerComponents.MANA.get(player));
        }
        return super.finishUsing(stack, world, user);
    }

    protected abstract void manipulateMana(ServerPlayerEntity player, IManaComponent mana);
    
}
