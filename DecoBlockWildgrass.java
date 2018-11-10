
package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DecoBlockWildgrass extends BlockFlower
{
	private Icon m_IconTop, m_IconMiddle, m_IconBottom;
	
	private static int m_GrowthChance = 50;
	
	public DecoBlockWildgrass(int id)
	{
		super(id);
		
		this.setUnlocalizedName("decoBlockWildgrass");
		this.setHardness(0.01F);
		this.setStepSound(soundGrassFootstep);
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register) 
	{ 
		this.m_IconTop = register.registerIcon("decoBlockWildgrass_top");
		this.m_IconMiddle = register.registerIcon("decoBlockWildgrass_middle");
		this.m_IconBottom = register.registerIcon("decoBlockWildgrass_bottom");
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		switch (metadata)
		{
			case 0:
				return this.m_IconTop;
			case 1:
				return this.m_IconMiddle;
			default:
				return this.m_IconBottom;
		}
	}
	
	/**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	var3.add(new ItemStack(var1, 1, 0));
    }
	
	/**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == Item.shears.itemID)
        {
            player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this, 1, 0));
        }
        else
        {
        	super.harvestBlock(world, player, x, y, z, 0);
        }
    }
    
    public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}

	public boolean isShearable(ItemStack item, World world, int x, int y, int z)
	{
		return true;
	}

	public ItemStack onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
	{
		return new ItemStack(this, 1, 0);
	}

	public boolean isBlockFoliage(World world, int x, int y, int z)
	{
		return true;
	}
    
    public int damageDropped(int metadata)
	{
		return -1;
	}
    
    protected boolean canThisPlantGrowOnThisBlockID(World world, int x, int y, int z, boolean flag)
    {
		int id = world.getBlockId(x, y, z);
		
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == FCBetterThanWolves.fcPlanter.blockID || (id == blockID && flag);
    }
    
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && canThisPlantGrowOnThisBlockID(world, x, y - 1, z, true);
    }
    
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canThisPlantGrowOnThisBlockID(world, x, y - 1, z, false);
	}
    
    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata) 
    {
    	if (world.getBlockId(x, y + 1, z) == this.blockID || (world.isAirBlock(x, y + 1, z) && world.getBlockId(x, y - 1, z) == this.blockID))
        	world.setBlockMetadata(x, y - 1, z, 0);
    }
    
    public void updateTick(World world, int x, int y, int z, Random random)
    {
    	/*float growthMultiplier = 0.025F * FCUtilsMisc.GetBlockGrowthMultiplier(world, x, y - 1, z, this);
		
		if (random.nextFloat() <= growthMultiplier && canThisPlantGrowOnThisBlockID(world, x, y - 1, z, false))
    		this.growPlant(world, x, y, z, random);
    	
        super.updateTick(world, x, y, z, random);*/
        
        if (random.nextInt(100) <= this.m_GrowthChance && canThisPlantGrowOnThisBlockID(world, x, y - 1, z, true))
        	this.growPlant(world, x, y, z, random);
	
        super.updateTick(world, x, y, z, random);
    }

    private void growPlant(World world, int x, int y, int z, Random random)
	{
    	int metadata = world.getBlockMetadata(x, y, z);
		int allowedPlantHeight = DecoUtilsWorld.GetHeightForBiome(world.getWorldChunkManager().getBiomeGenAt(x, z));
		
		int currentHeight;
		for (currentHeight = 1; !world.isAirBlock(x, y + currentHeight, z); currentHeight++) { ; }
		
		if (currentHeight <= allowedPlantHeight)
		{
			for (int index = 0; index <= currentHeight; index++)
			{
				if (world.isAirBlock(x, y + index, z))
					world.setBlockAndMetadata(x, y + index, z, this.blockID, currentHeight - index);
				else
					break;
			}
		
			if (world.getBlockId(x, y - 1, z) != FCBetterThanWolves.fcPlanter.blockID)
				this.spreadPlant(world, x, y, z, random);
    	}
	}

	private void spreadPlant(World world, int x, int y, int z, Random random)
	{
		float growthMultiplier = 0.025F * FCUtilsMisc.GetBlockGrowthMultiplier(world, x, y - 1, z, this);
		
		if (random.nextFloat() <= growthMultiplier)
		{
			for (int checkX = -2; x < 3; x++)
			{
				for (int checkY = -2; y < 3; y++)
				{
					for (int checkZ = -2; z < 3; z++)
					{
						if (canThisPlantGrowOnThisBlockID(world, x + checkX, y + checkY - 1, z + checkZ, false))
						{
							if (world.isAirBlock(x + checkX, y + checkY, z + checkZ))
							{
								if (random.nextInt(8) != 0)
									continue;
								else
								{
									world.setBlockAndMetadataWithNotify(x + checkX, y + checkY, z + checkZ, blockID, 0);
									break;
								}
							}
    					}
    				}
    			}
    		}
		}
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public int getBlockColor()
    {
    	double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrass.getGrassColor(var1, var3);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
    	return ColorizerFoliage.getFoliageColorBasic();
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
    {
    	return bAccess.getBiomeGenForCoords(x, z).getBiomeGrassColor();
    }
}