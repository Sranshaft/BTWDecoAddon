package net.minecraft.src;

public class DecoSubModuleToolSpyglass implements DecoISubModule 
{
	public static Item decoItemSpyglass;
	
	public static final int decoItemSpyglassID = DecoAddonManager.getBlockID("decoItemSpyglassID");
	
	public DecoSubModuleToolSpyglass()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Spyglass");
		
		this.decoItemSpyglass = new DecoItemSpyglass(this.decoItemSpyglassID);
		
		this.registerBlocks();
		this.addRecipes();
	}

	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemSpyglass, "Spyglass");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSpyglass),
				new Object[] { "#X#", "F F", "DXD", '#', Item.ingotGold, 'X', Block.thinGlass, 'F', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'D', Item.goldNugget });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSpyglass),
				new Object[] { "#X#", "F F", "DXD", '#', Item.ingotGold, 'X', Block.thinGlass, 'F', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'D', Item.goldNugget });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSpyglass),
				new Object[] { "#X#", "F F", "DXD", '#', Item.ingotGold, 'X', Block.thinGlass, 'F', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'D', Item.goldNugget });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSpyglass),
				new Object[] { "#X#", "F F", "DXD", '#', Item.ingotGold, 'X', Block.thinGlass, 'F', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'D', Item.goldNugget });
	}

	public void changeVanillaItems() {}
	public void setupCustomToolProperties() {}
}