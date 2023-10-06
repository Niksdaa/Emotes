package tf.tfischer.emotes.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public interface InterfaceInteraction extends TabCompleter, CommandExecutor {
    /**
     * Gives the message of what happens
     * @param executor      The executor of the command
     * @param interact    The player which gets interacted with
     * @return              The message
     */
    String message(Player executor, Player interact);

    String command();
    String hoverMessage(Player executor);

}
