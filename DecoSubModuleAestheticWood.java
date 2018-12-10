package net.minecraft.src;

public class DecoSubModuleAestheticWood implements DecoISubModule 
{
	public static Block decoBlockAestheticWood;
	public static Block decoBlockPaneAestheticWood;

	public static final int decoBlockAestheticWoodID = DecoAddonManager.getBlockID("decoBlockAestheticWoodID");
	public static final int decoBlockPaneAestheticWoodID = DecoAddonManager.getBlockID("decoBlockPaneAestheticWoodID");

	public DecoSubModuleAestheticWood()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Aesthetic Wood");

		this.decoBlockAestheticWood = new DecoBlockAestheticWood(this.decoBlockAestheticWoodID);
		this.decoBlockPaneAestheticWood = new DecoBlockPaneAestheticWood(this.decoBlockPaneAestheticWoodID);

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() {}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockPaneAestheticWood, 0, this.decoBlockAestheticWood, 0, 1);
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockPaneAestheticWood, 2, this.decoBlockAestheticWood, 3, 1);

		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockPaneAestheticWood, 4, 2), 
				new Object[] { "WWW", "PPP", "WWW", 'P', Item.paper, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockAestheticWood.SetAxesEffectiveOn(true);
		this.decoBlockPaneAestheticWood.SetAxesEffectiveOn(true);
	}

}
