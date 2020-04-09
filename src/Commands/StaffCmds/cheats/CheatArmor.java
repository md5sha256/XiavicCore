package Commands.StaffCmds.cheats;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

public class CheatArmor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration p = Permissions.get();
        FileConfiguration m = Messages.get();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(p.getString("CheatArmor")) || player.isOp()) {
                if (command.getName().equalsIgnoreCase("cheatarmor")) {
                    ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
                    helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                    helmet.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                    helmet.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                    helmet.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                    helmet.addEnchantment(Enchantment.WATER_WORKER, 1);
                    helmet.addEnchantment(Enchantment.OXYGEN, 3);
                    helmet.addEnchantment(Enchantment.DURABILITY, 3);
                    ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                    chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                    chestplate.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                    chestplate.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                    chestplate.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                    chestplate.addEnchantment(Enchantment.THORNS, 3);
                    chestplate.addEnchantment(Enchantment.DURABILITY, 3);
                    ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
                    legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                    legs.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                    legs.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                    legs.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                    legs.addEnchantment(Enchantment.THORNS, 3);
                    legs.addEnchantment(Enchantment.DURABILITY, 3);
                    ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
                    boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                    boots.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                    boots.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                    boots.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                    boots.addEnchantment(Enchantment.DURABILITY, 3);
                    boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);

                    player.getInventory().addItem(helmet);
                    player.getInventory().addItem(chestplate);
                    player.getInventory().addItem(legs);
                    player.getInventory().addItem(boots);
                    player.sendMessage(Utils.chat(m.getString("CheatArmor")));

                }
            }
            return true;
        }
        return false;
    }
}
