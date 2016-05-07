package com.thorinair.viveporter.item;

import com.thorinair.viveporter.Viveporter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Thorinair   <thorinair@gmail.com>
 */

public class ItemViveporter extends Item {

    // Item ID
    public static final String ID = "toolViveporter";

    /**
     * Constructor for the item.
     * @param itemName Unlocalized name for the item.
     */
    public ItemViveporter(String itemName) {
        setUnlocalizedName(itemName);
        setCreativeTab(Viveporter.tab);
        setTextureName(Viveporter.MODID + ":" + itemName);
    }

    /**
     * This is called when the item is used, before the block is activated.
     * @param stack The Item Stack
     * @param player The Player that used the item
     * @param world The Current World
     * @param x Target X Position
     * @param y Target Y Position
     * @param z Target Z Position
     * @param side The side of the target hit
     * @return Return true to prevent any further processing.
     */
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
            if (!world.isAirBlock(x, y, z) &&
                    world.isAirBlock(x, y + 1, z) &&
                    world.isAirBlock(x, y + 2, z)) {

                player.setPositionAndUpdate(x + 0.5, y + 1, z + 0.5);
            }

        return false;
    }
}
