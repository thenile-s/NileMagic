package net.nile.magic.items.mana;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.nile.magic.players.IManaComponent;

public class MaxManaItem extends AbstractManaManipulationItem {

    private final float m_amount;

    private final float m_increaseBound;

    private final boolean m_isDecreasing;

    public float getAmount(){
        return m_amount;
    }

    public float getIncreaseBound(){
        return m_increaseBound;
    }

    public boolean isDecreasing(){
        return m_isDecreasing;
    }

    public MaxManaItem(Settings settings, boolean isDecreasing, float amount, float bound) {
        super(settings);
        m_isDecreasing = isDecreasing;
        m_amount = Math.abs(amount);
        m_increaseBound = bound;
    }

    @Override
    protected void manipulateMana(ServerPlayerEntity player, IManaComponent mana) {

        float initialMana = mana.getMax();

        if(isDecreasing()){
            mana.setMax(mana.getMax() - m_amount);
            onDecrease(player, mana, initialMana);
        }
        else if(getIncreaseBound() > mana.getMax()){
            mana.setMax(Math.min(mana.getMax() + m_amount, m_increaseBound));
            onIncrease(player, mana, initialMana);
        }
        else{
            onFail(player, mana);
        }
    }

    protected void onDecrease(ServerPlayerEntity player, IManaComponent mana, float initial){
        if(initial == mana.getMax()){
        //player.sendMessage(new LiteralText("Not even Apollyon could spoil your spirit further than this..." + initial),false);
            
        }
        else{
            //player.sendMessage(new LiteralText("Your spirit shudders. Maximum mana decreased to " + mana.getMax() + " from " + initial),false);

        }
    }

    protected void onIncrease(ServerPlayerEntity player, IManaComponent mana, float initial){
        //player.sendMessage(new LiteralText("Your spirit blooms. Maximum mana increased to " + mana.getMax() + " from " + initial),false);

    }

    protected void onFail(ServerPlayerEntity player, IManaComponent mana){
        //player.sendMessage(new LiteralText("You must seek higher powers... your maximum mana did not change."),false);
    }
    
}
