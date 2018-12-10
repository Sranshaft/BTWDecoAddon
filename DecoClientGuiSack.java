package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class DecoClientGuiSack extends GuiContainer
{
	private DecoTileEntitySack m_AssociatedTileEntity;
	private int m_InventoryRows = 1;
	
	public DecoClientGuiSack(InventoryPlayer inventory, DecoTileEntitySack entity)
    {
        super(new DecoContainerSack(inventory, entity));
        this.m_AssociatedTileEntity = entity;
        
        short var3 = 222;
        int var4 = var3 - 108;
        this.ySize = var4 + this.m_InventoryRows * 18;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.fontRenderer.drawString("Sack", 8, 6, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/gui/container.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.m_InventoryRows * 18 + 17);
        this.drawTexturedModalRect(var4, var5 + this.m_InventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
}