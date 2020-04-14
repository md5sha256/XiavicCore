package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("TP")) || player.isOp()) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        player.teleport(target);
                    } else {
                        player.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                    }
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    Player target2 = Bukkit.getPlayer(args[1]);
                    if (target != null && target2 != null) {
                        target.teleport(target2);
                    } else {
                        player.sendMessage(Utils.chat(messages.getString("PlayerNotFound2")));
                    }
                } else {
                    player.sendMessage(Utils.chat(messages.getString("SpecifyTarget")));
                }
                return true;
            }
            return true;
        } else {
            sender.sendMessage(Utils.chat(messages.getString("NoPerms")));
        }
        sender.sendMessage(Utils.chat(messages.getString("SenderNotPlayer")));
        return false;
    }
}
