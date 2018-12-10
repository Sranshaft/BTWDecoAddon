package net.minecraft.src;

public class DecoSubModuleWhiteStoneBrick implements DecoISubModule 
{
	public static Block decoBlockWhiteStoneBrick;
	public static Block decoBlockWhiteStoneBrickMouldingAndDecorative;
	public static Block decoBlockWhiteStoneBrickSidingAndCorner;
	public static DecoBlockSlab decoBlockWhiteStoneBrickSlab;
	public static DecoBlockSlab decoBlockWhiteStoneBrickSlabTop;
	public static Block decoBlockWhiteStoneBrickStairs;
	public static Block decoBlockWhiteStoneBrickWall;
	
	public static Item decoItemWhiteStoneBrickSlab;
	public static Item decoItemWhiteStoneBrickSlabTop;
	
	public static final int decoBlockWhiteStoneBrickID = DecoAddonManager.getBlockID("decoBlockWhiteStoneBrickID");
	public static final int decoBlockWhiteStoneBrickMouldingAndDecorativeID = DecoAddonManager.getBlockID("decoBlockWhiteStoneBrickMouldingAndDecorativeID");
	public static final int decoBlockWhiteStoneBrickSidingAndCornerID = DecoAddonManager.getBlockID("decoBlockWhiteStoneBrickSidingAndCornerID");
	public static final int decoBlockWhiteStoneBrickSlabID = DecoAddonManager.getBlockID("decoBlockWhiteStoneBrickSlabID");
	public static final int decoBlockWhiteStoneBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockWhiteStoneBrickSlabTopID");
	public static final int decoBlockWhiteStoneBrickStairsID = DecoAddonManager.getBlockID("decoBlockWhiteStoneBrickStairsID");
	public static final int decoBlockWhiteStoneBrickWallID = DecoAddonManager.getBlockID("decoBlockWhiteStoneBrickWallID");
	
	public DecoSubModuleWhiteStoneBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: White Stone Brick");
		
		this.decoBlockWhiteStoneBrick = new DecoBlockStoneBrick(this.decoBlockWhiteStoneBrickID, "decoBlockWhiteStoneBrick", "White Stone Brick", 2.0F, 10.0F);
		this.decoBlockWhiteStoneBrickSlab = new DecoBlockSlab(this.decoBlockWhiteStoneBrickSlabID, this.decoBlockWhiteStoneBrick, false, 
				this.decoBlockWhiteStoneBrickSlab, this.decoBlockWhiteStoneBrickSlabTop);
		this.decoBlockWhiteStoneBrickSlabTop = new DecoBlockSlab(this.decoBlockWhiteStoneBrickSlabTopID, this.decoBlockWhiteStoneBrick, true, 
				this.decoBlockWhiteStoneBrickSlab, this.decoBlockWhiteStoneBrickSlabTop);
		this.decoBlockWhiteStoneBrickStairs = new DecoBlockStair(this.decoBlockWhiteStoneBrickStairsID, this.decoBlockWhiteStoneBrick, 0);
		this.decoBlockWhiteStoneBrickWall = new DecoBlockWall(this.decoBlockWhiteStoneBrickWallID, this.decoBlockWhiteStoneBrick);
		
		this.decoItemWhiteStoneBrickSlab = new DecoItemSlab(this.decoBlockWhiteStoneBrickSlabID - 256, 
				this.decoBlockWhiteStoneBrickSlab, this.decoBlockWhiteStoneBrickSlabTop, false).setUnlocalizedName(this.decoBlockWhiteStoneBrick.getUnlocalizedName() + ".slab");
		this.decoItemWhiteStoneBrickSlabTop = new DecoItemSlab(this.decoBlockWhiteStoneBrickSlabTopID - 256, 
				this.decoBlockWhiteStoneBrickSlab, this.decoBlockWhiteStoneBrickSlabTop, true).setUnlocalizedName(this.decoBlockWhiteStoneBrick.getUnlocalizedName() + ".slab");
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockWhiteStoneBrickSlab, "White Stone Brick Slab");
		DecoAddonManager.register(this.decoBlockWhiteStoneBrickSlabTop, "White Stone Brick Slab");
		DecoAddonManager.register(this.decoBlockWhiteStoneBrickStairs, "White Stone Brick Stairs");
		DecoAddonManager.register(this.decoBlockWhiteStoneBrickWall, "White Stone Brick Wall");
		
		DecoAddonManager.replaceItem(this.decoBlockWhiteStoneBrickSlabID, decoItemWhiteStoneBrickSlab);
		DecoAddonManager.replaceItem(this.decoBlockWhiteStoneBrickSlabTopID, decoItemWhiteStoneBrickSlabTop);
	}
	
	public void addRecipes()
	{
		DecoUtilsRecipes.addBlockRecipe(FCBetterThanWolves.fcAestheticOpaque, 9, this.decoBlockWhiteStoneBrick, 4, 4);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockWhiteStoneBrick, this.decoBlockWhiteStoneBrick, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockWhiteStoneBrick, this.decoBlockWhiteStoneBrick, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockWhiteStoneBrick, this.decoBlockWhiteStoneBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockWhiteStoneBrick, this.decoBlockWhiteStoneBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockWhiteStoneBrick, this.decoBlockWhiteStoneBrickWall, 2);
		
		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockWhiteStoneBrick, 12, 1),
				new Object[] { "####", "#  #", "#  #", "####", '#', this.decoBlockWhiteStoneBrick });
	}
	
	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties()
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockWhiteStoneBrick);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockWhiteStoneBrickSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockWhiteStoneBrickSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockWhiteStoneBrickStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockWhiteStoneBrickWall);
	}
}
