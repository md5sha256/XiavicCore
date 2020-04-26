package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;
import static utils.Utils.chat;

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
                        chat(target, messages.getString("God").replace("%mode%", messages.getString("Enabled")));
                        chat(player, messages.getString("GodOthers").replace("%mode%", messages.getString("Enabled").replace("%player%", target.getDisplayName())));
                    } else if (target.isInvulnerable()) {
                        target.setInvulnerable(false);
                        chat(target, messages.getString("God").replace("%mode%", messages.getString("Disabled")));
                        chat(player, messages.getString("GodOthers").replace("%mode%", messages.getString("Disabled").replace("%player%", target.getDisplayName())));
                    }
                    return true;
                } else {
                    player.sendMessage(chat(messages.getString("PlayerNotFound")));
                }
            } else {
                player.sendMessage(chat(messages.getString("NoPerms")));
            }
        } else {
            if (player.hasPermission(permissions.getString("God")) || player.isOp()) {
                if (!player.isInvulnerable()) {
                    player.setInvulnerable(true);
                    chat(player, messages.getString("God").replace("%mode%", messages.getString("Enabled")));
                } else if (player.isInvulnerable()) {
                    player.setInvulnerable(false);
                    chat(player, messages.getString("God").replace("%mode%", messages.getString("Disabled")));
                }
                return true;
            } else {
                chat(player, messages.getString("NoPerms"));
            }
        }
        return false;
    }
}