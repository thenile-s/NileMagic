package net.nile.magic.screenhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class SpellTomeInventory implements Inventory {

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ItemStack getStack(int slot) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ItemStack removeStack(int slot) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        // TODO Auto-generated method stub

    }

    @Override
    public void markDirty() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        // TODO Auto-generated method stub
        return true;
    }
    

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return stack.getItem() instanceof Object;
    }
}
