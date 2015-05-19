package me.ktar.randomchest.items;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChestWrapper {

	private ChestType type;
	private Player inUse;
    private ArmorStand stand;

	public ChestWrapper(ChestType type){
		this.type = type;
		inUse = null;
	}

	public ItemStack[] getRandomItems(){
		return type.getRandomItems();
	}

	public boolean getInUse(){
		return inUse != null;
	}

	public Player getUser(){
		return inUse;
	}

	public void stopUse(){
		this.inUse = null;
	}

    public void cycleItems(){

    }


}
