package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockBannister extends Block
{
	public Icon[] m_IconByMetadataArray;

	public DecoBlockBannister(int id)
	{
		super(id, Material.wood);

		this.setUnlocalizedName("decoBlockBannister");
		this.setHardness(Block.wood.blockHardness);
		this.setResistance(Block.wood.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);

		this.m_IconByMetadataArray = new Icon[DecoUtilsStrings.WOOD_TAGS.length];

		DecoAddonManager.register(this, DecoUtilsStrings.WOOD_TAGS, DecoUtilsStrings.WOOD_NAMES, " Bannister");
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
		boolean isConnectedSouth = this.canConnectBannisterTo(world, x, y, z - 1);
		boolean isConnectedNorth = this.canConnectBannisterTo(world, x, y, z + 1);
		boolean isConnectedWest = this.canConnectBannisterTo(world, x - 1, y, z);
		boolean isConnectedEast = this.canConnectBannisterTo(world, x + 1, y, z);

		float offsetWest = 0.325F;
		float offsetEast = 0.675F;
		float offsetSouth = 0.325F;
		float offsetNorth = 0.675F;

		if (isConnectedSouth) offsetSouth = 0.0F;
		if (isConnectedNorth) offsetNorth = 1.0F;

		if (isConnectedSouth || isConnectedNorth)
		{
			this.setBlockBounds(offsetWest, 0.0F, offsetSouth, offsetEast, 1.5F, offsetNorth);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}

		offsetSouth = 0.325F;
		offsetNorth = 0.675F;

		if (isConnectedWest) offsetWest = 0.0F;
		if (isConnectedEast) offsetEast = 1.0F;

		if (isConnectedWest || isConnectedEast || !isConnectedSouth && !isConnectedNorth)
		{
			this.setBlockBounds(offsetWest, 0.0F, offsetSouth, offsetEast, 1.5F, offsetNorth);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}

		if (isConnectedSouth) offsetSouth = 0.0F;
		if (isConnectedNorth) offsetNorth = 1.0F;

		this.setBlockBounds(offsetWest, 0.0F, offsetSouth, offsetEast, 1.0F, offsetNorth);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		boolean isConnectedSouth = this.canConnectBannisterTo(bAccess, x, y, z - 1);
		boolean isConnectedNorth = this.canConnectBannisterTo(bAccess, x, y, z + 1);
		boolean isConnectedWest = this.canConnectBannisterTo(bAccess, x - 1, y, z);
		boolean isConnectedEast = this.canConnectBannisterTo(bAccess, x + 1, y, z);

		float offsetWest = 0.325F;
		float offsetEast = 0.675F;
		float offsetSouth = 0.325F;
		float offsetNorth = 0.675F;

		if (isConnectedSouth) offsetSouth = 0.0F;
		if (isConnectedNorth) offsetNorth = 1.0F;
		if (isConnectedWest) offsetWest = 0.0F;
		if (isConnectedEast) offsetEast = 1.0F;

		this.setBlockBounds(offsetWest, 0.0F, offsetSouth, offsetEast, 1.0F, offsetNorth);
	}

	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return false;
	}

	/**
	 * Returns true if the specified block can be connected by a fence
	 */
	public boolean canConnectBannisterTo(IBlockAccess bAccess, int x, int y, int z)
	{
		int blockID = bAccess.getBlockId(x, y, z);

		if (blockID != this.blockID)
		{
			Block block = Block.blocksList[blockID];
			return block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
		}
		else return true;
	}

	public static boolean isIdAFence(int blockID)
	{
		return blockID == DecoModuleDecoration.decoSubModuleFurniture.decoBlockBannisterID;
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
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister register)
	{
		for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockFurniture_" + DecoUtilsStrings.WOOD_TAGS[index]);
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
		for (int index = 0; index < DecoUtilsStrings.STORAGE_TAGS.length; index++)
		{
			var3.add(new ItemStack(var1, 1, index));
		}
	}

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		IBlockAccess bAccess = render.blockAccess;

		render.setRenderBounds(0.325F, 0.0F, 0.325F, 0.675F, 0.125F, 0.675F);
		render.renderStandardBlock(this, x, y, z);
		
		render.setRenderBounds(0.425F, 0.125F, 0.425F, 0.575F, 0.875F, 0.575F);
		render.renderStandardBlock(this, x, y, z);
		
		render.setRenderBounds(0.325F, 0.875F, 0.325F, 0.675F, 1.0F, 0.675F);
		render.renderStandardBlock(this, x, y, z);
		
		boolean isConnectedWest = this.canConnectBannisterTo(bAccess, x - 1, y, z);
		boolean isConnectedEast = this.canConnectBannisterTo(bAccess, x + 1, y, z);
		boolean isConnectedSouth = this.canConnectBannisterTo(bAccess, x, y, z - 1);
		boolean isConnectedNorth = this.canConnectBannisterTo(bAccess, x, y, z + 1);
		
		if (isConnectedWest)
		{
			render.setRenderBounds(0.0F, 0.0F, 0.325F, 0.325F, 0.125F, 0.675F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.1F, 0.125F, 0.425F, 0.25F, 0.875F, 0.575F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.0, 0.875F, 0.325F, 0.325F, 1.0F, 0.675F);
			render.renderStandardBlock(this, x, y, z);
		}
		
		if (isConnectedEast)
		{
			render.setRenderBounds(0.675F, 0.0F, 0.325F, 1.0F, 0.125F, 0.675F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.75, 0.125F, 0.425F, 0.9F, 0.875F, 0.575F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.675F, 0.875F, 0.325F, 1.0F, 1.0F, 0.675F);
			render.renderStandardBlock(this, x, y, z);
		}
		
		if (isConnectedSouth)
		{
			render.setRenderBounds(0.325F, 0.0F, 0.0F, 0.675F, 0.125F, 0.325F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.425F, 0.125F, 0.1F, 0.575F, 0.875F, 0.25F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.325F, 0.875F, 0.0F, 0.675F, 1.0F, 0.325F);
			render.renderStandardBlock(this, x, y, z);
		}
		
		if (isConnectedNorth)
		{
			render.setRenderBounds(0.325F, 0.0F, 0.675F, 0.675F, 0.125F, 1.0F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.425F, 0.125F, 0.75, 0.575F, 0.875F, 0.9F);
			render.renderStandardBlock(this, x, y, z);
			
			render.setRenderBounds(0.325F, 0.875F, 0.675F, 0.675F, 1.0F, 1.0F);
			render.renderStandardBlock(this, x, y, z);
		}


		this.setBlockBoundsBasedOnState(bAccess, x, y, z);
		
		return true;
	}
	
	public void RenderBlockAsItem(RenderBlocks render, int metadata, float var3)
	{
		render.setRenderBounds(0.0F, 0.0F, 0.325F, 1.0F, 0.125F, 0.675F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, metadata);
		
		render.setRenderBounds(0.1F, 0.125F, 0.425F, 0.25F, 0.875F, 0.575F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, metadata);

		render.setRenderBounds(0.425F, 0.125F, 0.425F, 0.575F, 0.875F, 0.575F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, metadata);
		
		render.setRenderBounds(0.75, 0.125F, 0.425F, 0.9F, 0.875F, 0.575F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, metadata);

		render.setRenderBounds(0.0F, 0.875F, 0.325F, 1.0F, 1.0F, 0.675F);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, metadata);
	}
}
