package utils.Tpa;

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


    private ArrayList<TpaRequest> deadTeleports = new ArrayList<>();
    private ArrayList<TpaRequest> deadRequests = new ArrayList<>();

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

    public void addRequest(TpaRequest request) {
        this.requests.add(request);
    }

    @Override
    public void run() {
        checkRequests();
        checkTeleports();
    }

    private void checkRequests() {
        for (TpaRequest request : requests) {
            if (request.isDead(requestTimeout)) {
                this.deadRequests.add(request);
            }
        }
        this.requests.removeAll(this.deadRequests);
        this.deadRequests.clear();
    }

    private void checkTeleports() {
        for (Map.Entry<TpaRequest, Long> teleport : teleports.entrySet()) {
            if ((teleport.getValue() - System.currentTimeMillis())/1000 > this.teleportTime) {
                TpaRequest request = teleport.getKey();
                request.getOrigin().teleport(request.getTarget().getLocation());
                this.deadTeleports.add(request);
            }
        }
        this.requests.removeAll(this.deadRequests);
        this.deadRequests.clear();
    }

}
