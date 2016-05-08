package com.thorinair.viveporter;

import com.thorinair.viveporter.init.EventHandler;
import com.thorinair.viveporter.init.Items;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Thorinair   <thorinair@gmail.com>
 */

public class CommonProxy {

    /**
     * Handles preInit.
     */
    public void preInit(FMLPreInitializationEvent e) {

        Items.initializeItems();
    }

    /**
     * Handles init.
     */
    public void init(FMLInitializationEvent e) {

        FMLCommonHandler.instance().bus().register(new EventHandler());
    }

    /**
     * Handles postInit.
     */
    public void postInit(FMLPostInitializationEvent e) {

    }
}
