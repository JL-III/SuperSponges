package com.nessxxiii.supersponges.util;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class ItemInfo {

    public static final String RED = "§x§F§F§0§0§0§0";

    public static final String WATER = "§8Ancient Power §x§6§D§5§E§F§F♆ WaterMax";
    public static final String LAVA = "§8Ancient Power §x§F§F§0§0§4§C♆ Lava";

    public static final List<String> TITAN_SPONGE_LORE = new ArrayList<>(){
        {

            add(WATER);
            add(LAVA);

        }
    };

    public static ItemStack titanSponge(){

        ItemStack item = new ItemStack(Material.SPONGE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("§8Ancient Power §x§6§D§5§E§F§F♆ WaterMax");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.SILK_TOUCH,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;

    }


    public static boolean isTitanSponge(ItemStack item){

        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (TITAN_SPONGE_LORE.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWater(ItemStack item){

        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (WATER.contains(lore)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLava(ItemStack item){

        List<String> loreList = item.getItemMeta().getLore();
        if (loreList == null) return false;
        for (String lore : loreList) {
            if (LAVA.contains(lore)) {
                return true;
            }
        }
        return false;
    }



    public static Integer getAncientPowerLoreIndex(List<String> loreList) {
        /*      Bukkit.getServer().getConsoleSender().sendMessage("inside of getAncientPowerLoreIndex");*/
        for (int i = 0; i < loreList.size(); i++){
            if (TITAN_SPONGE_LORE.contains(loreList.get(i))) return i;
        }
        return null;
    }
}

