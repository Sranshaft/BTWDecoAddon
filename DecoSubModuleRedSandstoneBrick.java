package net.minecraft.src;

public class DecoSubModuleRedSandstoneBrick implements DecoISubModule
{
	public static Block decoBlockRedSandstoneBrick;
	public static Block decoBlockRedSandstoneBrickSlab;
	public static Block decoBlockRedSandstoneBrickSlabTop;
	public static Block decoBlockRedSandstoneBrickStairs;
	public static Block decoBlockRedSandstoneBrickWall;
	
	public static final int decoBlockRedSandstoneBrickID = DecoAddonManager.getBlockID("decoBlockRedSandstoneBrickID");
	public static final int decoBlockRedSandstoneBrickSlabID = DecoAddonManager.getBlockID("decoBlockRedSandstoneBrickSlabID");
	public static final int decoBlockRedSandstoneBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockRedSandstoneBrickSlabTopID");
	public static final int decoBlockRedSandstoneBrickStairsID = DecoAddonManager.getBlockID("decoBlockRedSandstoneBrickStairsID");
	public static final int decoBlockRedSandstoneBrickWallID = DecoAddonManager.getBlockID("decoBlockRedSandstoneBrickWallID");
	
	public DecoSubModuleRedSandstoneBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Red Sandstone Brick");
		
		this.decoBlockRedSandstoneBrick = new DecoBlockStoneBrick(this.decoBlockRedSandstoneBrickID, "decoBlockRedSandstoneBrick", "Red Sandstone Brick", 2.0F, 2.5F);
		this.decoBlockRedSandstoneBrickSlab = new DecoBlockSlab(this.decoBlockRedSandstoneBrickSlabID, this.decoBlockRedSandstoneBrick, false, 
				this.decoBlockRedSandstoneBrickSlabID, this.decoBlockRedSandstoneBrickSlabTopID);
		this.decoBlockRedSandstoneBrickSlabTop = new DecoBlockSlab(this.decoBlockRedSandstoneBrickSlabTopID, this.decoBlockRedSandstoneBrick, true, 
				this.decoBlockRedSandstoneBrickSlabID, this.decoBlockRedSandstoneBrickSlabTopID);
		this.decoBlockRedSandstoneBrickStairs = new DecoBlockStair(this.decoBlockRedSandstoneBrickStairsID, this.decoBlockRedSandstoneBrick, 0);
		this.decoBlockRedSandstoneBrickWall = new DecoBlockWall(this.decoBlockRedSandstoneBrickWallID, this.decoBlockRedSandstoneBrick);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockRedSandstoneBrickSlab, "Red Sandstone Brick Slab");
		DecoAddonManager.register(this.decoBlockRedSandstoneBrickSlabTop, "Red Sandstone Brick Slab");
		DecoAddonManager.register(this.decoBlockRedSandstoneBrickStairs, "Red Sandstone Brick Stairs");
		DecoAddonManager.register(this.decoBlockRedSandstoneBrickWall, "Red Sandstone Brick Wall");
	}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockRedSandstoneBrick, DecoModuleBuilding.decoSubModuleRedSandstone.decoBlockRedSandstone, 4);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockRedSandstoneBrick, this.decoBlockRedSandstoneBrick, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockRedSandstoneBrick, this.decoBlockRedSandstoneBrick, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockRedSandstoneBrick, this.decoBlockRedSandstoneBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockRedSandstoneBrick, this.decoBlockRedSandstoneBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockRedSandstoneBrick, this.decoBlockRedSandstoneBrickWall, 2);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneBrick);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneBrickSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneBrickSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneBrickStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneBrickWall);	
	}
}
