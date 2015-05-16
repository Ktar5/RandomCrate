package me.ktar.randomchest.items;

import me.ktar.randomchest.utils.ItemFactory;
import me.ktar.randomchest.utils.RandomCollection;
import me.ktar.randomchest.utils.RandomUtil;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChestType {

	private RandomCollection<ItemFactory> items;
	private final String name;
	private final int minItems, maxItems;

	public ChestType(Map<ItemFactory, Double> items, String name, int minItems, int maxItems) {
		this.items = new RandomCollection<ItemFactory>(RandomUtil.random());
		for(Map.Entry<ItemFactory, Double> entry : items.entrySet())
			this.items.add(entry.getValue(), entry.getKey());
		this.name = name;
		this.minItems = minItems;
		this.maxItems = maxItems;
	}

	protected ItemStack[] getRandomItems(){
		int items = RandomUtil.random().nextInt((maxItems - minItems) + 1) + minItems;
        List<ItemFactory> itemz = new ArrayList<>();
        while(items > 0){
             this.items.next();
		}
	}


}
