package net.nile.magic.screenhandlers;

import net.minecraft.item.ItemStack;
import net.nile.magic.items.spell.SpellTomeItem;

public class SpellTomeInventory extends ImplementedInventory {

    public SpellTomeInventory(int size) {
        super(size);
    }
    

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return stack.getItem() instanceof SpellTomeItem;
    }
}
