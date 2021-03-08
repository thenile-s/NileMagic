package net.nile.magic.spells;

import java.util.HashMap;

public class Spells {

    private static final HashMap<String, Spell> SPELLS = new HashMap<>();
    
    public static final TornadoSpell TORNADO = new TornadoSpell();

    static{
        SPELLS.put("tornado", TORNADO);
    }

    public static Spell getSpell(String id){
        return SPELLS.get(id);
    }

}
