package Commands.UserCmds.Essential.Teleport.Tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.*;

public class TpdenyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission(permissions.getString("TpDeny")) || player.isOp()) {
                tpaHandler.parseRequest(player, false);
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
            return true;
        } else {
            commandSender.sendMessage(Utils.chat(messages.getString("SenderNotPlayer")));
        }
        return false;
    }
}
