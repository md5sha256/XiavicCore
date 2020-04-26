package utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashMap;

public class BackHandler implements Listener {

    private HashMap<Player, Location> lastLocations;

    public BackHandler() {
        lastLocations = new HashMap<>();
    }

    public void processPlayer(Player player) {
        lastLocations.put(player, player.getLocation());
    }

    public Location getLastLocation(Player player) throws Exception {
        if (!contains(player)) throw new Exception();
        return lastLocations.get(player);
    }

    private boolean contains(Player player) {
        return lastLocations.containsKey(player);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        lastLocations.put(e.getEntity(), e.getEntity().getLocation());
    }

}
