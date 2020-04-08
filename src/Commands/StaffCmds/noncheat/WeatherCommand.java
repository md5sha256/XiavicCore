package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.weather.WeatherChangeEvent;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class WeatherCommand implements CommandExecutor {

    FileConfiguration m = Messages.get();
    FileConfiguration p = Permissions.get();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("ww") || label.equalsIgnoreCase("weather")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (player.hasPermission(p.getString("Weather"))  || player.isOp()) {
                        if (player.getWorld().getWeatherDuration() == 0) {
                            player.sendMessage(Utils.chat(m.getString("Weather").replace("%weather%", cw)));
                        }
                    }
                }
            }
        }
        return false;
    }
}
