package net.minecraft.src;

public class DecoItemBell extends FCItemPlacesAsBlock 
{
	private String m_Type;
	
	public DecoItemBell(int id, int blockID, String type)
	{
		super(id, blockID);

		this.setUnlocalizedName("decoItemBell." + type);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		this.m_Type = type;
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister0. This
     * is the only chance you get to register icons0.
     */
	public void registerIcons(IconRegister register)
	{
		this.itemIcon = register.registerIcon("decoItemBell_" + this.m_Type);
	}
}