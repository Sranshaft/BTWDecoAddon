package net.minecraft.src;

import java.util.Random;

public class DecoBlockLantern extends Block
{
	private Icon m_IconSide, m_IconTop, m_IconChain, m_IconWick;
	private String m_Tag;
	private boolean m_IsAnimated;

	public DecoBlockLantern(int id, Material material, float hardness)
	{
		this(id, material, "", hardness, 10.0F, false);
	}

	public DecoBlockLantern(int id, Material material, String tag, float hardness, float lightValue, boolean animate)
	{
		super(id, material);

		this.setUnlocalizedName("decoBlockLantern." + tag);
		this.setHardness(hardness);
		this.setResistance(hardness / 3);
		this.setLightValue(lightValue);
		if (tag == "wood" || tag == "paper") this.setStepSound(Block.soundWoodFootstep); else this.setStepSound(Block.soundMetalFootstep);

		this.m_IsAnimated = animate;
		this.m_Tag = tag;
	}

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		if (this.m_Tag == "gold") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternGold.itemID;
		if (this.m_Tag == "iron") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternIron.itemID;
		if (this.m_Tag == "paper") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternPaper.itemID;
		if (this.m_Tag == "soulforgedSteel") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternSoulforgedSteel.itemID;
		if (this.m_Tag == "wood") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternWood.itemID;

		return 0;
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int var1, Random var2, int var3)
	{
		if (this.m_Tag == "gold") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternGold.itemID;
		if (this.m_Tag == "iron") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternIron.itemID;
		if (this.m_Tag == "paper") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternPaper.itemID;
		if (this.m_Tag == "soulforgedSteel") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternSoulforgedSteel.itemID;
		if (this.m_Tag == "wood") return DecoModuleDecoration.decoSubModuleLantern.decoItemLanternWood.itemID;

		return 0;
	}

	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int direction)
	{
		this.setBlockBoundsBasedOnState(world, x, y,z);
		return this.SetFacingInMetadata(direction, side);
	}

	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.3125F, 0.0625F, 0.3125F, 0.6875F, 0.5625F, 0.6875F);
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
		return AxisAlignedBB.getAABBPool().getAABB(x + 0.34375F, y + 0.0625F, z + 0.34375F, x + 0.65625F, y + 0.4375F, z + 0.65625F);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		this.setBlockBounds(0.34375F, 0.0625F, 0.34375F, 0.65625F, 0.4375F, 0.65625F);
	}

	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		return true;

		/*return side == 0 && this.minY > 0.0D ? true : (side == 1 && this.maxY < 10.0D ? true : 
			(side == 2 && this.minZ > 0.0D ? true : (side == 3 && this.maxZ < 10.0D ? true : 
				(side == 4 && this.minX > 0.0D ? true : (side == 5 && this.maxX < 10.0D ? true : 
					!bAccess.isBlockOpaqueCube(x, y, z))))));*/
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
	 * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	 */
	public int getRenderBlockPass()
	{
		return 0;
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister0. This
	 * is the only chance you get to register icons0.
	 */
	public void registerIcons(IconRegister register)
	{
		this.m_IconSide = register.registerIcon("decoBlockLantern_" + this.m_Tag + "_side" + (this.m_IsAnimated ? "_animated" : ""));
		this.m_IconTop = register.registerIcon("decoBlockLantern_" + this.m_Tag + "_top");
		this.m_IconChain = register.registerIcon("decoBlockChain");
		this.m_IconWick = register.registerIcon("fcBlockCandleWick");
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture0. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return side < 2 ? this.m_IconTop : this.m_IconSide;
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		double xOffset = (double)((float)x + 0.5F);
		double yOffset = (double)((float)y + 0.125F + 0.2F);
		double zOffset = (double)((float)z + 0.5F);

		world.spawnParticle("flame", xOffset, yOffset, zOffset, 0.0D, 0.0D, 0.0D);
	}

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		int direction = this.GetFacing(render.blockAccess, x, y, z);
		int metadata = render.blockAccess.getBlockMetadata(x, y, z);

		if (this.m_Tag == "paper")
		{
			// BODY : MAIN
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.34375F, 0.0F, 0.34375F, 0.65625F, 0.0625F, 0.65625F, direction);
			render.renderStandardBlock(this, x, y, z);

			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.3125F, 0.0625F, 0.3125F, 0.6875F, 0.4375F, 0.6875F, direction);
			render.renderStandardBlock(this, x, y, z);

			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.34375F, 0.4375F, 0.34375F, 0.65625F, 0.5F, 0.65625F, direction);
			render.renderStandardBlock(this, x, y, z);
		}
		else
		{
			// BODY : MAIN
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.3125F, 0.0F, 0.3125F, 0.6875F, 0.0625F, 0.6875F, direction);
			render.renderStandardBlock(this, x, y, z);

			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.34375F, 0.0625F, 0.34375F, 0.65625F, 0.4375F, 0.65625F, direction);
			render.renderStandardBlock(this, x, y, z);

			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.3125F, 0.4375F, 0.3125F, 0.6875F, 0.5F, 0.6875F, direction);
			render.renderStandardBlock(this, x, y, z);

			// BODY : CANDLE
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.4375F, 0.0625F, 0.4375F, 0.5625F, 0.125F, 0.5625F, direction);
			render.setOverrideBlockTexture(render.getBlockIconFromSideAndMetadata(FCBetterThanWolves.fcBlockCandle, 0, 15));
			render.renderStandardBlock(this, x, y, z);
			render.clearOverrideBlockTexture();
		}

		// BODY : NUB
		DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.40625F, 0.5F, 0.40625F, 0.59375F, 0.5625F, 0.59375F, direction);
		render.renderStandardBlock(this, x, y, z);

		if (metadata == 0 && !render.blockAccess.isAirBlock(x, y + 1, z))
		{
			// BODY : CHAIN
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.4375F, 0.5625F, 0.4375F, 0.5625F, 1.0F, 0.5625F, direction);
			render.setOverrideBlockTexture(this.m_IconChain);
			render.renderStandardBlock(this, x, y, z);
			render.clearOverrideBlockTexture();
		}

		if (metadata != 0 && metadata != 1)
		{
			// BODY : CHAIN
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.4375F, 0.5625F, 0.4375F, 0.5625F, 0.70F, 0.5625F, direction);
			render.setOverrideBlockTexture(this.m_IconChain);
			render.renderStandardBlock(this, x, y, z);
			render.clearOverrideBlockTexture();

			// BODY : HOLDER
			DecoUtilsRender.setRenderBoundsRotatedAboutJToFacing(render, 0.0F, 0.70F, 0.375F, 0.85F, 0.85F, 0.625F, direction);
			render.setOverrideBlockTexture(this.m_IconTop);
			render.renderStandardBlock(this, x, y, z);
			render.clearOverrideBlockTexture();
		}

		return true;
	}

	public void RenderBlockSecondPass(RenderBlocks render, int x, int y, int z, boolean var5)
	{
		if (var5)
		{
			render.setRenderBounds(0.484375D, 0.125D, 0.484375D, 0.515625D, 0.1875D, 0.515625D);
			FCClientUtilsRender.RenderBlockFullBrightWithTexture(render, render.blockAccess, x, y, z, this.m_IconWick);
		}
	}
}