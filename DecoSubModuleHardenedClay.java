package net.minecraft.src;

public class DecoSubModuleHardenedClay implements DecoISubModule 
{
	public static Block decoBlockHardenedClay;
	public static Block decoBlockHardenedClaySlab;
	public static Block decoBlockHardenedClaySlabTop;
	public static Block decoBlockHardenedClayStairs;
	public static Block decoBlockHardenedClayWall;
	
	public static final int	decoBlockHardenedClayID = DecoAddonManager.getBlockID("decoBlockHardenedClayID");
	public static final int decoBlockHardenedClaySlabID = DecoAddonManager.getBlockID("decoBlockHardenedClaySlabID");
	public static final int decoBlockHardenedClaySlabTopID = DecoAddonManager.getBlockID("decoBlockHardenedClaySlabTopID");
	public static final int	decoBlockHardenedClayStairsID = DecoAddonManager.getBlockID("decoBlockHardenedClayStairsID");
	public static final int	decoBlockHardenedClayWallID = DecoAddonManager.getBlockID("decoBlockHardenedClayWallID");
	
	public DecoSubModuleHardenedClay()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Hardened Clay");
		
		this.decoBlockHardenedClay = new DecoBlockHardenedClay(this.decoBlockHardenedClayID, Material.rock);
		this.decoBlockHardenedClaySlab = new DecoBlockSlab(this.decoBlockHardenedClaySlabID, this.decoBlockHardenedClay, false, this.decoBlockHardenedClaySlabID, this.decoBlockHardenedClaySlabTopID);
		this.decoBlockHardenedClaySlabTop = new DecoBlockSlab(this.decoBlockHardenedClaySlabTopID, this.decoBlockHardenedClay, true, this.decoBlockHardenedClaySlabID, this.decoBlockHardenedClaySlabTopID);
		this.decoBlockHardenedClayStairs = new DecoBlockStair(this.decoBlockHardenedClayStairsID, this.decoBlockHardenedClay, 0);
		this.decoBlockHardenedClayWall = new DecoBlockWall(this.decoBlockHardenedClayWallID, this.decoBlockHardenedClay);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockHardenedClay, "Hardened Clay");
		DecoAddonManager.register(this.decoBlockHardenedClaySlab, "Hardened Clay Slab");
		DecoAddonManager.register(this.decoBlockHardenedClaySlabTop, "Hardened Clay Slab");
		DecoAddonManager.register(this.decoBlockHardenedClayStairs, "Hardened Clay Stairs");
		DecoAddonManager.register(this.decoBlockHardenedClayWall, "Hardened Clay Wall");
	}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockHardenedClay,this.decoBlockHardenedClaySlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockHardenedClay, this.decoBlockHardenedClayStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockHardenedClay, this.decoBlockHardenedClayWall, 2);
	}

	public void changeVanillaItems() 
	{
		Block.blockClay.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(this.decoBlockHardenedClay.blockID);
	}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockHardenedClay);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockHardenedClaySlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockHardenedClaySlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockHardenedClayStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockHardenedClayWall);	
	}
}
