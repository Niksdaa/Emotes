package tf.tfischer.emotes.commands;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tf.tfischer.emotes.util.Broadcaster;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractInteraction implements InterfaceInteraction{
    public static long   waitingTime                          = 120000L;
    private static Map<Player, Date> lastInteraction    = new HashMap<>();
    private Player executor;
    private Player interact;

    private BaseComponent[] transformer(){
        String message      = message(executor,interact);
        String hoverMessage = hoverMessage(executor);
        String command      = "/" + command() + " " + executor.getName();

        BaseComponent[] broadcastMessage    = TextComponent.fromLegacyText(message);
        HoverEvent      hoverEvent          = new HoverEvent(HoverEvent.Action.SHOW_TEXT,TextComponent.fromLegacyText(hoverMessage));
        ClickEvent      clickEvent          = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,command);

        Arrays.stream(broadcastMessage).forEach(baseComponent -> {
            baseComponent.setClickEvent(clickEvent);
            baseComponent.setHoverEvent(hoverEvent);
        });

        return broadcastMessage;
    }

    private long getWaitingTime(){
        Date dateOfLastInteraction  = lastInteraction.get(executor);
        if(dateOfLastInteraction == null)
            return waitingTime;
        Date currentDate            = new Date();
        return currentDate.getTime() - dateOfLastInteraction.getTime();
    }

    private boolean canInteract(){
        return getWaitingTime() >= waitingTime;
    }

    private void run(){
        Broadcaster.broadcast(transformer());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof  Player))
            return true;
        executor = ((Player) commandSender);

        boolean hasPermission = executor.hasPermission("Emotes.admin.bypass");
        if(!canInteract() && !hasPermission){
            long alreadyWaited  = getWaitingTime()/1000;
            long toWait         = waitingTime/1000;
            executor.sendMessage("§4You waited §6" + alreadyWaited + "§4 of §6" + toWait + " §4seconds.");
            return true;
        }

        lastInteraction.put(executor,new Date());

        if(strings.length == 0)
            return true;
        Player maybeInteraction = Bukkit.getPlayer(strings[0]);

        if(maybeInteraction == null)
            return true;
        interact = maybeInteraction;
        if(Broadcaster.disabled.contains(interact)){
            executor.sendMessage("§4You cant interact with §6" + interact.getName() + " §4because they have the function disabled.");
            return true;
        }


        run();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        String          search      = strings.length >= 1 ? strings[0] : "";
        List<Player>    disabled    = Broadcaster.disabled;
        return Bukkit.getOnlinePlayers().stream()   .filter(player -> player.getName().startsWith(search) && !disabled.contains(player))
                                                    .map(Player::getName).collect(Collectors.toList());
    }
}
