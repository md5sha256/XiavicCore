package Commands.UserCmds.Essential;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class InfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player pl = (Player) sender;
            if (pl.hasPermission(permissions.getString("Info")) || pl.isOp()) {
                pl.sendMessage(Utils.chat(" "));
                pl.sendMessage(Utils.chat("&eItem Name: &b" + pl.getInventory().getItemInMainHand().getType()));
                pl.sendMessage(Utils.chat("&eItem Data: &b" + pl.getInventory().getItemInMainHand().getData()));
                pl.sendMessage(Utils.chat("&eMax Stack: &b" + pl.getInventory().getItemInMainHand().getMaxStackSize()));
                if (!pl.getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
                    pl.sendMessage(Utils.chat("&eItem Enchantments: &c ===================================="));
                    pl.sendMessage(Utils.chat("&b" + pl.getInventory().getItemInMainHand().getEnchantments()).replace("Enchantment", "").replace("[", "").replace("]", "").replace("minecraft:", "").replace(",", " : ").toLowerCase());
                }
            } else {
                sender.sendMessage(Utils.chat(messages.getString("NoPerms")));
            }
            return true;
        }
        sender.sendMessage(Utils.chat(messages.getString("SenderNotPlayer")));
        return false;
    }
}
