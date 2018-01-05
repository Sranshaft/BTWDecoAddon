package net.minecraft.src;

public class DecoSubModuleMudstoneAndBrick implements DecoISubModule 
{
	public static Block decoBlockMudstone;
	public static Block decoBlockMudstoneSlab;
	public static Block decoBlockMudstoneSlabTop;
	public static Block decoBlockMudstoneStairs;
	public static Block decoBlockMudstoneWall;
	
	public static Block decoBlockMudstoneBrick;
	public static Block decoBlockMudstoneBrickSlab;
	public static Block decoBlockMudstoneBrickSlabTop;
	public static Block decoBlockMudstoneBrickStairs;
	public static Block decoBlockMudstoneBrickWall;
	
	public static final int decoBlockMudstoneID = DecoAddonManager.getBlockID("decoBlockMudstoneID");
	public static final int decoBlockMudstoneSlabID = DecoAddonManager.getBlockID("decoBlockMudstoneSlabID");
	public static final int decoBlockMudstoneSlabTopID = DecoAddonManager.getBlockID("decoBlockMudstoneSlabTopID");
	public static final int decoBlockMudstoneStairsID = DecoAddonManager.getBlockID("decoBlockMudstoneStairsID");
	public static final int decoBlockMudstoneWallID = DecoAddonManager.getBlockID("decoBlockMudstoneWallID");
	
	public static final int decoBlockMudstoneBrickID = DecoAddonManager.getBlockID("decoBlockMudstoneBrickID");
	public static final int decoBlockMudstoneBrickSlabID = DecoAddonManager.getBlockID("decoBlockMudstoneBrickSlabID");
	public static final int decoBlockMudstoneBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockMudstoneBrickSlabTopID");
	public static final int decoBlockMudstoneBrickStairsID = DecoAddonManager.getBlockID("decoBlockMudstoneBrickStairsID");
	public static final int decoBlockMudstoneBrickWallID = DecoAddonManager.getBlockID("decoBlockMudstoneBrickWallID");
	
	public DecoSubModuleMudstoneAndBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Mudstone and Mudstone Brick");
		
		this.decoBlockMudstone = new DecoBlockMudstone(this.decoBlockMudstoneID);
		this.decoBlockMudstoneSlab = new DecoBlockSlab(this.decoBlockMudstoneSlabID, this.decoBlockMudstone, false, this.decoBlockMudstoneSlabID, this.decoBlockMudstoneSlabTopID);
		this.decoBlockMudstoneSlabTop = new DecoBlockSlab(this.decoBlockMudstoneSlabTopID, this.decoBlockMudstone, true, this.decoBlockMudstoneSlabID, this.decoBlockMudstoneSlabTopID);
		this.decoBlockMudstoneStairs = new DecoBlockStair(this.decoBlockMudstoneStairsID, this.decoBlockMudstone, 0);
		this.decoBlockMudstoneWall = new DecoBlockWall(this.decoBlockMudstoneWallID, this.decoBlockMudstone);
		
		this.decoBlockMudstoneBrick = new DecoBlockMudstoneBrick(this.decoBlockMudstoneBrickID);
		this.decoBlockMudstoneBrickSlab = new DecoBlockSlab(this.decoBlockMudstoneBrickSlabID, this.decoBlockMudstoneBrick, false, this.decoBlockMudstoneBrickSlabID, this.decoBlockMudstoneBrickSlabTopID);
		this.decoBlockMudstoneBrickSlabTop = new DecoBlockSlab(this.decoBlockMudstoneBrickSlabTopID, this.decoBlockMudstoneBrick, true, this.decoBlockMudstoneBrickSlabID, this.decoBlockMudstoneBrickSlabTopID);
		this.decoBlockMudstoneBrickStairs = new DecoBlockStair(this.decoBlockMudstoneBrickStairsID, this.decoBlockMudstoneBrick, 0);
		this.decoBlockMudstoneBrickWall = new DecoBlockWall(this.decoBlockMudstoneBrickWallID, this.decoBlockMudstoneBrick);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockMudstone, "Mudstone");
		DecoAddonManager.register(this.decoBlockMudstoneSlab, "Mudstone Slab");
		DecoAddonManager.register(this.decoBlockMudstoneSlabTop, "Mudstone Slab");
		DecoAddonManager.register(this.decoBlockMudstoneStairs, "Mudstone Stairs");
		DecoAddonManager.register(this.decoBlockMudstoneWall, "Mudstone Wall");
		
		DecoAddonManager.register(this.decoBlockMudstoneBrick, "Mudstone Brick");
		DecoAddonManager.register(this.decoBlockMudstoneBrickSlab, "Mudstone Brick Slab");
		DecoAddonManager.register(this.decoBlockMudstoneBrickSlabTop, "Mudstone Brick Slab");
		DecoAddonManager.register(this.decoBlockMudstoneBrickStairs, "Mudstone Brick Stairs");
		DecoAddonManager.register(this.decoBlockMudstoneBrickWall, "Mudstone Brick Wall");
	}

	public void addRecipes()
	{
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockMudstone, this.decoBlockMudstoneSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockMudstone, this.decoBlockMudstoneStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockMudstone, this.decoBlockMudstoneWall, 2);
		
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockMudstone, this.decoBlockMudstoneBrick, 4);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockMudstoneBrick, this.decoBlockMudstoneBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockMudstoneBrick, this.decoBlockMudstoneBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockMudstoneBrick, this.decoBlockMudstoneBrickWall, 2);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstone);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneWall);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneBrick);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneBrickSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneBrickSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneBrickStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockMudstoneBrickWall);
	}
}
