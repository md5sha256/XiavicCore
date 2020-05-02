package xiaviccore.utils.tpa;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xiaviccore.utils.files.Messages;
import xiaviccore.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static xiaviccore.XiavicCore.*;

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
                requests.remove(request);
                return;
            }
        }
        player.sendMessage(Utils.chat(m.getString("NoRequest")));
    }

    // 0 - success
    // 1 - tpa already pending
    // 2 - tpa disabled
    public int addRequest(Player origin, Player target) {
        for (TpaRequest tpr : this.requests) {
            if (tpr.getOrigin() == origin) {
                origin.sendMessage(Utils.chat(messages.getString("TpaPending")));
                return 1;
            }
        }
        if (teleportHandler.isDisabled(target)) return 2;
        this.requests.add(new TpaRequest(origin, target));
        this.startCooldown(origin);
        return 0;
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
                System.out.println("Teleport request timed out");
                this.deadRequests.add(request);
            }
        }
        this.requests.removeAll(this.deadRequests);
        this.deadRequests.clear();
    }

    private void checkTeleports() {
        for (Map.Entry<TpaRequest, Long> teleport : this.teleports.entrySet()) {
            if ((System.currentTimeMillis() - teleport.getValue()) / 1000 > this.teleportTime) {
                System.out.println("Teleport request fulfilled");
                TpaRequest request = teleport.getKey();
                teleportHandler.teleport(request.getOrigin(), request.getTarget(), false);
                this.deadTeleports.add(request);
            }
        }
        for (TpaRequest tpr : this.deadTeleports) {
            this.teleports.remove(tpr);
            this.requests.remove(tpr);
        }
    }

    private void checkCooldowns() {
        for (Map.Entry<Player, Long> cooldown : this.cooldowns.entrySet()) {
            if ((System.currentTimeMillis() - cooldown.getValue()) / 1000 > this.tpaCooldown) {
                System.out.println("Cooldown Expired");
                this.deadCooldowns.add(cooldown.getKey());
            }
        }
        for (Player player : this.deadCooldowns) {
            this.cooldowns.remove(player);
        }
    }

}
