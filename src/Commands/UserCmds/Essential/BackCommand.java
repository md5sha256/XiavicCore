package Commands.UserCmds.Essential;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.backHandler;
import static Main.mainClass.messages;
import static utils.Utils.chat;

public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            // TODO I dont know what permission this should have
            if (true) {
                try {
                    Utils.teleport(player, backHandler.getLastLocation(player));
                } catch (Exception e) {
                    chat(player, "You do not have a recent location to go back to");
                }
                return true;
            }
        }
        chat(commandSender, messages.getString("SenderNotPlayer"));
        return false;
    }
}
