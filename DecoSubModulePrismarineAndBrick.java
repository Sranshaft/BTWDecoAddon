package net.minecraft.src;

public class DecoSubModulePrismarineAndBrick implements DecoISubModule 
{
	public static Block decoBlockPrismarine;
	public static DecoBlockSlab decoBlockPrismarineSlab;
	public static DecoBlockSlab decoBlockPrismarineSlabTop;
	public static Block decoBlockPrismarineStairs;
	public static Block decoBlockPrismarineWall;
	
	public static Block decoBlockPrismarineBrick;
	public static DecoBlockSlab decoBlockPrismarineBrickSlab;
	public static DecoBlockSlab decoBlockPrismarineBrickSlabTop;
	public static Block decoBlockPrismarineBrickStairs;
	public static Block decoBlockPrismarineBrickWall;

	public static Item decoItemPrismarineSlab;
	public static Item decoItemPrismarineSlabTop;
	
	public static Item decoItemPrismarineBrickSlab;
	public static Item decoItemPrismarineBrickSlabTop;

	public static final int decoBlockPrismarineID = DecoAddonManager.getBlockID("decoBlockPrismarineID");
	public static final int decoBlockPrismarineSlabID = DecoAddonManager.getBlockID("decoBlockPrismarineSlabID");
	public static final int decoBlockPrismarineSlabTopID = DecoAddonManager.getBlockID("decoBlockPrismarineSlabTopID");
	public static final int decoBlockPrismarineStairsID = DecoAddonManager.getBlockID("decoBlockPrismarineStairsID");
	public static final int decoBlockPrismarineWallID = DecoAddonManager.getBlockID("decoBlockPrismarineWallID");
	
	public static final int decoBlockPrismarineBrickID = DecoAddonManager.getBlockID("decoBlockPrismarineBrickID");
	public static final int decoBlockPrismarineBrickSlabID = DecoAddonManager.getBlockID("decoBlockPrismarineBrickSlabID");
	public static final int decoBlockPrismarineBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockPrismarineBrickSlabTopID");
	public static final int decoBlockPrismarineBrickStairsID = DecoAddonManager.getBlockID("decoBlockPrismarineBrickStairsID");
	public static final int decoBlockPrismarineBrickWallID = DecoAddonManager.getBlockID("decoBlockPrismarineBrickWallID");

	public DecoSubModulePrismarineAndBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Prismarine and Prismarine Brick");

		// PRISMARINE
		
		this.decoBlockPrismarine = new DecoBlockPrismarine(this.decoBlockPrismarineID);

		this.decoBlockPrismarineSlab = new DecoBlockSlab(this.decoBlockPrismarineSlabID, this.decoBlockPrismarine, false, 
				this.decoBlockPrismarineSlab, this.decoBlockPrismarineSlabTop);

		this.decoBlockPrismarineSlabTop = new DecoBlockSlab(this.decoBlockPrismarineSlabTopID, this.decoBlockPrismarine, true, 
				this.decoBlockPrismarineSlab, this.decoBlockPrismarineSlabTop);

		this.decoBlockPrismarineStairs = new DecoBlockStair(this.decoBlockPrismarineStairsID, this.decoBlockPrismarine, 0);
		this.decoBlockPrismarineWall = new DecoBlockWall(this.decoBlockPrismarineWallID, this.decoBlockPrismarine);

		this.decoItemPrismarineSlab = new DecoItemSlab(this.decoBlockPrismarineSlabID - 256, 
				this.decoBlockPrismarineSlab, this.decoBlockPrismarineSlabTop, false).setUnlocalizedName(this.decoBlockPrismarine.getUnlocalizedName() + ".slab");
		this.decoItemPrismarineSlabTop = new DecoItemSlab(this.decoBlockPrismarineSlabTopID - 256, 
				this.decoBlockPrismarineSlab, this.decoBlockPrismarineSlabTop, true).setUnlocalizedName(this.decoBlockPrismarine.getUnlocalizedName() + ".slab");
		
		// PRISMARINE BRICK
		
		this.decoBlockPrismarineBrick = new DecoBlockStoneBrick(this.decoBlockPrismarineBrickID, "decoBlockPrismarineBrick", "Prismarine Brick", 2.0F, 2.5F);

		this.decoBlockPrismarineBrickSlab = new DecoBlockSlab(this.decoBlockPrismarineBrickSlabID, this.decoBlockPrismarineBrick, false, 
				this.decoBlockPrismarineBrickSlab, this.decoBlockPrismarineBrickSlabTop);

