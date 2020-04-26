package Commands.UserCmds.Fun;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class TopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Top"))) {
                int locX = player.getLocation().getBlockX();
                int locZ = player.getLocation().getBlockZ();
                double locY = player.getWorld().getHighestBlockYAt(locX, locZ) + 1.5;
                Location top = new Location(player.getWorld(), locX, locY, locZ);
                Utils.teleport(player, top);
                return true;
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
        } else {
            sender.sendMessage(Utils.chat(messages.getString("SenderNotPlayer")));
        }
        return false;
    }
}
