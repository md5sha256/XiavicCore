package Commands.UserCmds.Essential.Teleport.Tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.*;
import static utils.Utils.chat;

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
                                int result = tpaHandler.addRequest(player, target);
                                if (result == 2) {
                                    chat(player, messages.getString("TpDisabled").replace("%target%", target.getDisplayName()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                player.sendMessage(chat(messages.getString("PlayerNotFound")));
                            }
                        }
                    } else {
                        player.sendMessage(chat(messages.getString("TpSelf")));
                    }
                    return true;
                } else {
                    player.sendMessage(chat("You must specify a target!"));
                }
            } else {
                player.sendMessage(chat(messages.getString("NoPerms")));
            }
            return true;
        }
        commandSender.sendMessage(chat(messages.getString("SenderNotPlayer")));
        return false;
    }
}
