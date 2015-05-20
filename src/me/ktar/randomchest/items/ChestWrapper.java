package me.ktar.randomchest.items;

import me.ktar.randomchest.utils.ChestUtil;
import me.ktar.randomchest.utils.InventoryUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
        this.inUse = player;
        cycleItems();
        ItemStack[] items = this.type.getRandomItems();
        if(!player.isOnline()){inUse = null; return;}
        if(InventoryUtil.howManyFreeSpaces(player) < items.length){
            player.sendMessage("Your inventory is too full");
            inUse = null;
        }else {
            player.getInventory().addItem(items);
            player.updateInventory();
            ChestUtil.changeChestState(location, false, player);
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
