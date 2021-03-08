package net.nile.magic.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

public class Commands implements ModInitializer {

    public static final ManaCommand MANA = new ManaCommand();

    private void commandRegistrationCallback(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated){

        MANA.register(dispatcher, dedicated);

    }

    @Override
    public void onInitialize() {
        
        CommandRegistrationCallback.EVENT.register(this::commandRegistrationCallback);

    }
}
