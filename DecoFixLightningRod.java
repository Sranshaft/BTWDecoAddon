package net.minecraft.src;

import java.util.ArrayList;

public class DecoFixLightningRod extends FCBlockAestheticNonOpaque
{
	private static Icon m_IconSide;
	private static ArrayList m_BlocksThatCanBeHeld = new ArrayList();
	
	public DecoFixLightningRod(int id)
	{
		super(id);
		setCreativeTab(CreativeTabs.tabDecorations);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this);
	}
	
	public static void AddHoldableBlock(Block block)
	{
		AddHoldableBlockID(block.blockID);
	}
	
	public static void AddHoldableBlockID(int id)
	{
		m_BlocksThatCanBeHeld.add(id);
	}

	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        super.registerIcons(register);
    }
	
	public boolean RenderLightningRod(RenderBlocks render, IBlockAccess blockAccess, int x, int y, int z, Block block)
	{
		render.setRenderBounds(0.46875D, 0.0D, 0.46875D, 0.53125D, 1.0D, 0.53125D);
		FCClientUtilsRender.RenderStandardBlockWithTexture(render, block, x, y, z, this.m_IconSide);

		if (blockAccess.getBlockId(x, y - 1, z) != FCBetterThanWolves.fcAestheticNonOpaque.blockID || blockAccess.getBlockMetadata(x, y - 1, z) != 12)
		{
			render.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D);
			FCClientUtilsRender.RenderStandardBlockWithTexture(render, block, x, y, z, this.m_IconSide);
		}

		int var8 = blockAccess.getBlockId(x, y + 1, z);
		int var9 = blockAccess.getBlockMetadata(x, y + 1, z);

		if (var8 != FCBetterThanWolves.fcAestheticNonOpaque.blockID || var9 != 12)
		{
			if (var8 == FCBetterThanWolves.fcBlockCandle.blockID || m_BlocksThatCanBeHeld.contains(var8))
				render.setRenderBounds(0.375D, 0.99609375D, 0.375D, 0.625D, 1.05859375D, 0.625D);
			else
				render.setRenderBounds(0.40625D, 0.625D, 0.40625D, 0.59375D, 0.8125D, 0.59375D);
			
			FCClientUtilsRender.RenderStandardBlockWithTexture(render, block, x, y, z, this.m_IconSide);
		}

		return true;
	}
}