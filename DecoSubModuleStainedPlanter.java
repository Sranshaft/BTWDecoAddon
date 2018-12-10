package net.minecraft.src;

public class DecoSubModuleStainedPlanter implements DecoISubModule 
{
	public static Block decoBlockStainedPlanter;
	
	public static final int decoBlockStainedPlanterID = DecoAddonManager.getBlockID("decoBlockStainedPlanterID");
	
	public DecoSubModuleStainedPlanter()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Stained Planter");
		
		this.decoBlockStainedPlanter = new DecoBlockStainedPlanter(this.decoBlockStainedPlanterID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() {}
	
	public void addRecipes() 
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(this.decoBlockStainedPlanter, 1, index), 
					new ItemStack[] { new ItemStack(FCBetterThanWolves.fcPlanter, 1, 1), new ItemStack(Item.dyePowder, 1, ~index & 15) });
		}
	}
	
	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties()
	{
		this.decoBlockStainedPlanter.SetPicksEffectiveOn(true);
	}
}
