package net.minecraft.src;

public class DecoSubModuleSandyBrick implements DecoISubModule
{
	public static Block decoBlockSandyBrick;
	public static Block decoBlockSandyBrickMouldingAndDecorative;
    public static Block decoBlockSandyBrickSidingAndCorner;
	public static Block decoBlockSandyBrickSlab;
	public static Block decoBlockSandyBrickSlabTop;
	public static Block decoBlockSandyBrickStairs;
	public static Block decoBlockSandyBrickWall;
	
	public static final int decoBlockSandyBrickID = DecoAddonManager.getBlockID("decoBlockSandyBrickID");
	public static final int decoBlockSandyBrickMouldingAndDecorativeID = DecoAddonManager.getBlockID("decoBlockSandyBrickMouldingAndDecorativeID");
	public static final int decoBlockSandyBrickSidingAndCornerID = DecoAddonManager.getBlockID("decoBlockSandyBrickSidingAndCornerID");
	public static final int decoBlockSandyBrickSlabID = DecoAddonManager.getBlockID("decoBlockSandyBrickSlabID");
	public static final int decoBlockSandyBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockSandyBrickSlabTopID");
	public static final int decoBlockSandyBrickStairsID = DecoAddonManager.getBlockID("decoBlockSandyBrickStairsID");
	public static final int decoBlockSandyBrickWallID = DecoAddonManager.getBlockID("decoBlockSandyBrickWallID");
	
	public DecoSubModuleSandyBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Sandy Brick");
		
		this.decoBlockSandyBrick = new DecoBlockSandyBrick(this.decoBlockSandyBrickID);
		this.decoBlockSandyBrickSlab = new DecoBlockSlab(this.decoBlockSandyBrickSlabID, this.decoBlockSandyBrick, false, 
				this.decoBlockSandyBrickSlabID, this.decoBlockSandyBrickSlabTopID);
		this.decoBlockSandyBrickSlabTop = new DecoBlockSlab(this.decoBlockSandyBrickSlabTopID, this.decoBlockSandyBrick, true, 
				this.decoBlockSandyBrickSlabID, this.decoBlockSandyBrickSlabTopID);
		this.decoBlockSandyBrickStairs = new DecoBlockStair(this.decoBlockSandyBrickStairsID, this.decoBlockSandyBrick, 0);
		this.decoBlockSandyBrickWall = new DecoBlockWall(this.decoBlockSandyBrickWallID, this.decoBlockSandyBrick);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockSandyBrick, "Sandy Bricks");
		DecoAddonManager.register(this.decoBlockSandyBrickSlab, "Sandy Brick Slab");
		DecoAddonManager.register(this.decoBlockSandyBrickSlabTop, "Sandy Brick Slab");
		DecoAddonManager.register(this.decoBlockSandyBrickStairs, "Sandy Brick Stairs");
		DecoAddonManager.register(this.decoBlockSandyBrickWall, "Sandy Brick Wall");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockSandyBrick, 4), new Object[] { "X#", "#X", 'X', Block.sand, '#', Block.brick });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockSandyBrick, 4), new Object[] { "#X", "X#", 'X', Block.brick, '#', Block.sand });
		
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockSandyBrick, this.decoBlockSandyBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockSandyBrick, this.decoBlockSandyBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockSandyBrick, this.decoBlockSandyBrickWall, 2);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandyBrick);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandyBrickSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandyBrickSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandyBrickStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandyBrickWall);
	}

}
