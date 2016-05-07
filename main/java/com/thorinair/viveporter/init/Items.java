package com.thorinair.viveporter.init;

import com.thorinair.viveporter.item.ItemViveporter;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * @author Thorinair   <celestek@openmailbox.org>
 */

public class Items {

    // Viveporter
    public static Item itemViveporter;

    /**
     * Initializes all items and adds them to GameRegistry.
     */
    public static void initializeItems() {
        // Viveporter
        itemViveporter = new ItemViveporter(ItemViveporter.ID);
        GameRegistry.registerItem(itemViveporter, ItemViveporter.ID);
    }
}
