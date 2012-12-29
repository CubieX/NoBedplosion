package com.github.CubieX.NoBedplosion;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class NoBedplosion extends JavaPlugin
{
   private NoBedplosionConfigHandler cHandler = null;
   private NoBedplosionEntityListener eListener = null;
   private NoBedplosionCommandHandler comHandler = null;

   private NoBedplosion plugin;
   private static final Logger log = Logger.getLogger("Minecraft");
   public static String logPrefix = "[NoBedplosion] "; // Prefix to go in front of all log entries

   @Override
   public void onEnable()
   {     
      this.plugin = this;       

      log.info(getDescription().getName() + " version " + getDescription().getVersion() + " is enabled!");

      cHandler = new NoBedplosionConfigHandler(this, log);       
      comHandler = new NoBedplosionCommandHandler(this, log, cHandler);
      getCommand("nbp").setExecutor(comHandler);
      eListener = new NoBedplosionEntityListener(this,log);        
   }   

   @Override
   public void onDisable()
   {       
      cHandler = null;       
      comHandler = null;
      eListener = null;
      log.info(getDescription().getName() + " version " + getDescription().getVersion() + " is disabled!");
   }   
}
