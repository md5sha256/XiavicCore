package utils.Tpa;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Main.mainClass.mainConfig;
import static Main.mainClass.messages;

public class TpaHandler implements Runnable {

    private FileConfiguration m = Messages.get();

    private ArrayList<TpaRequest> requests;
    private HashMap<TpaRequest, Long> teleports;
    private HashMap<Player, Long> cooldowns;

    private int requestTimeout;
    private int teleportTime;
    private int tpaCooldown;

    private ArrayList<TpaRequest> deadTeleports = new ArrayList<>();
    private ArrayList<TpaRequest> deadRequests = new ArrayList<>();
    private ArrayList<Player> deadCooldowns = new ArrayList<>();

    public TpaHandler() {
        this.requests = new ArrayList<>();
        this.teleports = new HashMap<>();
        this.cooldowns = new HashMap<>();
        this.requestTimeout = Integer.parseInt(mainConfig.getString("Tpa_Timeout"));
        this.teleportTime = Integer.parseInt(mainConfig.getString("Tpa_Delay"));
        this.tpaCooldown = Integer.parseInt(mainConfig.getString("TpaCooldown"));
    }

    public void startCooldown(Player player) {
        cooldowns.put(player, System.currentTimeMillis());
    }

    public boolean canTpa(Player player) {
        if (this.cooldowns.containsKey(player)) {
            int remaining = (int) (this.tpaCooldown - ((System.currentTimeMillis() - cooldowns.get(player)) / 1000));
            player.sendMessage(Utils.chat(messages.getString("TpaCooldown").replace("%time%", String.valueOf(remaining))));
            return false;
        }
        return true;
    }

    public void parseRequest(Player player, boolean accepted) {
        for (TpaRequest request : this.requests) {
            if (request.getTarget() == player) {
                if (accepted) {
                    request.getOrigin().sendMessage(Utils.chat(m.getString("Tpa_Accept").replace("%target%", request.getTarget().getDisplayName()).replace("%time%", String.valueOf(teleportTime))));
                    request.getTarget().sendMessage(Utils.chat(m.getString("Tpa_Accept1").replace("%sender%", request.getOrigin().getDisplayName())));
                    this.teleports.put(request, System.currentTimeMillis());
                } else {
                    request.getOrigin().sendMessage(Utils.chat(m.getString("Tpa_Deny").replace("%target%", request.getTarget().getDisplayName()).replace("%time%", String.valueOf(teleportTime))));
                    request.getTarget().sendMessage(Utils.chat(m.getString("Tpa_Deny1").replace("%sender%", request.getOrigin().getDisplayName())));
                }
                return;
            }
        }
        player.sendMessage(Utils.chat(m.getString("NoRequest")));
    }

    public void addRequest(Player origin, Player target) {
        for (TpaRequest tpr : this.requests) {
            if (tpr.getOrigin() == origin) {
                origin.sendMessage(Utils.chat(messages.getString("TpaPending")));
                return;
            }
        }
        this.requests.add(new TpaRequest(origin, target));
        this.startCooldown(origin);
    }

    @Override
    public void run() {
        checkRequests();
        checkTeleports();
        checkCooldowns();
    }

    private void checkRequests() {
        for (TpaRequest request : this.requests) {
            if (request.isDead(this.requestTimeout)) {
                this.deadRequests.add(request);
            }
        }
        this.requests.removeAll(this.deadRequests);
        this.deadRequests.clear();
    }

    private void checkTeleports() {
        for (Map.Entry<TpaRequest, Long> teleport : this.teleports.entrySet()) {
            if ((System.currentTimeMillis() - teleport.getValue()) / 1000 > this.teleportTime) {
                TpaRequest request = teleport.getKey();
                request.getOrigin().teleport(request.getTarget().getLocation());
                this.deadTeleports.add(request);
            }
        }
        for (TpaRequest tpr : this.deadTeleports) {
            this.teleports.remove(tpr);
        }
    }

    private void checkCooldowns() {
        for (Map.Entry<Player, Long> cooldown : this.cooldowns.entrySet()) {
            if ((System.currentTimeMillis() - cooldown.getValue()) / 1000 > this.tpaCooldown) {
                this.deadCooldowns.add(cooldown.getKey());
            }
        }
        for (Player player : this.deadCooldowns) {
            this.cooldowns.remove(player);
        }
    }

}
