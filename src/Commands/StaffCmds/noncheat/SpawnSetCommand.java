package Commands.StaffCmds.noncheat;

import Main.mainClass;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.*;

public class SpawnSetCommand implements CommandExecutor {

    private mainClass plugin;

    public SpawnSetCommand(mainClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("SetSpawn")) || player.isOp()) {
                if (cmd.getName().equalsIgnoreCase("setspawn")) {
                    Location loc = player.getLocation();

                    World world = loc.getWorld();
                    double x = loc.getX();
                    double y = loc.getY();
                    double z = loc.getZ();
                    float yaw = loc.getYaw();
                    float pitch = loc.getPitch();

                    String output = world.getName() + ","
                            + x + ","
                            + y + ","
                            + z + ","
                            + yaw + ","
                            + pitch;

                    mainConfig.set("Spawn_World_Name", output);
                    this.plugin.saveConfig();
                    player.getWorld().setSpawnLocation(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()));
                    player.sendMessage(Utils.chat(messages.getString("SetSpawn")));
                }
                return true;
            }

        }
        return false;
    }
}
