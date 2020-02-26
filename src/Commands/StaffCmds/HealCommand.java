package Commands.StaffCmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    //TODO fix the strings

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission("Xiavic.heal.others") || player.isOp()) {
                    String who = strings[0];
                    if (who.equalsIgnoreCase("all")) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage("You have been healed!");
                            target.setHealth(20.0);
                        }
                        player.sendMessage("All players have been healed!");
                        return true;
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(who);
                            target.sendMessage("You have been healed!");
                            target.setHealth(20.0);
                            player.sendMessage(who + " has been healed!");
                            return true;
                        } catch (Exception e) {
                            player.sendMessage("That player is not online!");
                            return true;
                        }
                    }
                } else {
                    player.sendMessage("You can't do that!");
                    return true;
                }
            } else {
                if (player.hasPermission("Xiavic.heal") || player.isOp()) {
                    player.sendMessage("You have been healed!");
                    player.setHealth(20);
                    return true;
                } else {
                    player.sendMessage("You can't do that!");
                    return true;
                }
            }
        } else {
            if (strings.length == 1) {
                String who = strings[0];
                if (who.equalsIgnoreCase("all")) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.sendMessage("You have been healed!");
                        target.setHealth(20.0);
                    }
                    commandSender.sendMessage("All players have been healed!");
                    return true;
                } else {
                    try {
                        Player target = Bukkit.getPlayer(who);
                        target.sendMessage("You have been healed!");
                        target.setHealth(20.0);
                        commandSender.sendMessage(who + " has been healed!");
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
