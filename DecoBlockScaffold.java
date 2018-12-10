package net.minecraft.src;

public class DecoBlockScaffold extends Block
{
	private Icon m_IconTop, m_IconSide;
	private String m_Tag;
	
	protected DecoBlockScaffold(int id, Material material, String tag)
	{
		super(id, material);
		this.setUnlocalizedName("decoBlockScaffold." + tag);
		this.setHardness(Block.planks.blockHardness);
		this.setResistance(Block.planks.blockResistance);
		this.setCreativeTab(CreativeTabs.tabRedstone);
		
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this);
		
		this.m_Tag = tag;
	}
	
	public boolean DoesBlockHaveSolidTop(IBlockAccess bAccess, int x, int y, int z) 
	{
		return true;
	}
	
	public boolean IsBlockClimable(World world, int x, int y, int z)
    {
        return true;
    }
	
	/**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
    	if (this.m_Tag == "wood")
    	{
    		int blockBelowID = world.getBlockId(x, y - 1, z);
    		return this.blockID == blockBelowID || world.doesBlockHaveSolidTopSurface(x, y - 1, z);
    	}
    	else if (this.m_Tag == "iron")
    	{
    		int blockWestID = world.getBlockId(x - 1, y, z);
    		int blockEastID = world.getBlockId(x + 1, y, z);
    		int blockSouthID = world.getBlockId(x, y, z - 1);
    		int blockNorthID = world.getBlockId(x, y, z + 1);
    		int blockBelowID = world.getBlockId(x, y - 1, z);
    		return this.blockID == blockBelowID || this.blockID == blockWestID || this.blockID == blockEastID || this.blockID == blockSouthID || this.blockID == blockNorthID 
    				|| world.doesBlockHaveSolidTopSurface(x, y - 1, z);
    	}
    	
    	return false;
    }
	
	/**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World world, int x, int y, int z)
    {
    	if (this.m_Tag == "wood")
    	{
    		int blockBelowID = world.getBlockId(x, y - 1, z);
    		return this.blockID == blockBelowID || world.doesBlockHaveSolidTopSurface(x, y - 1, z);
    	}
    	else if (this.m_Tag == "iron")
    	{
    		int blockWestID = world.getBlockId(x - 1, y, z);
    		int blockEastID = world.getBlockId(x + 1, y, z);
    		int blockSouthID = world.getBlockId(x, y, z - 1);
    		int blockNorthID = world.getBlockId(x, y, z + 1);
    		int blockBelowID = world.getBlockId(x, y - 1, z);
    		return this.blockID == blockBelowID || world.doesBlockHaveSolidTopSurface(x, y - 1, z) || 
    				(this.blockID == blockWestID && this.blockID == blockEastID) || (this.blockID == blockSouthID && this.blockID == blockNorthID);
    	}
    	
    	return false;
    }
    
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (player.getCurrentEquippedItem() != null)
        {
	    	ItemStack currentItem = player.getCurrentEquippedItem();
	
	        if (currentItem.itemID == new ItemStack(DecoModuleMechanical.decoSubModuleScaffold.decoBlockScaffoldWood).itemID)
	        {
	        	for (int yIndex = y; yIndex < 255; yIndex++)
	        	{
	        		if (world.isAirBlock(x, yIndex, z))
	        		{
	        			FCUtilsMisc.PlayPlaceSoundForBlock(world, x, y, z); 
	        			world.setBlock(x, yIndex, z, DecoModuleMechanical.decoSubModuleScaffold.decoBlockScaffoldWoodID);
	        			
	        			currentItem.stackSize--;

	                    if (currentItem.stackSize <= 0)
	                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
	        			
	        			return true;
	        		}
	        	}
	        }
	        else if (currentItem.itemID == new ItemStack(DecoModuleMechanical.decoSubModuleScaffold.decoBlockScaffoldMetal).itemID)
	        {
	        	for (int yIndex = y; yIndex < 255; yIndex++)
	        	{
	        		if (world.isAirBlock(x, yIndex, z))
	        		{
	        			FCUtilsMisc.PlayPlaceSoundForBlock(world, x, y, z); 
	        			world.setBlock(x, yIndex, z, DecoModuleMechanical.decoSubModuleScaffold.decoBlockScaffoldMetalID);
	        			
	        			currentItem.stackSize--;

	                    if (currentItem.stackSize <= 0)
	                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
	        			
	        			return true;
	        		}
	        	}
	        }
        }
        
        return false;
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
    {
    	if (!this.canBlockStay(world, x, y, z))
        {
            this.dropBlockAsItem(world, x, y, z, 0, 0);
            world.setBlockToAir(x, y, z);
        }

        super.onNeighborBlockChange(world, x, y, z, neighbourBlockID);
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1.0F, y + 1.0F, z + 1.0F);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
	
	public int getRenderBlockPass()
	{
		return 1;
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
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
    {
    	switch (side)
    	{
    		case 0 : return bAccess.isAirBlock(x, y, z);
    		case 2 : return !bAccess.isBlockOpaqueCube(x + 1, y, z);
    		case 3 : return !bAccess.isBlockOpaqueCube(x - 1, y, z);
    		case 4 : return !bAccess.isBlockOpaqueCube(x, y, z - 1);
    		case 5 : return !bAccess.isBlockOpaqueCube(x, y, z + 1);
    	}
    	
    	return true;
    }
	
	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return side <= 1 ? this.m_IconTop : this.m_IconSide;
	}
	
	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		this.m_IconSide = register.registerIcon("decoBlockScaffold_" + this.m_Tag + "_side");
		this.m_IconTop = register.registerIcon("decoBlockScaffold_" + this.m_Tag + "_top");
	}
}
