package Commands.StaffCmds.Cheats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class CheatEXP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration m = Messages.get();
        FileConfiguration p = Permissions.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("CheatExp")) || player.isOp()) {
                if (command.getName().equalsIgnoreCase("cheatexp")) {
                    player.giveExp(497473863);
                    player.sendMessage(Utils.chat(m.getString("CheatExp")));
                }
                return true;
            }
        }
        return false;
    }
}
