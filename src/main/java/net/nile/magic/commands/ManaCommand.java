package net.nile.magic.commands;

import java.text.MessageFormat;
import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.nile.magic.players.IManaComponent;
import net.nile.magic.players.PlayerComponents;

public class ManaCommand extends AbstractCommand {

    private final String baseLiteral = "mana";

    private final String displayLiteral = "output";

    private final String playersArgument = "players";

    private final String displayFormat = "{0} has {1} mana out of {2} max, a regen/degen rate of {3} & {4} with {5} recovery cd ticks.";

    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        //register display command
        dispatcher.register(
         CommandManager.literal(baseLiteral)
          .then(CommandManager.literal(displayLiteral)
           .then(CommandManager.argument(playersArgument, EntityArgumentType.players())
            .requires(this::canExecute)
             .executes(this::executeDisplay))));
    }

    private int executeDisplay(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        boolean broadcastToOps = false;

        Collection<ServerPlayerEntity> players = EntityArgumentType.getPlayers(context, playersArgument);

        for (ServerPlayerEntity player : players) {
            IManaComponent mana = PlayerComponents.MANA.get(player);
            context.getSource().sendFeedback(
                new LiteralText(MessageFormat.format(displayFormat, player.getEntityName(), mana.getCurrent(), mana.getMax(), mana.getRegen(), mana.getDegen(), mana.getRecoveryTicks())),
                broadcastToOps);
        }

        return 0;
    }
}
