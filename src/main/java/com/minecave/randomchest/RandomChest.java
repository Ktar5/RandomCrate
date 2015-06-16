package com.minecave.randomchest;

import com.minecave.randomchest.storage.CustomConfig;
import com.minecave.randomchest.listeners.CommandListener;
import com.minecave.randomchest.listeners.InteractListener;
import com.minecave.randomchest.listeners.LogOffListener;
import com.minecave.randomchest.storage.Loader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomChest extends JavaPlugin{

	private static RandomChest instance = null;

	public static CustomConfig chests, items, messages, chesttypes;

	@Override
	public void onLoad(){
		instance = this;
	}

	@Override
	public void onEnable(){
		chests = new CustomConfig(getDataFolder(), "chests.yml");
		items = new CustomConfig(getDataFolder(), "items.yml");
		messages = new CustomConfig(getDataFolder(), "messages.yml");
		chesttypes = new CustomConfig(getDataFolder(), "chesttypes.yml");

        Loader.load();

        Bukkit.getServer().getPluginManager().registerEvents(new LogOffListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getCommand("rc").setExecutor(new CommandListener());
    }

	@Override
	public void onDisable(){
        Loader.unload();
		instance = null;
	}

	public static RandomChest getInstance(){
		return instance;
	}

}
