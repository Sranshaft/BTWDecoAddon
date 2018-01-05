package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class DecoClientGuiCompostBin extends GuiContainer
{
	static final int iMillStoneGuiHeight = 158;
    static final int iMillStoneFireIconHeight = 12;
    private DecoTileEntityCompostBin m_AssociatedTileEntity;

    public DecoClientGuiCompostBin(InventoryPlayer player, DecoTileEntityCompostBin entity)
    {
        super(new DecoContainerCompostBin(player, entity));
        this.ySize = 158;
        this.m_AssociatedTileEntity = entity;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.fontRenderer.drawString("Compost Bin", 60, 6, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/gui/decoGUICompostBin.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);

        if (this.m_AssociatedTileEntity.IsDecomposing())
        {
            int var6 = this.m_AssociatedTileEntity.getGrindProgressScaled(12);
            this.drawTexturedModalRect(var4 + 80, var5 + 18 + 12 - var6, 176, 12 - var6, 14, var6 + 2);
        }
    }
}
