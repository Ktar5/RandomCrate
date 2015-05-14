package me.ktar.randomchest.items;

import java.util.HashMap;
import java.util.Map;

public class ChestType {

	private Map<ItemWrapper, Double> itemsAndChances = new HashMap<>();
	private final String name;
	private final int minChance, maxChance;

	private ChestType(Map<ItemWrapper, Double> itemsAndChances, String name, int minChance, int maxChance) {
		this.itemsAndChances = itemsAndChances;
		this.name = name;
		this.minChance = minChance;
		this.maxChance = maxChance;
	}

	public ItemStack[] getRandomItems(){

	}


}
