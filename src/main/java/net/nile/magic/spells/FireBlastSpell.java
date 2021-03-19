package net.nile.magic.spells;

import net.minecraft.server.network.ServerPlayerEntity;
import net.nile.magic.entities.FireBlastEntity;

public class FireBlastSpell extends Spell {

    @Override
    public int getManaCost() {
        return 20;
    }

    @Override
    public void cast(ServerPlayerEntity caster, int power) {

        power = Math.max(1, power);
        
        FireBlastEntity fireBlast = new FireBlastEntity(caster.world, caster, 5 * power);

        fireBlast.setVelocity(caster.getRotationVector());

        caster.world.spawnEntity(fireBlast);
        
    }
    
}
