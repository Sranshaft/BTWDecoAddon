package net.minecraft.src;

import java.util.List;

public class DecoBlockDirt extends FCBlockDirt 
{
	private static final String[] DIRT_TYPE = new String[] { "dirt", "dirt_coarse", "dirt_dried", "dirt_sandy" };
	private static final String[] DIRT_NAME = new String[] { "Dirt", "Coarse Dirt", "Dried Dirt", "Sandy Dirt" };
	
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockDirt(int id)
	{
		super(id);
		
		this.setUnlocalizedName("dirt");
		this.setStepSound(Block.soundGravelFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        
        DecoAddonManager.register(this, this.DIRT_TYPE, this.DIRT_NAME);
	}
	
	public boolean GetCanGrassSpreadToBlock(World world, int x, int y, int z)
    {
		int lightLevel = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
        int blockAboveID = world.getBlockId(x, y + 1, z);

        if (lightLevel >= 11 && Block.lightOpacity[blockAboveID] <= 2)
        {
            Block blockAbove = Block.blocksList[blockAboveID];

            if (blockAbove == null || blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)) return true;
        }

        return false;
    }
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		this.m_IconByMetadataArray = new Icon[this.DIRT_TYPE.length];
		
		for (int index = 0; index < this.DIRT_TYPE.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon(this.DIRT_TYPE[index]);
		}
		
		this.blockIcon = register.registerIcon(this.DIRT_TYPE[0]);
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
    	for (int index = 0; index < this.DIRT_TYPE.length; index++)
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
