package Commands.StaffCmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import utils.Files.Messages;
import utils.Files.Permissions;

public class Teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        FileConfiguration m = Messages.get();
        FileConfiguration p = Permissions.get();


        return false;
    }
}
