package net.minecraft.src;

public class DecoSubModuleDiamondium implements DecoISubModule
{
	public Block decoBlockDiamondium;
	
	public static final int decoBlockDiamondiumID = DecoAddonManager.getBlockID("decoBlockDiamondiumID");
	
	public DecoSubModuleDiamondium()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Diamondium");
		
		this.decoBlockDiamondium = new DecoBlockDiamondium(this.decoBlockDiamondiumID, Material.iron);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockDiamondium, "Block of Diamondium");
	}
	
	public void addRecipes()
	{
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcItemIngotDiamond, this.decoBlockDiamondium);
	}
	
	public void changeVanillaItems() { }
	
	public void setupCustomToolProperties()
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockDiamondium);
	}
}
