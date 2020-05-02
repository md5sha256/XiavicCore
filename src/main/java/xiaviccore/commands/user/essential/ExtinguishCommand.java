package xiaviccore.commands.user.essential;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xiaviccore.XiavicCore.messages;
import static xiaviccore.XiavicCore.permissions;
import static xiaviccore.utils.Utils.chat;

public class ExtinguishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
            if (player.hasPermission(permissions.getString("Extinguish")) || player.isOp()) {
                player.setFireTicks(0);
                chat(player, messages.getString("Extinguish"));
                return true;
            } else {
                chat(player, messages.getString("NoPerms"));
            }
        } else {
            if (args.length == 1) {
                if (player.hasPermission(permissions.getString("ExtinguishOthers"))) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target.isOnline()) {
                        target.setFireTicks(0);
                        chat(target, messages.getString("ExtinguishTarget").replace("%sender%", player.getDisplayName()));
                        return true;
                    }
                } else {
                    chat(player, messages.getString("NoPerms"));
                }
            } else {
                chat(player, messages.getString("PlayerNotFound"));
            }
        }
        return false;
    }
}

