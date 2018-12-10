package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockPaneStainedGlass extends DecoBlockPane
{
	public DecoBlockPaneStainedGlass(int id)
	{
		super(id, Material.glass);
		
		this.setUnlocalizedName("decoBlockPaneStainedGlass");
		this.setHardness(0.3F);
		this.setResistance(0.5F);
		this.setStepSound(soundGlassFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.canOverrideConnection = true;
		
		DecoAddonManager.register(this, DecoUtilsStrings.COLOUR_TAGS, DecoUtilsStrings.COLOUR_NAMES, " Stained Glass Pane");
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
	
	public int damageDropped(int metadata)
	{
		return metadata;
	}
	
	public int idDropped(int metadata, Random random, int fortune)
	{
		if (!DecoAddonManager.getConfigOption("enableGlassShards")) return 0;
		return DecoModuleTweaks.decoItemGlassShard.itemID;
	}
	
	public int quantityDropped(Random random)
	{
		if (!DecoAddonManager.getConfigOption("enableGlassShards")) return 0;
		return random.nextInt(3);
	}
	
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		if (!DecoAddonManager.getConfigOption("enableGlassShards")) return 0;
		return Math.min(4, this.quantityDropped(random) + random.nextInt(fortune + 1));
	}
	
	//CLIENT ONLY
	
	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register) 
	{
		this.m_IconByMetadataArrayFaces = new Icon[DecoUtilsStrings.COLOUR_TAGS.length];
		this.m_IconByMetadataArraySides = new Icon[DecoUtilsStrings.COLOUR_TAGS.length];
		
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			this.m_IconByMetadataArrayFaces[index] = register.registerIcon("decoBlockPaneStainedGlass_" + DecoUtilsStrings.COLOUR_TAGS[index] + "_face");
			this.m_IconByMetadataArraySides[index] = register.registerIcon("decoBlockPaneStainedGlass_" + DecoUtilsStrings.COLOUR_TAGS[index] + "_side");
		}
	}
	
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int var1, CreativeTabs var2, List var3)
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			var3.add(new ItemStack(var1, 1, index));
		}
	}
	
	public int getRenderBlockPass()
	{
		return 1;
	}
}
