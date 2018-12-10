package net.minecraft.src;

import java.util.Random;

public class DecoBlockPlanter extends FCBlockPlanter
{
	public DecoBlockPlanter(int id)
	{
		super(id);
		this.setHardness(0.6F);
		this.setStepSound(soundGlassFootstep);
		this.setUnlocalizedName("fcBlockPlanter");
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.SetPicksEffectiveOn(true);
		this.setTickRandomly(true);

		Block.useNeighborBrightness[id] = true;
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon("fcBlockPlanter");
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return this.blockIcon;
	}

	public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
	{
		IBlockAccess var5 = var1.blockAccess;
		int var6 = this.GetPlanterType(var5, var2, var3, var4);
		return var6 == 0 ? RenderEmptyPlanterBlock(var1, var5, var2, var3, var4, this) : RenderFilledPlanterBlock(var1, var5, var2, var3, var4, this);
	}

	public static boolean RenderEmptyPlanterBlock(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5)
	{
		var0.setRenderBounds(0.125D, 0.0D, 0.125D, 0.25D, 0.6875D, 0.75D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		var0.setRenderBounds(0.125D, 0.0D, 0.75D, 0.75D, 0.6875D, 0.875D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		var0.setRenderBounds(0.75D, 0.0D, 0.25D, 0.875D, 0.6875D, 0.875D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		var0.setRenderBounds(0.25D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.25D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		var0.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		var0.setRenderBounds(0.0D, 0.6875D, 0.0D, 0.125D, 1.0D, 0.875D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		var0.setRenderBounds(0.0D, 0.6875D, 0.875D, 0.875D, 1.0D, 1.0D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		var0.setRenderBounds(0.875D, 0.6875D, 0.125D, 1.0D, 1.0D, 1.0D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		var0.setRenderBounds(0.125D, 0.6875D, 0.0D, 1.0D, 1.0D, 0.125D);
		var0.renderStandardBlock(var5, var2, var3, var4);
		return true;
	}

	public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
	{
		if (var2 == 0) RenderEmptyPlanterInvBlock(var1, this, var2); else RenderFilledPlanterInvBlock(var1, this, var2);
	}

	public static void RenderEmptyPlanterInvBlock(RenderBlocks var0, Block var1, int var2)
	{
		var0.setRenderBounds(0.125D, 0.0D, 0.125D, 0.25D, 0.6875D, 0.75D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
		var0.setRenderBounds(0.125D, 0.0D, 0.75D, 0.75D, 0.6875D, 0.875D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
		var0.setRenderBounds(0.75D, 0.0D, 0.25D, 0.875D, 0.6875D, 0.875D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
		var0.setRenderBounds(0.25D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.25D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
		var0.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
		var0.setRenderBounds(0.0D, 0.6875D, 0.0D, 0.125D, 1.0D, 0.875D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
		var0.setRenderBounds(0.0D, 0.6875D, 0.875D, 0.875D, 1.0D, 1.0D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
		var0.setRenderBounds(0.875D, 0.6875D, 0.125D, 1.0D, 1.0D, 1.0D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
		var0.setRenderBounds(0.125D, 0.6875D, 0.0D, 1.0D, 1.0D, 0.125D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
	}

	public static boolean RenderFilledPlanterBlock(RenderBlocks render, IBlockAccess bAccess, int x, int y, int z, Block block)
	{
		render.setRenderBounds(0.125D, 0.0D, 0.125D, 0.25D, 0.6875D, 0.75D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.125D, 0.0D, 0.75D, 0.75D, 0.6875D, 0.875D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.75D, 0.0D, 0.25D, 0.875D, 0.6875D, 0.875D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.25D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.25D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.0D, 0.6875D, 0.0D, 0.125D, 1.0D, 0.875D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.0D, 0.6875D, 0.875D, 0.875D, 1.0D, 1.0D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.875D, 0.6875D, 0.125D, 1.0D, 1.0D, 1.0D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.125D, 0.6875D, 0.0D, 1.0D, 1.0D, 0.125D);
		render.renderStandardBlock(block, x, y, z);
		render.setRenderBounds(0.125D, 0.85D, 0.125D, 0.875D, 0.95D, 0.875D);

		switch (bAccess.getBlockMetadata(x, y, z))
		{
			case 1 : 
			{
				if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
					render.setOverrideBlockTexture(render.getBlockIconFromSide(DecoModuleTweaks.decoSubModuleExtendedDirtAndGrassBlocks.decoBlockFarmland, 1));
				else
					render.setOverrideBlockTexture(render.getBlockIconFromSide(Block.tilledField, 1));
	
				render.renderStandardBlock(block, x, y, z);
				break;
			}
	
			case 2 : 
			{
				render.setOverrideBlockTexture(render.getBlockIconFromSide(FCBetterThanWolves.fcBlockFarmlandFertilized, 1)); 
				render.renderStandardBlock(block, x, y, z);
				break;
			}
			case 8 : 
			{
				render.setOverrideBlockTexture(render.getBlockIcon(Block.slowSand)); 
				render.renderStandardBlock(block, x, y, z);
				break;
			}
			case 9 :
			case 11 : 
			case 13 :
			case 15 : 
			{
				if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
					render.setOverrideBlockTexture(render.getBlockIconFromSide(DecoModuleTweaks.decoSubModuleExtendedDirtAndGrassBlocks.decoBlockGrass, 1));
				else
					render.setOverrideBlockTexture(render.getBlockIconFromSide(Block.grass, 1));
	
				render.renderStandardBlockWithColorMultiplier(block, x, y, z, (float)getRGB(bAccess, x, y, z)[0], (float)getRGB(bAccess, x, y, z)[1], (float)getRGB(bAccess, x, y, z)[2]);
	
				break;
			}
		}

		render.clearOverrideBlockTexture();

		return true;
	}

	public static void RenderFilledPlanterInvBlock(RenderBlocks render, Block block, int metadata)
	{
		render.setRenderBounds(0.125D, 0.0D, 0.125D, 0.25D, 0.6875D, 0.75D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.setRenderBounds(0.125D, 0.0D, 0.75D, 0.75D, 0.6875D, 0.875D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.setRenderBounds(0.75D, 0.0D, 0.25D, 0.875D, 0.6875D, 0.875D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.setRenderBounds(0.25D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.25D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.setRenderBounds(0.0D, 0.6875D, 0.0D, 0.125D, 1.0D, 0.875D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.setRenderBounds(0.0D, 0.6875D, 0.875D, 0.875D, 1.0D, 1.0D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.setRenderBounds(0.875D, 0.6875D, 0.125D, 1.0D, 1.0D, 1.0D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.setRenderBounds(0.125D, 0.6875D, 0.0D, 1.0D, 1.0D, 0.125D);
		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);

		render.setRenderBounds(0.125D, 0.85D, 0.125D, 0.875D, 0.95D, 0.875D);

		switch (metadata)
		{
			case 1 : 
			{
				if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
					render.setOverrideBlockTexture(render.getBlockIcon(DecoModuleTweaks.decoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt));
				else
					render.setOverrideBlockTexture(render.getBlockIcon(Block.dirt));
	
				break;
			}
	
			case 2 : render.setOverrideBlockTexture(render.getBlockIconFromSide(FCBetterThanWolves.fcBlockFarmlandFertilized, 1)); break;
			case 8 : render.setOverrideBlockTexture(render.getBlockIcon(Block.slowSand)); break;
			case 9 :
			case 11 : 
			case 13 :
			case 15 : 
			{
				if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
					render.setOverrideBlockTexture(render.getBlockIconFromSide(DecoModuleTweaks.decoSubModuleExtendedDirtAndGrassBlocks.decoBlockGrass, 1));
				else
					render.setOverrideBlockTexture(render.getBlockIconFromSide(Block.grass, 1));
	
				break;
			}
		}

		FCClientUtilsRender.RenderInvBlockWithMetadata(render, block, -0.5F, -0.5F, -0.5F, metadata);
		render.clearOverrideBlockTexture();
	}

	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
	 * when first determining what to render.
	 */
	public static int[] getRGB(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int var5 = 0;
		int var6 = 0;
		int var7 = 0;

		for (int var8 = -1; var8 <= 1; ++var8)
		{
			for (int var9 = -1; var9 <= 1; ++var9)
			{
				int var10 = par1IBlockAccess.getBiomeGenForCoords(par2 + var9, par4 + var8).getBiomeGrassColor();
				var5 += (var10 & 16711680) >> 16;
				var6 += (var10 & 65280) >> 8;
				var7 += var10 & 255;
			}
		}

		return new int[] { (var5 / 9 & 255) << 16, (var6 / 9 & 255) << 8, var7 / 9 & 255};
	}
}
