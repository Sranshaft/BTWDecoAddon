package net.minecraft.src;

public class DecoSubModuleSack implements DecoISubModule 
{
	public static Block decoBlockSack;

	public static final int decoBlockSackID = DecoAddonManager.getBlockID("decoBlockSackID");
	public static final int decoContainerSackID = 302;

	public DecoSubModuleSack()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Sack");

		TileEntity.addMapping(DecoTileEntitySack.class, "Sack");
		this.decoBlockSack = new DecoBlockSack(this.decoBlockSackID);

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockSack, "Sack");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockSack, 1), 
				new Object[] { "###", "# #", "###", '#', FCBetterThanWolves.fcHempCloth });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockSack.SetAxesEffectiveOn(true);
	}

}
