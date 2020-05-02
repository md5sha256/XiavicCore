package xiaviccore.commands.staff.noncheat.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xiaviccore.utils.Utils;

import static xiaviccore.XiavicCore.*;
import static xiaviccore.utils.Utils.chat;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("TP")) || player.isOp()) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (!teleportHandler.teleport(player, target, false))
                            chat(player, messages.getString("TpDisabled"));
                    } else {
                        chat(player, messages.getString("PlayerNotFound"));
                    }
                } else if (args.length == 2) {
                    if (player.hasPermission(permissions.getString("TPOthers")) || player.isOp()) {
                        Player target = Bukkit.getPlayer(args[0]);
                        Player target2 = Bukkit.getPlayer(args[1]);
                        if (target != null && target2 != null) {
                            int result = teleportHandler.remoteTp(target, target2);
                            switch (result) {
                                case 0:
                                    chat(player, messages.getString("TpRemote")
                                        .replace("%target1%", target.getDisplayName())
                                        .replace("%target2%", target2.getDisplayName()));
                                    break;
                                case 1:
                                    chat(player, messages.getString("%target%")
                                        .replace("%target%", target.getDisplayName()));
                                    break;
                                case 2:
                                    chat(player, messages.getString("%target%")
                                        .replace("%target%", target2.getDisplayName()));
                                    break;
                            }
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
