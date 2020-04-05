package utils.Tpa;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TpaHandler implements Runnable {

    private FileConfiguration m = Messages.get();

    private ArrayList<TpaRequest> requests;
    private HashMap<TpaRequest, Long> teleports;

    private int requestTimeout = 3;
    private int teleportTime = 3;

    public TpaHandler() {
        this.requests = new ArrayList<>();
    }

    public void parseRequest(Player player, boolean accepted) {
        for (TpaRequest request : requests) {
            if (request.getTarget() == player) {
                if (accepted) {
                    request.getOrigin().sendMessage(Utils.chat(m.getString("Tpa_Accept").replace("%target%", request.getTarget().getDisplayName()).replace("%time%", String.valueOf(teleportTime))));
                    request.getTarget().sendMessage(Utils.chat(m.getString("Tpa_Accept1").replace("%sender%", request.getOrigin().getDisplayName())));
                } else {
                    request.getOrigin().sendMessage(Utils.chat(m.getString("Tpa_Deny").replace("%target%", request.getTarget().getDisplayName()).replace("%time%", String.valueOf(teleportTime))));
                    request.getTarget().sendMessage(Utils.chat(m.getString("Tpa_Deny1").replace("%sender%", request.getOrigin().getDisplayName())));
                }
                return;
            }
        }
        player.sendMessage(Utils.chat(m.getString("NoRequest")));
    }

    @Override
    public void run() {
        checkRequests();
        checkTeleports();
    }

    private void checkRequests() {
        ArrayList<TpaRequest> deadRequests = new ArrayList<>();
        for (TpaRequest request : requests) {
            if (request.isDead(requestTimeout)) {
                deadRequests.add(request);
            }
        }
        this.requests.removeAll(deadRequests);
    }

    private void checkTeleports() {
        //for (Map.Entry<TpaRequest, Long> teleport : )
    }

}
