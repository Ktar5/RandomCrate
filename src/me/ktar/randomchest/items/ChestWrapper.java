package me.ktar.randomchest.items;

import me.ktar.randomchest.utils.InventoryUtil;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChestWrapper {

	private ChestType type;
	private Player inUse;
    private ArmorStand stand;

    private static final int CYCLE_TICKS = 60;

	public ChestWrapper(ChestType type){
		this.type = type;
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
        if(InventoryUtil.howManyFreeSpaces(player) >= items.length){
            player.sendMessage("Your ");
            ItemStack st;
            st.getItemMeta().spigot().
        }
    }

    private void cycleItems(){
        /*
        ......shhh
         */
    }


}
