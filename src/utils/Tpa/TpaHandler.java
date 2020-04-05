package utils.Tpa;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;

import java.util.ArrayList;

public class TpaHandler implements Runnable {

    private FileConfiguration m = Messages.get();

    private ArrayList<TpaRequest> requests;

    private int requestTimeout = 3;

    public TpaHandler() {
        this.requests = new ArrayList<>();
    }

    public void parseRequest(Player player, boolean accepted) {
        for (TpaRequest request : requests) {
            if (request.getTarget() == player) {
                if (accepted) {

                } else {

                }
            }
        }
    }

    @Override
    public void run() {
        ArrayList<TpaRequest> deadRequests = new ArrayList<>();
        for (TpaRequest request : requests) {
            if (request.isDead(requestTimeout)) {
                deadRequests.add(request);
            }
        }
        this.requests.removeAll(deadRequests);
    }
}
