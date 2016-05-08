package com.thorinair.viveporter.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

/**
 * @author Thorinair   <thorinair@gmail.com>
 */

public class EventHandler {
    /**
     * Fired when player logs in.
     */
    @SubscribeEvent
    public void onPlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        // If this is the player on server side...
        if (event.player instanceof EntityPlayerMP && Config.cfgGeneralLogin) {
            EntityPlayerMP player = (EntityPlayerMP) event.player;

            if (!player.inventory.hasItem(Items.itemViveporter))
                player.inventory.addItemStackToInventory(new ItemStack(Items.itemViveporter));
        }
    }

    /**
     * Fired when player respawns.
     */
    @SubscribeEvent
    public void onPlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        // If this is the player on server side...
        if (event.player instanceof EntityPlayerMP && Config.cfgGeneralRespawn) {
            EntityPlayerMP player = (EntityPlayerMP) event.player;

            if (!player.inventory.hasItem(Items.itemViveporter))
                player.inventory.addItemStackToInventory(new ItemStack(Items.itemViveporter));
        }
    }
}
