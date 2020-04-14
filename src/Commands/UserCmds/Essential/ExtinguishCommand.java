package Commands.UserCmds.Essential;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class ExtinguishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Extinguish")) || player.isOp()) {
                if (label.equalsIgnoreCase("ext")) {
                    player.setFireTicks(0);
                    player.sendMessage(Utils.chat(messages.getString("Extinguish")));
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setFireTicks(0);
                    target.sendMessage(Utils.chat(messages.getString("ExtinguishTarget").replace("%sender%", player.getDisplayName())));
                } else {
                    player.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                }
                return true;
            }
        } else {
            sender.sendMessage(Utils.chat(messages.getString("NoPerms")));
        }
        return false;
    }
}
