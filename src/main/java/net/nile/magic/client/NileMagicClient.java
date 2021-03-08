package net.nile.magic.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.nile.magic.NileMagic;
import net.nile.magic.client.screens.SpellTomeScreen;

@Environment(EnvType.CLIENT)
public class NileMagicClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(NileMagic.SPELL_TOME_HANDLER, SpellTomeScreen::new);
    }
    
}
