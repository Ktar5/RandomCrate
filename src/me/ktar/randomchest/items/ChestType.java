package me.ktar.randomchest.items;

import me.ktar.randomchest.utils.ItemFactory;
import me.ktar.randomchest.utils.RandomCollection;
import me.ktar.randomchest.utils.RandomUtil;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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

	protected ItemStack[] getRandomItems(){
		int items = RandomUtil.random().nextInt((maxItems - minItems) + 1) + minItems;
        List<ItemStack> itemz = new ArrayList<>();
        while(items > 0){
			itemz.add(this.items.next().getItemStack());
			items--;
		}
		return itemz.toArray(new ItemStack[itemz.size()]);
	}

	public void add(ItemFactory item, int chance){
		this.items.add(chance, item);
	}


}
