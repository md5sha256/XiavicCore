package Commands.StaffCmds.noncheat;

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
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Fly")) || player.isOp()) {
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    player.sendMessage(Utils.chat(messages.getString("Fly").replace("%mode%", messages.getString("Enabled"))));
                } else if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.sendMessage(Utils.chat(messages.getString("Fly").replace("%mode%", messages.getString("Disabled"))));
                }
                return true;
            } else {
                player.sendMessage(messages.getString("NoPerms"));
            }
        }
        return false;
    }
}
