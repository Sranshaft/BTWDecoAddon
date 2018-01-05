package net.minecraft.src;

public class DecoModuleStorage implements DecoIModule
{
	public static Block decoBlockBone;
	public static Block decoBlockStorage;
	public static Block decoBlockStorageCongealed;
	public static Block decoBlockStoragePowder;
	
	public static final int decoBlockBoneID = DecoAddonManager.getBlockID("decoBlockBoneID");
	public static final int decoBlockStorageID = DecoAddonManager.getBlockID("decoBlockStorageID");
	public static final int decoBlockStorageCongealedID = DecoAddonManager.getBlockID("decoBlockStorageCongealedID");
	public static final int decoBlockStoragePowderID = DecoAddonManager.getBlockID("decoBlockStoragePowderID");
	
	public DecoModuleStorage()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Storage");
		
		this.decoBlockBone = new DecoBlockBone(this.decoBlockBoneID);
		this.decoBlockStorage = new DecoBlockStorage(this.decoBlockStorageID);
		this.decoBlockStorageCongealed = new DecoBlockStorageCongealed(this.decoBlockStorageCongealedID);
		this.decoBlockStoragePowder = new DecoBlockStoragePowder(this.decoBlockStoragePowderID);
		
		this.registerBlocks();
		this.addRecipes();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockBone, "Block of Bone");
	}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addStorageRecipe(Item.bone, this.decoBlockBone);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockBone);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockStorage);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockStorageCongealed);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockStoragePowder);
	}

}
