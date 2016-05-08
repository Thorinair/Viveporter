package com.thorinair.viveporter.client;

import com.thorinair.viveporter.CommonProxy;
import com.thorinair.viveporter.client.renderer.RendererViveporter;
import com.thorinair.viveporter.init.Items;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author Thorinair   <thorinair@gmail.com>
 */

public class ClientProxy extends CommonProxy {

    /**
     * Handles preInit.
     */
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        // Set up custom rendering.
        setCustomRenderers();
    }

    /**
     * Handles init.
     */
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    /**
     * Handles postInit.
     */
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

    /**
     * Registers renderers for all block.
     */
    public static void setCustomRenderers() {
        MinecraftForgeClient.registerItemRenderer(Items.itemViveporter, new RendererViveporter());
    }
}
