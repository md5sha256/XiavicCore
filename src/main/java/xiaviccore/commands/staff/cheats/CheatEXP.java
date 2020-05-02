package xiaviccore.commands.staff.cheats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xiaviccore.utils.Utils;

import static xiaviccore.XiavicCore.messages;
import static xiaviccore.XiavicCore.permissions;
import static xiaviccore.utils.Utils.chat;

public class CheatEXP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("CheatExp")) || player.isOp()) {
                {
                    player.setLevel(9999);
                    chat(player, messages.getString("CheatExp"));
                }
                return true;
            } else {
                Utils.chat(player, messages.getString("NoPerms"));
            }
        } else {
            Utils.chat(sender, messages.getString("SenderNotPlayer"));
        }
        return false;
    }
}
