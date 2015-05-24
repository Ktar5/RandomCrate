package me.ktar.randomchest.listeners;

import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.keys.KeyHandler;
import me.ktar.randomchest.storage.Loader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by carter on 5/18/2015.
 */
public class InteractListener implements Listener {

    /** I'd say this looks much pretier :) */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.hasBlock()){
            if (event.getClickedBlock().hasMetadata("ktarrandomchest")) {
                event.setCancelled(true); //cancel the chest inventory opening
                ChestWrapper chest = Loader.getChestWrapper(event.getClickedBlock());//get the wrapper associated with this
                if (event.getPlayer().getItemInHand() != null) {//check its not air in their hand
                    ItemStack key = event.getPlayer().getItemInHand();
                    if (KeyHandler.isKey(key, chest.getType()) && !chest.getInUse()) { //check if it is a key
                        if (!chest.getInUse()) {
                            LogOffListener.keys.put(event.getPlayer(), key); //add it to the listener
                            if (key.getAmount() > 1) {
                                event.getPlayer().getItemInHand().setAmount(key.getAmount() - 1);
                            } else
                                event.getPlayer().setItemInHand(null);
                            chest.use(event.getPlayer()); //Tell the chest to use
                        } else event.getPlayer().sendMessage("This chest in in use! Sorry");
                    } else event.getPlayer().sendMessage("That isn't a key, silly.");
                }
            }
        }
    }


}
