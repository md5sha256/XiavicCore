package xiaviccore.utils.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import xiaviccore.utils.LocationUtils;

public class RespawnEvent implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if (!e.isBedSpawn()) {
            e.setRespawnLocation(LocationUtils.getLocation("Spawn_World_Name"));
        }
    }

}
