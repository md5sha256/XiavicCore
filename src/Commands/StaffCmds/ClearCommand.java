package Commands.StaffCmds;

import Main.Files.permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ClearCommand implements CommandExecutor {

    //TODO redo the strings when mike adds the utils file
    //TODO permission nodes too

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission(permissions.get().getString("Clear")) || player.isOp()) {
                int total = 0;
                for (ItemStack stack : player.getInventory().getContents()) {
                    if (stack != null) {
                        total += stack.getAmount();
                    }
                }
                player.getInventory().clear();
                player.sendMessage(ChatColor.GOLD + String.valueOf(total) + " items cleared");
            } else {
                player.sendMessage(ChatColor.RED + "No permissions fucker...");
            }
            return true;
        } else {
            commandSender.sendMessage("Player only");
        }
        return false;
    }
}
