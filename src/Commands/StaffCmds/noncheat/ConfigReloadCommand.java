package Commands.StaffCmds.noncheat;

import Main.mainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class ConfigReloadCommand implements CommandExecutor {

    mainClass plugin;

    public ConfigReloadCommand(mainClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("ConfigUpdate")) || player.isOp()) {
                plugin.updateShit();
                player.sendMessage(Utils.chat(messages.getString("ConfigUpdate")));
                return true;
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
        } else {
            plugin.updateShit();
            sender.sendMessage(Utils.chat(messages.getString("ConfigUpdate")));
            return true;
        }
        return false;
    }
}
