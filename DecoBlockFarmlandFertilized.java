package net.minecraft.src;

import java.util.Random;

public class DecoBlockFarmlandFertilized extends FCBlockFarmland implements FCISoil
{
	public static final String[] DIRT_TEXTURE_PATHS = new String[] { "dirt", "decoBlockDirt_coarse", "decoBlockDirt_dried", "decoBlockDirt_rich" };
	
	private Icon[] m_IconByMetadataArray = new Icon[this.DIRT_TEXTURE_PATHS.length];
	private Icon m_IconFarmlandWet;
	private Icon m_IconFarmlandDry;
	
	private boolean m_IsFarmlandHydrated = false;
	
	public DecoBlockFarmlandFertilized(int id)
	{
		super(id);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setHardness(0.6F);
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("FCBlockFarmlandFertilized");
        this.setTickRandomly(true);
        this.setLightOpacity(255);
        Block.useNeighborBrightness[id] = true;
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	return side == 1 ? (this.isFarmlandHydrated(metadata) ? this.m_IconFarmlandWet : this.m_IconFarmlandDry) : this.m_IconByMetadataArray[this.getMetadata(metadata)];
    }
	
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
    	
        this.m_IconFarmlandWet = register.registerIcon("FCBlockFarmlandFertilized_wet");
        this.m_IconFarmlandDry = register.registerIcon("FCBlockFarmlandFertilized_dry");
    }
    
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World world, int x, int y, int z)
    {
        return Block.dirt.blockID;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
    	return world.getBlockMetadata(x, y, z);
    }
	
	/**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
    	int metadata = world.getBlockMetadata(x, y, z);
    	
        if (!this.isWaterNearby(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z))
        {
            if (!this.isCropsNearby(world, x, y, z) && random.nextInt(100) <= 50)
                world.setBlockAndMetadataWithNotify(x, y, z, Block.dirt.blockID, this.getMetadata(metadata));
        }
        else
        {
        	if (!this.isFarmlandHydrated(metadata))
        	{
        		FCAddOnHandler.LogMessage("[INFO] Setting block to hydrated metadata. Original metadata: " + metadata + " Adjusted metadata: " + ((metadata + 2) << 1));
        		world.setBlockMetadataWithNotify(x, y, z, (metadata + 2) << 1, 2);
        	}
        }
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
    {
        super.onNeighborBlockChange(world, x, y, z, neighbourBlockID);
        Material blockMaterial = world.getBlockMaterial(x, y + 1, z);

        if (blockMaterial.isSolid())
        {
        	int metadata = world.getBlockMetadata(x, y, z);
        	world.setBlockAndMetadataWithNotify(x, y, z, Block.dirt.blockID, this.getMetadata(metadata));
        }
    }

	/**
	 * Block's chance to react to an entity falling on it.
	 */
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance)
	{
	    if (!world.isRemote && world.rand.nextFloat() < distance - 0.5F)
	    {
	        if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
	            return;
	       
	        int metadata = world.getBlockMetadata(x, y, z);
	        world.setBlockAndMetadataWithNotify(x, y, z, Block.dirt.blockID, this.getMetadata(metadata));
	    }
	}
	
	/**
	 * returns true if there is at least one cropblock nearby (x-1 to x+1, y+1, z-1 to z+1)
	 */
	private boolean isCropsNearby(World world, int x, int y, int z)
	{
		byte var5 = 0;
	
	    for (int indexX = x - var5; indexX <= x + var5; ++indexX)
	    {
	        for (int indexY = z - var5; indexY <= z + var5; ++indexY)
	        {
	            int blockID = world.getBlockId(indexX, y + 1, indexY);
	            if (blockID == Block.crops.blockID || blockID == Block.melonStem.blockID || blockID == Block.pumpkinStem.blockID 
	            		|| blockID == Block.potato.blockID || blockID == Block.carrot.blockID || blockID == FCBetterThanWolves.fcHempCrop.blockID)
	                return true;
	        }
	    }
	
	    return false;
	}
	
	/**
	 * returns true if there's water nearby (x-4 to x+4, y to y+1, k-4 to k+4)
	 */
	private boolean isWaterNearby(World world, int x, int y, int z)
	{
		for (int indexX = x - 4; indexX <= x + 4; indexX++)
	    {
	        for (int indexY = y; indexY <= y + 1; indexY++)
	        {
	            for (int indexZ = z - 4; indexZ <= z + 4; indexZ++)
	            {
	                if (world.getBlockMaterial(indexX, indexY, indexZ) == Material.water)
	                    return true;
	            }
	        }
	    }
	
	    return false;
	}
	
	private boolean isFarmlandHydrated(int metadata)
	{
		return metadata > 3;
	}
	
	private int getMetadata(int metadata)
	{
		int result = metadata > 3 ? (metadata / 2) - 2 : metadata;
		return result < 0 || result > 16 ? 0 : result;		
	}

	public boolean CanPlantGrowOnBlock(World world, int x, int y, int z, Block block) 
	{
		return block.blockID == Block.crops.blockID || block.blockID == FCBetterThanWolves.fcHempCrop.blockID || block.blockID == Block.pumpkinStem.blockID || block.blockID == Block.melonStem.blockID 
				|| block.blockID == Block.pumpkin.blockID || block.blockID == Block.melon.blockID || block.blockID == Block.carrot.blockID || block.blockID == Block.potato.blockID;
	}

	public boolean IsPlantGrowthMaximizedOnBlock(World world, int x, int y, int z, Block block)
	{
		return true;
	}

	public boolean IsBlockHydrated(World world, int x, int y, int z) 
	{
		return this.isWaterNearby(world, x, y, z);
	}

	public boolean IsBlockConsideredNeighbouringWater(World world, int x, int y, int z) 
	{
		return false;
	}

	public float GetGrowthMultiplier(World world, int x, int y, int z, Block block) 
	{
		int metadata = world.getBlockMetadata(x, y, z);
		return getMetadata(metadata) == 3 ? 5.0F : getMetadata(metadata) == 2 ? 0.5F : 2.0F;
	}

	public void NotifyOfPlantGrowth(World world, int x, int y, int z, Block block) 
	{
		int metadata = world.getBlockMetadata(x, y, z);
		world.setBlockAndMetadataWithNotify(x, y, z, Block.tilledField.blockID, metadata);
	}
}
