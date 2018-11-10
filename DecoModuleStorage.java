package net.minecraft.src;

public class DecoModuleStorage implements DecoIModule
{
	public static Block decoBlockBone;
	public static Block decoBlockStorage;
	public static Block decoBlockStorageCongealed;
	public static Block decoBlockStoragePowder;
	
	public static Item decoItemFertilizer;
	
	public static final int decoBlockBoneID = DecoAddonManager.getBlockID("decoBlockBoneID");
	public static final int decoBlockStorageID = DecoAddonManager.getBlockID("decoBlockStorageID");
	public static final int decoBlockStorageCongealedID = DecoAddonManager.getBlockID("decoBlockStorageCongealedID");
	public static final int decoBlockStoragePowderID = DecoAddonManager.getBlockID("decoBlockStoragePowderID");
	
	public static final int decoItemFertilizerID = DecoAddonManager.getBlockID("decoItemFertilizerID");
	
	public DecoModuleStorage()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Storage");
		
		this.decoBlockBone = new DecoBlockBone(this.decoBlockBoneID);
		this.decoBlockStorage = new DecoBlockStorage(this.decoBlockStorageID);
		this.decoBlockStorageCongealed = new DecoBlockStorageCongealed(this.decoBlockStorageCongealedID);
		this.decoBlockStoragePowder = new DecoBlockStoragePowder(this.decoBlockStoragePowderID);
		
		this.decoItemFertilizer = new DecoItemFertilizer(this.decoItemFertilizerID);
		
		this.registerBlocks();
		this.addRecipes();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockBone, "Block of Bone");
		DecoAddonManager.register(this.decoItemFertilizer, "Fertilizer");
	}

	public void addRecipes() 
	{
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(this.decoItemFertilizer, 2),
				new ItemStack[] { new ItemStack(Item.dyePowder, 1, 15), new ItemStack(FCBetterThanWolves.fcDung) });
		
		DecoUtilsRecipes.addStorageRecipe(Item.bone, this.decoBlockBone);
		DecoUtilsRecipes.addStorageRecipe(new ItemStack(DecoModuleDecoration.decoBlockBambooID, 1, 0), new ItemStack(this.decoBlockStorage, 1, 0));
		DecoUtilsRecipes.addStorageRecipe(Item.blazeRod, new ItemStack(this.decoBlockStorage, 1, 1));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcItemBrimstone, new ItemStack(this.decoBlockStorage, 1, 2));
		DecoUtilsRecipes.addStorageRecipe(new ItemStack(Item.coal, 1, 1), new ItemStack(this.decoBlockStorage, 1, 3));
		DecoUtilsRecipes.addStorageRecipe(new ItemStack(Item.coal, 1, 0), new ItemStack(this.decoBlockStorage, 1, 4));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcNethercoal, new ItemStack(this.decoBlockStorage, 1, 5));
		DecoUtilsRecipes.addStorageRecipe(Item.netherStalkSeeds, new ItemStack(this.decoBlockStorage, 1, 6));
		DecoUtilsRecipes.addStorageRecipe(Item.reed, new ItemStack(this.decoBlockStorage, 1, 7));
		DecoUtilsRecipes.addStorageRecipe(Item.stick, new ItemStack(this.decoBlockStorage, 1, 8));
		
		// Congealed
		
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcItemCreeperOysters, new ItemStack(this.decoBlockStorageCongealed, 1, 0));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcGlue, new ItemStack(this.decoBlockStorageCongealed, 1, 1));
		DecoUtilsRecipes.addStorageRecipe(Item.magmaCream, new ItemStack(this.decoBlockStorageCongealed, 1, 2));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcItemMysteriousGland, new ItemStack(this.decoBlockStorageCongealed, 1, 3));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcNetherSludge, new ItemStack(this.decoBlockStorageCongealed, 1, 4));
		DecoUtilsRecipes.addStorageRecipe(Item.slimeBall, new ItemStack(this.decoBlockStorageCongealed, 1, 5));
		DecoUtilsRecipes.addStorageRecipe(Item.spiderEye, new ItemStack(this.decoBlockStorageCongealed, 1, 6));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcTallow, new ItemStack(this.decoBlockStorageCongealed, 1, 7));
		DecoUtilsRecipes.addStorageRecipe(new ItemStack(Item.fermentedSpiderEye, 1), new ItemStack(this.decoBlockStorageCongealed, 1, 8));
		
		// Powdered
		
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcPotash, new ItemStack(this.decoBlockStoragePowder, 1, 0));
		DecoUtilsRecipes.addStorageRecipe(Item.blazePowder, new ItemStack(this.decoBlockStoragePowder, 1, 1));
		DecoUtilsRecipes.addStorageRecipe(new ItemStack(Item.dyePowder, 1, 15), new ItemStack(this.decoBlockStoragePowder, 1, 2));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcCoalDust, new ItemStack(this.decoBlockStoragePowder, 1, 3));
		DecoUtilsRecipes.addStorageRecipe(new ItemStack(Item.dyePowder, 1, 3), new ItemStack(this.decoBlockStoragePowder, 1, 4));
		DecoUtilsRecipes.addStorageRecipe(this.decoItemFertilizer, new ItemStack(this.decoBlockStoragePowder, 1, 5));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcFlour, new ItemStack(this.decoBlockStoragePowder, 1, 6));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcGroundNetherrack, new ItemStack(this.decoBlockStoragePowder, 1, 7));
		DecoUtilsRecipes.addStorageRecipe(Item.gunpowder, new ItemStack(this.decoBlockStoragePowder, 1, 8));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcHellfireDust, new ItemStack(this.decoBlockStoragePowder, 1, 9));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcItemNitre, new ItemStack(this.decoBlockStoragePowder, 1, 10));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcPotash, new ItemStack(this.decoBlockStoragePowder, 1, 11));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcItemSawDust, new ItemStack(this.decoBlockStoragePowder, 1, 12));
		DecoUtilsRecipes.addStorageRecipe(FCBetterThanWolves.fcSoulDust, new ItemStack(this.decoBlockStoragePowder, 1, 13));
		DecoUtilsRecipes.addStorageRecipe(Item.sugar, new ItemStack(this.decoBlockStoragePowder, 1, 14));
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockBone);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockStorage);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockStorage);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockStorageCongealed);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockStoragePowder);
	}

}
