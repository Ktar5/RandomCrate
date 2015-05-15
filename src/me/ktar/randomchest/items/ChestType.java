package me.ktar.randomchest.items;

import me.ktar.randomchest.utils.RandomCollection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ChestType {

	private RandomCollection<ItemWrapper> test;
	private final String name;
	private final int minChance, maxChance;

	public ChestType(Map<ItemWrapper, Double> itemsAndChances, String name, int minChance, int maxChance) {
		this.itemsAndChances = itemsAndChances;
		this.name = name;
		this.minChance = minChance;
		this.maxChance = maxChance;
	}

	protected ItemStack[] getRandomItems(){

	}


}
