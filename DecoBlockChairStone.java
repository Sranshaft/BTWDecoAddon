package net.minecraft.src;

public class DecoBlockChairStone extends Block implements DecoIBlockWithMetadata 
{
	private Block m_ParentBlock;
	private int m_ParentMetadata;

	public DecoBlockChairStone(int id, Block parentBlock)
	{
		this(id, parentBlock, 0);
	}
	
	public DecoBlockChairStone(int id, Block parentBlock, int parentMetadata)
	{
		super(id, parentBlock.blockMaterial);
		
		this.setUnlocalizedName(parentBlock.getUnlocalizedName2() + ".chair");
		this.setHardness(parentBlock.blockHardness);
		this.setResistance(parentBlock.blockResistance / 3.0F);
		this.setStepSound(parentBlock.stepSound);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		Block.useNeighborBrightness[id] = true;
		Block.lightOpacity[id] = 255;

		this.m_ParentBlock = parentBlock;
		this.m_ParentMetadata = parentMetadata;
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

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int metadata) 
	{
		return metadata;
	}
	
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if (side < 2) side = 2;
		else side = FCUtilsMisc.GetOppositeFacing(side);

		return this.SetFacingInMetadata(metadata, side);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
	{
		int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(entity);
		this.SetFacing(world, x, y, z, var7);
	}

	public int GetFacing(IBlockAccess bAccess, int x, int y, int z)
	{
		return bAccess.getBlockMetadata(x, y, z);
	}
	
	public void SetFacing(World world, int x, int y, int z, int direction)
	{
		world.setBlockMetadataWithNotify(x, y, z, direction);
	}
	
	public int GetFacingFromMetadata(int metadata)
	{
		return metadata;
	}
	
	public int SetFacingInMetadata(int direction, int metadata)
	{
		return metadata;
	}
	
	public boolean CanRotateOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return false;
	}
	
	public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return false;
	}
	
	public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return false;
	}
	
	public boolean RotateAroundJAxis(World world, int x, int y, int z, boolean var5)
	{
		return FCUtilsMisc.StandardRotateAroundJ(this, world, x, y, z, var5);
	}
	
	public int RotateMetadataAroundJAxis(int metadata, boolean var2)
	{
		return FCUtilsMisc.StandardRotateMetadataAroundJ(this, metadata, var2);
	}
	
	public boolean ToggleFacing(World world, int x, int y, int z, boolean var5)
	{
		this.RotateAroundJAxis(world, x, y, z, var5);
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
		this.blockIcon = this.m_ParentBlock.blockIcon;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int direction = this.GetFacing(world, x, y, z);
		return direction != 2 && direction != 3 ? AxisAlignedBB.getAABBPool().getAABB((double) ((float) x), (double) ((float) y), (double) ((float) z + 0.5F - 0.25F), 
																				      (double) ((float) x + 1.0F), (double) ((float) y + 1.0F), (double) ((float) z + 0.5F + 0.25F)) 
												: AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + 0.5F - 0.25F), (double) ((float) y), (double) ((float) z), 
																					  (double) ((float) x + 0.5F + 0.25F), (double) ((float) y + 1.0F), (double) ((float) z + 1.0F));
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.25F, 0.9375F);
	}

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		int direction = GetFacing(render.blockAccess, x, y, z);
		
		// ARM: LEFT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.015F, 0.0F, 0.015F, 1.0F, 0.675F, 0.25F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// ARM: RIGHT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.015F, 0.0F, 0.725F, 1.0F, 0.675F, 0.975F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// SEAT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.0F, 0.25F, 0.975F, 0.50F, 0.725F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// LOWER BACK: LEFT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 0.25F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// LOWER BACK: MIDDLE
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.015F, 0.0F, 0.25F, 0.235F, 1.0F, 0.725F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// LOWER BACK: RIGHT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.0F, 0.0F, 0.725F, 0.25F, 1.0F, 1.0F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// UPPER BACK: LEFT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.0F, 1.0F, 0.0F, 0.25F, 2.0F, 0.25F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// UPPER BACK: MIDDLE
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.015F, 1.0F, 0.25F, 0.235F, 1.975F, 0.725F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// UPPER BACK: RIGHT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.0F, 1.0F, 0.725F, 0.25F, 2.0F, 1.0F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		return true;
	}
	
	public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
	{
		// ARM: LEFT
		render.setRenderBounds(0.015F, 0.0F, 0.015F, 1.0F, 0.675F, 0.25F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// ARM: RIGHT
		render.setRenderBounds(0.015F, 0.0F, 0.725F, 1.0F, 0.675F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// SEAT
		render.setRenderBounds(0.025F, 0.0F, 0.25F, 0.975F, 0.50F, 0.725F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// LOWER BACK: LEFT
		render.setRenderBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 0.25F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// LOWER BACK: MIDDLE
		render.setRenderBounds(0.015F, 0.0F, 0.25F, 0.235F, 1.0F, 0.725F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// LOWER BACK: RIGHT
		render.setRenderBounds(0.0F, 0.0F, 0.725F, 0.25F, 1.0F, 1.0F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);

		// UPPER BACK: LEFT
		render.setRenderBounds(0.0F, 1.0F, 0.0F, 0.25F, 2.0F, 0.25F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);

		// UPPER BACK: MIDDLE
		render.setRenderBounds(0.015F, 1.0F, 0.25F, 0.235F, 1.975F, 0.725F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);

		// UPPER BACK: RIGHT
		render.setRenderBounds(0.0F, 1.0F, 0.725F, 0.25F, 2.0F, 1.0F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
	}
}