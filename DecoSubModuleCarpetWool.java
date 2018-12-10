package net.minecraft.src;

public class DecoSubModuleCarpetWool implements DecoISubModule
{
	public static Block decoBlockCarpetWool;

	public static final int decoBlockCarpetWoolID = DecoAddonManager.getBlockID("decoBlockCarpetWoolID");

	public DecoSubModuleCarpetWool()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Wool Carpet");

		this.decoBlockCarpetWool = new DecoBlockCarpetWool(this.decoBlockCarpetWoolID);

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
			FCRecipes.AddVanillaRecipe( new ItemStack(this.decoBlockCarpetWool, 4, index),
					new Object[] {"XX ", 'X', new ItemStack(Block.cloth, 1, index) });
		}
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockCarpetWool.SetAxesEffectiveOn(true);
	}
}
