package net.minecraft.src;

public class DecoSubModuleHedge implements DecoISubModule 
{
	public static Block decoBlockHedgeBirch;
	public static Block decoBlockHedgeJungle;
	public static Block decoBlockHedgeOak;
	public static Block decoBlockHedgeSpruce;
	
	public static final int decoBlockHedgeBirchID = DecoAddonManager.getBlockID("decoBlockHedgeBirchID");
	public static final int decoBlockHedgeJungleID = DecoAddonManager.getBlockID("decoBlockHedgeJungleID");
	public static final int decoBlockHedgeOakID = DecoAddonManager.getBlockID("decoBlockHedgeOakID");
	public static final int decoBlockHedgeSpruceID = DecoAddonManager.getBlockID("decoBlockHedgeSpruceID");
	
	public DecoSubModuleHedge()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Hedge");
		
		this.decoBlockHedgeBirch = new DecoBlockHedge(this.decoBlockHedgeBirchID, "birch");
		this.decoBlockHedgeJungle = new DecoBlockHedge(this.decoBlockHedgeJungleID, "jungle");
		this.decoBlockHedgeOak = new DecoBlockHedge(this.decoBlockHedgeOakID, "oak");
		this.decoBlockHedgeSpruce = new DecoBlockHedge(this.decoBlockHedgeSpruceID, "spruce");
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockHedgeBirch, "Birch Hedge");
		DecoAddonManager.register(this.decoBlockHedgeJungle, "Jungle Hedge");
		DecoAddonManager.register(this.decoBlockHedgeOak, "Oak Hedge");
		DecoAddonManager.register(this.decoBlockHedgeSpruce, "Spruce Hedge");
	}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addWallRecipe(Block.leaves.createStackedBlock(2), new ItemStack(this.decoBlockHedgeBirch, 1, 0), 6);
		DecoUtilsRecipes.addWallRecipe(Block.leaves.createStackedBlock(3), new ItemStack(this.decoBlockHedgeJungle, 1, 1), 6);
		DecoUtilsRecipes.addWallRecipe(Block.leaves.createStackedBlock(0), new ItemStack(this.decoBlockHedgeOak, 1, 2), 6);
		DecoUtilsRecipes.addWallRecipe(Block.leaves.createStackedBlock(1), new ItemStack(this.decoBlockHedgeSpruce, 1, 3), 6);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockHedgeBirch);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockHedgeJungle);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockHedgeOak);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockHedgeSpruce);
	}

}
