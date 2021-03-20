package net.nile.magic.screenhandlers;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.nile.magic.items.spell.SpellTomeItem;

public class SpellTomeSlot extends Slot {
    public SpellTomeSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof SpellTomeItem;
    }
}
