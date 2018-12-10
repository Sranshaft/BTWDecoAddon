package net.minecraft.src;

public class DecoSubModuleDuskbound implements DecoISubModule {

	public static Block decoBlockDuskbound;
	public static DecoBlockSlab decoBlockDuskboundSlab;
	public static DecoBlockSlab decoBlockDuskboundSlabTop;
	public static Block decoBlockDuskboundStairs;
	public static Block decoBlockDuskboundWall;

	public static Item decoItemDuskboundSlab;
	public static Item decoItemDuskboundSlabTop;

	public static final int decoBlockDuskboundID = DecoAddonManager.getBlockID("decoBlockDuskboundID");
	public static final int decoBlockDuskboundSlabID = DecoAddonManager.getBlockID("decoBlockDuskboundSlabID");
	public static final int decoBlockDuskboundSlabTopID = DecoAddonManager.getBlockID("decoBlockDuskboundSlabTopID");
	public static final int decoBlockDuskboundStairsID = DecoAddonManager.getBlockID("decoBlockDuskboundStairsID");
	public static final int decoBlockDuskboundWallID = DecoAddonManager.getBlockID("decoBlockDuskboundWallID");

	public DecoSubModuleDuskbound()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Duskbound");

		this.decoBlockDuskbound = new DecoBlockDuskbound(this.decoBlockDuskboundID);
		this.decoBlockDuskboundSlab = new DecoBlockSlab(this.decoBlockDuskboundSlabID, this.decoBlockDuskbound, false, 
				this.decoBlockDuskboundSlab, this.decoBlockDuskboundSlabTop);
		this.decoBlockDuskboundSlabTop = new DecoBlockSlab(this.decoBlockDuskboundSlabTopID, this.decoBlockDuskbound, true,
				this.decoBlockDuskboundSlab, this.decoBlockDuskboundSlabTop);
		this.decoBlockDuskboundStairs = new DecoBlockStair(this.decoBlockDuskboundStairsID, this.decoBlockDuskbound, 0);
		this.decoBlockDuskboundWall = new DecoBlockWall(this.decoBlockDuskboundWallID, this.decoBlockDuskbound);

		this.decoItemDuskboundSlab = new DecoItemSlab(this.decoBlockDuskboundSlabID - 256, 
				this.decoBlockDuskboundSlab, this.decoBlockDuskboundSlabTop, false).setUnlocalizedName(this.decoBlockDuskbound.getUnlocalizedName() + ".slab");
		this.decoItemDuskboundSlabTop = new DecoItemSlab(this.decoBlockDuskboundSlabTopID - 256, 
				this.decoBlockDuskboundSlab, this.decoBlockDuskboundSlabTop, true).setUnlocalizedName(this.decoBlockDuskbound.getUnlocalizedName() + ".slab");

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockDuskboundSlab, "Duskbound Slab");
		DecoAddonManager.register(this.decoBlockDuskboundSlabTop, "Duskbound Slab");
		DecoAddonManager.register(this.decoBlockDuskboundStairs, "Duskbound Stairs");
		DecoAddonManager.register(this.decoBlockDuskboundWall, "Duskbound Wall");

		DecoAddonManager.replaceItem(this.decoBlockDuskboundSlabID, decoItemDuskboundSlab);
		DecoAddonManager.replaceItem(this.decoBlockDuskboundSlabTopID, decoItemDuskboundSlabTop);
	}

	public void addRecipes()
	{
		DecoUtilsRecipes.addBlockRecipe(Block.obsidian, this.decoBlockDuskbound, 0, 4);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockDuskbound, this.decoBlockDuskbound, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockDuskbound, this.decoBlockDuskbound, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockDuskbound, this.decoItemDuskboundSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockDuskbound, this.decoBlockDuskboundStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockDuskbound, this.decoBlockDuskboundWall, 2);

		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockDuskbound, 12, 1),
				new Object[] { "####", "#  #", "#  #", "####", '#', this.decoBlockDuskbound });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockDuskbound.SetPicksEffectiveOn(true);
		this.decoBlockDuskboundSlab.SetPicksEffectiveOn(true);
		this.decoBlockDuskboundSlabTop.SetPicksEffectiveOn(true);
		this.decoBlockDuskboundStairs.SetPicksEffectiveOn(true);
		this.decoBlockDuskboundWall.SetPicksEffectiveOn(true);
	}
}