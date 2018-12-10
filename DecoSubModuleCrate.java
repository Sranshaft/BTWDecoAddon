package net.minecraft.src;

public class DecoSubModuleCrate implements DecoISubModule 
{
	public static Block decoBlockCrate;

	public static final int decoBlockCrateID = DecoAddonManager.getBlockID("decoBlockCrateID");
	public static final int decoContainerCrateID = 301;

	public DecoSubModuleCrate()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Crates");

		TileEntity.addMapping(DecoTileEntityCrate.class, "Crate");
		this.decoBlockCrate = new DecoBlockCrate(this.decoBlockCrateID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() {}

	public void addRecipes() 
	{

	}

	public void changeVanillaItems() {}

	@Override
	public void setupCustomToolProperties() 
	{
		this.decoBlockCrate.SetAxesEffectiveOn(true);
	}

}
