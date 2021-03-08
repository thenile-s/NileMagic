package net.nile.magic.players;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.nile.magic.minions.MinionSummonData;

public interface PlayerMinionComponent extends Component {
    //gets the maximum amount of minion units the plaeyr can have. 1 minion can
    //consume multiple units
    public int getMaxMinionSlots();

    //gets the amount of minion slots currently being used
    public int getActiveMinionSlots();

    //gets the amount ominion slots that can be taken
    public int getFreeMinionSlots();

    //summons a minion
    public boolean summonMinion(MinionSummonData summoning);
}
