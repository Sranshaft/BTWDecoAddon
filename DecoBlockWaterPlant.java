package net.minecraft.src;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

public class DecoBlockWaterPlant extends BlockFlower
{
	public static final String[] WATER_PLANT_TYPES = new String[] { "cattail", "seaweed", "pondweed" };
	public static final String[] WATER_PLANT_NAMES = new String[] { "Cattail", "Seaweed", "Pondweed" };
	
	private Icon[] m_IconByMetadataArray;
	
	public DecoBlockWaterPlant(int id)
	{
		super(id, Material.plants);
		setUnlocalizedName("decoBlockWaterPlant");
		setStepSound(soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		setBlockBounds(0.25F, -1.0F, 0.25F, 0.75F, 1.0F, 0.75F);
		
		Block.useNeighborBrightness[id] = true;
		
		this.m_IconByMetadataArray = new Icon[this.WATER_PLANT_TYPES.length];
		
		DecoAddonManager.register(this, this.WATER_PLANT_TYPES, this.WATER_PLANT_NAMES);
	}
	
	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	if (canPlaceBlockAt(world, x, y, z))
    	{
    		world.setBlock(x, y, z, Block.waterStill.blockID, 0, 2);
    		world.setBlockAndMetadata(x, y + 1, z, this.blockID, metadata);
    	}
    	
    	return metadata;
    }
    
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return y >= 0 && y < 256 ? world.isAirBlock(x, y + 1, z) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 2, z)) : false;
	}
    
    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.isAirBlock(x, y + 1, z) && world.getBlockMaterial(x, y, z) == Material.water && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
    }
	
	/**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int blockID)
    {
    	return blockID == Block.dirt.blockID || blockID == Block.sand.blockID || blockID == Block.blockClay.blockID || blockID == DecoModuleWorld.decoBlockMudID;
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) 
    { 
    	if (world.getBlockMaterial(x, y - 1, z) != Material.water && !this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 2, z)))
    		world.setBlockToAir(x, y, z);
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata)
	{
		return -1;
	}
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
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
            this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this, 1, metadata));
        }
    }
	
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		for (int index = 0; index < this.WATER_PLANT_TYPES.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockWaterPlant_" + this.WATER_PLANT_TYPES[index]);
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
    	for (int index = 0; index < this.WATER_PLANT_TYPES.length; index++)
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
    
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
    	int metadata = render.blockAccess.getBlockMetadata(x, y, z);
    	
    	DecoUtilsRender.drawCrossedSquares(this, x, y - 1, z, render, 0.9, 2.0, metadata, this.getIcon(0, metadata));
    	return true;
    }
}