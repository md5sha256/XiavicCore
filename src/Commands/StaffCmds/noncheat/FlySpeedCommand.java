package Commands.StaffCmds.noncheat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class FlySpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration m = Messages.get();
        FileConfiguration p = Permissions.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (label.equalsIgnoreCase("flyspeed")) {
                if (args.length == 1) {
                    if (player.hasPermission(p.getString("FlySpeed")) || player.isOp()) {
                        String speed = args[0];
                        if (speed.equalsIgnoreCase("1")) {
                            player.setFlySpeed((float) 0.1);
                        } else if (speed.equalsIgnoreCase("2")) {
                            player.setFlySpeed((float) 0.2);
                        } else if (speed.equalsIgnoreCase("3")) {
                            player.setFlySpeed((float) 0.3);
                        } else if (speed.equalsIgnoreCase("4")) {
                            player.setFlySpeed((float) 0.4);
                        } else if (speed.equalsIgnoreCase("5")) {
                            player.setFlySpeed((float) 0.5);
                        } else if (speed.equalsIgnoreCase("6")) {
                            player.setFlySpeed((float) 0.6);
                        } else if (speed.equalsIgnoreCase("7")) {
                            player.setFlySpeed((float) 0.7);
                        } else if (speed.equalsIgnoreCase("8")) {
                            player.setFlySpeed((float) 0.8);
                        } else if (speed.equalsIgnoreCase("9")) {
                            player.setFlySpeed((float) 0.9);
                        } else if (speed.equalsIgnoreCase("10")) {
                            player.setFlySpeed(1);
                        }
                        player.sendMessage(Utils.chat(m.getString("FlySpeed").replace("%amount%", speed)));
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
