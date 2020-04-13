package Commands.StaffCmds.noncheat;

import Main.mainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Utils;

import java.io.File;

import static Main.mainClass.messages;

public class CoreVersionCommand implements CommandExecutor {

    private mainClass plugin;

    public CoreVersionCommand(mainClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("randomPerm") || player.isOp()) {
                String version = this.plugin.getDescription().getVersion();
                player.sendMessage(Utils.chat(messages.getString("Version").replace("%version%", version)));
                return true;
            } else {
                player.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
        } else {
            String version = this.plugin.getDescription().getVersion();
            sender.sendMessage(Utils.chat(messages.getString("Version").replace("%version%", version)));
            return true;
        }
        return false;
    }

}
