package net.nile.magic.minions;

import net.minecraft.entity.player.PlayerEntity;


// defines what a minion type should provide

public class MinionType {

    //how many minions lots out of the available minions slots should be consumed by this player?
    public int getMinionCost(PlayerEntity player){
        return 1;
    }

    //mana cost of summoning this minion
    public int getManaCost(PlayerEntity player){
        return 10;
    }
}