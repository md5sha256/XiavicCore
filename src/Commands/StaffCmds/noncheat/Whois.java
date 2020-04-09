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

public class Whois implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration p = Permissions.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("Whois"))) {
                if (args.length == 1) {
                    try {
                        Player target = getPlayer(args[0]);
                        player.sendMessage(Utils.chat("&6Name: &d" + target.getName() + "&6, Nickname: &d" + target.getDisplayName()));
                        player.sendMessage(Utils.chat("&6IP: &d" + target.getAddress().toString().replace("/", "")));
                        player.sendMessage(Utils.chat("&6Exp: &d" + target.getTotalExperience() + "&6, Next Level: &d" + target.getExpToLevel()));
                        player.sendMessage(Utils.chat("&6Health: &d" + target.getHealth() + "&6, Food: &d" + target.getFoodLevel()));
                        player.sendMessage(Utils.chat("&6Time: &d" + target.getPlayerTime()));
                        player.sendMessage(Utils.chat("&6Location: &d" + target.getWorld().getName().toUpperCase() + " | &cX &d" + target.getLocation().getBlockX() + " | &cY &d" + target.getLocation().getBlockY() + " | &cZ &d" + target.getLocation().getBlockZ()));
                        player.sendMessage(Utils.chat("&6Gamemode: &d" + target.getGameMode() + "&6, Can Fly: &d" + target.getAllowFlight()));
                        player.sendMessage(Utils.chat("&6First Joined: &d" + target.getFirstPlayed() + "&6, Last Played: &d" + target.getLastPlayed()));
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
