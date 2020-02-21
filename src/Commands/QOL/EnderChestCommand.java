package Commands.QOL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCommand implements CommandExecutor {

    //TODO fix string shit later

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length >= 1) {
                if (player.hasPermission("Xiavic.ec.others") || player.isOp()) {
                    try {
                        Player target = Bukkit.getPlayer(strings[0]);
                        player.openInventory(target.getEnderChest());
                        player.sendMessage(ChatColor.GOLD + "Opening " + target.getName() + "'s enderchest!");
                        return true;
                    } catch (Exception e) {
                        player.sendMessage("That player is not found!");
                        return true;
                    }
                }
            } else {
                if (player.hasPermission("Xiavic.ec") || player.isOp()) {
                    player.openInventory(player.getEnderChest());
                    player.sendMessage(ChatColor.GOLD + "Opening enderchest!");
                    return true;
                }
            }
        } else {
            commandSender.sendMessage("You are not a fucking player!");
            return true;
        }
        return false;
    }
}
