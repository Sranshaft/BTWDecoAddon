package net.minecraft.src;

public class DecoSubModuleCharredNetherBrick implements DecoISubModule 
{
	public static Block decoBlockCharredNetherBrick;
	public static Block decoBlockCharredNetherBrickSlab;
	public static Block decoBlockCharredNetherBrickSlabTop;
	public static Block decoBlockCharredNetherBrickStairs;
	public static Block decoBlockCharredNetherBrickWall;
	
	public static final int decoBlockCharredNetherBrickID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickID");
	public static final int decoBlockCharredNetherBrickSlabID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickSlabID");
	public static final int decoBlockCharredNetherBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickSlabTopID");
	public static final int decoBlockCharredNetherBrickStairsID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickStairsID");
	public static final int decoBlockCharredNetherBrickWallID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickWallID");
	
	public DecoSubModuleCharredNetherBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Charred Nether Brick");
		
		this.decoBlockCharredNetherBrick = new DecoBlockCharredNetherBrick(this.decoBlockCharredNetherBrickID);
		this.decoBlockCharredNetherBrickSlab = new DecoBlockSlab(this.decoBlockCharredNetherBrickSlabID, this.decoBlockCharredNetherBrick, false, this.decoBlockCharredNetherBrickSlabID, this.decoBlockCharredNetherBrickSlabTopID);
		this.decoBlockCharredNetherBrickSlabTop = new DecoBlockSlab(this.decoBlockCharredNetherBrickSlabTopID, this.decoBlockCharredNetherBrick, true, this.decoBlockCharredNetherBrickSlabID, this.decoBlockCharredNetherBrickSlabTopID);
		this.decoBlockCharredNetherBrickStairs = new DecoBlockStair(this.decoBlockCharredNetherBrickStairsID, this.decoBlockCharredNetherBrick, 0);
		this.decoBlockCharredNetherBrickWall = new DecoBlockWall(this.decoBlockCharredNetherBrickWallID, this.decoBlockCharredNetherBrick);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	@Override
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockCharredNetherBrick, "Charred Nether Bricks");
		DecoAddonManager.register(this.decoBlockCharredNetherBrickSlab, "Charred Nether Brick Slab");
		DecoAddonManager.register(this.decoBlockCharredNetherBrickSlabTop, "Charred Nether Brick Slab");
		DecoAddonManager.register(this.decoBlockCharredNetherBrickStairs, "Charred Nether Brick Stairs");
		DecoAddonManager.register(this.decoBlockCharredNetherBrickWall, "Charred Nether Brick Wall");
	}

	public void addRecipes() 
	{
		Block.netherBrick.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(this.decoBlockCharredNetherBrick.blockID);
		
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockCharredNetherBrick, this.decoBlockCharredNetherBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockCharredNetherBrick, this.decoBlockCharredNetherBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockCharredNetherBrick, this.decoBlockCharredNetherBrickWall, 2);	
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockCharredNetherBrick);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockCharredNetherBrickSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockCharredNetherBrickSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockCharredNetherBrickStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockCharredNetherBrickWall);
	}

}
