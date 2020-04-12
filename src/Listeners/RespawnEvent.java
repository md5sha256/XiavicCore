package Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import utils.LocationUtils;

import static Main.mainClass.mainConfig;

public class RespawnEvent implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if (!e.isBedSpawn()) {
            e.setRespawnLocation(LocationUtils.getLocation("Spawn_World_Name"));
        }
    }

}
