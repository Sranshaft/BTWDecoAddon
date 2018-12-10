package net.minecraft.src;

public class DecoModuleTweaks implements DecoIModule
{
	public static DecoSubModuleDiamondium decoSubModuleDiamondium;
	public static DecoSubModuleExtendedDirtAndGrassBlock decoSubModuleExtendedDirtAndGrassBlocks;
	public static DecoSubModuleExtendedStoneBlock decoSubModuleExtendedStoneBlocks;
	public static DecoSubModuleExtendedWoodBlock decoSubModuleExtendedWoodBlocks;
	public static DecoSubModuleStorageBlocks decoSubModuleStorageBlocks;
	
	public static DecoBlockAestheticNonOpaque decoBlockAestheticNonOpaque;
	public static BlockFlowerPot decoBlockFlowerPot;
	public static Block decoBlockGlass;
	public static Block decoBlockIce;
	public static Block decoBlockIronBar;
	public static Block decoBlockLadder;
	public static Block decoBlockObsidian;
	public static Block decoBlockSnowBlock;
	public static Block decoBlockPlanter;
	
	public static Item decoItemAsh;
	public static Item decoItemCoal;
	public static Item decoItemFertilizer;
	public static Item decoItemGlassShard;
	
	public static final int decoItemAshID = DecoAddonManager.getBlockID("decoItemAshID");
	public static final int decoItemFertilizerID = DecoAddonManager.getBlockID("decoItemFertilizerID");
	public static final int decoItemGlassShardID = DecoAddonManager.getBlockID("decoItemGlassShardID");
	
	public DecoModuleTweaks()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Tweaks");
		
		this.decoItemFertilizer = new DecoItemFertilizer(this.decoItemFertilizerID);
		this.decoItemGlassShard = new DecoItemGlassShard(this.decoItemGlassShardID);
		
		if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
			this.decoSubModuleExtendedDirtAndGrassBlocks = new DecoSubModuleExtendedDirtAndGrassBlock();
		
		if (DecoAddonManager.getConfigOption("enableExtendedStoneBlocks"))
			this.decoSubModuleExtendedStoneBlocks = new DecoSubModuleExtendedStoneBlock();
		
		if (DecoAddonManager.getConfigOption("enableExtendedWoodBlocks"))
			this.decoSubModuleExtendedWoodBlocks = new DecoSubModuleExtendedWoodBlock();
		
		if (DecoAddonManager.getConfigOption("enableDiamondium"))
			this.decoSubModuleDiamondium = new DecoSubModuleDiamondium();
		
		if (DecoAddonManager.getConfigOption("enableStorageBlocks"))
			this.decoSubModuleStorageBlocks = new DecoSubModuleStorageBlocks();
		
		// REPLACE VANILLA BLOCKS
		this.decoBlockFlowerPot = new DecoBlockFlowerPot(DecoAddonManager.replaceBlockID(Block.flowerPot));
		this.decoBlockGlass = new DecoBlockGlass(DecoAddonManager.replaceBlockID(Block.glass));
		this.decoBlockIce = new DecoBlockIce(DecoAddonManager.replaceBlockID(Block.ice));
		this.decoBlockIronBar = new DecoBlockPane(DecoAddonManager.replaceBlockID(Block.fenceIron), Material.iron).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("fenceIron");
		this.decoBlockLadder = new DecoBlockLadderWood(DecoAddonManager.replaceBlockID(Block.ladder), "oak");
		this.decoBlockObsidian = new DecoBlockObsidian(DecoAddonManager.replaceBlockID(Block.obsidian));
		this.decoBlockSnowBlock	= new DecoBlockSnowBlock(DecoAddonManager.replaceBlockID(Block.blockSnow));
		
		// REPLACE VANILLA ITEMS
		Item.coal = new DecoItemCoal(Item.coal.itemID);
		
		// REPLACE BTW BLOCKS
		FCBetterThanWolves.fcAestheticNonOpaque = new DecoBlockAestheticNonOpaque(DecoAddonManager.replaceBlockID(FCBetterThanWolves.fcAestheticNonOpaque));
		FCBetterThanWolves.fcPlanter = new DecoBlockPlanter(DecoAddonManager.replaceBlockID(FCBetterThanWolves.fcPlanter));
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemFertilizer, "Fertilizer");
		DecoAddonManager.register(this.decoItemGlassShard, "Glass Shard");
	}

	public void addRecipes() 
	{	
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(this.decoItemFertilizer, 2),
				new ItemStack[] { new ItemStack(Item.dyePowder, 1, 15), new ItemStack(FCBetterThanWolves.fcDung) });
		
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
		
		FCRecipes.AddVanillaRecipe(new ItemStack(Item.stick, 1), new Object[] {"#", '#', new ItemStack(Block.sapling, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(Item.stick, 1), new Object[] {"#", '#', new ItemStack(Block.sapling, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(Item.stick, 1), new Object[] {"#", '#', new ItemStack(Block.sapling, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(Item.stick, 1), new Object[] {"#", '#', new ItemStack(Block.sapling, 1, 3) });
		FCRecipes.AddVanillaRecipe(new ItemStack(Item.stick, 1), new Object[] {"#", '#', new ItemStack(FCBetterThanWolves.fcAestheticVegetation, 1, 2) });
	}

	public void setupCustomToolProperties() 
	{
		this.decoBlockFlowerPot.SetPicksEffectiveOn();
		this.decoBlockGlass.SetPicksEffectiveOn();
		this.decoBlockIce.SetPicksEffectiveOn();
		this.decoBlockIronBar.SetPicksEffectiveOn();
		this.decoBlockLadder.SetAxesEffectiveOn();
		this.decoBlockObsidian.SetPicksEffectiveOn();
		//this.decoBlockPlanter.SetPicksEffectiveOn();
		this.decoBlockSnowBlock.SetShovelsEffectiveOn();
	}
}
