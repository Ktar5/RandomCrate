package me.ktar.randomchest.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by carter on 5/20/2015.
 */
public class LogOffListener implements Listener {

    public static final Map<Player, ItemStack> keys = new HashMap<>();

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        if(keys.containsKey(event.getPlayer())){
            event.getPlayer().getInventory().addItem(keys.get(event.getPlayer()));
            event.getPlayer().updateInventory();
            keys.remove(event.getPlayer());
        }
    }

    public static void giveBackKey(Player player){
        if(keys.containsKey(player)){
            player.getInventory().addItem(keys.get(player));
            keys.remove(player);
            player.updateInventory();
        }
    }

    public static void removeIfIn(Player player){
        if(keys.containsKey(player))
            keys.remove(player);
    }
}
