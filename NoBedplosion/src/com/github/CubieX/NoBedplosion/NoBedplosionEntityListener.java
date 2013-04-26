package com.github.CubieX.NoBedplosion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class NoBedplosionEntityListener implements Listener
{
   private final NoBedplosion plugin;

   //Constructor
   public NoBedplosionEntityListener(NoBedplosion plugin)
   {
      this.plugin = plugin;

      plugin.getServer().getPluginManager().registerEvents(this, plugin);
   }
   /*
   Event Priorities

There are six priorities in Bukkit

    EventPriority.HIGHEST
    EventPriority.HIGH
    EventPriority.NORMAL
    EventPriority.LOW
    EventPriority.LOWEST
    EventPriority.MONITOR 

They are called in the following order

    EventPriority.LOWEST 
    EventPriority.LOW
    EventPriority.NORMAL
    EventPriority.HIGH
    EventPriority.HIGHEST
    EventPriority.MONITOR 

    All Events can be cancelled. Plugins with a high prio for the event can cancel or uncancel earlier issued lower prio plugin actions.
    MONITOR level should only be used, if the outcome of an event is NOT altered from this plugin and if you want to have the final state of the event.
    If the outcome gets changed (i.e. event gets cancelled, uncancelled or actions taken that can lead to it), a prio from LOWEST to HIGHEST must be used!

    The option "ignoreCancelled" if set to "true" says, that the plugin will not get this event if it has been cancelled beforehand from another plugin.
    */

   //================================================================================================
   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true) // event has MONITOR priority and will be skipped if it has been cancelled before
   public void onPlayerInteract(PlayerInteractEvent e)
   {
      Player p = e.getPlayer();
      if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
      {
         if(e.getClickedBlock().getType().equals(Material.BED_BLOCK))
         {            
            if(p.getLocation().getWorld().getEnvironment().equals(World.Environment.NETHER))
            {
               p.sendMessage(ChatColor.YELLOW + plugin.getConfig().getString("DenyBedUsageMessage"));

               for(Player cyclePlayer: plugin.getServer().getOnlinePlayers()) {

                  if((cyclePlayer.hasPermission("nobedplosion.notify")) &&
                        (p != cyclePlayer))
                  {
                     cyclePlayer.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.WHITE + " " + plugin.getConfig().getString("NotifyOfTryMessage"));
                  }
               }
               e.setCancelled(true);
            }
         }            
      }
   }
}
