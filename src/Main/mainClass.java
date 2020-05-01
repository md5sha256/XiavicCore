package Main;

import Commands.StaffCmds.cheats.CheatArmor;
import Commands.StaffCmds.cheats.CheatEXP;
import Commands.StaffCmds.noncheat.*;
import Commands.StaffCmds.noncheat.Teleport.TPPosCommand;
import Commands.StaffCmds.noncheat.Teleport.TPhereCommand;
import Commands.StaffCmds.noncheat.Teleport.TeleportCommand;
import Commands.StaffCmds.noncheat.Teleport.TpallCommand;
import Commands.UserCmds.Essential.*;
import Commands.UserCmds.Essential.Teleport.BackCommand;
import Commands.UserCmds.Essential.Teleport.RandomTPCommand;
import Commands.UserCmds.Essential.Teleport.SpawnCommand;
import Commands.UserCmds.Essential.Teleport.Tpa.TpaCommand;
import Commands.UserCmds.Essential.Teleport.Tpa.TpacceptCommand;
import Commands.UserCmds.Essential.Teleport.Tpa.TpdenyCommand;
import Commands.UserCmds.Fun.*;
import Commands.UserCmds.Fun.Links.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import utils.EquipAnything.EquipEvents;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Files.Players;
import utils.Listeners.JoinQuit;
import utils.Listeners.RespawnEvent;
import utils.Listeners.TeleportHandler;
import utils.Tpa.TpaHandler;

//import de.leonhard.storage.Json;

public class mainClass extends JavaPlugin implements Listener {

    public static FileConfiguration mainConfig;
    public static FileConfiguration messages;
    public static FileConfiguration permissions;
    public static TpaHandler tpaHandler;
    public static TeleportHandler teleportHandler;
    private Players cfmg;

    public void onEnable() {


        loadshit();
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" XIAVIC CORE IS ACTIVATED... ");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("Xiavic Network's Amazing Core");
        Bukkit.getConsoleSender().sendMessage("     In Development by the Xiavic Owners ");
        Bukkit.getConsoleSender().sendMessage(" ");
        registerShit();
        registerListeners();
        registerCommands();
        //Json json = new Json("Test", "plugins/XiavicCore/Resources");
        Bukkit.getScheduler().runTaskTimer(this, tpaHandler, 0, 20);
    }

    public void onDisable() {

    }

    private void registerCommands() {
        //getCommand("fireball").setExecutor(new FireBallCommand());
        getCommand("argh").setExecutor(new ArghCommand());
        getCommand("back").setExecutor(new BackCommand());
        getCommand("cartography").setExecutor(new CartographyCommand());
        getCommand("cheatarmor").setExecutor(new CheatArmor());
        getCommand("cheatexp").setExecutor(new CheatEXP());
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("clearall").setExecutor(new ClearAllCommand());
        //getCommand("coinflip").setExecutor(new CoinFlipCommand());
        getCommand("coreconfigupdate").setExecutor(new ConfigReloadCommand(this));
        getCommand("coreversion").setExecutor(new CoreVersionCommand(this));
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("dispose").setExecutor(new DisposeCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        getCommand("ext").setExecutor(new ExtinguishCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("flyspeed").setExecutor(new FlySpeedCommand());
        getCommand("forums").setExecutor(new ForumsCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("god").setExecutor(new GodCommand());
        getCommand("grindstone").setExecutor(new GrindstoneCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("info").setExecutor(new ItemInfoCommand());
        getCommand("loom").setExecutor(new LoomCommand());
        getCommand("more").setExecutor(new MoreCommand());
        getCommand("near").setExecutor(new NearCommand());
        getCommand("pony").setExecutor(new PonyCommand());
        getCommand("rtp").setExecutor(new RandomTPCommand(this));
        getCommand("setfirstspawn").setExecutor(new FirstSpawnSetCommand(this));
        getCommand("setspawn").setExecutor(new SpawnSetCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("stonecutter").setExecutor(new StonecutterCommand());
        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("top").setExecutor(new TopCommand());
        getCommand("tp").setExecutor(new TeleportCommand());
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpaccept").setExecutor(new TpacceptCommand());
        getCommand("tpall").setExecutor(new TpallCommand());
        getCommand("tpdeny").setExecutor(new TpdenyCommand());
        getCommand("tphere").setExecutor(new TPhereCommand());
        getCommand("tppos").setExecutor(new TPPosCommand());
        getCommand("twitter").setExecutor(new TwitterCommand());
        getCommand("walkspeed").setExecutor(new WalkSpeedCommand());
        getCommand("website").setExecutor(new WebsiteCommand());
        getCommand("whois").setExecutor(new WhoIsCommand());
        getCommand("workbench").setExecutor(new WorkbenchCommand());
        getCommand("youtube").setExecutor(new YoutubeCommand());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinQuit(), this);
        pm.registerEvents(new EquipEvents(), this);
        pm.registerEvents(new RespawnEvent(), this);
        pm.registerEvents(teleportHandler, this);
    }

    // Use this function for creating new shit
    private void registerShit() {
        tpaHandler = new TpaHandler();
        teleportHandler = new TeleportHandler();
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
    // build of the plugin and preserves the spawn location in the mainConfig
    public void updateShit() {
        String firstspawnLocation = mainConfig.getString("FirstSpawn");
        String spawnLocation = mainConfig.getString("Spawn");
        saveResource("Resources/permissions.yml", true);
        saveResource("Resources/messages.yml", true);
        saveResource("config.yml", true);
        Permissions.setup();
        Messages.setup();
        mainConfig = getConfig();
        permissions = Permissions.get();
        messages = Messages.get();
        mainConfig.set("FirstSpawn", firstspawnLocation);
        mainConfig.set("Spawn", spawnLocation);
        saveConfig();
    }

}
