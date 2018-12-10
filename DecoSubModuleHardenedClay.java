package net.minecraft.src;

public class DecoSubModuleHardenedClay implements DecoISubModule 
{
	public static Block decoBlockHardenedClay;
	public static DecoBlockSlab decoBlockHardenedClaySlab;
	public static DecoBlockSlab decoBlockHardenedClaySlabTop;
	public static Block decoBlockHardenedClayStairs;
	public static Block decoBlockHardenedClayWall;
	
	public static Item decoItemHardenedClaySlab;
	public static Item decoItemHardenedClaySlabTop;
	
	public static final int	decoBlockHardenedClayID = DecoAddonManager.getBlockID("decoBlockHardenedClayID");
	public static final int decoBlockHardenedClaySlabID = DecoAddonManager.getBlockID("decoBlockHardenedClaySlabID");
	public static final int decoBlockHardenedClaySlabTopID = DecoAddonManager.getBlockID("decoBlockHardenedClaySlabTopID");
	public static final int	decoBlockHardenedClayStairsID = DecoAddonManager.getBlockID("decoBlockHardenedClayStairsID");
	public static final int	decoBlockHardenedClayWallID = DecoAddonManager.getBlockID("decoBlockHardenedClayWallID");
	
	public DecoSubModuleHardenedClay()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Hardened Clay");
		
		this.decoBlockHardenedClay = new DecoBlockHardenedClay(this.decoBlockHardenedClayID, Material.rock);
		this.decoBlockHardenedClaySlab = new DecoBlockSlab(this.decoBlockHardenedClaySlabID, this.decoBlockHardenedClay, false,
				this.decoBlockHardenedClaySlab, this.decoBlockHardenedClaySlabTop);
		this.decoBlockHardenedClaySlabTop = new DecoBlockSlab(this.decoBlockHardenedClaySlabTopID, this.decoBlockHardenedClay, true,
				this.decoBlockHardenedClaySlab, this.decoBlockHardenedClaySlabTop);
		this.decoBlockHardenedClayStairs = new DecoBlockStair(this.decoBlockHardenedClayStairsID, this.decoBlockHardenedClay, 0);
		this.decoBlockHardenedClayWall = new DecoBlockWall(this.decoBlockHardenedClayWallID, this.decoBlockHardenedClay);
		
		this.decoItemHardenedClaySlab = new DecoItemSlab(this.decoBlockHardenedClaySlabID - 256, 
				this.decoBlockHardenedClaySlab, this.decoBlockHardenedClaySlabTop, false).setUnlocalizedName(this.decoBlockHardenedClay.getUnlocalizedName() + ".slab");
		this.decoItemHardenedClaySlabTop = new DecoItemSlab(this.decoBlockHardenedClaySlabTopID - 256, 
				this.decoBlockHardenedClaySlab, this.decoBlockHardenedClaySlabTop, true).setUnlocalizedName(this.decoBlockHardenedClay.getUnlocalizedName() + ".slab");
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
		
		FCAddOnHandler.LogMessage("[INFO]: Loaded submodule: Hardened Clay");
	}
	
	public void registerBlocks() 
	{
		if (DecoAddonManager.DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Registering blocks: Hardened Clay");
		
		DecoAddonManager.register(this.decoBlockHardenedClay, "Hardened Clay");
		DecoAddonManager.register(this.decoBlockHardenedClaySlab, "Hardened Clay Slab");
		DecoAddonManager.register(this.decoBlockHardenedClaySlabTop, "Hardened Clay Slab");
		DecoAddonManager.register(this.decoBlockHardenedClayStairs, "Hardened Clay Stairs");
		DecoAddonManager.register(this.decoBlockHardenedClayWall, "Hardened Clay Wall");
		
		DecoAddonManager.replaceItem(this.decoBlockHardenedClaySlabID, decoItemHardenedClaySlab);
		DecoAddonManager.replaceItem(this.decoBlockHardenedClaySlabTopID, decoItemHardenedClaySlabTop);
	}

	public void addRecipes() 
	{
		if (DecoAddonManager.DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Adding recipes: Hardened Clay");
		
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockHardenedClay,this.decoBlockHardenedClaySlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockHardenedClay, this.decoBlockHardenedClayStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockHardenedClay, this.decoBlockHardenedClayWall, 2);
	}

	public void changeVanillaItems() 
	{
		if (DecoAddonManager.DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Changing items: Hardened Clay");
		
		FCBetterThanWolves.fcBlockUnfiredClay.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(this.decoBlockHardenedClay.blockID);
	}

	public void setupCustomToolProperties() 
	{
		if (DecoAddonManager.DEBUG_ADDON_LOAD) FCAddOnHandler.LogMessage("[INFO]: Setting tool properties: Hardened Clay");
		
		this.decoBlockHardenedClay.SetPicksEffectiveOn(true);
		this.decoBlockHardenedClaySlab.SetPicksEffectiveOn(true);
		this.decoBlockHardenedClaySlabTop.SetPicksEffectiveOn(true);
		this.decoBlockHardenedClayStairs.SetPicksEffectiveOn(true);
		this.decoBlockHardenedClayWall.SetPicksEffectiveOn(true);	
	}
}
