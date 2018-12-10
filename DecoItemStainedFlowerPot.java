package net.minecraft.src;

public class DecoItemStainedFlowerPot extends FCItemPlacesAsBlock
{
	private String m_Color;
	
	public DecoItemStainedFlowerPot(int id, int blockID, String color)
	{
		super(id, blockID);
		
		this.setUnlocalizedName("decoItemStainedFlowerPot." + color);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		this.m_Color = color;
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister0. This
     * is the only chance you get to register icons0.
     */
	public void registerIcons(IconRegister register)
	{
		this.itemIcon = register.registerIcon("decoItemStainedFlowerPot_" + this.m_Color);
	}
}
