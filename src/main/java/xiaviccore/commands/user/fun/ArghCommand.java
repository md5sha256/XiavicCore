package xiaviccore.commands.user.fun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xiaviccore.utils.Utils;

import static xiaviccore.XiavicCore.messages;
import static xiaviccore.XiavicCore.permissions;

public class ArghCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Argh")) || player.isOp()) {
                if (command.getName().equalsIgnoreCase("Argh")) {
                    player.sendMessage(Utils.chat(messages.getString("Argh")));
                }
                return true;
            }
        }
        return false;
    }
}
