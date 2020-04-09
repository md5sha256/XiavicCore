package Commands.UserCmds.Essential.Tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.*;

public class TpaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission(permissions.getString("Tpa")) || player.isOp()) {
                if (strings.length == 1) {
                    if (!strings[0].equalsIgnoreCase(player.getName())) {
                        if (tpaHandler.canTpa(player)) {
                            try {
                                Player target = Bukkit.getPlayer(strings[0]);
                                tpaHandler.addRequest(player, target);
                            } catch (Exception e) {
                                e.printStackTrace();
                                player.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                            }
                        }
                    } else {
                        player.sendMessage(Utils.chat(messages.getString("TpSelf")));
                    }
                    return true;
                } else {
                    player.sendMessage(Utils.chat("You must specify a target!"));
                }
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
            return true;
        }
        commandSender.sendMessage(Utils.chat(messages.getString("SenderNotPlayer")));
        return false;
    }
}
