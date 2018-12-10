package net.minecraft.src;

import java.util.List;

public class DecoBlockPlank extends FCBlockPlanks 
{
	public static final String[] PLANK_TYPES = new String[] { "ebonwood", "ironwood" };
	public static final String[] PLANK_NAMES = new String[] { "Ebonwood", "Ironwood" };

	private Icon[] m_IconByMetadataArray;

	public DecoBlockPlank(int id)
	{
		super(id);

		this.setUnlocalizedName("decoBlockPlank");
		this.setHardness(Block.planks.blockHardness);
		this.setResistance(Block.planks.blockResistance);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoAddonManager.register(this, this.PLANK_TYPES, this.PLANK_NAMES, " Planks");
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return (metadata < 0) || (metadata >= this.m_IconByMetadataArray.length) ? this.m_IconByMetadataArray[0] : this.m_IconByMetadataArray[metadata];
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister register)
	{
		this.m_IconByMetadataArray = new Icon[this.PLANK_TYPES.length];

		for (int index = 0; index < this.m_IconByMetadataArray.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("wood_" + this.PLANK_TYPES[index]);
		}
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int index = 0; index < this.m_IconByMetadataArray.length; index++)
		{
			par3List.add(new ItemStack(par1, 1, index));
		}
	}
}
