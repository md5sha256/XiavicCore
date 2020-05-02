package xiaviccore.commands.staff.noncheat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xiaviccore.XiavicCore;
import xiaviccore.utils.Utils;

import static xiaviccore.XiavicCore.messages;
import static xiaviccore.XiavicCore.permissions;

public class ConfigReloadCommand implements CommandExecutor {

    XiavicCore plugin;

    public ConfigReloadCommand(XiavicCore plugin) {
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
