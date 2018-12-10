package net.minecraft.src;

public class DecoSubModuleAestheticMetal implements DecoISubModule 
{
	public static Block decoBlockAestheticMetal;
	public static Block decoBlockPaneAestheticMetal;

	public static final int decoBlockAestheticMetalID = DecoAddonManager.getBlockID("decoBlockAestheticMetalID");
	public static final int decoBlockPaneAestheticMetalID = DecoAddonManager.getBlockID("decoBlockPaneAestheticMetalID");

	public DecoSubModuleAestheticMetal()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Aesthetic Metal");

		this.decoBlockAestheticMetal = new DecoBlockAestheticMetal(this.decoBlockAestheticMetalID);
		this.decoBlockPaneAestheticMetal = new DecoBlockPaneAestheticMetal(this.decoBlockPaneAestheticMetalID);

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() {}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockPaneAestheticMetal, 10, 0), new Object[] { "XXX", "XXX", 'X', new ItemStack(Item.ingotGold) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockPaneAestheticMetal, 10, 1), new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcSteel) });
		
		DecoUtilsRecipes.addBlockRecipe(Item.ingotGold, this.decoBlockPaneAestheticMetal, 2, 2);
		DecoUtilsRecipes.addBlockRecipe(Item.ingotIron, this.decoBlockPaneAestheticMetal, 3, 2);
		DecoUtilsRecipes.addBlockRecipe(FCBetterThanWolves.fcSteel, this.decoBlockPaneAestheticMetal, 4, 2);
		
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockPaneAestheticMetal, 2, this.decoBlockAestheticMetal, 0, 4);
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockPaneAestheticMetal, 3, this.decoBlockAestheticMetal, 1, 4);
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockPaneAestheticMetal, 4, this.decoBlockAestheticMetal, 2, 4);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockAestheticMetal.SetPicksEffectiveOn();
		this.decoBlockPaneAestheticMetal.SetPicksEffectiveOn();
	}
}
