package net.minecraft.src;

public class DecoSubModuleEbonstoneAndBrick  implements DecoISubModule 
{
	public static Block decoBlockEbonstone;
	public static Block decoBlockEbonstoneSlab;
	public static Block decoBlockEbonstoneSlabTop;
	public static Block decoBlockEbonstoneStairs;
	public static Block decoBlockEbonstoneWall;
	
	public static Block decoBlockEbonstoneBrick;
	public static Block decoBlockEbonstoneBrickSlab;
	public static Block decoBlockEbonstoneBrickSlabTop;
	public static Block decoBlockEbonstoneBrickStairs;
	public static Block decoBlockEbonstoneBrickWall;
	
	public static final int decoBlockEbonstoneID = DecoAddonManager.getBlockID("decoBlockEbonstoneID");
	public static final int decoBlockEbonstoneSlabID = DecoAddonManager.getBlockID("decoBlockEbonstoneSlabID");
	public static final int decoBlockEbonstoneSlabTopID = DecoAddonManager.getBlockID("decoBlockEbonstoneSlabTopID");
	public static final int decoBlockEbonstoneStairsID = DecoAddonManager.getBlockID("decoBlockEbonstoneStairsID");
	public static final int decoBlockEbonstoneWallID = DecoAddonManager.getBlockID("decoBlockEbonstoneWallID");
	
	public static final int decoBlockEbonstoneBrickID = DecoAddonManager.getBlockID("decoBlockEbonstoneBrickID");
	public static final int decoBlockEbonstoneBrickSlabID = DecoAddonManager.getBlockID("decoBlockEbonstoneBrickSlabID");
	public static final int decoBlockEbonstoneBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockEbonstoneBrickSlabTopID");
	public static final int decoBlockEbonstoneBrickStairsID = DecoAddonManager.getBlockID("decoBlockEbonstoneBrickStairsID");
	public static final int decoBlockEbonstoneBrickWallID = DecoAddonManager.getBlockID("decoBlockEbonstoneBrickWallID");
	
	public DecoSubModuleEbonstoneAndBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Ebonstone and Ebonstone Brick");
		
		this.decoBlockEbonstone = new DecoBlockEbonstone(this.decoBlockEbonstoneID);
		this.decoBlockEbonstoneSlab = new DecoBlockSlab(this.decoBlockEbonstoneSlabID, this.decoBlockEbonstone, false, this.decoBlockEbonstoneSlabID, this.decoBlockEbonstoneSlabTopID);
		this.decoBlockEbonstoneSlabTop = new DecoBlockSlab(this.decoBlockEbonstoneSlabTopID, this.decoBlockEbonstone,true, this.decoBlockEbonstoneSlabID, this.decoBlockEbonstoneSlabTopID);
		this.decoBlockEbonstoneStairs = new DecoBlockStair(this.decoBlockEbonstoneStairsID, this.decoBlockEbonstone, 0);
		this.decoBlockEbonstoneWall = new DecoBlockWall(this.decoBlockEbonstoneWallID, this.decoBlockEbonstone);
		
		this.decoBlockEbonstoneBrick = new DecoBlockStoneBrick(this.decoBlockEbonstoneBrickID, "decoBlockEbonstoneBrick", "Ebonstone Brick", 10.0F, 20.0F);
		this.decoBlockEbonstoneBrickSlab = new DecoBlockSlab(this.decoBlockEbonstoneBrickSlabID, this.decoBlockEbonstoneBrick, false, this.decoBlockEbonstoneBrickSlabID, this.decoBlockEbonstoneBrickSlabTopID);
		this.decoBlockEbonstoneBrickSlabTop = new DecoBlockSlab(this.decoBlockEbonstoneBrickSlabTopID, this.decoBlockEbonstoneBrick, true, this.decoBlockEbonstoneBrickSlabID, this.decoBlockEbonstoneBrickSlabTopID);
		this.decoBlockEbonstoneBrickStairs = new DecoBlockStair(this.decoBlockEbonstoneBrickStairsID, this.decoBlockEbonstoneBrick, 0);
		this.decoBlockEbonstoneBrickWall = new DecoBlockWall(this.decoBlockEbonstoneBrickWallID, this.decoBlockEbonstoneBrick);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockEbonstone, "Ebonstone");
		DecoAddonManager.register(this.decoBlockEbonstoneSlab, "Ebonstone Slab");
		DecoAddonManager.register(this.decoBlockEbonstoneSlabTop, "Ebonstone Slab");
		DecoAddonManager.register(this.decoBlockEbonstoneStairs, "Ebonstone Stairs");
		DecoAddonManager.register(this.decoBlockEbonstoneWall, "Ebonstone Wall");
		
		DecoAddonManager.register(this.decoBlockEbonstoneBrickSlab, "Ebonstone Brick Slab");
		DecoAddonManager.register(this.decoBlockEbonstoneBrickSlabTop, "Ebonstone Brick Slab");
		DecoAddonManager.register(this.decoBlockEbonstoneBrickStairs, "Ebonstone Brick Stairs");
		DecoAddonManager.register(this.decoBlockEbonstoneBrickWall, "Ebonstone Brick Wall");
	}

	public void addRecipes()
	{
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockEbonstone, this.decoBlockEbonstoneSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockEbonstone, this.decoBlockEbonstoneStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockEbonstone, this.decoBlockEbonstoneWall, 2);
		
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockEbonstone, this.decoBlockEbonstoneBrick, 4);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockEbonstoneBrick, this.decoBlockEbonstoneBrick, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockEbonstoneBrick, this.decoBlockEbonstoneBrick, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockEbonstoneBrick, this.decoBlockEbonstoneBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockEbonstoneBrick, this.decoBlockEbonstoneBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockEbonstoneBrick, this.decoBlockEbonstoneBrickWall, 2);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstone);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneWall);
		
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneBrick);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneBrickSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneBrickSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneBrickStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockEbonstoneBrickWall);
	}
}
