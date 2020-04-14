package Commands.UserCmds.Essential;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.*;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Spawn")) || player.isOp()) {
                if (cmd.getName().equalsIgnoreCase("spawn")) {

                    String test = mainConfig.getString("Spawn_World_Name");
                    String[] list = test.split(",");
                    World world = Bukkit.getWorld(list[0]);
                    double x = Double.parseDouble(list[1]);
                    double y = Double.parseDouble(list[2]);
                    double z = Double.parseDouble(list[3]);
                    float yaw = Float.parseFloat(list[4]);
                    float pitch = Float.parseFloat(list[5]);
                    Location location = new Location(world, x, y, z, yaw, pitch);
                    player.teleport(location);
                    player.sendMessage(Utils.chat(messages.getString("Spawn")));
                } else {
                    player.sendMessage(Utils.chat(messages.getString("NoPerms")));
                }
                return true;
            }
            if (player.hasPermission(permissions.getString("SpawnOthers")) || player.isOp()) {
                Player target = Bukkit.getPlayer(args[0]);
                String test = mainConfig.getString("Spawn_World_Name");
                String[] list = test.split(",");
                World world = Bukkit.getWorld(list[0]);
                double x = Double.parseDouble(list[1]);
                double y = Double.parseDouble(list[2]);
                double z = Double.parseDouble(list[3]);
                float yaw = Float.parseFloat(list[4]);
                float pitch = Float.parseFloat(list[5]);
                Location location = new Location(world, x, y, z, yaw, pitch);
                if (args.length == 1) {
                    if (target != null) {
                        target.teleport(location);
                        target.sendMessage(Utils.chat(messages.getString("Spawn")));
                    } else {
                        player.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                    }
                }
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
                return true;
            }
        }
        return false;
    }
}
