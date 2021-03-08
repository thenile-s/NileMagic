package net.nile.magic.screenhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class SpellTomeScreenHandlerFactory implements NamedScreenHandlerFactory {

    private Text m_title;

    private ItemStack m_spelltomeItemStack;

    public SpellTomeScreenHandlerFactory(ItemStack spellTomeItemStack){
        m_title = spellTomeItemStack.getName();
        m_spelltomeItemStack = spellTomeItemStack;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SpellTomeScreenHandler(syncId, inv, m_spelltomeItemStack);
    }

    @Override
    public Text getDisplayName() {
        return m_title;
    }
    
}
