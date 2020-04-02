package Commands.StaffCmds;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.messages;
import utils.Files.permissions;
import utils.utils;

public class GamemodeCommand implements CommandExecutor {

    FileConfiguration m = messages.get();
    FileConfiguration p = permissions.get();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("gamemode") || s.equalsIgnoreCase("gm")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (strings.length == 1) {
                    if (player.hasPermission(p.getString("Gamemode")) || player.isOp()) {
                        String mode = strings[0];
                        if (mode.equalsIgnoreCase("creative") || mode.equalsIgnoreCase("1") || mode.equalsIgnoreCase("c")) {
                            player.setGameMode(GameMode.CREATIVE);
                        } else if (mode.equalsIgnoreCase("survival") || mode.equalsIgnoreCase("0") || mode.equalsIgnoreCase("su")) {
                            player.setGameMode(GameMode.SURVIVAL);
                        } else if (mode.equalsIgnoreCase("adventure") || mode.equalsIgnoreCase("2") || mode.equalsIgnoreCase("a")) {
                            player.setGameMode(GameMode.ADVENTURE);
                        } else if (mode.equalsIgnoreCase("spectator") || mode.equalsIgnoreCase("3") || mode.equalsIgnoreCase("sp")) {
                            player.setGameMode(GameMode.SPECTATOR);
                        }
                        player.sendMessage(utils.chat(m.getString("Gamemode").replace("%mode%", player.getGameMode().name())));
                    } else {
                        player.sendMessage(utils.chat(m.getString("NoPerms")));
                    }
                    return true;
                }
                if (strings.length == 2) {
                    if (player.hasPermission(p.getString("GamemodeOthers")) || player.isOp()) {
                        String mode = strings[0];
                        String who = strings[1];
                        try {
                            Player target = Bukkit.getPlayer(who);
                            if (mode.equalsIgnoreCase("creative") || mode.equalsIgnoreCase("1") || mode.equalsIgnoreCase("c")) {
                                target.setGameMode(GameMode.CREATIVE);
                            } else if (mode.equalsIgnoreCase("survival") || mode.equalsIgnoreCase("0") || mode.equalsIgnoreCase("su")) {
                                target.setGameMode(GameMode.SURVIVAL);
                            } else if (mode.equalsIgnoreCase("adventure") || mode.equalsIgnoreCase("2") || mode.equalsIgnoreCase("a")) {
                                target.setGameMode(GameMode.ADVENTURE);
                            } else if (mode.equalsIgnoreCase("spectator") || mode.equalsIgnoreCase("3") || mode.equalsIgnoreCase("sp")) {
                                target.setGameMode(GameMode.SPECTATOR);
                            }
                            target.sendMessage(utils.chat(m.getString("Gamemode").replace("%mode%", target.getGameMode().name())));
                            commandSender.sendMessage(utils.chat(m.getString("GamemodeOther").replace("%target%", target.getDisplayName()) + target.getGameMode().name()));
                            return true;
                        } catch (Exception e) {
                            commandSender.sendMessage(utils.chat(m.getString("PlayerNotFound")));
                            return true;
                        }
                    } else {
                        player.sendMessage(utils.chat(m.getString("NoPerms")));
                        return true;
                    }
                }
            } else {
                if (strings.length == 2) {
                    String mode = strings[0];
                    String who = strings[1];
                    try {
                        Player target = Bukkit.getPlayer(who);
                        if (mode.equalsIgnoreCase("creative") || mode.equalsIgnoreCase("1") || mode.equalsIgnoreCase("c")) {
                            target.setGameMode(GameMode.CREATIVE);
                        } else if (mode.equalsIgnoreCase("survival") || mode.equalsIgnoreCase("0") || mode.equalsIgnoreCase("su")) {
                            target.setGameMode(GameMode.SURVIVAL);
                        } else if (mode.equalsIgnoreCase("adventure") || mode.equalsIgnoreCase("2") || mode.equalsIgnoreCase("a")) {
                            target.setGameMode(GameMode.ADVENTURE);
                        } else if (mode.equalsIgnoreCase("spectator") || mode.equalsIgnoreCase("3") || mode.equalsIgnoreCase("sp")) {
                            target.setGameMode(GameMode.SPECTATOR);
                        }
                        target.sendMessage(utils.chat(m.getString("Gamemode").replace("%mode%", target.getGameMode().name())));
                        commandSender.sendMessage(utils.chat(m.getString("GamemodeOther").replace("%target%", target.getDisplayName()) + target.getGameMode().name()));
                        return true;
                    } catch (Exception e) {
                        commandSender.sendMessage(utils.chat(m.getString("PlayerNotFound")));
                        return true;
                    }
                } else {
                    commandSender.sendMessage("You need to specify a target and gamemode!");
                    return true;
                }
            }
        }
        return false;
    }
}
