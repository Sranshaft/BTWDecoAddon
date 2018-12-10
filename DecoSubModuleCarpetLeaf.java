package net.minecraft.src;

public class DecoSubModuleCarpetLeaf implements DecoISubModule
{
	public static Block decoBlockCarpetLeaf;

	public static final int decoBlockCarpetLeafID = DecoAddonManager.getBlockID("decoBlockCarpetLeafID");

	public DecoSubModuleCarpetLeaf()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Leaf Carpet");

		this.decoBlockCarpetLeaf = new DecoBlockCarpetLeaf(this.decoBlockCarpetLeafID);

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() {}

	public void addRecipes() 
	{	
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCarpetLeaf, 4, 0), new Object[] {"XX", 'X', new ItemStack(Block.leaves, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCarpetLeaf, 4, 1), new Object[] {"XX", 'X', new ItemStack(Block.leaves, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCarpetLeaf, 4, 2), new Object[] {"XX", 'X', new ItemStack(Block.leaves, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCarpetLeaf, 4, 3), new Object[] {"XX", 'X', new ItemStack(Block.leaves, 1, 3) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCarpetLeaf, 4, 4), new Object[] {"XX", 'X', new ItemStack(FCBetterThanWolves.fcBlockBloodLeaves, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCarpetLeaf, 4, 5), new Object[] {"XX", 'X', new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLeaves, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCarpetLeaf, 4, 6), new Object[] {"XX", 'X', new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLeaves, 1, 1) });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockCarpetLeaf.SetAxesEffectiveOn(true);
	}
}
