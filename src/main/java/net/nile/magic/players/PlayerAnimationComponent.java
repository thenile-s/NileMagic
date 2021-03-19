package net.nile.magic.players;

import net.minecraft.nbt.CompoundTag;

public class PlayerAnimationComponent implements IAnimationComponent {

    private boolean m_isCasting;

    @Override
    public void readFromNbt(CompoundTag tag) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isCasting() {
        return m_isCasting;
    }
    
}
