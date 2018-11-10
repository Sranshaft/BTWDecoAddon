package net.minecraft.src;

import java.util.List;

public class DecoBlockTableStone extends Block implements DecoIBlock
{
	public String[] m_Tags, m_Names;
	private Icon[] m_IconByMetadataArray;

	public DecoBlockTableStone(int id, String[] tags, String[] names)
	{
		super(id, Material.rock);
		
		this.setUnlocalizedName("decoBlockTableStone");
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);

		Block.useNeighborBrightness[id] = true;
		Block.lightOpacity[id] = 255;

		this.m_Tags = tags;
		this.m_Names = names;

		DecoAddonManager.register(this, this.m_Tags, this.m_Names, " Plinth");
	}

	//CLIENT ONLY

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
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int metadata)
    {
        return metadata;
    }

	public boolean DoesBlockHaveSolidTop(IBlockAccess bAccess, int x, int y, int z) 
	{
		return true;
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return this.m_IconByMetadataArray[metadata];
	}

	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		this.m_IconByMetadataArray = new Icon[this.m_Tags.length];

		for (int index = 0; index < this.m_Tags.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockFurniture_" + this.m_Tags[index]);
		}
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int var1, CreativeTabs var2, List var3)
	{
		for (int index = 0; index < this.m_Tags.length; index++)
		{
			var3.add(new ItemStack(var1, 1, index));
		}
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

	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		return true;
	}

	/**
	 * Return whether an adjacent block can connect to a wall.
	 */
	public boolean canConnectTo(IBlockAccess bAccess, int x, int y, int z)
	{
		int blockID = bAccess.getBlockId(x, y, z);
		return blockID != this.blockID ? false : true;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x), (double) ((float) y), (double) ((float) z), 
				(double) ((float) x + 1.0F), (double) ((float) y + 1.0F), (double) ((float) z + 1.0F));
	}

	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		boolean isBlockWest = this.canConnectTo(render.blockAccess, x - 1, y, z);
		boolean isBlockEast = this.canConnectTo(render.blockAccess, x + 1, y, z);
		boolean isBlockNorth = this.canConnectTo(render.blockAccess, x, y, z - 1);
		boolean isBlockSouth = this.canConnectTo(render.blockAccess, x, y, z + 1);

		render.setRenderBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.25F, 0.8125F);
		render.renderStandardBlock(this, x, y, z);

		render.setRenderBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.375F, 0.75F);
		render.renderStandardBlock(this, x, y, z);

		render.setRenderBounds(0.1875F, 0.375F, 0.1875F, 0.8125F, 0.6875F, 0.8125F);
		render.renderStandardBlock(this, x, y, z);

		render.setRenderBounds(0.0F, 0.6875F, 0.0F, 1.0F, 1.0F, 1.0F);
		render.renderStandardBlock(this, x, y, z);

		if (isBlockNorth)
		{
			render.setRenderBounds(0.1875F, 0.0F, 0.0F, 0.8125F, 0.25F, 0.1875F);
			render.renderStandardBlock(this, x, y, z);

			render.setRenderBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.375F, 0.25F);
			render.renderStandardBlock(this, x, y, z);

			render.setRenderBounds(0.1875F, 0.375F, 0.0F, 0.8125F, 0.6875F, 0.1875F);
			render.renderStandardBlock(this, x, y, z);
		}

		if (isBlockSouth)
		{
			render.setRenderBounds(0.1875F, 0.0F, 0.8125F, 0.8125F, 0.25F, 1.0F);
			render.renderStandardBlock(this, x, y, z);

			render.setRenderBounds(0.25F, 0.25F, 0.75F, 0.75F, 0.375F, 1.0F);
			render.renderStandardBlock(this, x, y, z);

			render.setRenderBounds(0.1875F, 0.375F, 0.8125F, 0.8125F, 0.6875F, 1.0F);
			render.renderStandardBlock(this, x, y, z);
		}

		if (isBlockEast)
		{
			render.setRenderBounds(0.8125F, 0.0F, 0.1875F, 1.0F, 0.25F, 0.8125F);
			render.renderStandardBlock(this, x, y, z);

			render.setRenderBounds(0.75F, 0.25F, 0.25F, 1.0F, 0.375F, 0.75F);
			render.renderStandardBlock(this, x, y, z);

			render.setRenderBounds(0.8125F, 0.375F, 0.1875F, 1.0F, 0.6875F, 0.8125F);
			render.renderStandardBlock(this, x, y, z);
		}

		if (isBlockWest)
		{
			render.setRenderBounds(0.0F, 0.0F, 0.1875F, 0.1875F, 0.25F, 0.8125F);
			render.renderStandardBlock(this, x, y, z);

			render.setRenderBounds(0.0F, 0.25F, 0.25F, 0.25F, 0.375F, 0.75F);
			render.renderStandardBlock(this, x, y, z);

			render.setRenderBounds(0.0F, 0.375F, 0.1875F, 0.1875F, 0.6875F, 0.8125F);
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
