package Commands.UserCmds.Essential;

import Main.mainClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

import static Main.mainClass.mainConfig;

public class Spawn implements CommandExecutor {

    private static mainClass plugin;
    FileConfiguration m = Messages.get();
    FileConfiguration p = Permissions.get();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("Spawn")) || player.isOp()) {
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
                    player.sendMessage(Utils.chat(m.getString("Spawn")));
                } else {
                    player.sendMessage(Utils.chat(m.getString("NoPerms")));
                }
                return true;
            }
        }
        return false;
    }

}
