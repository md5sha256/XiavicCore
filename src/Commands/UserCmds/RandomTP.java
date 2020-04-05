package Commands.UserCmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomTP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission(p.getString("RandomTP")) || player.isOp()) {
                if (cmd.getName().equalsIgnoreCase("setspawn")) {
        }
        return false;
    }
}
