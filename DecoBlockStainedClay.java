package net.minecraft.src;

import java.util.List;

public class DecoBlockStainedClay extends FCBlockStone
{
	private Icon[] m_IconByMetadataArray = new Icon[16];
	
	public DecoBlockStainedClay(int id, Material material)
	{
		super(id);
		setUnlocalizedName("decoBlockStainedClay");
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		
		DecoAddonManager.register(this, DecoUtilsStrings.COLOUR_TAGS, DecoUtilsStrings.COLOUR_NAMES, " Stained Clay");
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
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockStainedClay_" + DecoUtilsStrings.COLOUR_TAGS[index]);
		}
		
		if (DecoUtilsStrings.COLOUR_TAGS.length < 16)
		{
			for (int index = DecoUtilsStrings.COLOUR_TAGS.length; index < 16; index++)
				this.m_IconByMetadataArray[index] = this.blockIcon;
		}
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		return this.m_IconByMetadataArray[metadata];
	}
	
	 /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World World, int x, int y, int z)
    {
        return World.getBlockId(x, y, z);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World World, int x, int y, int z)
    {
        return World.getBlockMetadata(x, y, z);
    }
}