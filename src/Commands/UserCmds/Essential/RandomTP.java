package Commands.UserCmds.Essential;

import Main.mainClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class RandomTP implements CommandExecutor {

    private static mainClass plugin;
    FileConfiguration p = Permissions.get();
    FileConfiguration m = Messages.get();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("RandomTP")) || player.isOp()) {

                double randomX = (Math.random() * 50000) + 0.5;
                double randomZ = (Math.random() * 50000) + 0.5;
                double randomY = player.getWorld().getHighestBlockYAt((int) randomX, (int) randomZ) + 1.5;
                Location rtp = new Location(player.getWorld(), randomX, randomY, randomZ);
                player.teleport(rtp);
                rtp.getBlock().getRelative(0, -1, 0).setType(Material.DIRT);
                return true;
            } else {
                player.sendMessage(Utils.chat(m.getString("NoPerms")));
            }
        } else {
            sender.sendMessage(Utils.chat(m.getString("SenderNotPlayer")));
        }
        return false;
    }
}
