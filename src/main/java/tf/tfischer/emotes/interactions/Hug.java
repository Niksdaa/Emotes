package tf.tfischer.emotes.interactions;

import org.bukkit.entity.Player;
import tf.tfischer.emotes.commands.AbstractInteraction;

public class Hug extends AbstractInteraction {
    @Override
    public String message(Player executor, Player interact) {
        return String.format("§6%s§d hugged §6%s",executor.getName(),interact.getName());
    }

    @Override
    public String command() {
        return "hug";
    }

    @Override
    public String hoverMessage(Player executor) {
        return String.format("§dHug §6" + executor.getName());
    }
}
