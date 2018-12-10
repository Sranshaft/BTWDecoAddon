package net.minecraft.src;

public class DecoSubModuleBrimstone implements DecoISubModule 
{
	public static Block decoBlockBrimstone;
	public static DecoBlockSlab decoBlockBrimstoneSlab;
	public static DecoBlockSlab decoBlockBrimstoneSlabTop;
	public static Block decoBlockBrimstoneStairs;
	public static Block decoBlockBrimstoneWall;

	public static Item decoItemBrimstoneSlab;
	public static Item decoItemBrimstoneSlabTop;

	public static final int decoBlockBrimstoneID = DecoAddonManager.getBlockID("decoBlockBrimstoneID");
	public static final int decoBlockBrimstoneSlabID = DecoAddonManager.getBlockID("decoBlockBrimstoneSlabID");
	public static final int decoBlockBrimstoneSlabTopID = DecoAddonManager.getBlockID("decoBlockBrimstoneSlabTopID");
	public static final int decoBlockBrimstoneStairsID = DecoAddonManager.getBlockID("decoBlockBrimstoneStairsID");
	public static final int decoBlockBrimstoneWallID = DecoAddonManager.getBlockID("decoBlockBrimstoneWallID");

	public DecoSubModuleBrimstone()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Brimstone");

		this.decoBlockBrimstone = new DecoBlockBrimstone(this.decoBlockBrimstoneID);
		
		this.decoBlockBrimstoneSlab = new DecoBlockSlab(this.decoBlockBrimstoneSlabID, this.decoBlockBrimstone, false, 
				this.decoBlockBrimstoneSlab, this.decoBlockBrimstoneSlabTop);
		this.decoBlockBrimstoneSlabTop = new DecoBlockSlab(this.decoBlockBrimstoneSlabTopID, this.decoBlockBrimstone, true, 
				this.decoBlockBrimstoneSlab, this.decoBlockBrimstoneSlabTop);

		this.decoBlockBrimstoneStairs = new DecoBlockStair(this.decoBlockBrimstoneStairsID, this.decoBlockBrimstone, 0);
		this.decoBlockBrimstoneWall = new DecoBlockWall(this.decoBlockBrimstoneWallID, this.decoBlockBrimstone);

		this.decoItemBrimstoneSlab = new DecoItemSlab(this.decoBlockBrimstoneSlabID - 256, 
				this.decoBlockBrimstoneSlab, this.decoBlockBrimstoneSlabTop, false).setUnlocalizedName(this.decoBlockBrimstone.getUnlocalizedName() + ".slab");
		this.decoItemBrimstoneSlabTop = new DecoItemSlab(this.decoBlockBrimstoneSlabTopID - 256, 
				this.decoBlockBrimstoneSlab, this.decoBlockBrimstoneSlabTop, true).setUnlocalizedName(this.decoBlockBrimstone.getUnlocalizedName() + ".slab");

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockBrimstoneSlab, "Brimstone Slab");
		DecoAddonManager.register(this.decoBlockBrimstoneSlabTop, "Brimstone Slab");
		DecoAddonManager.register(this.decoBlockBrimstoneStairs, "Brimstone Stairs");
		DecoAddonManager.register(this.decoBlockBrimstoneWall, "Brimstone Wall");

		DecoAddonManager.replaceItem(this.decoBlockBrimstoneSlabID, decoItemBrimstoneSlab);
		DecoAddonManager.replaceItem(this.decoBlockBrimstoneSlabTopID, decoItemBrimstoneSlabTop);
	}

	public void addRecipes()
	{
		DecoUtilsRecipes.addBlockRecipe(Block.blockNetherQuartz, this.decoBlockBrimstone, 0, 4);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockBrimstone, this.decoBlockBrimstone, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockBrimstone, this.decoBlockBrimstone, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockBrimstone, this.decoBlockBrimstoneSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockBrimstone, this.decoBlockBrimstoneStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockBrimstone, this.decoBlockBrimstoneWall, 2);

		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockBrimstone, 12, 1),
				new Object[] { "####", "#  #", "#  #", "####", '#', this.decoBlockBrimstone });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockBrimstone.SetPicksEffectiveOn(true);
		this.decoBlockBrimstoneSlab.SetPicksEffectiveOn(true);
		this.decoBlockBrimstoneSlabTop.SetPicksEffectiveOn(true);
		this.decoBlockBrimstoneStairs.SetPicksEffectiveOn(true);
		this.decoBlockBrimstoneWall.SetPicksEffectiveOn(true);
	}
}
