package net.minecraft.src;

import java.util.List;

public class DecoBlockBark extends Block 
{
	private Icon[] m_IconByMetadataArray = new Icon[DecoUtilsStrings.TREE_TYPES.length];
	
	public DecoBlockBark(int id)
	{
		super(id, FCBetterThanWolves.fcMaterialLog);
		this.setUnlocalizedName("decoBlockBark");
		this.setHardness(Block.wood.blockHardness);
		this.setResistance(Block.wood.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
        
        DecoAddonManager.register(this, DecoUtilsStrings.TREE_TYPES, "Block of ", DecoUtilsStrings.TREE_NAMES, " Bark");
	}
	
	/**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	//CLIENT ONLY
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		for (int index = 0; index < DecoUtilsStrings.TREE_TYPES.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon(DecoUtilsStrings.TREE_SIDE_TEXTURE_PATHS[index]);
		}
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		if (metadata < 0 || metadata > this.m_IconByMetadataArray.length) return this.m_IconByMetadataArray[0];
		else return this.m_IconByMetadataArray[metadata];
	}
	
	 /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < DecoUtilsStrings.TREE_TYPES.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World world, int x, int y, int z)
    {
        return world.getBlockId(x, y, z);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
}
