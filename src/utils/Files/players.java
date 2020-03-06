package utils.Files;

import Main.mainClass;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import utils.utils;

import java.io.File;
import java.io.IOException;

public class players {

    public FileConfiguration playercfg;
    public File playersfile;
    private mainClass plugin = mainClass.getPlugin(mainClass.class);

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        playersfile = new File(plugin.getDataFolder(), "players.yml");

        if (!playersfile.exists()) {
            try {
                playersfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(utils.chat("&cCould not create the players.yml file.... Uhh ohh..."));
            }
        }
        playercfg = YamlConfiguration.loadConfiguration(playersfile);
        Bukkit.getServer().getConsoleSender().sendMessage(utils.chat("&aSuccessfully created the players.yml file."));
    }

    public FileConfiguration getPlayers() {
        return playercfg;
    }

    public void savePlayers() {
        try {
            playercfg.save(playersfile);
            Bukkit.getServer().getConsoleSender().sendMessage(utils.chat("&aSuccessfully saved the players.yml file!"));
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(utils.chat("&cCould NOT save the players.yml file... Is it configured correctly?"));
        }
    }

    public void reloadPlayers() {
        playercfg = YamlConfiguration.loadConfiguration(playersfile);
    }
}
