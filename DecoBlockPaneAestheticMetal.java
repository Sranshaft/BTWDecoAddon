package net.minecraft.src;

import java.util.List;

public class DecoBlockPaneAestheticMetal extends DecoBlockPane 
{
	private static final String[] AESTHETIC_TYPE = new String[] { "fenceGold", "fenceSoulforgedSteel", "plateGold", "plateIron", "plateSoulforgedSteel" };
	private static final String[] AESTHETIC_NAME = new String[] { "Gold Bars", "Soulforged Steel Bars", "Gold Plate", "Iron Plate", "Soulforged Steel Plate" };
	
	public DecoBlockPaneAestheticMetal(int id)
	{
		super(id, Material.wood);
		
		this.setUnlocalizedName("decoBlockPaneAestheticMetal");
		this.setHardness(Block.blockIron.blockHardness);
		this.setResistance(Block.blockIron.blockResistance);
		this.setStepSound(Block.soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);

		DecoAddonManager.register(this, this.AESTHETIC_TYPE, this.AESTHETIC_NAME);
	}
	
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister register)
	{
		this.m_IconByMetadataArrayFaces = new Icon[this.AESTHETIC_TYPE.length];
		this.m_IconByMetadataArraySides = new Icon[this.AESTHETIC_TYPE.length];

		for (int index = 0; index < this.AESTHETIC_TYPE.length; index++)
		{
			this.m_IconByMetadataArrayFaces[index] = register.registerIcon("decoBlockPaneAestheticMetal_" + this.AESTHETIC_TYPE[index] + "_face");
			this.m_IconByMetadataArraySides[index] = register.registerIcon("decoBlockPaneAestheticMetal_" + this.AESTHETIC_TYPE[index] + "_side");
		}
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int var1, CreativeTabs var2, List var3)
	{
		for (int index = 0; index < this.AESTHETIC_TYPE.length; index++)
		{
			var3.add(new ItemStack(var1, 1, index));
		}
	}
}
