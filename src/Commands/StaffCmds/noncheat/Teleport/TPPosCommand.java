package Commands.StaffCmds.noncheat.Teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;
import static utils.Utils.chat;
import static utils.Utils.teleport;

public class TPPosCommand implements CommandExecutor {

    /*
    /tppos X Y Z

    /tppos <player> X Y Z
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Tppos")) || player.isOp()) {
                if (args.length == 3 || args.length == 4) {
                    double x = Double.parseDouble(args[args.length - 3]);
                    double y = Double.parseDouble(args[args.length - 2]);
                    double z = Double.parseDouble(args[args.length - 1]);
                    if (args.length == 3) {
                        teleport(player, new Location(player.getWorld(), x, y, z));
                    } else {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            teleport(target, new Location(target.getWorld(), x, y, z));
                        } else {
                            chat(player, messages.getString("PlayerNotFound"));
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
