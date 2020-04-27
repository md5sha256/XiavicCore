package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;
import static utils.Utils.chat;

public class ClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission(permissions.getString("ClearOthers")) || player.isOp()) {
                    try {
                        Player target = Bukkit.getPlayer(strings[0]);
                        clear(target);
                        chat(player, messages.getString("ClearInventoryOther").replace("%target%", target.getDisplayName()));
                        return true;
                    } catch (Exception e) {
                        chat(player, messages.getString("PlayerNotFound"));
                        return true;
                    }
                }
            } else {
                if (player.hasPermission(permissions.getString("Clear")) || player.isOp()) {
                    clear(player);
                } else {
                    chat(player, messages.getString("NoPerms"));
                }
                return true;
            }
        } else {
            if (strings.length == 1) {
                try {
                    Player target = Bukkit.getPlayer(strings[0]);
                    clear(target);
                    chat(commandSender, messages.getString("ClearInventoryOther").replace("%target%", target.getDisplayName()));
                    return true;
                } catch (Exception e) {
                    chat(commandSender, messages.getString("PlayerNotFound"));
                    return true;
                }
            } else {
                commandSender.sendMessage("You need to specify a player to clear their inventory!");
            }
        }
        return false;
    }

    private void clear(Player player) {
        Inventory pInventory = player.getInventory();
        for (ItemStack item : pInventory.getStorageContents()) {
            pInventory.remove(item);
        }
        player.sendMessage(Utils.chat(messages.getString("ClearInventory")));
    }

}
