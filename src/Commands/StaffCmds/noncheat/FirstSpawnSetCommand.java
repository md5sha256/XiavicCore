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

public class FirstSpawnSetCommand implements CommandExecutor {

    private mainClass plugin;

    public FirstSpawnSetCommand(mainClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("SetFirstSpawn")) || player.isOp()) {
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

                mainConfig.set("FirstSpawn", output);
                this.plugin.saveConfig();
                player.sendMessage(Utils.chat(messages.getString("SetFirstSpawn")));
                return true;

            } else {
                Utils.chat(player, messages.getString("NoPerms"));
            }
        } else {
            Utils.chat(sender, messages.getString("SenderNotPlayer"));
        }
        return false;
    }
}