		this.decoBlockPrismarineBrickSlabTop = new DecoBlockSlab(this.decoBlockPrismarineBrickSlabTopID, this.decoBlockPrismarineBrick, true, 
				this.decoBlockPrismarineBrickSlab, this.decoBlockPrismarineBrickSlabTop);

		this.decoBlockPrismarineBrickStairs = new DecoBlockStair(this.decoBlockPrismarineBrickStairsID, this.decoBlockPrismarineBrick, 0);
		this.decoBlockPrismarineBrickWall = new DecoBlockWall(this.decoBlockPrismarineBrickWallID, this.decoBlockPrismarineBrick);

		this.decoItemPrismarineBrickSlab = new DecoItemSlab(this.decoBlockPrismarineBrickSlabID - 256, 
				this.decoBlockPrismarineBrickSlab, this.decoBlockPrismarineBrickSlabTop, false).setUnlocalizedName(this.decoBlockPrismarineBrick.getUnlocalizedName() + ".slab");
		this.decoItemPrismarineBrickSlabTop = new DecoItemSlab(this.decoBlockPrismarineBrickSlabTopID - 256, 
				this.decoBlockPrismarineBrickSlab, this.decoBlockPrismarineBrickSlabTop, true).setUnlocalizedName(this.decoBlockPrismarineBrick.getUnlocalizedName() + ".slab");

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() 
	{
		// PRISMARINE
		
		DecoAddonManager.register(this.decoBlockPrismarine, "Prismarine");
		DecoAddonManager.register(this.decoBlockPrismarineSlab, "Prismarine Slab");
		DecoAddonManager.register(this.decoBlockPrismarineSlabTop, "Prismarine Slab");
		DecoAddonManager.register(this.decoBlockPrismarineStairs, "Prismarine Stairs");
		DecoAddonManager.register(this.decoBlockPrismarineWall, "Prismarine Wall");

		DecoAddonManager.replaceItem(this.decoBlockPrismarineSlabID, decoItemPrismarineSlab);
		DecoAddonManager.replaceItem(this.decoBlockPrismarineSlabTopID, decoItemPrismarineSlabTop);
		
		// PRISMARINE BRICK
		
		DecoAddonManager.register(this.decoBlockPrismarineBrickSlab, "Prismarine Brick Slab");
		DecoAddonManager.register(this.decoBlockPrismarineBrickSlabTop, "Prismarine Brick Slab");
		DecoAddonManager.register(this.decoBlockPrismarineBrickStairs, "Prismarine Brick Stairs");
		DecoAddonManager.register(this.decoBlockPrismarineBrickWall, "Prismarine Brick Wall");

		DecoAddonManager.replaceItem(this.decoBlockPrismarineBrickSlabID, decoItemPrismarineBrickSlab);
		DecoAddonManager.replaceItem(this.decoBlockPrismarineBrickSlabTopID, decoItemPrismarineBrickSlabTop);
	}

	public void addRecipes()
	{
		// PRISMARINE
		
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockPrismarine, this.decoBlockPrismarineSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockPrismarine, this.decoBlockPrismarineStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockPrismarine, this.decoBlockPrismarineWall, 2);
		
		// PRISMARINE BRICK
		
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockPrismarineBrick, this.decoBlockPrismarineBrick, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockPrismarineBrick, this.decoBlockPrismarineBrick, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockPrismarineBrick, this.decoBlockPrismarineBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockPrismarineBrick, this.decoBlockPrismarineBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockPrismarineBrick, this.decoBlockPrismarineBrickWall, 2);

		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockPrismarineBrick, 12, 1),
				new Object[] { "####", "#  #", "#  #", "####", '#', this.decoBlockPrismarineBrick });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		// PRISMARINE
		
		this.decoBlockPrismarine.SetPicksEffectiveOn();
		this.decoBlockPrismarineSlab.SetPicksEffectiveOn();
		this.decoBlockPrismarineSlabTop.SetPicksEffectiveOn();
		this.decoBlockPrismarineStairs.SetPicksEffectiveOn();
		this.decoBlockPrismarineWall.SetPicksEffectiveOn();
		
		// PRISMARINE BRICK
		
		this.decoBlockPrismarineBrick.SetPicksEffectiveOn();
		this.decoBlockPrismarineBrickSlab.SetPicksEffectiveOn();
		this.decoBlockPrismarineBrickSlabTop.SetPicksEffectiveOn();
		this.decoBlockPrismarineBrickStairs.SetPicksEffectiveOn();
		this.decoBlockPrismarineBrickWall.SetPicksEffectiveOn();
	}
}
