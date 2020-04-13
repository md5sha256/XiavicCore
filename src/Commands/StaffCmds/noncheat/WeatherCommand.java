package Commands.StaffCmds.noncheat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class WeatherCommand implements CommandExecutor {

    // Use messages.getString() and permissions.getString() please

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("ww") || label.equalsIgnoreCase("weather")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (player.hasPermission(permissions.getString("Weather")) || player.isOp()) {
                        if (player.getWorld().getWeatherDuration() == 0) {
                            // player.sendMessage(Utils.chat(m.getString("Weather").replace("%weather%", cw)));
                        }
                    }
                }
            }
        }
        return false;
    }
}
