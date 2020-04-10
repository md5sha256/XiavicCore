package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class TPhereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration m = Messages.get();
        FileConfiguration p = Permissions.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("Tphere")) || player.isOp()) {
                if (label.equalsIgnoreCase("tphere")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.teleport(player);
                    } else {
                        player.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                    }
                    return true;
                }
                return true;
            }
        } else {
            sender.sendMessage(Utils.chat(m.getString("NoPerms")));
        }
        return false;
    }
}
