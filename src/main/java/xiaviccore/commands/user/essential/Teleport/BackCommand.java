package xiaviccore.commands.user.essential.Teleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xiaviccore.XiavicCore.*;
import static xiaviccore.utils.Utils.chat;
import static xiaviccore.utils.Utils.teleport;

public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission(permissions.getString("Back")) || player.isOp()) {
                try {
                    teleportHandler.teleport(player, teleportHandler.getLastLocation(player));
                } catch (Exception e) {
                    chat(player, messages.getString("BackNone"));
                }
            } else {
                chat(player, messages.getString("NoPerms"));
            }
            return true;
        }
        chat(commandSender, messages.getString("SenderNotPlayer"));
        return false;
    }
}
