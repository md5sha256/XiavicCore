package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class HealCommand implements CommandExecutor {

    FileConfiguration m = Messages.get();
    FileConfiguration p = Permissions.get();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission(p.getString("HealOthers")) || player.isOp()) {
                    String who = strings[0];
                    if (who.equalsIgnoreCase("all")) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.sendMessage(Utils.chat(m.getString("Heal")));
                            target.setHealth(20.0);
                        }
                        player.sendMessage(Utils.chat(m.getString("HealAll")));
                        return true;
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(who);
                            target.sendMessage(Utils.chat(m.getString("Heal")));
                            target.setHealth(20.0);
                            player.sendMessage(Utils.chat(m.getString("HealOther").replace("%target%", target.getDisplayName())));
                            return true;
                        } catch (Exception e) {
                            player.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                            return true;
                        }
                    }
                } else {
                    player.sendMessage(Utils.chat(m.getString("NoPerms")));
                    return true;
                }
            } else {
                if (player.hasPermission(p.getString("Heal")) || player.isOp()) {
                    player.sendMessage(Utils.chat(m.getString("Heal")));
                    player.setHealth(20.0);
                } else {
                    player.sendMessage(Utils.chat(m.getString("NoPerms")));
                }
                return true;
            }
        } else {
            if (strings.length == 1) {
                String who = strings[0];
                if (who.equalsIgnoreCase("all")) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.sendMessage(Utils.chat(m.getString("Heal")));
                        target.setHealth(20.0);
                    }
                    commandSender.sendMessage(Utils.chat(m.getString("HealAll")));
                    return true;
                } else {
                    try {
                        Player target = Bukkit.getPlayer(who);
                        target.sendMessage(Utils.chat(m.getString("Heal")));
                        target.setHealth(20.0);
                        commandSender.sendMessage(Utils.chat(m.getString("HealOther").replace("%target%", target.getDisplayName())));
                        return true;
                    } catch (Exception e) {
                        commandSender.sendMessage(Utils.chat(m.getString("PlayerNotFound")));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
