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

public class ClearCommand implements CommandExecutor {

    FileConfiguration m = Messages.get();
    FileConfiguration p = Permissions.get();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission(p.getString("ClearOthers")) || player.isOp()) {
                    try {
                        Player target = Bukkit.getPlayer(strings[0]);
                        target.getInventory().clear();
                        target.sendMessage(Utils.chat(m.getString("ClearInventory")));
                        player.sendMessage(Utils.chat(m.getString("ClearInventoryOther").replace("%target%", target.getDisplayName())));
                        return true;
                    } catch (Exception e) {
                        player.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                        return true;
                    }
                }
            } else {
                if (player.hasPermission(p.getString("Clear")) || player.isOp()) {
                    player.sendMessage(Utils.chat(m.getString("ClearInventory")));
                } else {
                    player.sendMessage(Utils.chat(m.getString("NoPerms")));
                }
                return true;
            }
        } else {
            if (strings.length == 1) {
                try {
                    Player target = Bukkit.getPlayer(strings[0]);
                    target.getInventory().clear();
                    target.sendMessage(Utils.chat(m.getString("ClearInventory")));
                    commandSender.sendMessage(Utils.chat(m.getString("ClearInventoryOther").replace("%target%", target.getDisplayName())));
                    return true;
                } catch (Exception e) {
                    commandSender.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                    return true;
                }
            } else {
                commandSender.sendMessage("You need to specify a player to clear their inventory!");
            }
        }
        return false;
    }
}
