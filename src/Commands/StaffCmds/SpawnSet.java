package Commands.StaffCmds;

import Main.mainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Files.permissions;
import utils.utils;

public class SpawnSet implements CommandExecutor {

    private static mainClass plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.get().getString("SetSpawn")) || player.isOp()) {
                if (cmd.getName().equalsIgnoreCase("setspawn")) {
                    player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                    player.sendMessage(utils.chat(plugin.getConfig().getString("SetSpawn")));
                }
            }

        }
        return false;
    }
}
