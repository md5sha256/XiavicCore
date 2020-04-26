package Commands.StaffCmds.noncheat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;
import static utils.Utils.chat;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (player.hasPermission(permissions.getString("HealOthers")) || player.isOp()) {
                    String who = strings[0];
                    if (who.equalsIgnoreCase("all")) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            chat(target, messages.getString("Heal"));
                            target.setHealth(20.0);
                            target.getActivePotionEffects().clear();
                        }
                        player.sendMessage(chat(messages.getString("HealAll")));
                        return true;
                    } else {
                        try {
                            Player target = Bukkit.getPlayer(who);
                            chat(target, messages.getString("Heal"));
                            target.setHealth(20.0);
                            target.getActivePotionEffects().clear();
                            chat(player, messages.getString("HealOther").replace("%target%", target.getDisplayName()));
                            return true;
                        } catch (Exception e) {
                            chat(player, messages.getString("PlayerNotFound"));
                            return true;
                        }
                    }
                } else {
                    chat(player, messages.getString("NoPerms"));
                    return true;
                }
            } else {
                if (player.hasPermission(permissions.getString("Heal")) || player.isOp()) {
                    chat(player, messages.getString("Heal"));
                    player.setHealth(20.0);
                    player.getActivePotionEffects().clear();
                } else {
                    chat(player, messages.getString("NoPerms"));
                }
                return true;
            }
        } else {
            if (strings.length == 1) {
                String who = strings[0];
                if (who.equalsIgnoreCase("all")) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        chat(target, messages.getString("Heal"));
                        target.setHealth(20.0);
                        target.getActivePotionEffects().clear();
                    }
                    commandSender.sendMessage(chat(messages.getString("HealAll")));
                    return true;
                } else {
                    try {
                        Player target = Bukkit.getPlayer(who);
                        chat(target, messages.getString("Heal"));
                        target.setHealth(20.0);
                        target.getActivePotionEffects().clear();
                        chat(commandSender, messages.getString("HealOther").replace("%target%", target.getDisplayName()));
                        return true;
                    } catch (Exception e) {
                        chat(commandSender, messages.getString("PlayerNotFound"));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
