package Commands.StaffCmds.noncheat.Teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Main.mainClass.*;
import static utils.Utils.chat;
import static utils.Utils.teleport;

public class TpallCommand implements CommandExecutor {

    // TODO probably ought to create messages in the config files but it can stay like this for now

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission(permissions.getString("Tpall")) || player.isOp()) {
                if (strings.length == 1) {
                    Player target1 = Bukkit.getPlayer(strings[0]);
                    if (target1 != null) {
                        chat(player, "Teleporting all players to %target%, I'm sure they'll be happy about it".replace("%target%", target1.getDisplayName()));
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            if (target != target1) {
                                teleport(target, player.getLocation());
                                chat(target, "You are being teleported!");
                            }
                        }
                    }
                } else {
                    chat(player, "Teleporting all players to you, I'm sure they'll be happy about it");
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        if (target != player) {
                            teleport(target, player.getLocation());
                            chat(target, "You are being teleported!");
                        }
                    }
                }
                return true;
            }
        }
        chat(commandSender, messages.getString("SenderNotPlayer"));
        return false;
    }
}
