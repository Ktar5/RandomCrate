package me.ktar.randomchest.items;

import me.ktar.randomchest.utils.ItemFactory;

import org.bukkit.inventory.ItemStack;

public class ItemWrapper {

	private ItemFactory item;
	//private int chance;

	public ItemWrapper(ItemFactory item/*, int chance*/){
		this.item = item;
		//this.chance = chance;
	}

	public ItemStack getItem(){
		return item.getItemStack();
	}

	//public int getChance(){
	//	return chance;
	//}

}
