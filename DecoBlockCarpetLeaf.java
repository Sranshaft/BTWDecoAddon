package net.minecraft.src;

import java.util.List;

public class DecoBlockCarpetLeaf extends Block 
{
	private Icon[][] m_IconByMetadataArray = new Icon[DecoUtilsStrings.LEAF_TEXTURE_PATHS.length][DecoUtilsStrings.LEAF_TEXTURE_PATHS.length];
	private int m_graphicsLevel;

	public DecoBlockCarpetLeaf(int id) 
	{
		super(id, Material.leaves);

		this.setUnlocalizedName("decoBlockCarpetLeaf");
		this.setHardness(0.3F);
		this.setResistance(0.5F);
		this.setStepSound(Block.soundGrassFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);

		DecoAddonManager.register(this, DecoUtilsStrings.LEAF_TAGS, DecoUtilsStrings.LEAF_NAMES, " Leaf Carpet");
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return AxisAlignedBB.getAABBPool().getAABB((double)x, (double)y, (double)z, (double)((float)x + 1.0F), (double)((float)y + 0.125F), (double)((float)z + 1.0F));
	}

	/**
	 * only called by clickMiddleMouseButton , and passed to
	 * inventory.setCurrentItem (along with isCreative)
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

	// CLIENT ONLY

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or
	 * not to render the shared face of two adjacent blocks and also whether the
	 * player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() 
	{
		if (Block.leaves.graphicsLevel) this.m_graphicsLevel = 0; else this.m_graphicsLevel = 1;
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
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
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
		for (int index = 0; index < DecoUtilsStrings.LEAF_TEXTURE_PATHS.length; ++index)
		{
			this.m_IconByMetadataArray[0][index] = register.registerIcon(DecoUtilsStrings.LEAF_TEXTURE_PATHS[index]);
			this.m_IconByMetadataArray[1][index] = register.registerIcon(DecoUtilsStrings.LEAF_OPAQUE_TEXTURE_PATHS[index]);
		}
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture.
	 * Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata) 
	{
		return this.m_IconByMetadataArray[this.m_graphicsLevel][metadata];
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	public void getSubBlocks(int var1, CreativeTabs var2, List var3) 
	{
		for (int index = 0; index < DecoUtilsStrings.LEAF_TEXTURE_PATHS.length; index++) 
		{
			var3.add(new ItemStack(var1, 1, index));
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

	public void RenderBlockDamageEffect(RenderBlocks render, int var2, int var3, int var4, Icon var5)
	{
		render.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125D, 1.0F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, var5);
	}
}
