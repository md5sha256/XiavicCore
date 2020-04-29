package utils.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.HashMap;

import static Main.mainClass.messages;
import static utils.Utils.chat;

public class TeleportHandler implements Listener {

    private ArrayList<Player> disabledPlayers;
    private HashMap<Player, Location> lastLocations;

    public TeleportHandler() {
        lastLocations = new HashMap<>();
        disabledPlayers = new ArrayList<>();
    }

    public void processPlayerTeleport(Player player) {
        lastLocations.put(player, player.getLocation());
    }

    public void processPlayerToggle(Player player) {
        if (disabledPlayers.contains(player)) {
            disabledPlayers.remove(player);
            chat(player, messages.getString("TpToggleOff"));
        } else {
            disabledPlayers.add(player);
            chat(player, messages.getString("TpToggleOn"));
        }
    }

    private void teleport(Player player, Location location) {
        processPlayerTeleport(player);
        player.teleport(location);
    }

    private void teleport(Player p1, Player p2) {
        processPlayerTeleport(p1);
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