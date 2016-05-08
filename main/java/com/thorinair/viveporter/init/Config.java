package com.thorinair.viveporter.init;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

/**
 * @author Thorinair   <celestek@openmailbox.org>
 */

public class Config {

    // General
    public static boolean cfgGeneralLogin = true;
    public static boolean cfgGeneralRespawn = true;
    public static boolean cfgGeneralDebugging = false;

    public static void initializeConfig(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        // General
        config.setCategoryComment(Configuration.CATEGORY_GENERAL, "General configuration of the mod.");
        cfgGeneralLogin = config.getBoolean("Give Viveporter on login", Configuration.CATEGORY_GENERAL, cfgGeneralLogin, "If set to true, players will be automatically given a Viveporter on login if they don't already have one.\n");
        cfgGeneralRespawn = config.getBoolean("Give Viveporter on respawn", Configuration.CATEGORY_GENERAL, cfgGeneralRespawn, "If set to true, players will be automatically given a new Viveporter after they die.\n");
        cfgGeneralDebugging = config.getBoolean("Write debug to console", Configuration.CATEGORY_GENERAL, cfgGeneralDebugging, "If set to true, player's teleportations will be logged to console.\n");

        config.save();
    }
}
