package Commands.StaffCmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class FeedCommand implements CommandExecutor {

    FileConfiguration m = Messages.get();
    FileConfiguration p = Permissions.get();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission(p.getString("FeedOthers")) || player.isOp()) {
                    String who = strings[0];
                    if (who.equalsIgnoreCase("all")) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage(Utils.chat(m.getString("Feed")));
                            target.setFoodLevel(20);
                            target.setSaturation(20f);
                        }
                        player.sendMessage(Utils.chat(m.getString("FeedAll")));
                        return true;
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(who);
                            target.sendMessage(Utils.chat(m.getString("Feed")));
                            target.setFoodLevel(20);
                            target.setSaturation(20f);
                            player.sendMessage(who + " has been fed!");
                            return true;
                        } catch (Exception e) {
                            player.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                            return true;
                        }
                    }
                } else {
                    player.sendMessage(Utils.chat(m.getString("NoPerms")));
                    return true;
                }
            } else {
                if (player.hasPermission(p.getString("Feed")) || player.isOp()) {
                    player.sendMessage(Utils.chat(m.getString("Feed")));
                    player.setFoodLevel(20);
                    player.setSaturation(20f);
                } else {
                    player.sendMessage(Utils.chat(m.getString("NoPerms")));
                }
                return true;
            }
        } else {
            if (strings.length == 1) {
                String who = strings[0];
                if (who.equalsIgnoreCase("all")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(Utils.chat(m.getString("Feed")));
                        player.setFoodLevel(20);
                        player.setSaturation(20f);
                    }
                    commandSender.sendMessage(Utils.chat(m.getString("FeedAll")));
                    return true;
                } else {
                    try {
                        Player player = Bukkit.getPlayer(who);
                        player.sendMessage(Utils.chat(m.getString("Feed")));
                        player.setFoodLevel(20);
                        player.setSaturation(20f);
                        commandSender.sendMessage(player.getDisplayName() + " has been fed!");
                        return true;
                    } catch (Exception e) {
                        commandSender.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
