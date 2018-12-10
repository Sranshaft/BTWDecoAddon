package net.minecraft.src;

public class DecoSubModuleEndstoneBrick implements DecoISubModule 
{
	public static Block decoBlockEndstoneBrick;
	public static DecoBlockSlab decoBlockEndstoneBrickSlab;
	public static DecoBlockSlab decoBlockEndstoneBrickSlabTop;
	public static Block decoBlockEndstoneBrickStairs;
	public static Block decoBlockEndstoneBrickWall;
	
	public static Item decoItemEndstoneBrickSlab;
	public static Item decoItemEndstoneBrickSlabTop;
	
	public static final int decoBlockEndstoneBrickID = DecoAddonManager.getBlockID("decoBlockEndstoneBrickID");
	public static final int decoBlockEndstoneBrickSlabID = DecoAddonManager.getBlockID("decoBlockEndstoneBrickSlabID");
	public static final int decoBlockEndstoneBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockEndstoneBrickSlabTopID");
	public static final int decoBlockEndstoneBrickStairsID = DecoAddonManager.getBlockID("decoBlockEndstoneBrickStairsID");
	public static final int decoBlockEndstoneBrickWallID = DecoAddonManager.getBlockID("decoBlockEndstoneBrickWallID");
	
	public DecoSubModuleEndstoneBrick()
	{
		this.decoBlockEndstoneBrick = new DecoBlockStoneBrick(this.decoBlockEndstoneBrickID, "decoBlockEndstoneBrick", "Endstone Brick", 2.0F, 2.5F);

		this.decoBlockEndstoneBrickSlab = new DecoBlockSlab(this.decoBlockEndstoneBrickSlabID, this.decoBlockEndstoneBrick, false, 
				this.decoBlockEndstoneBrickSlab, this.decoBlockEndstoneBrickSlabTop);

		this.decoBlockEndstoneBrickSlabTop = new DecoBlockSlab(this.decoBlockEndstoneBrickSlabTopID, this.decoBlockEndstoneBrick, true, 
				this.decoBlockEndstoneBrickSlab, this.decoBlockEndstoneBrickSlabTop);

		this.decoBlockEndstoneBrickStairs = new DecoBlockStair(this.decoBlockEndstoneBrickStairsID, this.decoBlockEndstoneBrick, 0);
		this.decoBlockEndstoneBrickWall = new DecoBlockWall(this.decoBlockEndstoneBrickWallID, this.decoBlockEndstoneBrick);

		this.decoItemEndstoneBrickSlab = new DecoItemSlab(this.decoBlockEndstoneBrickSlabID - 256, 
				this.decoBlockEndstoneBrickSlab, this.decoBlockEndstoneBrickSlabTop, false).setUnlocalizedName(this.decoBlockEndstoneBrick.getUnlocalizedName() + ".slab");
		this.decoItemEndstoneBrickSlabTop = new DecoItemSlab(this.decoBlockEndstoneBrickSlabTopID - 256, 
				this.decoBlockEndstoneBrickSlab, this.decoBlockEndstoneBrickSlabTop, true).setUnlocalizedName(this.decoBlockEndstoneBrick.getUnlocalizedName() + ".slab");

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockEndstoneBrickSlab, "Endstone Brick Slab");
		DecoAddonManager.register(this.decoBlockEndstoneBrickSlabTop, "Endstone Brick Slab");
		DecoAddonManager.register(this.decoBlockEndstoneBrickStairs, "Endstone Brick Stairs");
		DecoAddonManager.register(this.decoBlockEndstoneBrickWall, "Endstone Brick Wall");

		DecoAddonManager.replaceItem(this.decoBlockEndstoneBrickSlabID, decoItemEndstoneBrickSlab);
		DecoAddonManager.replaceItem(this.decoBlockEndstoneBrickSlabTopID, decoItemEndstoneBrickSlabTop);
	}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockEndstoneBrick, this.decoBlockEndstoneBrick, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockEndstoneBrick, this.decoBlockEndstoneBrick, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockEndstoneBrick, this.decoBlockEndstoneBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockEndstoneBrick, this.decoBlockEndstoneBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockEndstoneBrick, this.decoBlockEndstoneBrickWall, 2);

		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockEndstoneBrick, 12, 1),
				new Object[] { "####", "#  #", "#  #", "####", '#', this.decoBlockEndstoneBrick });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockEndstoneBrick.SetPicksEffectiveOn();
		this.decoBlockEndstoneBrickSlab.SetPicksEffectiveOn();
		this.decoBlockEndstoneBrickSlabTop.SetPicksEffectiveOn();
		this.decoBlockEndstoneBrickStairs.SetPicksEffectiveOn();
		this.decoBlockEndstoneBrickWall.SetPicksEffectiveOn();
	}

}
