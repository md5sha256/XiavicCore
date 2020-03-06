package Commands.StaffCmds;

import utils.Files.permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    //TODO String fix

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission(permissions.get().getString("Feed")) || player.isOp()) {
                    String who = strings[0];
                    if (who.equalsIgnoreCase("all")) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage("You have been fed!");
                            target.setFoodLevel(20);
                            target.setSaturation(20f);
                        }
                        player.sendMessage("All players have been fed!");
                        return true;
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(who);
                            target.sendMessage("You have been fed!");
                            target.setFoodLevel(20);
                            target.setSaturation(20f);
                            player.sendMessage(who + " has been fed!");
                            return true;
                        } catch (Exception e) {
                            player.sendMessage("That player is not online!");
                            return true;
                        }
                    }
                } else {
                    player.sendMessage("You do not have permission!");
                    return true;
                }
            } else {
                if (player.hasPermission("Xiavic.feed") || player.isOp()) {
                    player.sendMessage("You have been fed!");
                    player.setFoodLevel(20);
                    player.setSaturation(20f);
                    return true;
                } else {
                    player.sendMessage("You do not have permission!");
                    return true;
                }
            }
        } else {
            if (strings.length == 1) {
                String who = strings[0];
                if (who.equalsIgnoreCase("all")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("You have been fed");
                        player.setFoodLevel(20);
                        player.setSaturation(20f);
                    }
                    commandSender.sendMessage("All players have been fed");
                    return true;
                } else {
                    try {
                        Player player = Bukkit.getPlayer(who);
                        player.sendMessage("You have been fed");
                        player.setFoodLevel(20);
                        player.setSaturation(20f);
                        return true;
                    } catch (Exception e) {
                        commandSender.sendMessage("That player is not online!");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
