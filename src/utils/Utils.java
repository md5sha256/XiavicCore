package utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Main.mainClass.backHandler;
import static Main.mainClass.messages;

public class Utils {

    //EZ Chat Colors

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void chat(Player player, String string) {
        player.sendMessage(chat(string));
    }

    public static void chat(CommandSender sender, String string) {
        sender.sendMessage(chat(string));
    }

    public static void teleport(Player player, Location location) {
        backHandler.processPlayer(player);
        player.teleport(location);
    }

    public static void teleport(Player player, Location location, String messagePath) {
        backHandler.processPlayer(player);
        player.teleport(location);
        chat(player, messages.getString(messagePath));
    }

}
