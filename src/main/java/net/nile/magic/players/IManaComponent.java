package net.nile.magic.players;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;

public interface IManaComponent extends AutoSyncedComponent, ServerTickingComponent{

    //returns the current amount of mana
   public float getCurrent();

   //sets the current amount of mana, respecting overflow
   public void setCurrent(float value);

   public float getMax();

   public void setMax(float value);

   public boolean canRecover();

   public int getRecoveryTicks();

   public void setRecoveryTicks(int value);

   public void recover(float amount, boolean ignoreCooldown);

   public void deplete(float amount);

   public float getRegen();

   public void setRegen(float value);

   public float getDegen();

   public void setDegen(float value);

   public void reset();
}
