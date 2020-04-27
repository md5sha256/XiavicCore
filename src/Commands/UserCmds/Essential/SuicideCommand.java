package Commands.UserCmds.Essential;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;
import static utils.Utils.chat;

public class SuicideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission(permissions.getString("Suicide")) || player.isOp()) {
            //player.setHealth(0);
            boolean wasGod = false;
            if (player.isInvulnerable()) {
                wasGod = true;
                player.setInvulnerable(false);
            }
            player.damage(999999999);
            if (wasGod) player.setInvulnerable(true);
            return true;
        } else {
            chat(player, messages.getString("NoPerms"));
        }
        return false;
    }
}