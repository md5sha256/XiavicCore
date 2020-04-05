package Commands.StaffCmds;

import Main.mainClass;
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

public class SpawnSet implements CommandExecutor {

    FileConfiguration m = Messages.get();
    FileConfiguration p = Permissions.get();

    private mainClass plugin;

    public SpawnSet(mainClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("SetSpawn")) || player.isOp()) {
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

                    player.getWorld().setSpawnLocation(new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                    player.sendMessage(Utils.chat(m.getString("SetSpawn")));
                }
                return true;
            }

        }
        return false;
    }
}
