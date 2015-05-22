package me.ktar.randomchest;

import me.ktar.randomchest.listeners.CommandListener;
import me.ktar.randomchest.listeners.InteractListener;
import me.ktar.randomchest.listeners.LogOffListener;
import me.ktar.randomchest.storage.CustomConfig;
import me.ktar.randomchest.storage.Loader;
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
		chests = new CustomConfig(getDataFolder(), "chests");
		items = new CustomConfig(getDataFolder(), "items");
		messages = new CustomConfig(getDataFolder(), "messages");
		chesttypes = new CustomConfig(getDataFolder(), "chesttypes");

        Loader.load();

        Bukkit.getServer().getPluginManager().registerEvents(new LogOffListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getCommand("rc").setExecutor(new CommandListener());
    }

	@Override
	public void onDisable(){
        Loader.save();
		instance = null;
	}

	public static RandomChest getInstance(){
		return instance;
	}

}
