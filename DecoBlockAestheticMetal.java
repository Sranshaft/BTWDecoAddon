package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockAestheticMetal extends Block
{
	private static final String[] AESTHETIC_TYPE = new String[] { "plateGold", "plateIron", "plateSoulforgedSteel" };
	private static final String[] AESTHETIC_NAME = new String[] { "Gold Plate", "Iron Plate", "Soulforged Steel Plate" };

	private Icon[] m_IconByMetadataArray;
	private int m_ChanceToDry = 35;

	public DecoBlockAestheticMetal(int id)
	{
		super(id, Material.iron);

		this.setUnlocalizedName("decoBlockAestheticMetal");
		this.setHardness(Block.blockIron.blockHardness);
		this.setResistance(Block.blockIron.blockResistance);
		this.setStepSound(Block.soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoAddonManager.register(this, this.AESTHETIC_TYPE, this.AESTHETIC_NAME);
	}

	public int damageDropped(int metadata)
	{
		return metadata;
	}

	public int idDropped(int par1, Random random, int par3)
	{
		return DecoModuleBuilding.decoSubModuleAestheticMetal.decoBlockAestheticMetalID;
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister register)
	{
		this.m_IconByMetadataArray = new Icon[this.AESTHETIC_TYPE.length];

		for (int index = 0; index < this.AESTHETIC_TYPE.length; index++)
		{
			this.m_IconByMetadataArray[index] = register.registerIcon("decoBlockAestheticMetal_" + this.AESTHETIC_TYPE[index]);
		}
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int side, int metadata)
	{
		return this.m_IconByMetadataArray[metadata];
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

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y, z);
	}

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}    
}