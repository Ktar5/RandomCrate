package me.ktar.randomchest.armorstand;

import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.utils.RandomUtil;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by Carter on 5/23/2015.
 */
public class Cycler implements Runnable{


    private final Plugin plugin;
    private final List<Material> mats;
    private final ArmorStand stand;

    private int iterations = 0;
    private int spot = 0;
    private int last = 0;

    private int taskId;

    public Cycler(Plugin plugin, ChestWrapper wrapper) {
        this.plugin = plugin;
        this.stand = wrapper.getStand();
        this.mats = wrapper.getType().getMaterials();
    }

    public void start() {
        long delay_before_starting = 10;
        long delay_between_restarting = 2;
        iterations = 10;
        // synchronous - thread safe
        taskId = plugin.getServer().getScheduler().runTaskTimer(plugin, this, delay_before_starting, delay_between_restarting).getTaskId();
    }

    @Override
    public void run() {

        if(spot == 0){
            spot = mats.size();
            iterations--;
            if(iterations < 0) plugin.getServer().getScheduler().cancelTask(taskId);
        }

        spot--;
        int next = 1;
        while(next == last){
            next = RandomUtil.random().nextInt(mats.size());
        }
        StandHandler.cycle(mats.get(next), stand);
    }


}
