package net.nile.magic.screenhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.nile.magic.NileMagic;

public class SpellTomeScreenHandler extends ScreenHandler {

    // client side
    public SpellTomeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SpellTomeInventory(9));
    }

    private ImplementedInventory m_tomeInventory;

    // server side
    public SpellTomeScreenHandler(int syncId, PlayerInventory playerInventory, ImplementedInventory tomeInventory) {
        super(NileMagic.SPELL_TOME_HANDLER, syncId);

        tomeInventory.onOpen(playerInventory.player);
        
        m_tomeInventory = tomeInventory;

        // This will place the slot in the correct locations for a 3x3 Grid. The slots
        // exist on both server and client!
        // This will not render the background of the slots however, this is the Screens
        // job
        int m;
        int l;
        // Our inventory
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(tomeInventory, m, 8 + m * 18, 17 + 1 * 18));
        }
        // The player inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // The player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        // TODO Auto-generated method stub
        return super.transferSlot(player, index);
    }

    @Override
    public void close(PlayerEntity player) {

        m_tomeInventory.onClose(player);
        
        super.close(player);
    }

}
