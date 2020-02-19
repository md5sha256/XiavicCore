package Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuit implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        if (!(p.hasPlayedBefore())) {
            String name = p.getName();
            event.setJoinMessage(ChatColor.DARK_PURPLE + "Welcome to the development server." + ChatColor.GREEN + " \nOnly those with authorized access may use this server.!");
            p.sendMessage(ChatColor.GOLD + "Welcome " + p.getName());
            Location spawn = new Location(p.getWorld(), 548.0f, 97.0f, -438.0f);
            p.teleport(spawn);

        } else {
            p.sendMessage(ChatColor.GOLD + "+ " + p.getName());
        }

    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage(ChatColor.DARK_RED + "- " + p.getName());

    }
}
