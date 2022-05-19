package com.nessxxiii.supersponges.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import java.util.ArrayList;
import java.util.List;

public class SpongePlaceEvent implements Listener {

    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent event){

        Block block = event.getBlockPlaced();
        if (block.getType() != Material.SPONGE) return;
        Location location = block.getLocation();
        Player player = event.getPlayer();
        Material coolDown = Material.SPONGE;
        if (player.hasCooldown(coolDown)) return;
        player.setCooldown(coolDown,20);

        List<Block> blockList = generateSphere(location,15,false);
/*        boolean hasLava = false;
        for (Block blocks : blockList ) {
            if (blocks.getType() == Material.LAVA) {
                hasLava = true;
            }
        }
        if (!hasLava) {
            player.sendMessage(ChatColor.RED + "You cannot use a super sponge here!");
            event.setCancelled(true);
            return;
        }*/
        block.setType(Material.AIR);
        for (Block blocks : blockList ) {
            if (blocks.getType() == Material.WATER ||
                blocks.getType() == Material.KELP  ||
                blocks.getType() == Material.TALL_SEAGRASS  ||
                blocks.getType() == Material.SEAGRASS ) {
                blocks.setType(Material.AIR);
            }
        }
    }

    public static List<Block> generateSphere(Location location, int radius, boolean hollow) {
        List<Block> circleBlocks = new ArrayList<>();
        int bx = location.getBlockX();
        int by = location.getBlockY();
        int bz = location.getBlockZ();
        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {
                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));
                    if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {
                        circleBlocks.add(location.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
        }
        return circleBlocks;
    }

}
