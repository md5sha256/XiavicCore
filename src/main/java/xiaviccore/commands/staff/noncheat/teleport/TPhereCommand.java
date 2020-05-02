package xiaviccore.commands.staff.noncheat.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xiaviccore.XiavicCore.*;
import static xiaviccore.utils.Utils.chat;

public class TPhereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Tphere")) || player.isOp()) {
                if (label.equalsIgnoreCase("tphere")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        teleportHandler.teleport(player, target, true);
                    } else {
                        player.sendMessage(chat(messages.getString("PlayerNotFound")));
                    }
                    return true;
                }
                return true;
            }
        } else {
            sender.sendMessage(chat(messages.getString("NoPerms")));
        }
        return false;
    }
}
