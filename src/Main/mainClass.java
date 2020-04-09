package Main;

import Commands.StaffCmds.cheats.CheatArmor;
import Commands.StaffCmds.cheats.CheatEXP;
import Commands.StaffCmds.noncheat.*;
import Commands.UserCmds.Essential.Dispose;
import Commands.UserCmds.Essential.EnderChestCommand;
import Commands.UserCmds.Essential.Spawn;
import Commands.UserCmds.Essential.Tpa.TpaCommand;
import Commands.UserCmds.Essential.Tpa.TpacceptCommand;
import Commands.UserCmds.Essential.Tpa.TpdenyCommand;
import Commands.UserCmds.Fun.Argh;
import Listeners.JoinQuit;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import utils.EquipAnything.EquipEvents;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Files.Players;
import utils.Tpa.TpaHandler;

public class mainClass extends JavaPlugin implements Listener {

    public static FileConfiguration mainConfig;
    public static FileConfiguration messages;
    public static FileConfiguration permissions;
    public static TpaHandler tpaHandler;
    private Players cfmg;

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
        registerShit();

        Bukkit.getScheduler().runTaskTimer(this, tpaHandler, 0, 20);
    }

    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("clearall").setExecutor(new ClearAllCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaccept").setExecutor(new TpacceptCommand());
        getCommand("tpdeny").setExecutor(new TpdenyCommand());
        getCommand("argh").setExecutor(new Argh());
        getCommand("setspawn").setExecutor(new SpawnSetCommand(this));
        getCommand("cheatexp").setExecutor(new CheatEXP());
        getCommand("cheatarmor").setExecutor(new CheatArmor());
        getCommand("flyspeed").setExecutor(new FlySpeedCommand());
        getCommand("walkspeed").setExecutor(new WalkSpeedCommand());
        getCommand("dispose").setExecutor(new Dispose());
        getCommand("whois").setExecutor(new WhoIsCommand());
        getCommand("coreconfigupdate").setExecutor(new ConfigReloadCommand(this));
        //getCommand("rtp").setExecutor(new RandomTP());
        // getCommand("weather").setExecutor(new WeatherCommand());
        //getCommand("tp").setExecutor(new Teleport());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinQuit(), this);
        pm.registerEvents(new EquipEvents(this), this);
    }

    // Use this function for creating new shit
    private void registerShit() {
        tpaHandler = new TpaHandler();
    }

    private void loadshit() {
        saveResource("Resources/permissions.yml", false);
        saveResource("Resources/messages.yml", false);
        saveResource("config.yml", false);
        Permissions.setup();
        Messages.setup();
        mainConfig = getConfig();
        permissions = Permissions.get();
        messages = Messages.get();
    }

    // I am using this function for updating the configs from the files inside the current
    // build of the plugin
    public void updateShit() {
        saveResource("Resources/permissions.yml", true);
        saveResource("Resources/messages.yml", true);
        saveResource("config.yml", true);
        Permissions.setup();
        Messages.setup();
        mainConfig = getConfig();
        permissions = Permissions.get();
        messages = Messages.get();
    }

}
