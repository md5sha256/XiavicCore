package Commands.StaffCmds.noncheat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Permissions;
import utils.Utils;

import static Main.mainClass.messages;
import static org.bukkit.Bukkit.getPlayer;

//This command cannot be edited @ Files.

public class WhoIsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration p = Permissions.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("Whois")) || player.isOp()) {
                if (args.length == 1) {
                    try {
                        Player target = getPlayer(args[0]);
                        player.sendMessage(Utils.chat("&6Name: &9" + target.getName() + "&6, Nickname: &9" + target.getDisplayName()));
                        player.sendMessage(Utils.chat("&6IP: &9" + target.getAddress().getHostName().replace("/", "")));
                        player.sendMessage(Utils.chat("&6Exp: &9" + target.getTotalExperience() + "&6, Next Level: &9" + target.getExpToLevel()));
                        player.sendMessage(Utils.chat("&6Health: &9" + target.getHealth() + "&6, Food: &9" + target.getFoodLevel()));
                        player.sendMessage(Utils.chat("&6Time: &9" + target.getPlayerTime()));
                        player.sendMessage(Utils.chat("&6Location: &9" + target.getWorld().getName().toUpperCase() + " | &cX &9" + target.getLocation().getBlockX() + " | &cY &9" + target.getLocation().getBlockY() + " | &cZ &9" + target.getLocation().getBlockZ()));
                        player.sendMessage(Utils.chat("&6Gamemode: &9" + target.getGameMode() + "&6, Can Fly: &9" + target.getAllowFlight()));
                        player.sendMessage(Utils.chat("&6First Joined: &9" + target.getFirstPlayed() + "&6, Last Played: &9" + target.getLastPlayed()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        player.sendMessage(Utils.chat(messages.getString("PlayerNotFound")));
                    }
                }
                return true;
            } else {
                player.sendMessage(Utils.chat("You must specify a target!"));
            }
        } else {
            sender.sendMessage(Utils.chat(messages.getString("NoPerms")));
        }
        sender.sendMessage(Utils.chat(messages.getString("SenderNotPlayer")));
        return false;
    }
}
