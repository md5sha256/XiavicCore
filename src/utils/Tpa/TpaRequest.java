package utils.Tpa;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Utils;

public class TpaRequest {

    private FileConfiguration m = Messages.get();

    private Player origin;
    private Player target;
    private long requestTime;

    public TpaRequest(Player origin, Player target) {
        this.origin = origin;
        this.target = target;
        this.requestTime = System.currentTimeMillis();
        sendRequest();
    }

    public void sendRequest() {
        this.origin.sendMessage(Utils.chat(m.getString("Tpa").replace("%target%", target.getDisplayName())));
        this.target.sendMessage(Utils.chat(m.getString("Tpa_Request").replace("%sender%", origin.getDisplayName())));
    }

    public Player getOrigin() {
        return this.origin;
    }

    public Player getTarget() {
        return this.target;
    }

    public boolean isDead(int duration) {
        long currentTime = System.currentTimeMillis();
        return (currentTime-requestTime)/1000 > duration;
    }

}
