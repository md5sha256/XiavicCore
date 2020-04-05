package Commands.UserCmds;

import Main.mainClass;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class Spawn implements CommandExecutor {

    FileConfiguration m = Messages.get();
    FileConfiguration p = Permissions.get();

    private static mainClass plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("Spawn")) || player.isOp()) {
                if (cmd.getName().equalsIgnoreCase("spawn")) {
                    player.teleport(Bukkit.getWorld(plugin.getConfig().getString("Spawn_World_Name")).getSpawnLocation());
                    player.sendMessage(Utils.chat(m.getString("Spawn")));
                } else {
                    player.sendMessage(Utils.chat(m.getString("NoPerms")));
                }
            }
            return false;
        }
        return true;
    }

}
