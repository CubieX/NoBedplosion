package com.github.CubieX.NoBedplosion;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoBedplosionCommandHandler implements CommandExecutor
{
   private final NoBedplosion plugin;
   private final NoBedplosionConfigHandler cHandler;

   public NoBedplosionCommandHandler(NoBedplosion plugin, NoBedplosionConfigHandler cHandler)
   {
      this.plugin = plugin;
      this.cHandler = cHandler;
   }

   @Override
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
   {
      Player player = null;
      if (sender instanceof Player) 
      {
         player = (Player) sender;
      }

      if (cmd.getName().equalsIgnoreCase("nbp"))
      { // If the player typed /plague then do the following... (can be run from console also)
         if (args.length == 0)
         { //no arguments, so help will be displayed
            return false;
         }
         if (args.length==1)
         {
            if (args[0].equalsIgnoreCase("version")) // argument 0 is given and correct
            {            
               sender.sendMessage(ChatColor.GREEN + "This server is running " + plugin.getDescription().getName() + " " + plugin.getDescription().getVersion());                    
               return true;
            }    
            if (args[0].equalsIgnoreCase("reload")) // argument 0 is given and correct
            {            
               if(sender.hasPermission("nobedplosion.admin"))
               {
                  cHandler.reloadConfig(sender);                        
                  return true;
               }
               else
               {
                  sender.sendMessage(ChatColor.RED + "You do not have sufficient permission to reload " + plugin.getDescription().getName() + "!");
               }
            }

         }           
         else
         {
            sender.sendMessage(ChatColor.YELLOW + "Falsche Anzahl an Parametern.");
         }         
      }         
      return false; // if false is returned, the help for the command stated in the plugin.yml will be displayed to the player
   }
}
