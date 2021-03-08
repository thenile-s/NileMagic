package net.nile.magic.statuseffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.nile.magic.players.IManaComponent;
import net.nile.magic.players.PlayerComponents;

public class ManaInstantEffect extends StatusEffect {

    private boolean m_isDepleting;

    private boolean m_ignoreCooldown;

    private float m_amount;

    public boolean ignoreCooldown(){
       return m_ignoreCooldown;
    }

    public float getAmount(){
        return m_amount;
    }

    public boolean isDepleting(){
        return m_isDepleting;
    }

    protected ManaInstantEffect(StatusEffectType type, int color, float amount, boolean ignoreCoolown) {
        super(type, color);
        m_ignoreCooldown = ignoreCoolown;
        m_amount = Math.abs(amount);
    }

    protected ManaInstantEffect(StatusEffectType type, int color, float amount) {
        super(type, color);
        m_isDepleting = true;
        m_amount = Math.abs(amount);
    }

    @Override
    public void applyInstantEffect(Entity source, Entity attacker, LivingEntity target, int amplifier,
            double proximity) {
        if(target instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity)target;
            IManaComponent mana = PlayerComponents.MANA.get(player);

            if(m_isDepleting){
                mana.deplete(m_amount * amplifier);
            }
            else{
                mana.recover(m_amount * amplifier, m_ignoreCooldown);
            }
        }
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    
    
}
