package xiaviccore.utils.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xiaviccore.XiavicCore;
import xiaviccore.utils.Utils;

import java.io.File;
import java.io.IOException;

public class Players {

    public FileConfiguration playercfg;
    public File playersfile;
    private XiavicCore plugin = XiavicCore.getPlugin(XiavicCore.class);

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        playersfile = new File(plugin.getDataFolder(), "players.yml");

        if (!playersfile.exists()) {
            try {
                playersfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not create the players.yml file.... Uhh ohh..."));
            }
        }
        playercfg = YamlConfiguration.loadConfiguration(playersfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&aSuccessfully created the players.yml file."));
    }

    public FileConfiguration getPlayers() {
        return playercfg;
    }

    public void savePlayers() {
        try {
            playercfg.save(playersfile);
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&aSuccessfully saved the players.yml file!"));
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould NOT save the players.yml file... Is it configured correctly?"));
        }
    }

    public void reloadPlayers() {
        playercfg = YamlConfiguration.loadConfiguration(playersfile);
    }
}
