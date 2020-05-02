package xiaviccore.commands.staff.noncheat.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xiaviccore.XiavicCore.*;
import static xiaviccore.utils.Utils.chat;

public class TpallCommand implements CommandExecutor {

    // TODO probably ought to create messages in the config files but it can stay like this for now

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s,
        String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission(permissions.getString("Tpall")) || player.isOp()) {
                if (strings.length == 1) {
                    Player target1 = Bukkit.getPlayer(strings[0]);
                    if (target1 != null) {
                        if (teleportHandler.isDisabled(target1)) {
                            chat(player,
                                "Teleporting all players to %target%, I'm sure they'll be happy about it"
                                    .replace("%target%", target1.getDisplayName()));
                            for (Player target : Bukkit.getOnlinePlayers()) {
                                if (target != target1) {
                                    boolean result =
                                        teleportHandler.teleport(target1, target, true);
                                    if (result)
                                        chat(target, "You are being teleported!");
                                }
                            }
                        }
                    }
                } else {
                    chat(player,
                        "Teleporting all players to you, I'm sure they'll be happy about it");
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        if (target != player) {
                            boolean result = teleportHandler.teleport(player, target, true);
                            if (result)
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
