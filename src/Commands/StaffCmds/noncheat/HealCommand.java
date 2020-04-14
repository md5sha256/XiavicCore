package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission(permissions.getString("HealOthers")) || player.isOp()) {
                    String who = strings[0];
                    if (who.equalsIgnoreCase("all")) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage(Utils.chat(messages.getString("Heal")));
                            target.setHealth(20.0);
                        }
                        player.sendMessage(Utils.chat(messages.getString("HealAll")));
                        return true;
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(who);
                            target.sendMessage(Utils.chat(messages.getString("Heal")));
                            target.setHealth(20.0);
                            player.sendMessage(Utils.chat(messages.getString("HealOther").replace("%target%", target.getDisplayName())));
                            return true;
                        } catch (Exception e) {
                            player.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                            return true;
                        }
                    }
                } else {
                    player.sendMessage(Utils.chat(messages.getString("NoPerms")));
                    return true;
                }
            } else {
                if (player.hasPermission(permissions.getString("Heal")) || player.isOp()) {
                    player.sendMessage(Utils.chat(messages.getString("Heal")));
                    player.setHealth(20.0);
                } else {
                    player.sendMessage(Utils.chat(messages.getString("NoPerms")));
                }
                return true;
            }
        } else {
            if (strings.length == 1) {
                String who = strings[0];
                if (who.equalsIgnoreCase("all")) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.sendMessage(Utils.chat(messages.getString("Heal")));
                        target.setHealth(20.0);
                    }
                    commandSender.sendMessage(Utils.chat(messages.getString("HealAll")));
                    return true;
                } else {
                    try {
                        Player target = Bukkit.getPlayer(who);
                        target.sendMessage(Utils.chat(messages.getString("Heal")));
                        target.setHealth(20.0);
                        commandSender.sendMessage(Utils.chat(messages.getString("HealOther").replace("%target%", target.getDisplayName())));
                        return true;
                    } catch (Exception e) {
                        commandSender.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
