package net.nile.magic.statuseffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.nile.magic.players.IManaComponent;
import net.nile.magic.players.PlayerComponents;

//we can use the on appleid and onremoved
//methods to change the mana regen
//because status effects are persistent
//and een if an effect is upgraded
//the on removed method is called for the previous effect instance
//so we can do this. gj.

public class ManaOverTimeEffect extends StatusEffect {

    protected ManaOverTimeEffect(StatusEffectType type, int color, boolean deplete, float amount) {
        super(type, color);
        m_amount = Math.abs(amount);
        m_isDepleting = deplete;
    }

    protected ManaOverTimeEffect(StatusEffectType type, int color, boolean deplete){
        this(type, color, deplete, DEFAULT_AMOUNT);
    }

    private static final float DEFAULT_AMOUNT = .16f;

    private float m_amount;

    public float getAmount(){
        return m_amount;
    }

    private boolean m_isDepleting;

    public boolean isDepleting() {
        return m_isDepleting;
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //the super impl contains a bunch oif shit. dont do it here, optimise
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;

            IManaComponent mana = PlayerComponents.MANA.get(player);

            if (isDepleting()) {
                mana.setDegen(mana.getDegen() + (amplifier) * m_amount);
            } else {
                mana.setRegen(mana.getRegen() + (amplifier) * m_amount);
            }
        }
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;

            IManaComponent mana = PlayerComponents.MANA.get(player);

            if (isDepleting()) {
                mana.setDegen(mana.getDegen() - (amplifier) * m_amount);
            } else {
                mana.setRegen(mana.getRegen() - (amplifier) * m_amount);
            }
        }
        super.onRemoved(entity, attributes, amplifier);
    }

}
