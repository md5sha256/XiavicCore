package Commands.StaffCmds.noncheat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class MoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("More")) || player.isOp()) {
                player.getInventory().getItemInMainHand().setAmount(64);
            } else {
                Utils.chat(player, messages.getString("NoPerms"));
            }
        } else {
            Utils.chat(sender, messages.getString("SenderNotPlayer"));
        }
        return false;
    }
}
