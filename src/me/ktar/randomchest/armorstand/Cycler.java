package me.ktar.randomchest.armorstand;

import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.listeners.LogOffListener;
import me.ktar.randomchest.utils.FireworkUtil;
import me.ktar.randomchest.utils.InventoryUtil;
import me.ktar.randomchest.utils.RandomUtil;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by Carter on 5/23/2015.
 */
public class Cycler implements Runnable{


    private final Plugin plugin;
    private final List<Material> mats;
    private ChestWrapper wrapper;

    private int iterations = 0;
    private int spot = 0;
    private int last = 0;

    private int taskId;

    FireworkEffect ef = FireworkEffect.builder().trail(true).flicker(true).with(FireworkEffect.Type.BURST).withColor(Color.AQUA, Color.BLUE, Color.BLACK, Color.WHITE, Color.TEAL).build();

    public Cycler(Plugin plugin, ChestWrapper wrapper) {
        this.plugin = plugin;
        this.wrapper = wrapper;
        this.mats = wrapper.getType().getMaterials();
    }

    public void start() {
        long delay_before_starting = 10;
        long delay_between_restarting = 3;
        iterations = 7;
        // synchronous - thread safe
        taskId = plugin.getServer().getScheduler().runTaskTimer(plugin, this, delay_before_starting, delay_between_restarting).getTaskId();
    }

    @Override
    public void run() {
        if(spot == 0){
            spot = mats.size();
            iterations--;
            if(iterations < 0){
                StandHandler.cycle(Material.AIR, wrapper.getStand());
                plugin.getServer().getScheduler().cancelTask(taskId);
                FireworkUtil.playFirework(wrapper.getStand().getLocation().add(0,1,0), ef);
                ItemStack[] items = wrapper.getType().getRandomItems(); //generate the items that are going to be given
                if(!wrapper.getUser().isOnline()){wrapper.inUse = null; return;} //make sure player is still online
                if(InventoryUtil.howManyFreeSpaces(wrapper.getUser()) < items.length){//make sure they have enough space in their inventory
                    wrapper.getUser().sendMessage("Your inventory is too full");
                    LogOffListener.giveBackKey(wrapper.inUse);
                    wrapper.inUse = null; //no longer in use
                }
                else{
                    wrapper.inUse.getInventory().addItem(items); //add items if player has enough space
                    wrapper.inUse.updateInventory();//give player items
                    //ChestUtil.changeChestState(location, false, player);//close the chest
                    LogOffListener.removeIfIn(wrapper.inUse);
                    wrapper.inUse.sendMessage("Congratulations! You got " + items.length + " items!");
                    wrapper.inUse = null; //no longer in use
                }
                return;
            }
        }
        spot--;
        int next;
        do{
          next = RandomUtil.random().nextInt(mats.size());
        }while(next == last);
        last = next;
        wrapper.getLocation().getWorld().playSound(wrapper.getLocation(), Sound.NOTE_STICKS, 1f, 2f);
        StandHandler.cycle(mats.get(next), wrapper.getStand());
    }


}
