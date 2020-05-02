package xiaviccore.commands.user.essential;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xiaviccore.XiavicCore.messages;
import static xiaviccore.XiavicCore.permissions;
import static xiaviccore.utils.Utils.chat;

public class EnderChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length >= 1) {
                if (player.hasPermission(permissions.getString("EnderChestOthers")) || player.isOp()) {
                    try {
                        Player target = Bukkit.getPlayer(strings[0]);
                        player.openInventory(target.getEnderChest());
                        chat(player, messages.getString("EnderChestOthers").replace("%target%", target.getDisplayName()));
                        return true;
                    } catch (Exception e) {
                        chat(player, messages.getString("PlayerNotFound"));
                        return true;
                    }
                }
            } else {
                if (player.hasPermission(permissions.getString("EnderChest")) || player.isOp()) {
                    player.openInventory(player.getEnderChest());
                    chat(player, messages.getString("EnderChest"));
                    return true;
                }
            }
        } else {
            chat(commandSender, messages.getString("SenderNotPlayer"));
            return true;
        }
        return false;
    }
}
