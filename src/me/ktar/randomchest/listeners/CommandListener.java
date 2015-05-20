package me.ktar.randomchest.listeners;

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

    public void createChest(Player player){

    }

    public boolean removeChest(Player player){

    }

    public boolean forceUseChest(Player player){

    }

    public boolean getKeyForChest(Player player){

    }

    public boolean

}
