package net.nile.magic.spells;

import net.minecraft.server.network.ServerPlayerEntity;

public abstract class Spell {
    
    public abstract int getManaCost();

    public abstract void cast(ServerPlayerEntity caster, int power);

}
