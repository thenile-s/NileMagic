package net.nile.magic.screenhandlers;

import net.minecraft.client.render.entity.model.BookModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.dynamic.RegistryOps;
import net.nile.magic.NileMagic;

public class SpellTomeScreenHandler extends ScreenHandler {

    // client side
    public SpellTomeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, null);
    }

    private ImplementedInventory m_chaptersInventory;

    private ImplementedInventory m_tomeInventory;

    // server side
    public SpellTomeScreenHandler(int syncId, PlayerInventory playerInventory, Object eatMyDick) {
        super(NileMagic.SPELL_TOME_HANDLER, syncId);
        
        m_chaptersInventory = new ImplementedInventory(9);

        m_tomeInventory = new ImplementedInventory(1);

        // The slots
        // exist on both server and client!
        // This will not render the background of the slots however, this is the Screens
        // job

        this.addSlot(new SpellTomeSlot(m_tomeInventory, 0, 8 + 4 * 18, 1 * 18 + 14));

        int m;
        int l;
        // chapters
        for (m = 0; m < 9; ++m) {
            this.addSlot(new SpellChapterSlot(m_chaptersInventory, m, 8 + m * 18, 17 + 2 * 18));
        }

        // The player inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // The player hot bar
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
        return super.transferSlot(player, index);
    }

    @Override
    public void close(PlayerEntity player) {

        super.close(player);
    }

}
