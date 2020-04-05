package Commands.UserCmds;

import Main.mainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Tpa.TpaRequest;
import utils.Utils;

import static Main.mainClass.*;

public class TpaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission(permissions.getString("Tpa")) || player.isOp()) {
                if (strings.length == 1) {
                    try {
                        Player target = Bukkit.getPlayer(strings[0]);
                        TpaRequest request = new TpaRequest(player, target);
                        tpaHandler.addRequest(request);
                    } catch (Exception e) {
                        player.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                    }
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
