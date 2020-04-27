package Commands.StaffCmds.cheats;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;
import static utils.Utils.chat;

public class CheatArmor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("CheatArmor")) || player.isOp()) {
                if (command.getName().equalsIgnoreCase("cheatarmor")) {
                    ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
                    helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10); // Default - 4
                    helmet.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10); // Default - 4
                    helmet.addEnchantment(Enchantment.PROTECTION_FIRE, 10); // Default - 4
                    helmet.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 10); // Default - 4
                    helmet.addEnchantment(Enchantment.WATER_WORKER, 10);  // Default - 1
                    helmet.addEnchantment(Enchantment.OXYGEN, 10);  // Default - 3
                    helmet.addEnchantment(Enchantment.DURABILITY, 10);  // Default - 3
                    ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                    chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10); // Default - 4
                    chestplate.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10); // Default - 4
                    chestplate.addEnchantment(Enchantment.PROTECTION_FIRE, 10); // Default - 4
                    chestplate.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 10); // Default - 4
                    chestplate.addEnchantment(Enchantment.THORNS, 10);  // Default - 3
                    chestplate.addEnchantment(Enchantment.DURABILITY, 10);  // Default - 3
                    ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
                    legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10); // Default - 4
                    legs.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10); // Default - 4
                    legs.addEnchantment(Enchantment.PROTECTION_FIRE, 10); // Default - 4
                    legs.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 10); // Default - 4
                    legs.addEnchantment(Enchantment.THORNS, 10);  // Default - 3
                    legs.addEnchantment(Enchantment.DURABILITY, 10);  // Default - 3
                    ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
                    boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10); // Default - 4
                    boots.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10); // Default - 4
                    boots.addEnchantment(Enchantment.PROTECTION_FIRE, 10); // Default - 4
                    boots.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 10); // Default - 4
                    boots.addEnchantment(Enchantment.DURABILITY, 10);  // Default - 3
                    boots.addEnchantment(Enchantment.PROTECTION_FALL, 10); // Default - 4

                    player.getInventory().addItem(helmet);
                    player.getInventory().addItem(chestplate);
                    player.getInventory().addItem(legs);
                    player.getInventory().addItem(boots);
                    chat(player, messages.getString("CheatArmor"));

                }
            }
            return true;
        }
        return false;
    }
}
