package com.thorinair.viveporter;

import com.thorinair.viveporter.init.Items;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.item.Item;

/**
 * @author Thorinair   <thorinair@gmail.com>
 */

@Mod(modid = Viveporter.MODID, name = Viveporter.MODNAME, version = Viveporter.VERSION)
public class Viveporter
{
    public static final String MODID = "viveporter";
    public static final String MODNAME = "Viveporter";
    public static final String VERSION = "0.1.0";

    @SidedProxy(clientSide = "com.thorinair.viveporter.client.ClientProxy", serverSide = "com.thorinair.viveporter.server.ServerProxy")
    public static CommonProxy proxy;

    // Set up creative tabs.
    public static CreativeTabs tab = new CreativeTabs("tab") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Items.itemViveporter;
        }
    };

    /**
     * Handles preInit.
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    /**
     * Handles load.
     */
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    /**
     * Handles postInit.
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
