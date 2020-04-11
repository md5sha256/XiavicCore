package Commands.UserCmds.Essential;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class ExtinguishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration p = Permissions.get();
        FileConfiguration m = Messages.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("Extinguish")) || player.isOp()) {
                if (label.equalsIgnoreCase("ext")) {
                    player.setFireTicks(0);
                    player.sendMessage(Utils.chat(m.getString("Extinguish")));
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setFireTicks(0);
                    target.sendMessage(Utils.chat(m.getString("ExtinguishTarget").replace("%sender%", player.getDisplayName())));
                } else {
                    player.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                }
                return true;
            }
        } else {
            sender.sendMessage(Utils.chat(m.getString("NoPerms")));
        }
        return false;
    }
}
