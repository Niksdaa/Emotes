package tf.tfischer.emotes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class WaitingTime implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length==0)
            return true;
        long newWaitingTime = -1;
        try {
            newWaitingTime =    Long.parseLong(strings[0]);
            newWaitingTime *=   1000L;
        } catch (Exception exception) {
            commandSender.sendMessage("§4Something went wrong with parsing §6" + newWaitingTime);
            return true;
        }
        if(newWaitingTime < 0){
            commandSender.sendMessage("§4You aren't allowed to use a negative number!");
            return true;
        }
        AbstractInteraction.waitingTime = newWaitingTime;
        commandSender.sendMessage("§aThe new Waiting time is now §6" + newWaitingTime/1000 + "§a seconds.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return new ArrayList<>();
    }
}
