package com.thorinair.viveporter.item;

import com.thorinair.viveporter.Viveporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

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
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            Vec3 vec = player.getLookVec();
            vec.xCoord *= 32;
            vec.yCoord *= 32;
            vec.zCoord *= 32;
            System.out.println(vec.xCoord + ", " + vec.yCoord + ", " + vec.zCoord);
            
        }

        return stack;
    }
}
