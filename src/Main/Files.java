package Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Files {

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
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the players.yml file.... Uhh ohh...");
            }
        }
        playercfg = YamlConfiguration.loadConfiguration(playersfile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Successfully created the players.yml file.");
    }

    public FileConfiguration getPlayers() {
        return playercfg;
    }

    public void savePlayers() {
        try {
            playercfg.save(playersfile);
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Successfully saved the players.yml file!");
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could NOT save the players.yml file... Is it configured correctly?");
        }
    }

    public void reloadPlayers() {
        playercfg = YamlConfiguration.loadConfiguration(playersfile);
    }
}
