package Commands.StaffCmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.messages;
import utils.Files.permissions;
import utils.utils;

public class FeedCommand implements CommandExecutor {

    FileConfiguration m = messages.get();
    FileConfiguration p = permissions.get();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission(p.getString("FeedOthers")) || player.isOp()) {
                    String who = strings[0];
                    if (who.equalsIgnoreCase("all")) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage(utils.chat(m.getString("Feed")));
                            target.setFoodLevel(20);
                            target.setSaturation(20f);
                        }
                        player.sendMessage(utils.chat(m.getString("FeedAll")));
                        return true;
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(who);
                            target.sendMessage(utils.chat(m.getString("Feed")));
                            target.setFoodLevel(20);
                            target.setSaturation(20f);
                            player.sendMessage(who + " has been fed!");
                            return true;
                        } catch (Exception e) {
                            player.sendMessage(utils.chat(m.getString("PlayerNotFound")));
                            return true;
                        }
                    }
                } else {
                    player.sendMessage(utils.chat(m.getString("NoPerms")));
                    return true;
                }
            } else {
                if (player.hasPermission(p.getString("Feed")) || player.isOp()) {
                    player.sendMessage(utils.chat(m.getString("Feed")));
                    player.setFoodLevel(20);
                    player.setSaturation(20f);
                    return true;
                } else {
                    player.sendMessage(utils.chat(m.getString("NoPerms")));
                    return true;
                }
            }
        } else {
            if (strings.length == 1) {
                String who = strings[0];
                if (who.equalsIgnoreCase("all")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(utils.chat(m.getString("Feed")));
                        player.setFoodLevel(20);
                        player.setSaturation(20f);
                    }
                    commandSender.sendMessage(utils.chat(m.getString("FeedAll")));
                    return true;
                } else {
                    try {
                        Player player = Bukkit.getPlayer(who);
                        player.sendMessage(utils.chat(m.getString("Feed")));
                        player.setFoodLevel(20);
                        player.setSaturation(20f);
                        return true;
                    } catch (Exception e) {
                        commandSender.sendMessage(utils.chat(m.getString("PlayerNotFound")));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
