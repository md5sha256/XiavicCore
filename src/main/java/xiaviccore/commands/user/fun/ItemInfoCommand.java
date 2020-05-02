package xiaviccore.commands.user.fun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import xiaviccore.utils.Utils;

import java.util.Map;

import static xiaviccore.XiavicCore.messages;
import static xiaviccore.XiavicCore.permissions;

public class ItemInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player pl = (Player) sender;
            if (pl.hasPermission(permissions.getString("ItemInfo")) || pl.isOp()) {
                if (!pl.getInventory().getItemInMainHand().getType().isAir()) {
                    pl.sendMessage(Utils.chat(" "));
                    if (pl.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
                        pl.sendMessage(Utils.chat("&eItem Meta: &b" + pl.getInventory().getItemInMainHand().getItemMeta().getDisplayName()));
                    }
                    pl.sendMessage(Utils.chat("&eMinecraft Name: &b" + pl.getInventory().getItemInMainHand().getType().name()));
                    pl.sendMessage(Utils.chat("&eMax Stack: &b" + pl.getInventory().getItemInMainHand().getMaxStackSize()));
                    if (!pl.getInventory().getItemInMainHand().getEnchantments().isEmpty()) {
                        pl.sendMessage(Utils.chat("&eItem Enchantments: &c ===================================="));
                        Map<Enchantment, Integer> ench = pl.getInventory().getItemInMainHand().getEnchantments();
                        for (Map.Entry<Enchantment, Integer> e : ench.entrySet()) {
                            String str = e.getKey().getKey().getKey().replace("_", " ");
                            String name = str.substring(0, 1).toUpperCase() + str.substring(1);
                            int level = e.getValue();
                            String output = "      &b" + name + " : " + level;
                            pl.sendMessage(Utils.chat(output));
                        }
                    }
                } else {
                    pl.sendMessage(Utils.chat(messages.getString("ItemIsAir")));
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
