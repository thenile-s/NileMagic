package net.nile.magic.statuseffects;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nile.magic.NileMagic;

//some effects will do nothing if amplifier is 0
//care

public class StatusEffects implements ModInitializer {
    //maybe add a minimum
    //mana recovery effect accepted
    //to prevent accidently triggering recovery
    //cooldown with a low tire mana potion ?

    public static final StatusEffect MANA_REGENERATION = new ManaOverTimeEffect(StatusEffectType.BENEFICIAL, 2896019, false);

    public static final StatusEffect MANA_DEGENERATION = new ManaOverTimeEffect(StatusEffectType.HARMFUL, 2896019, true);

    public static final StatusEffect MANA_RECOVERY = new ManaInstantEffect(StatusEffectType.BENEFICIAL, 2896019, 10, false);

    public static final StatusEffect MANA_DEPLETION = new ManaInstantEffect(StatusEffectType.BENEFICIAL, 2896019, 10);

    @Override
    public void onInitialize() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(NileMagic.modid, "mana_regeneration"), MANA_REGENERATION);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(NileMagic.modid, "mana_degeneration"), MANA_DEGENERATION);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(NileMagic.modid, "mana_recovery"), MANA_RECOVERY);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(NileMagic.modid, "mana_depletion"), MANA_DEPLETION);

    }
}
