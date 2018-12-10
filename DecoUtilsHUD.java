package net.minecraft.src;

import net.minecraft.client.Minecraft;

import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

public final class DecoUtilsHUD
{
    private static final Minecraft mc = Minecraft.getMinecraft();
    
    private static final int m_textureWidth = 32;
    private static final int m_textureHeight = 32;
    private static final int m_topBorder = 8;
    private static final int m_sideBorder = 8;
    private static final int m_backgroundYPos = 166;
    private static final float m_alpha = 0.85F;
    
    private static int[] colorCodes = new int[] { 0, 170, 43520, 43690, 11141120, 11141290, 16755200, 11184810, 5592405, 5592575, 5635925, 
    		5636095, 16733525, 16733695, 16777045, 16777215, 0, 42, 10752, 10794, 2752512, 2752554, 2763264, 2763306, 1381653, 1381695, 
    		1392405, 1392447, 4134165, 4134207, 4144917, 4144959 };

    /**
     * Displays debuff/potion effects that are currently being applied to the player
     */
    public static void displayDebuffEffects()
    {
    	if (Minecraft.getMinecraft().thePlayer == null)
    		return;
    	
    	ScaledResolution var5 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int canvasWidth = var5.getScaledWidth();
    	int currentXPosition = canvasWidth - m_sideBorder - m_textureWidth;
    	
        Collection activeEffects = mc.thePlayer.getActivePotionEffects();

        if (!activeEffects.isEmpty())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            
            for (Iterator index = activeEffects.iterator(); index.hasNext(); currentXPosition -= m_textureWidth + m_sideBorder)
            {
                PotionEffect potionEffect = (PotionEffect)index.next();
                Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
                
                int backgroundXPos = 150;
                
                if (potion.isBadEffect())
                	backgroundXPos = 182;
                
                // BIT GLITCHY : NEEDS A BETTER WAY
                
                /*String durationRemaining = potion.getDurationString(potionEffect);
                int seconds = Integer.parseInt(durationRemaining.substring((int)(durationRemaining.length() - 2)));
                
                if ((seconds < 10) && (seconds % 2 == 0))
                {
                	GL11.glEnable(GL11.GL_BLEND);
                	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
                	GL11.glColor4f(m_alpha, m_alpha, m_alpha, m_alpha);
                }
                else
                	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);*/
                
                Minecraft.getMinecraft().renderEngine.bindTexture("/gui/inventory.png");
                drawTexturedModalRect(currentXPosition, m_topBorder, backgroundXPos, m_backgroundYPos, m_textureWidth, m_textureHeight);

                if (potion.hasStatusIcon())
                {
                    int potionIconIndex = potion.getStatusIconIndex();
                    drawTexturedModalRect(currentXPosition + 7, m_topBorder + 7, 0 + potionIconIndex % 8 * 18, 198 + potionIconIndex / 8 * 18, 18, 18);
                }

                String var11 = StatCollector.translateToLocal(potion.getName());
                
                GL11.glDisable(GL11.GL_BLEND);
            }
            
            GL11.glEnable(GL11.GL_BLEND);
        }
    }
    
    public static int getColorCode(char c, boolean isLighter)
    {
        return colorCodes[isLighter ? "0123456789abcdef".indexOf(c) : "0123456789abcdef".indexOf(c) + 16];
    }
    
    public static void drawTexturedModalRect(int x, int y, int u, int v, int width, int height)
    {
    	mc.ingameGUI.drawTexturedModalRect(x, y, u, v, width, height);
    }
}
