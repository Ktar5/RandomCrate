package me.ktar.randomchest.listeners;

import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.keys.KeyHandler;
import me.ktar.randomchest.storage.Loader;
import me.ktar.randomchest.utils.ChestUtil;
import org.bukkit.Material;
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
            event.setCancelled(true); //cancel the chest inventory opening
            ChestWrapper wrapper = Loader.getChestWrapper(event.getClickedBlock());//get the wrapper associated with this
            if(event.getPlayer().getItemInHand() != null){//check its not air in their hand
                if(KeyHandler.isKey(event.getPlayer().getItemInHand(), wrapper.getType())){ //check if it is a key
                    if (wrapper.getInUse()) { //check if it is in use
                        event.getPlayer().sendMessage("This chest in in use! Sorry");
                    }else{ //if not in use
                        ChestUtil.changeChestState(event.getClickedBlock().getLocation(), true, event.getPlayer()); //open the chest animation
                        LogOffListener.keys.put(event.getPlayer(), event.getPlayer().getItemInHand()); //add it to the listener
                        event.getPlayer().getItemInHand().setType(Material.AIR); //remove the key from their inventory
                        wrapper.use(event.getPlayer()); //Tell the chest to use
                    }
                }
            }

        }
    }

}
