package Commands.StaffCmds;

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
            if (player.hasPermission(p.getString("Clear")) || player.isOp()) {
                int total = 0;
                for (ItemStack stack : player.getInventory().getContents()) {
                    if (stack != null) {
                        total += stack.getAmount();
                    }
                }
                player.getInventory().clear();
                player.sendMessage(ChatColor.GOLD + String.valueOf(total) + " items cleared");
            } else {
                player.sendMessage(utils.chat(m.getString("NoPerms")));
            }
            return true;
        } else {
            commandSender.sendMessage(utils.chat(m.getString("SenderNotPlayer")));
        }
        return false;
    }
}
