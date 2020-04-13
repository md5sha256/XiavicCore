package Listeners;

import Main.mainClass;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import utils.Files.Messages;
import utils.LocationUtils;
import utils.Utils;

import static Main.mainClass.messages;

public class JoinQuit implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String name = p.getDisplayName();
        if (!(p.hasPlayedBefore())) {
            event.setJoinMessage(Utils.chat(messages.getString("FirstJoin_Message").replace("%player%", name)));
            p.sendMessage(Utils.chat("&6Welcome " + p.getName()));
            p.teleport(LocationUtils.getLocation("Spawn_World_Name"));
        } else {
            event.setJoinMessage(Utils.chat(messages.getString("ReJoin_Message").replace("%player%", name)));
        }

    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        String name = p.getDisplayName();
        event.setQuitMessage(Utils.chat(messages.getString("Quit_Message").replace("%player%", name)));
    }
}
