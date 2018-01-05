package net.minecraft.src;

public class DecoModuleTweaks implements DecoIModule
{
	public static DecoSubModuleDiamondium decoSubModuleDiamondium;
	public static DecoSubModuleExtendedStoneBlocks decoSubModuleExtendedStoneBlocks;
	public static DecoSubModuleExtendedWoodBlocks decoSubModuleExtendedWoodBlocks;
	public static DecoSubModuleExtendedDirtAndGrassBlocks decoSubModuleExtendedDirtAndGrassBlocks;
	
	public static Block decoBlockGlass;
	public static Block decoBlockIce;
	public static Block decoBlockLadder;
	public static Block decoBlockObsidian;
	public static Block decoBlockSnowBlock;
	
	public static Item decoItemAsh;
	public static Item decoItemCoal;
	public static Item decoItemGlassShard;
	
	public static final int decoItemAshID = DecoAddonManager.getBlockID("decoItemAshID");
	public static final int decoItemGlassShardID = DecoAddonManager.getBlockID("decoItemGlassShardID");
	
	public DecoModuleTweaks()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Tweaks");
		
		if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
			this.decoSubModuleExtendedDirtAndGrassBlocks = new DecoSubModuleExtendedDirtAndGrassBlocks();
		
		if (DecoAddonManager.getConfigOption("enableExtendedStoneBlocks"))
			this.decoSubModuleExtendedStoneBlocks = new DecoSubModuleExtendedStoneBlocks();
		
		if (DecoAddonManager.getConfigOption("enableExtendedWoodBlocks"))
			this.decoSubModuleExtendedWoodBlocks = new DecoSubModuleExtendedWoodBlocks();
		
		if (DecoAddonManager.getConfigOption("enableDiamondium"))
			this.decoSubModuleDiamondium = new DecoSubModuleDiamondium();
		
		this.decoItemGlassShard = new DecoItemGlassShard(this.decoItemGlassShardID);
		
		// REPLACE VANILLA BLOCKS
		this.decoBlockGlass = new DecoBlockGlass(DecoAddonManager.replaceBlockID(Block.glass));
		this.decoBlockIce = new DecoBlockIce(DecoAddonManager.replaceBlockID(Block.ice));
		this.decoBlockLadder = new DecoBlockLadder(DecoAddonManager.replaceBlockID(Block.ladder));
		this.decoBlockObsidian = new DecoBlockObsidian(DecoAddonManager.replaceBlockID(Block.obsidian));
		this.decoBlockSnowBlock	= new DecoBlockSnowBlock(DecoAddonManager.replaceBlockID(Block.blockSnow));
		
		// REPLACE VANILLA ITEMS
		Item.coal = new DecoItemCoal(Item.coal.itemID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemGlassShard, "Glass Shard");
	}

	public void addRecipes() 
	{	
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Block.glass, 1), new ItemStack[] { new ItemStack(this.decoItemGlassShard, 4) });
		
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(this.decoItemGlassShard, 1), new ItemStack[] { new ItemStack(FCBetterThanWolves.fcItemPileSand) });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(this.decoItemGlassShard, 2), new ItemStack[] { new ItemStack(Item.glassBottle, 1) });
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(this.decoItemGlassShard, 4), new Object[] { new ItemStack(Block.glass, 1) });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(Block.snow, 6), new Object[] { "SS", 'S', Block.blockSnow });
		
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockObsidian, this.decoBlockObsidian, 2, 4);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockObsidian, this.decoBlockObsidian, 1, 4);
	}

	public void changeVanillaItems()
	{
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.cobblestoneWall, 6, 0), new Object[] { "###", "###", '#', Block.cobblestone });
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.cobblestoneWall, 6, 1), new Object[] { "###", "###", '#', Block.cobblestoneMossy });

		FCRecipes.AddVanillaRecipe(new ItemStack(Block.cobblestoneWall, 2, 0), new Object[] { "###", "###", '#', Block.cobblestone });
		FCRecipes.AddVanillaRecipe(new ItemStack(Block.cobblestoneWall, 2, 1), new Object[] { "###", "###", '#', Block.cobblestoneMossy });
		
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.glassBottle, 3), new Object[] { "# #", " # ", '#', Block.glass });
		FCRecipes.AddVanillaRecipe(new ItemStack(Item.glassBottle, 3), new Object[] {" # ", "# #", "###", '#', this.decoItemGlassShard });
	}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockObsidian);
	}
}
