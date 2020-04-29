package Commands.StaffCmds.noncheat.Teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;
import static utils.Utils.chat;
import static utils.Utils.teleport;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("TP")) || player.isOp()) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        teleport(player, target.getLocation());
                    } else {
                        chat(player, messages.getString("PlayerNotFound"));
                    }
                } else if (args.length == 2) {
                    if (player.hasPermission(permissions.getString("TPOthers")) || player.isOp()) {
                        Player target = Bukkit.getPlayer(args[0]);
                        Player target2 = Bukkit.getPlayer(args[1]);
                        if (target != null && target2 != null) {
                            teleport(target, target2.getLocation());

                        } else {
                            chat(player, messages.getString("PlayerNotFound2"));
                            return true;
                        }
                    } else {
                        chat(player, messages.getString("NoPerms"));
                    }
                } else {
                    chat(player, messages.getString("SpecifyTarget"));

                }
                return true;
            } else {
                chat(sender, messages.getString("NoPerms"));
            }

        }
        sender.sendMessage(Utils.chat(messages.getString("SenderNotPlayer")));
        return false;
    }
}
