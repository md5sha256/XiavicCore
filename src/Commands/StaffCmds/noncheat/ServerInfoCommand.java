package Commands.StaffCmds.noncheat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;

public class ServerInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration p = Permissions.get();
        FileConfiguration m = Messages.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("ServerInfo")) || player.isOp()) {

            }
        }
        return false;
    }
}
