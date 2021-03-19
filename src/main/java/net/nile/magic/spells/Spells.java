package net.nile.magic.spells;

import java.util.HashMap;

public class Spells {

    private static final HashMap<String, Spell> SPELLS = new HashMap<>();
    
    public static final TornadoSpell TORNADO = new TornadoSpell();

    public static final Spell FIRE_BLAST = new FireBlastSpell();

    static{
        SPELLS.put("tornado", TORNADO);
        SPELLS.put("fire_blast", FIRE_BLAST);
    }

    public static Spell getSpell(String id){
        return SPELLS.get(id);
    }

}
