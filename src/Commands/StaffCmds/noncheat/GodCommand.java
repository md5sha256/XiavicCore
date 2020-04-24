package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Main.mainClass.permissions;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if (player.hasPermission(permissions.getString("GodOthers")) || player.isOp()) {
            if (args.length == 1) {
                target.getLastDamageCause().isCancelled();

            }
        }
        return false;
    }
}
