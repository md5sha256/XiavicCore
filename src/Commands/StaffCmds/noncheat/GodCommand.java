package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 1) {
            if (player.hasPermission(permissions.getString("GodOthers")) || player.isOp()) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (!target.isInvulnerable()) {
                        target.setInvulnerable(true);
                        target.sendMessage(Utils.chat(messages.getString("God").replace("%mode%", messages.getString("Enabled"))));
                        player.sendMessage(Utils.chat(messages.getString("GodOthers").replace("%mode%", messages.getString("Enabled"))));
                    } else if (target.isInvulnerable()) {
                        target.setInvulnerable(false);
                        target.sendMessage(Utils.chat(messages.getString("God").replace("%mode%", messages.getString("Disabled"))));
                        player.sendMessage(Utils.chat(messages.getString("GodOthers").replace("%mode%", messages.getString("Disabled"))));
                    }
                    return true;
                } else {
                    player.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                }
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
        } else {
            if (player.hasPermission(permissions.getString("God")) || player.isOp()) {
                if (!player.isInvulnerable()) {
                    player.setInvulnerable(true);
                    player.sendMessage(Utils.chat(messages.getString("God").replace("%mode%", messages.getString("Enabled"))));
                } else if (player.isInvulnerable()) {
                    player.setInvulnerable(false);
                    player.sendMessage(Utils.chat(messages.getString("God").replace("%mode%", messages.getString("Disabled"))));
                }
                return true;
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
        }
        return false;
    }
}