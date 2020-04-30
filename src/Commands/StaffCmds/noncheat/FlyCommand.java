package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 1) {
            if (player.hasPermission(permissions.getString("FlyOthers")) || player.isOp()) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (target.getAllowFlight()) {
                        target.setAllowFlight(false);
                        Utils.chat(target, messages.getString("Fly").replace("%mode%", messages.getString("Disabled")));
                        Utils.chat(player, messages.getString("FlyOthers").replace("%target%", target.getDisplayName()).replace("%mode%", messages.getString("Disabled")));
                    } else if (!target.getAllowFlight()) {
                        target.setAllowFlight(true);
                        Utils.chat(target, messages.getString("Fly").replace("%mode%", messages.getString("Enabled")));
                        Utils.chat(player, messages.getString("FlyOthers").replace("%target%", target.getDisplayName()).replace("%mode%", messages.getString("Enabled")));
                    }
                    return true;
                } else {
                    Utils.chat(player, messages.getString("PlayerNotFound"));
                }
            } else {
                Utils.chat(player, messages.getString("NoPerms"));
            }
        } else {
            if (player.hasPermission(permissions.getString("Fly")) || player.isOp()) {
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    Utils.chat(player, messages.getString("Fly").replace("%mode%", messages.getString("Enabled")));
                } else if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    Utils.chat(player, messages.getString("Fly").replace("%mode%", messages.getString("Disabled")));
                }
                return true;
            } else {
                Utils.chat(player, messages.getString("NoPerms"));
            }
        }
        return false;
    }
}