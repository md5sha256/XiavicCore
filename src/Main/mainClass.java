package Main;

import Commands.StaffCmds.*;
import Commands.UserCmds.EnderChestCommand;
import Commands.UserCmds.Spawn;
import Listeners.JoinQuit;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import utils.Files.messages;
import utils.Files.permissions;
import utils.Files.players;

public class mainClass extends JavaPlugin implements Listener {

    private players cfmg;

    public void onEnable() {
        loadshit();
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" XIAVIC CORE IS ACTIVATED... ");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("Xiavic Network's Amazing Core");
        Bukkit.getConsoleSender().sendMessage("     In Development by the Xiavic Owners ");
        Bukkit.getConsoleSender().sendMessage(" ");
        registerListeners();
        registerCommands();
        registerStaffcmds();
    }

    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("spawn").setExecutor(new Spawn());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinQuit(), this);
    }

    private void registerStaffcmds() {
        getCommand("setspawn").setExecutor(new SpawnSet());
        //getCommand("tp").setExecutor(new Teleport());
    }

    public void loadshit() {
        saveResource("Resources/permissions.yml", true);
        saveResource("Resources/messages.yml", true);
        getConfig().options().copyDefaults(true);
        saveConfig();
        permissions.setup();
        permissions.get();
        permissions.get().options().copyDefaults(true);
        permissions.save();
        permissions.reload();
        cfmg = new players();
        cfmg.setup();
        cfmg.savePlayers();
        cfmg.reloadPlayers();
        cfmg.getPlayers();
        messages.setup();
        messages.get();
        messages.get().options().copyDefaults(true);
        messages.save();
        messages.reload();
    }

}
