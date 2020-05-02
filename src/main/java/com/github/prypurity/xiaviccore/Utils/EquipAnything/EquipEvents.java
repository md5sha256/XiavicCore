package com.github.prypurity.xiaviccore.Utils.EquipAnything;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EquipEvents implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player;
        if ((event.getWhoClicked() instanceof Player)) {
            player = (Player) event.getWhoClicked();
            if (player.getItemOnCursor().getType() != Material.AIR) {
                ItemStack item = player.getItemOnCursor();
                if (item.hasItemMeta()) {
                    ItemMeta meta = item.getItemMeta();
                    if (meta.hasLore()) {
                        for (String l : meta.getLore()) {
                            if ((l.contains("helm")) && (event.getSlot() == 39)) {
                                ItemStack temp = player.getInventory().getItem(39);
                                if (temp != null) {
                                    temp = temp.clone();
                                }
                                player.getInventory().setItem(39, player.getItemOnCursor());
                                player.setItemOnCursor(temp);
                                player.updateInventory();
                            } else if ((l.contains("chest")) && (event.getSlot() == 38)) {
                                ItemStack temp = player.getInventory().getItem(38);
                                if (temp != null) {
                                    temp = temp.clone();
                                }
                                player.getInventory().setItem(38, player.getItemOnCursor());
                                player.setItemOnCursor(temp);
                                player.updateInventory();
                            } else if ((l.contains("legs")) && (event.getSlot() == 37)) {
                                ItemStack temp = player.getInventory().getItem(37);
                                if (temp != null) {
                                    temp = temp.clone();
                                }
                                player.getInventory().setItem(37, player.getItemOnCursor());
                                player.setItemOnCursor(temp);
                                player.updateInventory();
                            } else if ((l.contains("feet")) && (event.getSlot() == 36)) {
                                ItemStack temp = player.getInventory().getItem(36);
                                if (temp != null) {
                                    temp = temp.clone();
                                }
                                player.getInventory().setItem(36, player.getItemOnCursor());
                                player.setItemOnCursor(temp);
                                player.updateInventory();
                            }
                        }
                    }
                }
            }
        }
    }
}