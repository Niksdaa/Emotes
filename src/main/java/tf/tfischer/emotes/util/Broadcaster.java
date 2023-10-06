package tf.tfischer.emotes.util;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Broadcaster {
    public static List<Player> disabled = new ArrayList();
    public static void broadcast(BaseComponent[] message){
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(disabled.contains(player))
                return;
            player.spigot().sendMessage(message);
        });
    }
}
