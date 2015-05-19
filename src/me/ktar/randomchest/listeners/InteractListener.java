package me.ktar.randomchest.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by carter on 5/18/2015.
 */
public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event){
        if(event.getRightClicked().getUniqueId().equals(getUuid()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(event.getEntity().getUniqueId().equals(getUuid()))
            event.setCancelled(true);
    }
}
