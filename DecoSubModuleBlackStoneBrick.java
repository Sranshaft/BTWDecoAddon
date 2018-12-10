package net.minecraft.src;

public class DecoSubModuleBlackStoneBrick implements DecoISubModule 
{
	public static Block decoBlockBlackStoneBrick;
	public static DecoBlockSlab decoBlockBlackStoneBrickSlab;
	public static DecoBlockSlab decoBlockBlackStoneBrickSlabTop;
	public static Block decoBlockBlackStoneBrickStairs;
	public static Block decoBlockBlackStoneBrickWall;

	public static Item decoItemBlackStoneBrickSlab;
	public static Item decoItemBlackStoneBrickSlabTop;

	public static final int decoBlockBlackStoneBrickID = DecoAddonManager.getBlockID("decoBlockBlackStoneBrickID");
	public static final int decoBlockBlackStoneBrickSlabID = DecoAddonManager.getBlockID("decoBlockBlackStoneBrickSlabID");
	public static final int decoBlockBlackStoneBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockBlackStoneBrickSlabTopID");
	public static final int decoBlockBlackStoneBrickStairsID = DecoAddonManager.getBlockID("decoBlockBlackStoneBrickStairsID");
	public static final int decoBlockBlackStoneBrickWallID = DecoAddonManager.getBlockID("decoBlockBlackStoneBrickWallID");

	public DecoSubModuleBlackStoneBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Black Stone Brick");

		this.decoBlockBlackStoneBrick = new DecoBlockStoneBrick(this.decoBlockBlackStoneBrickID, "decoBlockBlackStoneBrick", "Black Stone Brick", 2.0F, 2.5F);

		this.decoBlockBlackStoneBrickSlab = new DecoBlockSlab(this.decoBlockBlackStoneBrickSlabID, this.decoBlockBlackStoneBrick, false, 
				this.decoBlockBlackStoneBrickSlab, this.decoBlockBlackStoneBrickSlabTop);

		this.decoBlockBlackStoneBrickSlabTop = new DecoBlockSlab(this.decoBlockBlackStoneBrickSlabTopID, this.decoBlockBlackStoneBrick, true, 
				this.decoBlockBlackStoneBrickSlab, this.decoBlockBlackStoneBrickSlabTop);

		this.decoBlockBlackStoneBrickStairs = new DecoBlockStair(this.decoBlockBlackStoneBrickStairsID, this.decoBlockBlackStoneBrick, 0);
		this.decoBlockBlackStoneBrickWall = new DecoBlockWall(this.decoBlockBlackStoneBrickWallID, this.decoBlockBlackStoneBrick);

		this.decoItemBlackStoneBrickSlab = new DecoItemSlab(this.decoBlockBlackStoneBrickSlabID - 256, 
				this.decoBlockBlackStoneBrickSlab, this.decoBlockBlackStoneBrickSlabTop, false).setUnlocalizedName(this.decoBlockBlackStoneBrick.getUnlocalizedName() + ".slab");
		this.decoItemBlackStoneBrickSlabTop = new DecoItemSlab(this.decoBlockBlackStoneBrickSlabTopID - 256, 
				this.decoBlockBlackStoneBrickSlab, this.decoBlockBlackStoneBrickSlabTop, true).setUnlocalizedName(this.decoBlockBlackStoneBrick.getUnlocalizedName() + ".slab");

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockBlackStoneBrickSlab, "Black Stone Brick Slab");
		DecoAddonManager.register(this.decoBlockBlackStoneBrickSlabTop, "Black Stone Brick Slab");
		DecoAddonManager.register(this.decoBlockBlackStoneBrickStairs, "Black Stone Brick Stairs");
		DecoAddonManager.register(this.decoBlockBlackStoneBrickWall, "Black Stone Brick Wall");

		DecoAddonManager.replaceItem(this.decoBlockBlackStoneBrickSlabID, decoItemBlackStoneBrickSlab);
		DecoAddonManager.replaceItem(this.decoBlockBlackStoneBrickSlabTopID, decoItemBlackStoneBrickSlabTop);
	}

	public void addRecipes()
	{
		DecoUtilsRecipes.addBlockRecipe(Block.blockNetherQuartz, this.decoBlockBlackStoneBrick, 0, 4);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockBlackStoneBrick, this.decoBlockBlackStoneBrick, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockBlackStoneBrick, this.decoBlockBlackStoneBrick, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockBlackStoneBrick, this.decoBlockBlackStoneBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockBlackStoneBrick, this.decoBlockBlackStoneBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockBlackStoneBrick, this.decoBlockBlackStoneBrickWall, 2);

		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockBlackStoneBrick, 12, 1),
				new Object[] { "####", "#  #", "#  #", "####", '#', this.decoBlockBlackStoneBrick });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockBlackStoneBrick.SetPicksEffectiveOn(true);
		this.decoBlockBlackStoneBrickSlab.SetPicksEffectiveOn(true);
		this.decoBlockBlackStoneBrickSlabTop.SetPicksEffectiveOn(true);
		this.decoBlockBlackStoneBrickStairs.SetPicksEffectiveOn(true);
		this.decoBlockBlackStoneBrickWall.SetPicksEffectiveOn(true);
	}
}
