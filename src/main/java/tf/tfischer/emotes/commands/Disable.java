package tf.tfischer.emotes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tf.tfischer.emotes.util.Broadcaster;

import java.util.ArrayList;
import java.util.List;

public class Disable implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
            return true;
        Player          player         = (Player) commandSender;
        List<Player>    isDisabledFor  = Broadcaster.disabled;
        if(isDisabledFor.contains(player)) {
            isDisabledFor.remove(player);
            player.sendMessage("§aYou see the interactions now.");
            return true;
        }
        isDisabledFor.add(player);
        player.sendMessage("§aYou don't see the interactions now!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }
}
