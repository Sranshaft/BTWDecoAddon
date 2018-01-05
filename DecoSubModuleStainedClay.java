package net.minecraft.src;

/**Adds hardened and stained clay blocks, stairs and walls*/
public class DecoSubModuleStainedClay implements DecoISubModule
{
	public static Block decoBlockStainedClay;
	public static Block decoBlockStainedClayMouldingAndDecorative;
	public static Block decoBlockStainedClaySidingAndCorner;
	public static Block decoBlockStainedClaySlab;
	public static Block decoBlockStainedClaySlabTop;
	public static Block decoBlockStainedClayStairs;
	public static Block decoBlockStainedClayWall;
	
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
		
		this.decoBlockStainedClaySlab = new DecoBlockSlab(this.decoBlockStainedClaySlabID, this.decoBlockStainedClay, false, this.decoBlockStainedClaySlabID, this.decoBlockStainedClaySlabTopID, 
				DecoUtilsStrings.COLOUR_TAGS, DecoUtilsStrings.COLOUR_NAMES, "decoBlockStainedClay_", DecoUtilsStrings.COLOUR_TAGS);
		this.decoBlockStainedClaySlabTop = new DecoBlockSlab(this.decoBlockStainedClaySlabTopID, this.decoBlockStainedClay, true, this.decoBlockStainedClaySlabID, this.decoBlockStainedClaySlabTopID, 
				DecoUtilsStrings.COLOUR_TAGS, DecoUtilsStrings.COLOUR_NAMES, "decoBlockStainedClay_", DecoUtilsStrings.COLOUR_TAGS);
		
		//this.decoBlockStainedClayStairs = new DecoBlockStair(this.decoBlockStainedClayStairsID, this.decoBlockStainedClay, 0);
		this.decoBlockStainedClayWall = new DecoBlockWall(this.decoBlockStainedClayWallID, this.decoBlockStainedClay);

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() {}
	
	public void addRecipes()
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(this.decoBlockStainedClay, 1, index), 
					new ItemStack[] { new ItemStack(DecoSubModuleHardenedClay.decoBlockHardenedClay), new ItemStack(Item.dyePowder, 1, index) });
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(this.decoBlockStainedClay, 1, index),
					new ItemStack[] { new ItemStack(DecoSubModuleHardenedClay.decoBlockHardenedClay), new ItemStack(Item.dyePowder, 1, index + 16) });
		}
	}
	
	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties()
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockStainedClay);
	}
}
