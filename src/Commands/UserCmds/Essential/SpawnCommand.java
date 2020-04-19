package Commands.UserCmds.Essential;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.LocationUtils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;
        if (args.length == 1) {
            if (player.hasPermission(permissions.getString("SpawnOthers")) || player.isOp()) {
                Player target = Bukkit.getPlayer(args[0]);
                Location l = LocationUtils.getLocation("Spawn_World_Name");
                if (target != null) {
                    target.teleport(l);
                    return true;
                } else {
                    player.sendMessage(messages.getString("PlayerNotFound"));
                }
            }
            player.sendMessage(messages.getString("NoPerms"));
        } else {
            if (player.hasPermission(permissions.getString("Spawn")) || player.isOp()) {
                Location l = LocationUtils.getLocation("Spawn_World_Name");
                player.teleport(l);
                return true;
            }
            player.sendMessage(messages.getString("NoPerms"));
        }
        return false;
    }
}
