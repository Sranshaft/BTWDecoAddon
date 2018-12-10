package net.minecraft.src;

public class DecoSubModuleHedge implements DecoISubModule 
{
	public static Block decoBlockHedge;
	
	public static final int decoBlockHedgeID = DecoAddonManager.getBlockID("decoBlockHedgeID");
	
	public DecoSubModuleHedge()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Hedge");
		
		this.decoBlockHedge = new DecoBlockHedge(this.decoBlockHedgeID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() {}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addWallRecipe(new ItemStack(Block.leaves, 1, 0), new ItemStack(this.decoBlockHedge, 1, 0), 6);
		DecoUtilsRecipes.addWallRecipe(new ItemStack(Block.leaves, 1, 1), new ItemStack(this.decoBlockHedge, 1, 1), 6);
		DecoUtilsRecipes.addWallRecipe(new ItemStack(Block.leaves, 1, 2), new ItemStack(this.decoBlockHedge, 1, 2), 6);
		DecoUtilsRecipes.addWallRecipe(new ItemStack(Block.leaves, 1, 3), new ItemStack(this.decoBlockHedge, 1, 3), 6);
		DecoUtilsRecipes.addWallRecipe(new ItemStack(FCBetterThanWolves.fcBlockBloodLeaves, 1, 0), new ItemStack(this.decoBlockHedge, 1, 4), 6);
		DecoUtilsRecipes.addWallRecipe(new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLeaves, 1, 0), new ItemStack(this.decoBlockHedge, 1, 5), 6);
		DecoUtilsRecipes.addWallRecipe(new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLeaves, 1, 1), new ItemStack(this.decoBlockHedge, 1, 6), 6);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockHedge.SetAxesEffectiveOn(true);
	}
}
