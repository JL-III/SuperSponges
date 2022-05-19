package com.nessxxiii.supersponges.util;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TogglePower implements Listener {

    public static Material sponge = Material.SPONGE;

    @EventHandler
    public static void activateClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material coolDown = Material.JIGSAW;

        if (!event.getAction().isRightClick()) return;
        if (!player.isSneaking()) return;
        if (player.isFlying()) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != sponge) return;
        if (!item.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) return;
        if (!item.hasItemMeta()) return;
        if (!ItemInfo.isTitanSponge(item)) return;

        if (player.hasCooldown(coolDown)) return;
        player.setCooldown(coolDown,25);
        if (!player.hasPermission("benchants.charge.toggle")) return;
        player.sendMessage("Passed activateClick checks");
        event.setCancelled(true);
        toggleChargedAxeEnchant(item,player);
    }

    public static void toggleChargedAxeEnchant(ItemStack item, Player player){
        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return;
        if (ItemInfo.isLava(item)) {
            lavaToWater(item);
            player.sendMessage(ChatColor.GREEN + "Sponge set to Water Pickup");
        } else if (ItemInfo.isWater(item)) {
            waterToLava(item);
            player.sendMessage(ChatColor.GREEN + "Sponge set to Lava Pickup");
        }
    }

    public static void lavaToWater(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.WATER);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }
    public static void waterToLava(ItemStack item) {
        List<String> loreList = item.getItemMeta().getLore();
        Integer index = ItemInfo.getAncientPowerLoreIndex(loreList);
        loreList.set(index,ItemInfo.LAVA);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(loreList);
        item.setItemMeta(meta);
    }



}

