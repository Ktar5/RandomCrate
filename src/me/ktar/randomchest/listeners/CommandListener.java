package me.ktar.randomchest.listeners;

import me.ktar.randomchest.items.ChestType;
import me.ktar.randomchest.items.ChestWrapper;
import me.ktar.randomchest.storage.Loader;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Carter on 5/19/2015.
 */
public class CommandListener implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            switch(args.length){
                case 0:

                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    public void createChest(Player player, ChestType type){
        if(isLookingAtChest(player)){
            Loader.addChest(getLookingAt(player), type);
        }
        return false;
    }

    public boolean removeChest(Player player){
        ChestWrapper wrapper = isLookingAtRandomChest(player);
        if(wrapper != null){
            Loader.removeChestWrapper(wrapper.getLocation());
            return true;
        }
        return false;
    }

    public boolean forceUseChest(Player player){
        ChestWrapper wrapper = isLookingAtRandomChest(player);
        if(wrapper != null){
            wrapper.forceStopUse();
            wrapper.use(player);
            return true;
        }
        return false;
    }

    public ChestWrapper isLookingAtRandomChest(Player player){
        if(Loader.getChestWrapper(getLookingAt(player)) != null) {
            return Loader.getChestWrapper(getLookingAt(player));
        }else{
            return null;
        }
    }

    public boolean isLookingAtChest(Player player){
        return getLookingAt(player).getType().equals(Material.CHEST);
    }

    public Block getLookingAt(Player player){
        return player.getTargetBlock(null, 10);
    }

}
