package xiaviccore.commands.staff.noncheat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xiaviccore.XiavicCore;
import xiaviccore.utils.Utils;

import static xiaviccore.XiavicCore.messages;

public class CoreVersionCommand implements CommandExecutor {

    private XiavicCore plugin;

    public CoreVersionCommand(XiavicCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("Version") || player.isOp()) {
                String version = this.plugin.getDescription().getVersion();
                player.sendMessage(
                    Utils.chat(messages.getString("Version").replace("%version%", version)));
                return true;
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
        } else {
            String version = this.plugin.getDescription().getVersion();
            sender.sendMessage(
                Utils.chat(messages.getString("Version").replace("%version%", version)));
            return true;
        }
        return false;
    }

}
