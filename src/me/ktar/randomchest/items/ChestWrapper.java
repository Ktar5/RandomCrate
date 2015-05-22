package me.ktar.randomchest.items;

import me.ktar.randomchest.RandomChest;
import me.ktar.randomchest.listeners.LogOffListener;
import me.ktar.randomchest.utils.ChestUtil;
import me.ktar.randomchest.utils.InventoryUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ChestWrapper {

	private ChestType type;
	private Player inUse;
    private Location location;
    //private ArmorStand stand;

    private static final int CYCLE_TICKS = 60;

	public ChestWrapper(ChestType type, Location location){
		this.type = type;
        this.location = location;
		inUse = null;
        location.getBlock().setMetadata("ktarrandomchest", new FixedMetadataValue(RandomChest.getInstance(), true));
	}

    public ChestType getType(){
        return type;
    }

	public boolean getInUse(){
		return inUse != null;
	}

	public Player getUser(){
		return inUse;
	}

	public void forceStopUse(){
		this.inUse = null;
	}

    public void use(Player player){
        this.inUse = player; //make the chest become in use
        ChestUtil.changeChestState(getLocation(), true, player); //open the chest animation
        cycleItems(); //make fancy display to the player
        ItemStack[] items = this.type.getRandomItems(); //generate the items that are going to be given
        if(!player.isOnline()){inUse = null; return;} //make sure player is still online
        if(InventoryUtil.howManyFreeSpaces(player) < items.length){//make sure they have enough space in their inventory
            player.sendMessage("Your inventory is too full");
            inUse = null;
        }else{
            player.getInventory().addItem(items); //add items if player has enough space
            player.updateInventory();//give player items
            ChestUtil.changeChestState(location, false, player);//close the chest
            LogOffListener.removeIfIn(player);
            this.inUse = null;
            player.sendMessage("Congratulations! You got " + items.length + " items!");
        }
    }

    public Location getLocation(){
        return this.location.getBlock().getLocation();
    }

    private void cycleItems(){
        /*
        ......shhh
         */
    }


}
