package me.ktar.randomchest.items;

import me.ktar.randomchest.utils.ItemFactory;

import org.bukkit.inventory.ItemStack;

public class ItemWrapper {

	private final ItemFactory item;
	private final String name;

	public ItemWrapper(ItemFactory item, String name){
		this.name = name;
		this.item = item;
	}

	public ItemStack getItem(){
		return item.getItemStack();
	}

	public String getName(){
		return name;
	}

}
