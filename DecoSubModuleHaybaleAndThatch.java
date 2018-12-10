package net.minecraft.src;

public class DecoSubModuleHaybaleAndThatch implements DecoISubModule
{
	public static Block decoBlockHaybale;
	public static Block decoBlockThatch;
	public static DecoBlockSlab decoBlockThatchSlab;
	public static DecoBlockSlab decoBlockThatchSlabTop;
	public static Block decoBlockThatchStairs;
	
	public static Item decoItemThatchSlab;
	public static Item decoItemThatchSlabTop;
	
	public static final int decoBlockHaybaleID = DecoAddonManager.getBlockID("decoBlockHaybaleID");
	public static final int decoBlockThatchID = DecoAddonManager.getBlockID("decoBlockThatchID");
	public static final int decoBlockThatchSlabID = DecoAddonManager.getBlockID("decoBlockThatchSlabID");
	public static final int decoBlockThatchSlabTopID = DecoAddonManager.getBlockID("decoBlockThatchSlabTopID");
	public static final int decoBlockThatchStairsID = DecoAddonManager.getBlockID("decoBlockThatchStairsID");
	
	
	public DecoSubModuleHaybaleAndThatch()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Haybale and Thatch");
		
		this.decoBlockHaybale = new DecoBlockHaybale(this.decoBlockHaybaleID);
		this.decoBlockThatch = new DecoBlockThatch(this.decoBlockThatchID);
		this.decoBlockThatchSlab = new DecoBlockSlab(this.decoBlockThatchSlabID, this.decoBlockThatch, false,
				this.decoBlockThatchSlab, this.decoBlockThatchSlabTop);
		this.decoBlockThatchSlabTop = new DecoBlockSlab(this.decoBlockThatchSlabTopID, this.decoBlockThatch, true,
				this.decoBlockThatchSlab, this.decoBlockThatchSlabTop);
		this.decoBlockThatchStairs = new DecoBlockStair(this.decoBlockThatchStairsID, this.decoBlockThatch, 0);
		
		this.decoItemThatchSlab = new DecoItemSlab(this.decoBlockThatchSlabID - 256, 
				this.decoBlockThatchSlab, this.decoBlockThatchSlabTop, false).setUnlocalizedName(this.decoBlockThatch.getUnlocalizedName() + ".slab");
		this.decoItemThatchSlabTop = new DecoItemSlab(this.decoBlockThatchSlabTopID - 256, 
				this.decoBlockThatchSlab, this.decoBlockThatchSlabTop, false).setUnlocalizedName(this.decoBlockThatch.getUnlocalizedName() + ".slab");
		
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
		
		DecoAddonManager.replaceItem(this.decoBlockThatchSlabID, decoItemThatchSlab);
		DecoAddonManager.replaceItem(this.decoBlockThatchSlabTopID, decoItemThatchSlabTop);
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
