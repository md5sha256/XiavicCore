package xiaviccore.commands.user.essential;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xiaviccore.XiavicCore.messages;
import static xiaviccore.XiavicCore.permissions;
import static xiaviccore.utils.Utils.chat;

public class SuicideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission(permissions.getString("Suicide")) || player.isOp()) {
            player.setHealth(0);
            return true;
        } else {
            chat(player, messages.getString("NoPerms"));
        }
        return false;
    }
}
