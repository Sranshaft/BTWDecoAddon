package net.minecraft.src;

import java.util.List;

public class DecoBlockHedge extends Block
{
	public Icon[] m_IconByMetadataArrayNormal;
	public Icon[] m_IconByMetadataArrayOpaque;
	
	public DecoBlockHedge(int id)
	{
		super(id, Material.leaves);
		
		this.setUnlocalizedName("decoBlockHedge");
		this.setHardness(Block.leaves.blockHardness);
		this.setResistance(Block.leaves.blockResistance / 3.0F);
		this.setStepSound(Block.leaves.stepSound);
		this.setCreativeTab(CreativeTabs.tabDecorations);

		DecoAddonManager.register(this, DecoUtilsStrings.LEAF_TAGS, DecoUtilsStrings.LEAF_NAMES, " Hedge");
	}
	
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y, z);
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}
	
	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	{
		boolean isConnectedSouth = this.canConnectHedgeToBlock(world, x, y, z - 1);
		boolean isConnectedNorth = this.canConnectHedgeToBlock(world, x, y, z + 1);
		boolean isConnectedWest = this.canConnectHedgeToBlock(world, x - 1, y, z);
		boolean isConnectedEast = this.canConnectHedgeToBlock(world, x + 1, y, z);

		float offsetWest = 0.125F;
		float offsetEast = 0.875F;
		float offsetSouth = 0.125F;
		float offsetNorth = 0.875F;
		
		boolean isBlockAbove = world.isAirBlock(x, y + 1, z);
		boolean isHedgeAbove = !isBlockAbove && (world.getBlockId(x, y + 1, z) == this.blockID);
		
		float maxY = 0.8125F;
		if (isHedgeAbove) maxY = 1.0F;

		if (isConnectedSouth) offsetSouth = 0.0F;
		if (isConnectedNorth) offsetNorth = 1.0F;

		if (isConnectedSouth || isConnectedNorth)
		{
			this.setBlockBounds(offsetWest, 0.0F, offsetSouth, offsetEast, maxY, offsetNorth);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}

		offsetSouth = 0.325F;
		offsetNorth = 0.675F;

		if (isConnectedWest) offsetWest = 0.0F;
		if (isConnectedEast) offsetEast = 1.0F;

		if (isConnectedWest || isConnectedEast || !isConnectedSouth && !isConnectedNorth)
		{
			this.setBlockBounds(offsetWest, 0.0F, offsetSouth, offsetEast, maxY, offsetNorth);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}

		if (isConnectedSouth) offsetSouth = 0.0F;
		if (isConnectedNorth) offsetNorth = 1.0F;

		this.setBlockBounds(offsetWest, 0.0F, offsetSouth, offsetEast, maxY, offsetNorth);
		this.setBlockBoundsForItemRender();
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		boolean isConnectedSouth = this.canConnectHedgeToBlock(bAccess, x, y, z - 1);
		boolean isConnectedNorth = this.canConnectHedgeToBlock(bAccess, x, y, z + 1);
		boolean isConnectedWest = this.canConnectHedgeToBlock(bAccess, x - 1, y, z);
		boolean isConnectedEast = this.canConnectHedgeToBlock(bAccess, x + 1, y, z);
		boolean isBlockAbove = bAccess.isAirBlock(x, y + 1, z);
		boolean isHedgeAbove = !isBlockAbove && canConnectHedgeToBlock(bAccess, x, y + 1, z);
		
		float maxY = 0.8125F;
		if (isHedgeAbove) maxY = 1.0F;

		float offsetWest = 0.125F;
		float offsetEast = 0.875F;
		float offsetSouth = 0.125F;
		float offsetNorth = 0.875F;

		if (isConnectedSouth) offsetSouth = 0.0F;
		if (isConnectedNorth) offsetNorth = 1.0F;
		if (isConnectedWest) offsetWest = 0.0F;
		if (isConnectedEast) offsetEast = 1.0F;

		this.setBlockBounds(offsetWest, 0.0F, offsetSouth, offsetEast, maxY, offsetNorth);
	}
	
	/**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
    	this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 0.8125F, 1.0F);
    }
	
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return false;
	}
	
	/**
     * Return whether an adjacent block can connect to a wall.
     */
    public boolean canConnectHedgeToBlock(IBlockAccess bAccess, int x, int y, int z)
    {
        return bAccess.getBlockId(x, y, z) == this.blockID;
    }
    
    /**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		return true;
	}
	
    /**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or
	 * not to render the shared face of two adjacent blocks and also whether the
	 * player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() 
	{
		return false;
	}
	
	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	 /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	this.m_IconByMetadataArrayNormal = new Icon[DecoUtilsStrings.LEAF_TAGS.length];
    	this.m_IconByMetadataArrayOpaque = new Icon[DecoUtilsStrings.LEAF_TAGS.length];
    	
    	for (int index = 0; index < DecoUtilsStrings.LEAF_TEXTURE_PATHS.length; ++index)
        {
    		this.m_IconByMetadataArrayNormal[index] = register.registerIcon(DecoUtilsStrings.LEAF_TEXTURE_PATHS[index]);
    		this.m_IconByMetadataArrayOpaque[index] = register.registerIcon(DecoUtilsStrings.LEAF_OPAQUE_TEXTURE_PATHS[index]);
        }
    }
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	if (Block.leaves.graphicsLevel) return this.m_IconByMetadataArrayNormal[metadata]; else return this.m_IconByMetadataArrayOpaque[metadata];
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int blockID, CreativeTabs creativeTab, List list)
    {
    	for (int index = 0; index < DecoUtilsStrings.LEAF_TAGS.length; index++)
        {
    		list.add(new ItemStack(blockID, 1, index));
        }
    }
   
    public int getBlockColor()
    {
    	double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerFoliage.getFoliageColor(var1, var3);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int metadata)
    {
        switch (metadata)
        {
        	case 1 : return ColorizerFoliage.getFoliageColorPine();
        	case 2 : return ColorizerFoliage.getFoliageColorBirch();
        	case 4 : return DecoUtilsColorizer.getFoliageBloodwood();
        	case 5 : return DecoUtilsColorizer.getFoliageEbonwood();
        	case 6 : return DecoUtilsColorizer.getFoliageIronwood();
        	
        	default : return ColorizerFoliage.getFoliageColorBasic();
        }  
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
    {
    	int metadata = bAccess.getBlockMetadata(x, y, z);
    	
    	switch (metadata)
        {
        	case 1 : return ColorizerFoliage.getFoliageColorPine();
        	case 2 : return ColorizerFoliage.getFoliageColorBirch();
        	case 4 : return DecoUtilsColorizer.getFoliageBloodwood();
        	case 5 : return DecoUtilsColorizer.getFoliageEbonwood();
        	case 6 : return DecoUtilsColorizer.getFoliageIronwood();
        	
        	default : return ColorizerFoliage.getFoliageColorBasic();
        }
    }
    
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		IBlockAccess bAccess = render.blockAccess;
		
		boolean isBlockAbove = bAccess.isAirBlock(x, y + 1, z);
		boolean isHedgeAbove = !isBlockAbove && this.canConnectHedgeToBlock(render.blockAccess, x, y + 1, z);

		boolean isConnectedWest = this.canConnectHedgeToBlock(bAccess, x - 1, y, z);
		boolean isConnectedEast = this.canConnectHedgeToBlock(bAccess, x + 1, y, z);
		boolean isConnectedSouth = this.canConnectHedgeToBlock(bAccess, x, y, z - 1);
		boolean isConnectedNorth = this.canConnectHedgeToBlock(bAccess, x, y, z + 1);
		boolean isConnectedWestOrEast = false;
		boolean isConnectedSouthOrNorth = false;
		
		if (isConnectedWest || isConnectedEast) isConnectedWestOrEast = true;
		if (isConnectedSouth || isConnectedNorth) isConnectedSouthOrNorth = true;
		
		if (!isConnectedWestOrEast && !isConnectedSouthOrNorth) isConnectedWestOrEast = true;
		
		float minY = 0.0F;
		float maxY = 0.8125F;
		if (isHedgeAbove) maxY = 1.0F;
		
		float minX = isConnectedWest ? 0.0F : 0.125F;
		float maxX = isConnectedEast ? 1.0F : 0.875F;
		float minZ = isConnectedSouth ? 0.0F : 0.125F;
		float maxZ = isConnectedNorth ? 1.0F : 0.875F;
		
		if (isConnectedWestOrEast)
		{
			render.setRenderBounds(minX, 0.0F, 0.125F, maxX, maxY, 0.875F);
			render.renderStandardBlock(this, x, y, z);
		}
		
		if (isConnectedSouthOrNorth)
		{
			render.setRenderBounds(0.125F, 0.0F, minZ, 0.875F, maxY, maxZ);
			render.renderStandardBlock(this, x, y, z);
		}

		this.setBlockBoundsBasedOnState(bAccess, x, y, z);
		
		return true;
	}
}