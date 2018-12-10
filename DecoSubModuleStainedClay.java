package net.minecraft.src;

/**Adds hardened and stained clay blocks, stairs and walls*/
public class DecoSubModuleStainedClay implements DecoISubModule
{
	public static Block[] decoBlockStainedClayStairs;
	public static DecoBlockSlab[] decoBlockStainedClaySlab;
	public static DecoBlockSlab[] decoBlockStainedClaySlabTop;

	public static Block decoBlockStainedClay;
	public static Block decoBlockStainedClayMouldingAndDecorative;
	public static Block decoBlockStainedClaySidingAndCorner;
	public static Block decoBlockStainedClayWall;

	public static Item[] decoItemStainedClaySlab;
	public static Item[] decoItemStainedClaySlabTop;

	public static final int	decoBlockStainedClayID = DecoAddonManager.getBlockID("decoBlockStainedClayID");
	public static final int	decoBlockStainedClayMouldingAndDecorativeID = DecoAddonManager.getBlockID("decoBlockStainedClayMouldingAndDecorativeID");
	public static final int	decoBlockStainedClaySidingAndCornerID = DecoAddonManager.getBlockID("decoBlockStainedClaySidingAndCornerID");
	public static final int	decoBlockStainedClaySlabID = DecoAddonManager.getBlockID("decoBlockStainedClaySlabID");
	public static final int	decoBlockStainedClaySlabTopID = DecoAddonManager.getBlockID("decoBlockStainedClaySlabTopID");
	public static final int	decoBlockStainedClayStairsID = DecoAddonManager.getBlockID("decoBlockStainedClayStairsID");
	public static final int	decoBlockStainedClayWallID = DecoAddonManager.getBlockID("decoBlockStainedClayWallID");

	public DecoSubModuleStainedClay() 
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Stained Clay");

		this.decoBlockStainedClay = new DecoBlockStainedClay(this.decoBlockStainedClayID, Material.rock);

		this.decoBlockStainedClayStairs = new Block[DecoUtilsStrings.COLOUR_TAGS.length];
		//this.decoBlockStainedClaySlab = new DecoBlockSlab[DecoUtilsStrings.COLOUR_TAGS.length];
		//this.decoBlockStainedClaySlabTop = new DecoBlockSlab[DecoUtilsStrings.COLOUR_TAGS.length];
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			/*this.decoBlockStainedClaySlab[index] = new DecoBlockSlab(this.decoBlockStainedClaySlabID + index, this.decoBlockStainedClay, false, 
					this.decoBlockStainedClaySlab[index], this.decoBlockStainedClaySlabTop[index], index);
			this.decoBlockStainedClaySlabTop[index] = new DecoBlockSlab(this.decoBlockStainedClaySlabTopID + index, this.decoBlockStainedClay, false, 
				this.decoBlockStainedClaySlab[index], this.decoBlockStainedClaySlabTop[index], index);*/

			this.decoBlockStainedClayStairs[index] = new DecoBlockStair(this.decoBlockStainedClayStairsID + index, this.decoBlockStainedClay, 
					DecoUtilsStrings.COLOUR_TAGS[index], index);

			//this.decoBlockStainedClaySlab[index].overrideUnlocalizedName(this.decoBlockStainedClay.getUnlocalizedName2() + ".slab." + DecoUtilsStrings.COLOUR_TAGS[index]);
			//this.decoBlockStainedClaySlabTop[index].overrideUnlocalizedName(this.decoBlockStainedClay.getUnlocalizedName2() + ".slab." + DecoUtilsStrings.COLOUR_TAGS[index]);
		}

		this.decoBlockStainedClayWall = new DecoBlockWall(this.decoBlockStainedClayWallID, this.decoBlockStainedClay,
				DecoUtilsStrings.COLOUR_TAGS, DecoUtilsStrings.COLOUR_NAMES, DecoUtilsStrings.COLOUR_TAGS, "decoBlockStainedClay_", " Stained Clay" );

		/*this.decoItemStainedClaySlab = new Item[DecoUtilsStrings.COLOUR_TAGS.length];
		this.decoItemStainedClaySlabTop = new Item[DecoUtilsStrings.COLOUR_TAGS.length];
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			this.decoItemStainedClaySlab[index] = new DecoItemSlab((this.decoBlockStainedClaySlabID + index) - 256, 
					this.decoBlockStainedClaySlab[index], this.decoBlockStainedClaySlabTop[index], false).setUnlocalizedName(this.decoBlockStainedClay.getUnlocalizedName2() + ".slab." + DecoUtilsStrings.COLOUR_TAGS[index]);
			this.decoItemStainedClaySlabTop[index] = new DecoItemSlab((this.decoBlockStainedClaySlabTopID + index) - 256, 
					this.decoBlockStainedClaySlab[index], this.decoBlockStainedClaySlabTop[index], true).setUnlocalizedName(this.decoBlockStainedClay.getUnlocalizedName2() + ".slab." + DecoUtilsStrings.COLOUR_TAGS[index]);
		}*/

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() 
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			//DecoAddonManager.register(this.decoBlockStainedClaySlab[index], DecoUtilsStrings.COLOUR_NAMES[index] + " Stained Clay Slab");
			//DecoAddonManager.register(this.decoBlockStainedClaySlabTop[index], DecoUtilsStrings.COLOUR_NAMES[index] + " Stained Clay Slab");
			DecoAddonManager.register(this.decoBlockStainedClayStairs[index], DecoUtilsStrings.COLOUR_NAMES[index] + " Stained Clay Stairs");

			//DecoAddonManager.replaceItem(this.decoBlockStainedClaySlabID + index, this.decoItemStainedClaySlab[index]);
			//DecoAddonManager.replaceItem(this.decoBlockStainedClaySlabTopID + index, this.decoItemStainedClaySlabTop[index]);
		}
	}

	public void addRecipes()
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(this.decoBlockStainedClay, 1, index), 
					new ItemStack[] { new ItemStack(DecoSubModuleHardenedClay.decoBlockHardenedClay), new ItemStack(Item.dyePowder, 1, ~index & 15) });

			//DecoUtilsRecipes.addSlabRecipe(this.decoBlockStainedClay, index, this.decoItemStainedClaySlab[index], index, 3);
			DecoUtilsRecipes.addStairsRecipe(new ItemStack(this.decoBlockStainedClay, 1, index), this.decoBlockStainedClayStairs[index], 4);
			DecoUtilsRecipes.addWallRecipe(new ItemStack(this.decoBlockStainedClay, 1, index), new ItemStack(this.decoBlockStainedClayWall, 1, index), 4);
		}
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties()
	{
		this.decoBlockStainedClay.SetPicksEffectiveOn(true);
		this.decoBlockStainedClayWall.SetPicksEffectiveOn(true);

		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			//this.decoBlockStainedClaySlab[index].SetPicksEffectiveOn(true);
			//this.decoBlockStainedClaySlabTop[index].SetPicksEffectiveOn(true);
			this.decoBlockStainedClayStairs[index].SetPicksEffectiveOn(true);
		}
	}
}
