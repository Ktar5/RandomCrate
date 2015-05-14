package me.ktar.randomchest;

import me.ktar.randomchest.storage.CustomConfig;

import org.bukkit.plugin.java.JavaPlugin;

public class RandomChest extends JavaPlugin{

	private static RandomChest instance = null;

	public CustomConfig chests, items, messages;

	@Override
	public void onLoad(){
		instance = this;
	}

	@Override
	public void onEnable(){
		chests = new CustomConfig(getDataFolder(), "chests");
		items = new CustomConfig(getDataFolder(), "items");
		messages = new CustomConfig(getDataFolder(), "messages");
	}

	@Override
	public void onDisable(){
		instance = null;
	}

	public static RandomChest getInstance(){
		return instance;
	}

}
