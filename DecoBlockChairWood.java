package net.minecraft.src;

public class DecoBlockChairWood extends Block implements DecoIBlockWithMetadata 
{
	private String m_Tag;

	public DecoBlockChairWood(int id, Material material, String tag)
	{
		super(id, material);
		
		this.setUnlocalizedName("decoBlockChairWood." + tag);
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

	public int onBlockPlaced(World world, int x, int y, int z, int direction, float hitX, float hitY, float hitZ, int metadata)
	{
		if (direction < 2) direction = 2;
		else direction = FCUtilsMisc.GetOppositeFacing(direction);

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
		this.blockIcon = register.registerIcon("decoBlockFurniture_" + this.m_Tag);
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
		
		// LEG: NW
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.0F, 0.025F, 0.15F, 0.5F, 0.15F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// LEG: SW
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.0F, 0.85F, 0.15F, 0.5F, 0.975F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// LEG: NE
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.85F, 0.0F, 0.025F, 0.975F, 0.5F, 0.15F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// LEG: SE
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.85F, 0.0F, 0.85F, 0.975F, 0.5F, 0.975F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// SEAT
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.5F, 0.025F, 0.975F, 0.65F, 0.975F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		// BACK
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.025F, 0.65F, 0.025F, 0.15F, 1.5F, 0.975F, direction);
		render.renderStandardBlock(this, x, y, z);
		
		return true;
	}
	
	public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
	{
		// LEG: NW
		render.setRenderBounds(0.025F, 0.0F, 0.025F, 0.15F, 0.5F, 0.15F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);

		// LEG: SW
		render.setRenderBounds(0.025F, 0.0F, 0.85F, 0.15F, 0.5F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);

		// LEG: NE
		render.setRenderBounds(0.85F, 0.0F, 0.025F, 0.975F, 0.5F, 0.15F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);

		// LEG: SW
		render.setRenderBounds(0.85F, 0.0F, 0.85F, 0.975F, 0.5F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// SEAT
		render.setRenderBounds(0.025F, 0.5F, 0.025F, 0.975F, 0.65F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		
		// BACK
		render.setRenderBounds(0.025F, 0.65F, 0.025F, 0.15F, 1.5F, 0.975F);
		FCClientUtilsRender.RenderInvBlockWithTexture(render, this, -0.5F, -0.5F, -0.5F, blockIcon);
	}
}