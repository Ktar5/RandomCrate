package me.ktar.randomchest.utils;

import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Created by carter on 5/15/2015.
 */
public class InventoryUtil {

    /**
     * Find how many open slots (air slots) the player has in their inventory
     *
     * @param player player who's inventory will be searched
     * @return amount of slots free (air), 0 if none
     */
    public static int howManyFreeSpaces(Player player){
        return (int) Arrays.stream(player.getInventory().getContents()).filter(item -> item == null).count();
        /*
        int empty = 0;
        for(ItemStack i : player.getInventory().getContents())
            if(i == null) empty++;
        return empty;
        */
    }

}
