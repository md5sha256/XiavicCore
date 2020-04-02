package Commands.UserCmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.messages;
import utils.Files.permissions;
import utils.utils;

public class EnderChestCommand implements CommandExecutor {

    FileConfiguration m = messages.get();
    FileConfiguration p = permissions.get();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length >= 1) {
                if (player.hasPermission(p.getString("EnderChestOthers")) || player.isOp()) {
                    try {
                        Player target = Bukkit.getPlayer(strings[0]);
                        player.openInventory(target.getEnderChest());
                        player.sendMessage(utils.chat(m.getString("EnderChestOthers").replace("%target", target.getDisplayName())));
                        return true;
                    } catch (Exception e) {
                        player.sendMessage(utils.chat(m.getString("PlayerNotFound")));
                        return true;
                    }
                }
            } else {
                if (player.hasPermission(p.getString("EnderChest")) || player.isOp()) {
                    player.openInventory(player.getEnderChest());
                    player.sendMessage(utils.chat(m.getString("EnderChest")));
                    return true;
                }
            }
        } else {
            commandSender.sendMessage(utils.chat(m.getString("SenderNotPlayer")));
            return true;
        }
        return false;
    }
}
