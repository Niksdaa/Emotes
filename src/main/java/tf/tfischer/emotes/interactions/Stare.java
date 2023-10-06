package tf.tfischer.emotes.interactions;

import org.bukkit.entity.Player;
import tf.tfischer.emotes.commands.AbstractInteraction;

public class Stare extends AbstractInteraction {

    @Override
    public String message(Player executor, Player interact) {
        return String.format("§6%s§d stared at §6%s",executor.getName(),interact.getName());
    }

    @Override
    public String command() {
        return "stare";
    }

    @Override
    public String hoverMessage(Player executor) {
        return String.format("§dStare at §6" + executor.getName());
    }
}
