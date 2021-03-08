package net.nile.magic.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.ServerCommandSource;

public abstract class AbstractCommand {
    //use this to register all comamnds and subcomamnds related to the command/other command initialisation
    public abstract void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated);

    protected int permissionLevel = 0;

    public boolean canExecute(ServerCommandSource commandSource){
        return commandSource.hasPermissionLevel(permissionLevel);
    }
}
