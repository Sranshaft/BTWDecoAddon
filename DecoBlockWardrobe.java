package net.minecraft.src;

public class DecoBlockWardrobe extends Block
{
	private String m_Tag;
	
	public DecoBlockWardrobe(int id, String tag)
	{
		super(id, Material.wood);
		
		this.setUnlocalizedName("decoBlockWardrobe." + tag);
		this.setHardness(Block.wood.blockHardness);
		this.setResistance(Block.wood.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		Block.useNeighborBrightness[id] = true;
		Block.lightOpacity[id] = 255;

		this.m_Tag = tag;
	}
	
	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y, z);
	}
	
	public boolean DoesBlockHaveSolidTop(IBlockAccess bAccess, int x, int y, int z) 
	{
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
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int direction = this.GetFacing(world, x, y, z);
		return direction != 2 && direction != 3 ? AxisAlignedBB.getAABBPool().getAABB((double) ((float) x), (double) ((float) y), (double) ((float) z), 
																					  (double) ((float) x + 1.0F), (double) ((float) y + 2.0F), (double) ((float) z + 1.0F)) 
												: AxisAlignedBB.getAABBPool().getAABB((double) ((float) x), (double) ((float) y), (double) ((float) z), 
																				      (double) ((float) x + 1.0F), (double) ((float) y + 2.0F), (double) ((float) z + 1.0F));
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
			rotatedMinX = 1.0F - 1.0F;
			rotatedMinZ = 1.0F - 1.0F;
			rotatedMaxX = 1.0F - 0.0F;
			rotatedMaxZ = 1.0F - 0.0F;
		} 
		else if (direction == 3)
		{
			rotatedMinX = 0.0F;
			rotatedMinZ = 0.0F;
			rotatedMaxX = 1.0F;
			rotatedMaxZ = 1.0F;
		} 
		else if (direction == 2)
		{
			rotatedMinX = 1.0F - 1.0F;
			rotatedMinZ = 1.0F - 1.0F;
			rotatedMaxX = 1.0F - 0.0F;
			rotatedMaxZ = 1.0F - 0.0F;
		} 
		else
		{
			rotatedMinX = 0.0F;
			rotatedMinZ = 0.0F;
			rotatedMaxX = 1.0F;
			rotatedMaxZ = 1.0F;
		}
		
		setBlockBounds(rotatedMinX, 0.0F, rotatedMinZ, rotatedMaxX, 2.0F, rotatedMaxZ);
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
	
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		int direction = GetFacing(render.blockAccess, x, y, z);
		
		// LEGS
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.0F, 0.025F, 0.15F, 1.85F, 0.15F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.0F, 0.85F, 0.15F, 1.85F, 0.975F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.85F, 0.0F, 0.025F, 0.975F, 1.85F, 0.15F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.85F, 0F, 0.85F, 0.975F, 1.85F, 0.975F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		
		// SHELVES
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.1F, 0.2F, 0.1F, 0.9F, 0.35F, 0.9F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.1F, 0.55F, 0.1F, 0.9F, 0.65F, 0.9F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		
		// SIDE: TOP
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.0F, 1.85F, 0.0F, 1.0F, 2.0F, 1.0F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		
		// SIDE: LEFT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.05F, 0.2F, 0.05F, 0.95F, 1.85F, 0.1F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		
		// SIDE: RIGHT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.05F, 0.2F, 0.05F, 0.1F, 1.85F, 0.95F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		
		// SIDE: BACK
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.05F, 0.2F, 0.9F, 0.95F, 1.85F, 0.95F, direction);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, blockIcon);
		
		return true;
	}
	
	public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
	{
		//Legs
		render.setRenderBounds(0.025F, 0.0F, 0.025F, 0.15F, 1.85F, 0.15F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		render.setRenderBounds( 0.025F, 0.0F, 0.85F, 0.15F, 1.85F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		render.setRenderBounds(0.85F, 0.0F, 0.025F, 0.975F, 1.85F, 0.15F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		render.setRenderBounds(0.85F, 0F, 0.85F, 0.975F, 1.85F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		
		//Shelves
		render.setRenderBounds(0.1F, 0.2F, 0.1F, 0.9F, 0.35F, 0.9F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		render.setRenderBounds(0.1F, 0.5F, 0.1F, 0.9F, 0.65F, 0.9F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		
		//Top
		render.setRenderBounds(0.0F, 0.75F, 0.0F, 1.0F, 0.9F, 1.0F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		
		//Sides
		render.setRenderBounds(0.05F, 0.2F, 0.05F, 0.95F, 1.85F, 0.1F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		render.setRenderBounds(0.05F, 0.2F, 0.05F, 0.1F, 1.85F, 0.95F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
		render.setRenderBounds(0.05F, 0.2F, 0.9F, 0.95F, 1.85F, 0.95F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.75F, -0.5F, blockIcon);
	}
}
