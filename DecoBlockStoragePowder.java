package net.minecraft.src;

import java.util.List;

public class DecoBlockStoragePowder extends BlockSand 
{
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockStoragePowder(int id)
	{
		super(id, Material.sand);
		
		this.setUnlocalizedName("decoBlockStoragePowder");
		this.setHardness(1.0F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundSandFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
        
        this.m_IconByMetadataArray = new Icon[DecoUtilsStrings.STORAGE_POWDER_TAGS.length];
        
        DecoAddonManager.register(this, DecoUtilsStrings.STORAGE_POWDER_TAGS, "Block of ", DecoUtilsStrings.STORAGE_POWDER_NAMES);
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
		for (int index = 0; index < DecoUtilsStrings.STORAGE_POWDER_TAGS.length; index++)
    	{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockStorage_" + DecoUtilsStrings.STORAGE_POWDER_TAGS[index]);
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
    	for (int index = 0; index < DecoUtilsStrings.STORAGE_POWDER_TAGS.length; index++)
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
