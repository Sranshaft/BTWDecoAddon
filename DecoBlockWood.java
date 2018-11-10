package net.minecraft.src;

import java.util.List;

public class DecoBlockWood extends FCBlockPlanks
{
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockWood(int id)
	{
        super(id);
        
        this.setUnlocalizedName("wood");
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		DecoAddonManager.register(this, DecoUtilsStrings.WOOD_TAGS, DecoUtilsStrings.WOOD_NAMES, " Planks");
	}
	
	/**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata)
    {
        return metadata;
    }
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	return (metadata < 0) || (metadata >= this.m_IconByMetadataArray.length) ? this.m_IconByMetadataArray[0] : this.m_IconByMetadataArray[metadata];
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        this.m_IconByMetadataArray = new Icon[DecoUtilsStrings.WOOD_TEXTURE_PATHS.length];
        
        for (int index = 0; index < this.m_IconByMetadataArray.length; index++)
        {
        	this.m_IconByMetadataArray[index] = register.registerIcon(DecoUtilsStrings.WOOD_TEXTURE_PATHS[index]);
        }
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for (int index = 0; index < this.m_IconByMetadataArray.length; index++)
        {
    		par3List.add(new ItemStack(par1, 1, index));
        }
    }
}
