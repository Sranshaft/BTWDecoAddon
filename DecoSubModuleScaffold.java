package net.minecraft.src;

public class DecoSubModuleScaffold implements DecoISubModule 
{
	public static Block decoBlockScaffoldMetal;
	public static Block decoBlockScaffoldWood;
	
	public static final int decoBlockScaffoldMetalID = DecoAddonManager.getBlockID("decoBlockScaffoldMetalID");
	public static final int decoBlockScaffoldWoodID = DecoAddonManager.getBlockID("decoBlockScaffoldWoodID");
	
	public DecoSubModuleScaffold()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Scaffold");
		
		this.decoBlockScaffoldMetal = new DecoBlockScaffold(this.decoBlockScaffoldMetalID, Material.iron, "iron");
		this.decoBlockScaffoldWood = new DecoBlockScaffold(this.decoBlockScaffoldWoodID, Material.wood, "wood");
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockScaffoldMetal, "Iron Scaffold");
		DecoAddonManager.register(this.decoBlockScaffoldWood, "Wood Scaffold");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockScaffoldMetal, 2), 
				new Object[] { "SXS", "XSX", "SXS", 'S', Item.stick, 'X', Item.ingotIron });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockScaffoldWood, 2), 
				new Object[] { "SXS", "XSX", "SXS", 'S', Item.stick, 'X', new ItemStack(Block.planks, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockScaffoldWood, 2), 
				new Object[] { "SXS", "XSX", "SXS", 'S', Item.stick, 'X', new ItemStack(Block.planks, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockScaffoldWood, 2), 
				new Object[] { "SXS", "XSX", "SXS", 'S', Item.stick, 'X', new ItemStack(Block.planks, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockScaffoldWood, 2), 
				new Object[] { "SXS", "XSX", "SXS", 'S', Item.stick, 'X', new ItemStack(Block.planks, 1, 3) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockScaffoldWood, 2), 
				new Object[] { "SXS", "XSX", "SXS", 'S', Item.stick, 'X', new ItemStack(FCBetterThanWolves.fcBloodWood, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockScaffoldWood, 2), 
				new Object[] { "SXS", "XSX", "SXS", 'S', Item.stick, 'X', new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockPlanks, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockScaffoldWood, 2), 
				new Object[] { "SXS", "XSX", "SXS", 'S', Item.stick, 'X', new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockPlanks, 1, 1) });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockScaffoldMetal.SetPicksEffectiveOn(true);
		this.decoBlockScaffoldWood.SetAxesEffectiveOn(true);
	}

}
