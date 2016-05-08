package com.thorinair.viveporter.client.renderer;

import com.thorinair.viveporter.init.Items;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * @author Thorinair   <thorinair@gmail.com>
 */

public class RendererViveporter implements IItemRenderer {
    private static RenderItem renderItem = new RenderItem();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type) {
            case ENTITY:
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        switch (type) {
            case ENTITY: {
                return (helper == ItemRendererHelper.ENTITY_BOBBING ||
                        helper == ItemRendererHelper.ENTITY_ROTATION ||
                        helper == ItemRendererHelper.BLOCK_3D);
            }
            case EQUIPPED: {
                return (helper == ItemRendererHelper.BLOCK_3D ||
                        helper == ItemRendererHelper.EQUIPPED_BLOCK);
            }
            case EQUIPPED_FIRST_PERSON: {
                return (helper == ItemRendererHelper.EQUIPPED_BLOCK);
            }
            case INVENTORY: {
                return (helper == ItemRendererHelper.INVENTORY_BLOCK);
            }
            default: {
                return false;
            }
        }
    }

    private enum TransformationTypes {NONE, DROPPED, INVENTORY, THIRDPERSONEQUIPPED};

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        double range = 22.0;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();

        // adjust rendering space to match what caller expects
        TransformationTypes transformationToBeUndone = TransformationTypes.NONE;
        switch (type) {
            case EQUIPPED: {
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                GL11.glRotatef(-0.5F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-46.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(110.5F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(-0.475F, -0.345F, -0.5F);
                transformationToBeUndone = TransformationTypes.THIRDPERSONEQUIPPED;
                break;
            }

            case EQUIPPED_FIRST_PERSON: {
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-42.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(2.5F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                break; // caller expects us to render over [0,0,0] to [1,1,1], no transformation necessary
            }
            case INVENTORY: {  // caller expects [-0.5, -0.5, -0.5] to [0.5, 0.5, 0.5]
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                transformationToBeUndone = TransformationTypes.INVENTORY;
                break;
            }
            case ENTITY: {
                // translate our coordinates and scale so that [0,0,0] to [1,1,1] translates to the [-0.25, -0.25, -0.25] to [0.25, 0.25, 0.25] expected by the caller.
                GL11.glScalef(0.5F, 0.5F, 0.5F);
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                transformationToBeUndone = TransformationTypes.DROPPED;
                break;
            }
            default:
                break; // never here
        }

        // ItemLampshade.getSpriteNumber() has been overridden to return 0, which lets us use the block texture instead of an item texture
        IIcon icon = Items.itemViveporter.getIcon(new ItemStack(Items.itemViveporter), 0);



        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(0.375, 0.375, 0.125, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
        tessellator.addVertexWithUV(0.375, 0.625, 0.125, icon.getInterpolatedU(4), icon.getInterpolatedV(4));
        tessellator.addVertexWithUV(0.625, 0.625, 0.125, icon.getInterpolatedU(8), icon.getInterpolatedV(4));
        tessellator.addVertexWithUV(0.625, 0.375, 0.125, icon.getInterpolatedU(8), icon.getInterpolatedV(0));

        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.addVertexWithUV(0.625, 0.375, 0.875, icon.getInterpolatedU(12), icon.getInterpolatedV(4));
        tessellator.addVertexWithUV(0.625, 0.625, 0.875, icon.getInterpolatedU(12), icon.getInterpolatedV(0));
        tessellator.addVertexWithUV(0.375, 0.625, 0.875, icon.getInterpolatedU(8), icon.getInterpolatedV(0));
        tessellator.addVertexWithUV(0.375, 0.375, 0.875, icon.getInterpolatedU(8), icon.getInterpolatedV(4));

        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.625, 0.625, 0.125, icon.getInterpolatedU(4), icon.getInterpolatedV(4));
        tessellator.addVertexWithUV(0.625, 0.625, 0.875, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
        tessellator.addVertexWithUV(0.625, 0.375, 0.875, icon.getInterpolatedU(0), icon.getInterpolatedV(16));
        tessellator.addVertexWithUV(0.625, 0.375, 0.125, icon.getInterpolatedU(0), icon.getInterpolatedV(4));

        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.375, 0.625, 0.125, icon.getInterpolatedU(4), icon.getInterpolatedV(4));
        tessellator.addVertexWithUV(0.375, 0.625, 0.875, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
        tessellator.addVertexWithUV(0.625, 0.625, 0.875, icon.getInterpolatedU(8), icon.getInterpolatedV(16));
        tessellator.addVertexWithUV(0.625, 0.625, 0.125, icon.getInterpolatedU(8), icon.getInterpolatedV(4));

        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.375, 0.375, 0.125, icon.getInterpolatedU(12), icon.getInterpolatedV(4));
        tessellator.addVertexWithUV(0.375, 0.375, 0.875, icon.getInterpolatedU(12), icon.getInterpolatedV(16));
        tessellator.addVertexWithUV(0.375, 0.625, 0.875, icon.getInterpolatedU(8), icon.getInterpolatedV(16));
        tessellator.addVertexWithUV(0.375, 0.625, 0.125, icon.getInterpolatedU(8), icon.getInterpolatedV(4));

        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        tessellator.addVertexWithUV(0.625, 0.375, 0.125, icon.getInterpolatedU(16), icon.getInterpolatedV(4));
        tessellator.addVertexWithUV(0.625, 0.375, 0.875, icon.getInterpolatedU(16), icon.getInterpolatedV(16));
        tessellator.addVertexWithUV(0.375, 0.375, 0.875, icon.getInterpolatedU(12), icon.getInterpolatedV(16));
        tessellator.addVertexWithUV(0.375, 0.375, 0.125, icon.getInterpolatedU(12), icon.getInterpolatedV(4));

        switch (type) {
            case EQUIPPED_FIRST_PERSON:
            case EQUIPPED: {
                tessellator.draw();
                tessellator.startDrawingQuads();
                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_LIGHTING);
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);

                tessellator.addVertexWithUV(0.46875, 0.46875, 0 - range, icon.getInterpolatedU(1), icon.getInterpolatedV(0));
                tessellator.addVertexWithUV(0.46875, 0.46875, 0.5, icon.getInterpolatedU(1), icon.getInterpolatedV(1));
                tessellator.addVertexWithUV(0.46875, 0.53125, 0.5, icon.getInterpolatedU(0), icon.getInterpolatedV(1));
                tessellator.addVertexWithUV(0.46875, 0.53125, 0 - range, icon.getInterpolatedU(0), icon.getInterpolatedV(0));

                tessellator.addVertexWithUV(0.53125, 0.46875, 0 - range, icon.getInterpolatedU(0), icon.getInterpolatedV(0));
                tessellator.addVertexWithUV(0.53125, 0.46875, 0.5, icon.getInterpolatedU(0), icon.getInterpolatedV(1));
                tessellator.addVertexWithUV(0.46875, 0.46875, 0.5, icon.getInterpolatedU(1), icon.getInterpolatedV(1));
                tessellator.addVertexWithUV(0.46875, 0.46875, 0 - range, icon.getInterpolatedU(1), icon.getInterpolatedV(0));

                tessellator.addVertexWithUV(0.53125, 0.53125, 0 - range, icon.getInterpolatedU(0), icon.getInterpolatedV(0));
                tessellator.addVertexWithUV(0.53125, 0.53125, 0.5, icon.getInterpolatedU(0), icon.getInterpolatedV(1));
                tessellator.addVertexWithUV(0.53125, 0.46875, 0.5, icon.getInterpolatedU(1), icon.getInterpolatedV(1));
                tessellator.addVertexWithUV(0.53125, 0.46875, 0 - range, icon.getInterpolatedU(1), icon.getInterpolatedV(0));

                tessellator.addVertexWithUV(0.46875, 0.53125, 0 - range, icon.getInterpolatedU(1), icon.getInterpolatedV(0));
                tessellator.addVertexWithUV(0.46875, 0.53125, 0.5, icon.getInterpolatedU(1), icon.getInterpolatedV(1));
                tessellator.addVertexWithUV(0.53125, 0.53125, 0.5, icon.getInterpolatedU(0), icon.getInterpolatedV(1));
                tessellator.addVertexWithUV(0.53125, 0.53125, 0 - range, icon.getInterpolatedU(0), icon.getInterpolatedV(0));

                GL11.glPopMatrix();
                break;
            }
            case INVENTORY:
            case ENTITY:
                break;
            default:
                break;
        }

        tessellator.draw();

        switch (transformationToBeUndone) {
            case NONE: {
                break;
            }
            case DROPPED: {
                GL11.glTranslatef(0.5F, 0.5F, 0.0F);
                GL11.glScalef(2.0F, 2.0F, 2.0F);
                break;
            }
            case INVENTORY: {
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                break;
            }
            case THIRDPERSONEQUIPPED: {
                GL11.glDisable(GL11.GL_CULL_FACE);
            }
            default:
                break;
        }
    }

}
