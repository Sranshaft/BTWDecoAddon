package net.minecraft.src;

public class DecoBlockWoodenBeam extends Block  
{
	private String m_Tag;
	
	public DecoBlockWoodenBeam(int id, String tag)
	{
		super(id, Material.wood);
		
		this.setUnlocalizedName("decoBlockWoodenBeam." + tag);
		this.setHardness(Block.wood.blockHardness);
		this.setResistance(Block.wood.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		Block.useNeighborBrightness[id] = true;
		Block.lightOpacity[id] = 255;

		this.m_Tag = tag;
	}
	
	public boolean DoesBlockHaveSolidTop(IBlockAccess bAccess, int x, int y, int z) 
	{
		return true;
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
	
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if (side < 2)
			side = 2;
		else
			side = FCUtilsMisc.GetOppositeFacing(side);

		return SetFacingInMetadata(metadata, side);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
	{
		int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(entity);
		this.SetFacing(world, x, y, z, var7);
		
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int direction = this.GetFacing(world, x, y, z);
		
		float minX = 0.0F;
		float minY = 0.75F;
		float minZ = 0.0F;
		float maxX = 0.65F;
		float maxY = 1.0F;
		float maxZ = 1.0F;
		
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
		
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + rotatedMinX), (double) ((float) y + minY), (double) ((float) z + rotatedMinZ), 
												   (double) ((float) x + rotatedMaxX), (double) ((float) y + maxY), (double) ((float) z + rotatedMaxZ));
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		int direction = GetFacing(bAccess, x, y, z);
		
		float rotatedMinX;
		float rotatedMinZ;
		float rotatedMaxX;
		float rotatedMaxZ;
		
		if (direction == 4)
		{
			rotatedMinX = 1.0F - 0.65F;
			rotatedMinZ = 1.0F - 1.0F;
			rotatedMaxX = 1.0F - 0.0F;
			rotatedMaxZ = 1.0F - 0.0F;
		} 
		else if (direction == 3)
		{
			rotatedMinX = 0.0F;
			rotatedMinZ = 0.0F;
			rotatedMaxX = 1.0F;
			rotatedMaxZ = 0.65F;
		} 
		else if (direction == 2)
		{
			rotatedMinX = 1.0F - 1.0F;
			rotatedMinZ = 1.0F - 0.65F;
			rotatedMaxX = 1.0F - 0.0F;
			rotatedMaxZ = 1.0F - 0.0F;
		} 
		else
		{
			rotatedMinX = 0.0F;
			rotatedMinZ = 0.0F;
			rotatedMaxX = 0.65F;
			rotatedMaxZ = 1.0F;
		}
		
		setBlockBounds(rotatedMinX, 0.75F, rotatedMinZ, rotatedMaxX, 1.0F, rotatedMaxZ);
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
		this.blockIcon = register.registerIcon("decoBlockFurniture_" + this.m_Tag);
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
		
		// BRACE: LEFT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.70F, 0.15F, 0.45F, 0.85F, 0.3F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BRACE: RIGHT
		SetRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.70F, 0.70F, 0.45F, 0.85F, 0.85F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// SHELF
		SetRenderBoundsRotatedAboutJToFacing(render, 0.0F, 0.85F, 0.0F, 0.65F, 1.0F, 1.0F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BACK
		SetRenderBoundsRotatedAboutJToFacing(render, 0.0F, 0.70F, 0.025F, 0.15F, 0.85F, 0.975F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		return true;
	}
	
	public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
	{
		// BRACES
		render.setRenderBounds(0.025F, 0.70F, 0.15F, 0.45F, 0.85F, 0.3F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		render.setRenderBounds(0.025F, 0.70F, 0.70F, 0.45F, 0.85F, 0.85F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// SHELF
		render.setRenderBounds(0.0F, 0.85F, 0.0F, 0.65F, 1.0F, 1.0F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// BACK
		render.setRenderBounds(0.0F, 0.70F, 0.025F, 0.15F, 0.85F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
	}
}
