package net.minecraft.src;

import java.util.List;

public class DecoBlockTableStone extends Block implements FCIBlockSolidTop
{
	public String[] STONE_TYPES;
	public String[] STONE_NAMES;
	private Icon[] m_IconByMetadataArray;
	private Icon[] m_SideIconByMetadataArray;

	public DecoBlockTableStone(int id, String[] stoneTags, String[] stoneNames)
	{
		super(id, Material.rock);
		setUnlocalizedName("decoBlockTableStone");
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		
		Block.useNeighborBrightness[id] = true;
		Block.lightOpacity[id] = 255;
		
		this.STONE_TYPES = stoneTags;
		this.STONE_NAMES = stoneNames;
		
		DecoAddonManager.register(this, this.STONE_TYPES, this.STONE_NAMES, " Plinth");
	}
	
	public boolean DoesBlockHaveSolidTop(IBlockAccess bAccess, int x, int y, int z) 
	{
		return true;
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x), (double) ((float) y), (double) ((float) z), 
												   (double) ((float) x + 1.0F), (double) ((float) y + 1.0F), (double) ((float) z + 1.0F));
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		return true;
	}
	
	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		this.m_IconByMetadataArray = new Icon[this.STONE_TYPES.length];
		this.m_SideIconByMetadataArray = new Icon[this.STONE_TYPES.length];
		
		for (int index = 0; index < this.STONE_TYPES.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlock" + this.STONE_TYPES[index]);
			this.m_SideIconByMetadataArray[index] = register.registerIcon("decoBlock" + this.STONE_TYPES[index]);
		}
	}
	
	public Icon getIcon(int side, int metadata)
	{
		if (side < 2)
			return this.m_IconByMetadataArray[metadata];
		else
			return this.m_SideIconByMetadataArray[metadata];
	}
	
	/**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < this.STONE_TYPES.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }
	
	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int metadata)
	{
		return metadata;
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
     * Return whether an adjacent block can connect to a wall.
     */
    public boolean canConnectTo(IBlockAccess bAccess, int x, int y, int z)
    {
        int var5 = bAccess.getBlockId(x, y, z);
        
        return var5 != this.blockID ? false : true;
    }

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		boolean isBlockWest = canConnectTo(render.blockAccess, x - 1, y, z);
		boolean isBlockEast = canConnectTo(render.blockAccess, x + 1, y, z);
		boolean isBlockNorth = canConnectTo(render.blockAccess, x, y, z - 1);
		boolean isBlockSouth = canConnectTo(render.blockAccess, x, y, z + 1);
		boolean isBlockNorthAndSouth = isBlockNorth && isBlockSouth && !isBlockWest && !isBlockEast;
		boolean isBlockEastAndWest = !isBlockNorth && !isBlockSouth && isBlockWest && isBlockEast;
		
		if (isBlockNorthAndSouth || isBlockEastAndWest)
		{
			if (isBlockNorthAndSouth)
			{
				render.setRenderBounds(0.1875F, 0.0F, 0.0F, 0.8125F, 0.25F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.375F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.1875F, 0.375F, 0.0F, 0.8125F, 0.6875F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
			}
			else
			{
				render.setRenderBounds(0.0F, 0.0F, 0.1875F, 1.0F, 0.25F, 0.8125F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.25F, 0.25F, 1.0F, 0.375F, 0.75F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.375F, 0.1875F, 1.0F, 0.6875F, 0.8125F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
			}	
		}
		else if ((isBlockWest || isBlockEast || isBlockNorth || isBlockSouth) && !(isBlockNorthAndSouth || isBlockEastAndWest))
		{
			if (isBlockWest)
			{
				render.setRenderBounds(0.0F, 0.0F, 0.1875F, 0.8125F, 0.25F, 0.8125F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.25F, 0.25F, 0.75F, 0.375F, 0.75F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.375F, 0.1875F, 0.8125F, 0.6875F, 0.8125F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
			}
			
			if (isBlockEast)
			{
				render.setRenderBounds(0.1875F, 0.0F, 0.1875F, 1.0F, 0.25F, 0.8125F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.25F, 0.25F, 0.25F, 1.0F, 0.375F, 0.75F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.1875F, 0.375F, 0.1875F, 1.0F, 0.6875F, 0.8125F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
			}
			
			if (isBlockNorth)
			{
				render.setRenderBounds(0.1875F, 0.0F, 0.0F, 0.8125F, 0.25F, 0.8125F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.375F, 0.75F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.1875F, 0.375F, 0.0F, 0.8125F, 0.6875F, 0.8125F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
			}
			
			if (isBlockSouth)
			{
				render.setRenderBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.25F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.375F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.1875F, 0.375F, 0.1875F, 0.8125F, 0.6875F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
				
				render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
				render.renderStandardBlock(this, x, y, z);
			}
		}
		else
		{
			render.setRenderBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.25F, 0.8125F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.375F, 0.75F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.1875F, 0.375F, 0.1875F, 0.8125F, 0.6875F, 0.8125F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
			render.renderStandardBlock(this, x, y, z);
		}
		
		return true;
	}
	
	public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
	{
		render.setRenderBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.25F, 0.8125F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		
		render.setRenderBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.375F, 0.75F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		
		render.setRenderBounds(0.1875F, 0.375F, 0.1875F, 0.8125F, 0.6875F, 0.8125F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
		
		render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
	}
}
