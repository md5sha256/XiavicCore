package Commands.StaffCmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import utils.Files.messages;
import utils.Files.permissions;
import utils.utils;

public class ClearCommand implements CommandExecutor {

    //TODO add the ability to clear others inventories and execute from console

    FileConfiguration m = messages.get();
    FileConfiguration p = permissions.get();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                try {
                    Player target = Bukkit.getPlayer(strings[0]);
                    target.getInventory().clear();
                    target.sendMessage(utils.chat(m.getString("ClearInventory")));
                    player.sendMessage(utils.chat(m.getString("ClearInventoryOther").replace("%target%", target.getDisplayName())));
                    return true;
                } catch (Exception e) {
                    player.sendMessage(utils.chat(m.getString("PlayerNotFound")));
                    return true;
                }
            } else {
                if (player.hasPermission(p.getString("Clear")) || player.isOp()) {
                    player.sendMessage(utils.chat(m.getString("ClearInventory")));
                } else {
                    player.sendMessage(utils.chat(m.getString("NoPerms")));
                }
                return true;
            }
        } else {
            if (strings.length == 1) {
                try {
                    Player target = Bukkit.getPlayer(strings[0]);
                    target.getInventory().clear();
                    target.sendMessage(utils.chat(m.getString("ClearInventory")));
                    commandSender.sendMessage(utils.chat(m.getString("ClearInventoryOther").replace("%target%", target.getDisplayName())));
                    return true;
                } catch (Exception e) {
                    commandSender.sendMessage(utils.chat(m.getString("PlayerNotFound")));
                    return true;
                }
            } else {
                commandSender.sendMessage("You need to specify a player to clear their inventory!");
            }
        }
        return false;
    }
}
