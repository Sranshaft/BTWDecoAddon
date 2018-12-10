package net.minecraft.src;

import java.util.Random;

public class DecoBlockBell extends Block
{
	private Icon m_IconSide, m_IconTop, m_IconBottom, m_IconChain, m_IconSupport;
	private String m_Tag;

	public DecoBlockBell(int id, Material material, String tag)
	{
		super(id, material);

		this.setUnlocalizedName("decoBlockBell." + tag);
		this.setHardness(Block.blockIron.blockHardness);
		this.setResistance(Block.blockIron.blockResistance);
		this.setTickRandomly(true);

		this.m_Tag = tag;
	}

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		if (this.m_Tag == "gold") return DecoModuleDecoration.decoSubModuleBell.decoItemBellGold.itemID;
		if (this.m_Tag == "iron") return DecoModuleDecoration.decoSubModuleBell.decoItemBellIron.itemID;
		if (this.m_Tag == "soulforgedSteel") return DecoModuleDecoration.decoSubModuleBell.decoItemBellSoulforgedSteel.itemID;

		return 0;
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int var1, Random var2, int var3)
	{
		if (this.m_Tag == "gold") return DecoModuleDecoration.decoSubModuleBell.decoItemBellGold.itemID;
		if (this.m_Tag == "iron") return DecoModuleDecoration.decoSubModuleBell.decoItemBellIron.itemID;
		if (this.m_Tag == "soulforgedSteel") return DecoModuleDecoration.decoSubModuleBell.decoItemBellSoulforgedSteel.itemID;

		return 0;
	}

	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if (side > 1) side = side + 4;

		return this.SetFacingInMetadata(metadata, side);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		if (metadata == 1) metadata = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(entity);

		this.SetFacing(world, x, y, z, metadata);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (((hitX > 0.3125F && hitX < 0.6875F) || (hitZ > 0.3125F && hitZ < 0.6875F)) && (hitY > 0.125 && hitY < 0.875F))
		{
			this.playBellSound(world, x, y, z);
			return true;
		}

		return false;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random random) 
	{
		if (!world.isRaining() && world.rainingStrength < 0.5F) return;
		if (world.rainingStrength > 0.5F) this.playBellSound(world, x, y, z);
	}

	private void playBellSound(World world, int x, int y, int z)
	{
		float pitchOffset = 0.25F;

		if (this.m_Tag == "gold") pitchOffset = 1.0F;
		if (this.m_Tag == "iron") pitchOffset = 0.75F;

		world.playSoundEffect((double)x, (double)y, (double)z, "random.orb", 1.0F, pitchOffset);
	}

	public int GetFacing(IBlockAccess bAccess, int x, int y, int z)
	{
		return bAccess.getBlockMetadata(x, y, z);
	}

	public void SetFacing(World world, int x, int y, int z, int metadata)
	{
		world.setBlockMetadataWithNotify(x, y, z, metadata);
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
		return 0;
	}

	public boolean ToggleFacing(World world, int x, int y, int z, boolean canToggle)
	{
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		float minY = 0.1875F;
		float maxY = 0.6875F;

		float rotatedMinX = 0.3125F;
		float rotatedMinZ = 0.3125F;
		float rotatedMaxX = 0.6875F;
		float rotatedMaxZ = 0.6875F;

		switch (metadata)
		{
			case 2:
			{
				rotatedMinX = 1.0F - 0.9375F;
				rotatedMinZ = 1.0F - 0.625F;
				rotatedMaxX = 1.0F - 0.0625F;
				rotatedMaxZ = 1.0F - 0.375F;
	
				break;
			}
			case 3:
			{
				rotatedMinX = 0.0625F;
				rotatedMinZ = 0.375F;
				rotatedMaxX = 0.9375F;
				rotatedMaxZ = 0.625F;
	
				break;
			}
			case 4:
			{
				rotatedMinX = 1.0F - 0.625F;
				rotatedMinZ = 1.0F - 0.9375F;
				rotatedMaxX = 1.0F - 0.375F;
				rotatedMaxZ = 1.0F - 0.0625F;
	
				break;
			}
			case 5:
			{
				rotatedMinX = 0.375F;
				rotatedMinZ = 0.0625F;
				rotatedMaxX = 0.625F;
				rotatedMaxZ = 0.9375F;
	
				break;
			}
		}

		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + rotatedMinX), (double) ((float) y + minY), (double) ((float) z + rotatedMinZ), 
												   (double) ((float) x + rotatedMaxX), (double) ((float) y + maxY), (double) ((float) z + rotatedMaxZ));
	}

	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		int metadata = bAccess.getBlockMetadata(x, y, z);

		float minY = 0.1875F;
		float maxY = 0.6875F;

		if (metadata > 0 && metadata < 6) maxY = 1.0F;

		float rotatedMinX = 0.3125F;
		float rotatedMinZ = 0.3125F;
		float rotatedMaxX = 0.6875F;
		float rotatedMaxZ = 0.6875F;

		switch (metadata)
		{
			case 2:
			{
				rotatedMinX = 1.0F - 0.9375F;
				rotatedMinZ = 1.0F - 0.625F;
				rotatedMaxX = 1.0F - 0.0625F;
				rotatedMaxZ = 1.0F - 0.375F;
	
				break;
			}
			case 3:
			{
				rotatedMinX = 0.0625F;
				rotatedMinZ = 0.375F;
				rotatedMaxX = 0.9375F;
				rotatedMaxZ = 0.625F;
	
				break;
			}
			case 4:
			{
				rotatedMinX = 1.0F - 0.625F;
				rotatedMinZ = 1.0F - 0.9375F;
				rotatedMaxX = 1.0F - 0.375F;
				rotatedMaxZ = 1.0F - 0.0625F;
	
				break;
			}
			case 5:
			{
				rotatedMinX = 0.375F;
				rotatedMinZ = 0.0625F;
				rotatedMaxX = 0.625F;
				rotatedMaxZ = 0.9375F;
	
				break;
			}
		}

		this.setBlockBounds(rotatedMinX, minY, rotatedMinZ, rotatedMaxX, maxY, rotatedMaxZ);
	}

	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
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

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister0. This
	 * is the only chance you get to register icons0.
	 */
	public void registerIcons(IconRegister register)
	{
		this.m_IconSide = register.registerIcon("decoBlockBell_" + this.m_Tag + "_side");
		this.m_IconTop = register.registerIcon("decoBlockBell_" + this.m_Tag + "_top");
		this.m_IconBottom = register.registerIcon("decoBlockBell_" + this.m_Tag + "_bottom");
		this.m_IconChain = register.registerIcon("decoBlockChain");
		this.m_IconSupport = register.registerIcon("decoBlockFurniture_oak");
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture0. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return side < 2 ? side == 0 ? this.m_IconBottom : this.m_IconTop : this.m_IconSide;
	}

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		int direction = this.GetFacing(render.blockAccess, x, y, z);
		int metadata = render.blockAccess.getBlockMetadata(x, y, z);

		// BODY : CLACKER
		render.setRenderBounds(0.4375F, 0.1875F, 0.4375F, 0.5625F, 0.25F, 0.5625F);
		render.renderStandardBlock(this, x, y, z);

		// BODY : RIM
		render.setRenderBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.375F, 0.75F);
		render.renderStandardBlock(this, x, y, z);

		// BODY : MAIN
		render.setRenderBounds(0.3125F, 0.375F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);
		render.renderStandardBlock(this, x, y, z);

		// BODY : NUB
		render.setRenderBounds(0.4375F, 0.6875F, 0.4375F, 0.5625F, 0.75F, 0.5625F);
		render.renderStandardBlock(this, x, y, z);

		if (metadata > 1 && metadata < 6)
		{
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.375F, 0.75F, 0.4375F, 0.625F, 0.9375F, 0.5625F, direction);
			render.renderStandardBlock(this, x, y, z);

			// BODY : LEFT SUPPORT
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.375F, 0.0F, 0.0625F, 0.625F, 1.0F, 0.125F, direction);
			render.setOverrideBlockTexture(this.m_IconSupport);
			render.renderStandardBlock(this, x, y, z);

			// BODY : RIGHT SUPPORT
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.375F, 0.0F, 0.875F, 0.625F, 1.0F, 0.9375F, direction);
			render.renderStandardBlock(this, x, y, z);

			// BODY : CROSS SUPPORT
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.4375F, 0.75F, 0.0F, 0.5625F, 0.875F, 1.0F, direction);
			render.renderStandardBlock(this, x, y, z);
			
			render.clearOverrideBlockTexture();
		}

		if (metadata == 0 && !render.blockAccess.isAirBlock(x, y + 1, z))
		{
			// BODY : CHAIN
			render.setRenderBounds(0.4375F, 0.75F, 0.4375F, 0.5625F, 1.0F, 0.5625F);
			render.setOverrideBlockTexture(this.m_IconSupport);
			render.renderStandardBlock(this, x, y, z);
			render.clearOverrideBlockTexture();
		}
		else
		{
			// BODY : HOLDER
			switch (metadata)
			{
				case 6 : render.setRenderBounds(0.375F, 0.75F, 0.125F, 0.625F, 0.875F, 1.0F); break;
				case 7 : render.setRenderBounds(0.375F, 0.75F, 0.0F, 0.625F, 0.875F, 0.875F); break;
				case 8 : render.setRenderBounds(0.125F, 0.75F, 0.375F, 1.0F, 0.875F, 0.625F); break;
				case 9 : render.setRenderBounds(0.0F, 0.75F, 0.375F, 0.875F, 0.875F, 0.625F); break;
			}

			render.setOverrideBlockTexture(this.m_IconSupport);
			render.renderStandardBlock(this, x, y, z);
			render.clearOverrideBlockTexture();
		}

		return true;
	}
}
