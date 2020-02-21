package Main;

import Commands.QOL.*;
import Listeners.JoinQuit;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class mainClass extends JavaPlugin implements Listener {

    private Files cfmg;

    public void onEnable() {
        loadConfigManager();
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" XIAVIC CORE IS ACTIVATED... ");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("Xiavic Network's Amazing Core");
        Bukkit.getConsoleSender().sendMessage("     In Development by the Xiavic Owners ");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" ");
        registerListeners();
        registerCommands();
        registerStaffcmds();
        loadconfig();
    }

    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinQuit(), this);
    }

    private void registerStaffcmds() {
        //getCommand("setspawn").setExecutor(new SpawnSet());
    }

    public void loadconfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void loadConfigManager() {
        cfmg = new Files();
        cfmg.setup();
        cfmg.savePlayers();
        cfmg.reloadPlayers();
    }

}
