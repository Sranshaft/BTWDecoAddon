package net.minecraft.src;

public class DecoBlockDebug extends Block 
{
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockDebug(int id)
	{
		super(id, Material.rock);
		
		this.setUnlocalizedName("decoBlockDebug");
		this.setHardness(1.5F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return this.m_IconByMetadataArray[side];
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        this.m_IconByMetadataArray = new Icon[6];
        
        for (int index = 0; index < 6; index++)
        {
        	this.m_IconByMetadataArray[index] = register.registerIcon("debug_side_" + index);
        }
    }
}
