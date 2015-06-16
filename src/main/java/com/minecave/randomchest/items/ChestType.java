package com.minecave.randomchest.items;

import com.minecave.randomchest.utils.ItemFactory;
import com.minecave.randomchest.utils.RandomCollection;
import com.minecave.randomchest.utils.RandomUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChestType {

	private RandomCollection<ItemFactory> items;
	private final int minItems, maxItems;
	private final String name;

	public ChestType(int minItems, int maxItems, String name) {
		this.items = new RandomCollection<>(RandomUtil.random());
		this.minItems = minItems;
		this.name = name.toUpperCase();
		this.maxItems = maxItems;
	}

	public String getName(){
		return this.name;
	}

	public ItemStack[] getRandomItems(){
		int items = RandomUtil.random().nextInt((maxItems - minItems) + 1) + minItems;
        List<ItemStack> itemz = new ArrayList<>();
        while(items > 0){
			itemz.add(this.items.next().getItemStack());
			items--;
		}
		return itemz.toArray(new ItemStack[itemz.size()]);
	}

	public List<Material> getMaterials(){
		return items.values().stream().map(fac -> fac.getItemStack().getType()).collect(Collectors.toList());
	}

	public void add(ItemFactory item, int chance){
		this.items.add(chance, item);
	}


}
