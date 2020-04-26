package Commands.StaffCmds.cheats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;
import static utils.Utils.chat;

public class CheatEXP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("CheatExp")) || player.isOp()) {
                if (command.getName().equalsIgnoreCase("cheatexp")) {
                    player.setLevel(9999);
                    chat(player, messages.getString("CheatExp"));
                }
                return true;
            }
        }
        return false;
    }
}
