package net.minecraft.src;

public class DecoSubModuleHaybale implements DecoISubModule
{
	public static Block decoBlockHaybale;
	public static Block decoBlockThatch;
	public static Block decoBlockThatchStairs;
	public static Block decoBlockThatchSlab;
	public static Block decoBlockThatchSlabTop;
	
	public static final int decoBlockHaybaleID = DecoAddonManager.getBlockID("decoBlockHaybaleID");
	public static final int decoBlockThatchID = DecoAddonManager.getBlockID("decoBlockThatchID");
	public static final int decoBlockThatchSlabID = DecoAddonManager.getBlockID("decoBlockThatchSlabID");
	public static final int decoBlockThatchSlabTopID = DecoAddonManager.getBlockID("decoBlockThatchSlabTopID");
	public static final int decoBlockThatchStairsID = DecoAddonManager.getBlockID("decoBlockThatchStairsID");
	
	
	public DecoSubModuleHaybale()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Haybale");
		
		this.decoBlockHaybale = new DecoBlockHaybale(this.decoBlockHaybaleID, Material.grass);
		this.decoBlockThatch = new DecoBlockThatch(this.decoBlockThatchID, Material.grass);
		this.decoBlockThatchSlab = new DecoBlockSlab(this.decoBlockThatchSlabID, this.decoBlockThatch, false, this.decoBlockThatchSlabID, this.decoBlockThatchSlabTopID);
		this.decoBlockThatchSlabTop = new DecoBlockSlab(this.decoBlockThatchSlabTopID, this.decoBlockThatch, true, this.decoBlockThatchSlabID, this.decoBlockThatchSlabTopID);
		this.decoBlockThatchStairs = new DecoBlockStair(this.decoBlockThatchStairsID, this.decoBlockThatch, 0);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
		this.setupCustomFireProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockHaybale, "Haybale");
		DecoAddonManager.register(this.decoBlockThatch, "Thatch");
		DecoAddonManager.register(this.decoBlockThatchSlab, "Thatch Slab");
		DecoAddonManager.register(this.decoBlockThatchSlabTop, "Thatch Slab");
		DecoAddonManager.register(this.decoBlockThatchStairs, "Thatch Stairs");
	}
	
	public void addRecipes()
	{
		DecoUtilsRecipes.addBlockRecipe(Block.tallGrass, this.decoBlockThatch, 1);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockThatch, this.decoBlockThatchSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockThatch, this.decoBlockThatchStairs, 4);
		DecoUtilsRecipes.addStorageRecipe(Item.wheat, this.decoBlockHaybale);
	}
	
	public void changeVanillaItems() {}
	
	public void setupCustomFireProperties()
	{
		DecoUtils.setFirePropertiesOfBlock(this.decoBlockHaybaleID, 60, 100);
		DecoUtils.setFirePropertiesOfBlock(this.decoBlockThatchID, 60, 100);
		DecoUtils.setFirePropertiesOfBlock(this.decoBlockThatchSlabID, 60, 100);
		DecoUtils.setFirePropertiesOfBlock(this.decoBlockThatchSlabTopID, 60, 100);
		DecoUtils.setFirePropertiesOfBlock(this.decoBlockThatchStairsID, 60, 100);
	}
	
	public void setupCustomToolProperties()
	{
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockHaybale);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockThatch);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockThatchSlab);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockThatchSlabTop);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockThatchStairs);
	}
}
