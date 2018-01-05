package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockDirt extends BlockDirt
{
	public static final String[] DIRT_TEXTURE_PATHS = new String[] { "dirt", "decoBlockDirt_coarse", "decoBlockDirt_dried", "decoBlockDirt_rich" };
	
	private Icon[] m_IconByMetadataArray = new Icon[this.DIRT_TEXTURE_PATHS.length];
	
	public DecoBlockDirt(int id)
	{
		super(id);
		
		this.setUnlocalizedName("dirt");
		this.setHardness(0.5F);
		this.setTickRandomly(true);
		this.setStepSound(Block.soundGravelFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
        
        DecoAddonManager.register(this, DecoUtilsStrings.DIRT_TAGS, DecoUtilsStrings.DIRT_NAMES, " Dirt");
	}
	
	//CLIENT ONLY
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		for (int index = 0; index < this.DIRT_TEXTURE_PATHS.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon(this.DIRT_TEXTURE_PATHS[index]);
		}
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		return this.m_IconByMetadataArray[this.getMetadata(metadata)];
	}

	 /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < DecoUtilsStrings.DIRT_TAGS.length / 4; index++)
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
        return this.getMetadata(world.getBlockMetadata(x, y, z));
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
	public int damageDropped(int metadata)
	{
		return this.getMetadata(metadata);
	}
    
    private int getMetadata(int metadata)
	{
		int result = metadata > 3 ? (metadata / 2) - 2 : metadata;
		return result < 0 || result > 16 ? 0 : result;		
	}
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
    	int metadata = world.getBlockMetadata(x, y, z);
    	
    	if (DecoUtils.isWaterNearby(world, x, y, z) && metadata != 1)
        	world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, 3);
        else if (!DecoUtils.isWaterNearby(world, x, y, z) && metadata != 1)
        	world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, 0);
    }
}
