package xiaviccore.commands.user.essential.Teleport;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xiaviccore.XiavicCore;

import static xiaviccore.XiavicCore.*;
import static xiaviccore.utils.Utils.chat;

public class RandomTPCommand implements CommandExecutor {

    private static XiavicCore plugin;

    public RandomTPCommand(XiavicCore plugin) {
        RandomTPCommand.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("RandomTP")) || player.isOp()) {
                double randomX = getCoord();
                double randomZ = getCoord();
                double randomY =
                    player.getWorld().getHighestBlockYAt((int) randomX, (int) randomZ) + 1.5;
                Location rtp = new Location(player.getWorld(), randomX, randomY, randomZ);
                //                player.teleport(rtp);
                teleportHandler.teleport(player, rtp);
                chat(player, messages.getString("RTP"));
                Block block = rtp.getBlock().getRelative(0, -1, 0);
                if (block.getType().equals(Material.WATER) || block.getType()
                    .equals(Material.LAVA)) {
                    block.setType(Material.DIRT);
                }
                return true;
            } else {
                chat(player, messages.getString("NoPerms"));
            }
        } else {
            chat(sender, messages.getString("SenderNotPlayer"));
        }
        return false;
    }

    public double getCoord() {
        double distance = plugin.getConfig().getDouble("RTPDistance");
        return (Math.random() * (distance * 2)) - distance;
    }

}
