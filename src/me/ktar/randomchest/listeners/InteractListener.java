package me.ktar.randomchest.listeners;

import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.storage.Loader;
import me.ktar.randomchest.utils.ChestUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by carter on 5/18/2015.
 */
public class InteractListener implements Listener {

    /*@EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event){
        if(event.getRightClicked().getUniqueId().equals(getUuid()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(event.getEntity().getUniqueId().equals(getUuid()))
            event.setCancelled(true);
    }*/

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getClickedBlock().hasMetadata("ktarrandomchest")){
            event.setCancelled(true);
            ChestWrapper wrapper = Loader.getChestWrapper(event.getClickedBlock());
            if(wrapper.getInUse()){
                event.getPlayer().sendMessage("This chest in in use! Sorry");
            }else{
                ChestUtil.changeChestState(event.getClickedBlock().getLocation(), true, event.getPlayer());
                wrapper.use(event.getPlayer());
                if(event.getPlayer().isOnline()){
                   ChestUtil.changeChestState(event.getClickedBlock().getLocation(), false, event.getPlayer());
                }
            }
        }
    }

}
