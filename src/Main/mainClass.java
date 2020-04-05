package Main;

import Commands.StaffCmds.*;
import Commands.UserCmds.EnderChestCommand;
import Commands.UserCmds.Spawn;
import Commands.UserCmds.TpaCommand;
import Listeners.JoinQuit;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Files.Players;

public class mainClass extends JavaPlugin implements Listener {

    private Players cfmg;

    public static FileConfiguration mainConfig;

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

        mainConfig = getConfig();
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
        //getCommand("tpa").setExecutor(new TpaCommand());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinQuit(), this);
    }

    private void registerStaffcmds() {
        getCommand("setspawn").setExecutor(new SpawnSet(this));
        //getCommand("tp").setExecutor(new Teleport());
    }

    public void loadshit() {
        saveResource("Resources/permissions.yml", false);
        saveResource("Resources/messages.yml", false);
        saveResource("config.yml", false);
        Permissions.setup();
        Messages.setup();
    }

}
