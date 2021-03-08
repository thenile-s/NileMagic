package net.nile.magic.spells;

import net.minecraft.server.network.ServerPlayerEntity;
import net.nile.magic.entities.TornadoEntity;

public class TornadoSpell extends Spell {

    @Override
    public int getManaCost() {
        return 10;
    }

    //you can move netities with the updatePostiion method. seems to work the ebst :/

    @Override
    public void cast(ServerPlayerEntity caster, int power) {

        power = Math.max(1, power);

        TornadoEntity tornado = new TornadoEntity(caster.world, 100, power * 5 + 5, caster);

        //funny thing here
        //if there is lava at 0,0,0 (where the tornado initally ahs itss pos with the world, maxAge cstrctr)
        //the tornado will be on fire
        //(T_T)

        //weird order. we have to set pos after adding it to the world with the spawnEntity method?

        tornado.updatePosition(caster.getX(), caster.getY(), caster.getZ());

        tornado.setVelocity(caster.getRotationVector());

        caster.world.spawnEntity(tornado);

        tornado.extinguish();

    }
    
}
