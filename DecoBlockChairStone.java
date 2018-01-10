package net.minecraft.src;

public class DecoBlockChairStone extends Block implements FCIBlock 
{
	private Block m_ParentBlock;

	public DecoBlockChairStone(int id, Block parentBlock)
	{
		super(id, parentBlock.blockMaterial);
		setUnlocalizedName(parentBlock.getUnlocalizedName2() + ".chair");
		setHardness(parentBlock.blockHardness);
		setResistance(parentBlock.blockResistance / 3.0F);
		setStepSound(parentBlock.stepSound);
		setCreativeTab(CreativeTabs.tabDecorations);
		
		Block.useNeighborBrightness[id] = true;
		Block.lightOpacity[id] = 255;

		this.m_ParentBlock = parentBlock;
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
		return true;
	}
	
	public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return false;
	}
	
	public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return false;
	}
	
	public void RotateAroundJAxis(World world, int x, int y, int z, boolean var5)
	{
		FCUtilsMisc.StandardRotateAroundJ(this, world, x, y, z, var5);
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
	
	public int onBlockPlaced(World world, int x, int y, int z, int direction, float hitX, float hitY, float hitZ, int metadata)
	{
		if (direction < 2)
			direction = 2;
		else
			direction = FCUtilsMisc.GetOppositeFacing(direction);

		return SetFacingInMetadata(metadata, direction);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
	{
		int var7 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacing(entity);
		this.SetFacing(world, x, y, z, var7);
		
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
		setBlockBounds(.0625F, 0.0F, .0625F, .9375F, 1.25F, .9375F);
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
	
	public void SetRenderBoundsRotatedAboutJToFacing(RenderBlocks render, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int direction)
	{
		float rotatedMinX;
		float rotatedMinZ;
		float rotatedMaxX;
		float rotatedMaxZ;
		
		if (direction == 4)
		{
			rotatedMinX = 1.0F - maxX;
			rotatedMinZ = 1.0F - maxZ;
			rotatedMaxX = 1.0F - minX;
			rotatedMaxZ = 1.0F - minZ;
		} 
		else if (direction == 3)
		{
			rotatedMinX = minZ;
			rotatedMinZ = minX;
			rotatedMaxX = maxZ;
			rotatedMaxZ = maxX;
		} 
		else if (direction == 2)
		{
			rotatedMinX = 1.0F - maxZ;
			rotatedMinZ = 1.0F - maxX;
			rotatedMaxX = 1.0F - minZ;
			rotatedMaxZ = 1.0F - minX;
		} 
		else
		{
			rotatedMinX = minX;
			rotatedMinZ = minZ;
			rotatedMaxX = maxX;
			rotatedMaxZ = maxZ;
		}
		
		render.setRenderBounds((double) rotatedMinX, (double) minY, (double) rotatedMinZ, (double) rotatedMaxX, (double) maxY, (double) rotatedMaxZ);
	}

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		int direction = GetFacing(render.blockAccess, x, y, z);
		
		// ARM: LEFT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.015F, 0.0F, 0.015F, 1.0F, 0.675F, 0.25F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// ARM: RIGHT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.015F, 0.0F, 0.725F, 1.0F, 0.675F, 0.975F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// SEAT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.0F, 0.25F, 0.975F, 0.50F, 0.725F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BACK: LEFT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 0.25F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BACK: MIDDLE
		SetRenderBoundsRotatedAboutJToFacing(render, 0.015F, 0.0F, 0.25F, 0.235F, 1.0F, 0.725F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BACK: RIGHT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.0F, 0.0F, 0.725F, 0.25F, 1.0F, 1.0F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BACK: LEFT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.0F, 1.0F, 0.0F, 0.25F, 2.0F, 0.25F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BACK: MIDDLE
		SetRenderBoundsRotatedAboutJToFacing(render, 0.015F, 1.0F, 0.25F, 0.235F, 1.975F, 0.725F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BACK: RIGHT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.0F, 1.0F, 0.725F, 0.25F, 2.0F, 1.0F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		return true;
	}
	
	public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
	{
		//Legs
		render.setRenderBounds(0.70F, 0.0F, 0.025F, 0.95F, 0.65F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		render.setRenderBounds(0.025F, 0.0F, 0.85F, 0.15F, 0.5F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		render.setRenderBounds(0.85F, 0.0F, 0.025F, 0.975F, 0.5F, 0.15F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		render.setRenderBounds(0.85F, 0.0F, 0.85F, 0.975F, 0.5F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		//Seat
		render.setRenderBounds(0.025F, 0.5F, 0.025F, 0.975F, 0.65F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		//Back
		render.setRenderBounds(0.025F, 0.65F, 0.025F, 0.15F, 1.5F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
	}
}