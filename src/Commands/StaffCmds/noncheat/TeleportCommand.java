package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        FileConfiguration m = Messages.get();
        FileConfiguration p = Permissions.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("TP")) || player.isOp()) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        player.teleport(target);
                    } else {
                        player.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                    }
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    Player target2 = Bukkit.getPlayer(args[1]);
                    if (target != null && target2 != null) {
                        target.teleport(target2);
                    } else {
                        player.sendMessage(Utils.chat(m.getString("PlayerNotFound2")));
                    }
                } else {
                    player.sendMessage(Utils.chat("You must specify a target!"));
                }
                return true;
            }
            return true;
        } else {
            sender.sendMessage(Utils.chat(m.getString("NoPerms")));
        }
        sender.sendMessage(Utils.chat(m.getString("SenderNotPlayer")));
        return false;
    }
}
