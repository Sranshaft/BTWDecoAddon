package net.minecraft.src;

public class DecoSubModuleFurniture implements DecoISubModule 
{
	public static Block[] decoBlockBookcases = new Block[DecoUtilsStrings.WOOD_TAGS.length];
	public static Block[] decoBlockCabinets = new Block[DecoUtilsStrings.WOOD_TAGS.length];
	public static Block[] decoBlockChairs = new Block[DecoUtilsStrings.WOOD_TAGS.length];
	public static Block[] decoBlockShelves = new Block[DecoUtilsStrings.WOOD_TAGS.length];
	public static Block[] decoBlockWardrobes = new Block[DecoUtilsStrings.WOOD_TAGS.length];
	
	public static Block decoBlockBannister;
	public static Block decoBlockChairBlackStone;
	public static Block decoBlockChairBlackStoneBrick;
	public static Block decoBlockChairBrick;
	public static Block decoBlockChairBrimstone;
	public static Block decoBlockChairCharredNetherBrick;
	public static Block decoBlockChairDuskbound;
	public static Block decoBlockChairEbonstone;
	public static Block decoBlockChairEbonstoneBrick;
	public static Block decoBlockChairFroststone;
	public static Block decoBlockChairMidori;
	public static Block decoBlockChairNetherBrick;
	public static Block decoBlockChairNetherWroughtBrick;
	public static Block decoBlockChairObsidian;
	public static Block decoBlockChairRedSandstone;
	public static Block decoBlockChairRedSandstoneBrick;
	public static Block decoBlockChairSandstone;
	public static Block decoBlockChairSandstoneBrick;
	public static Block decoBlockChairSoulSandstone;
	public static Block decoBlockChairStone;
	public static Block decoBlockChairStoneBrick;
	public static Block decoBlockChairWhiteStone;
	public static Block decoBlockChairWhiteStoneBrick;
	
	public static Block decoBlockKegSmall;
	
	public static Block decoBlockTableWood;
	
	public static Block decoBlockTableStone;
	public static Block decoBlockTableStone2;
	public static Block decoBlockTableStone3;
	
	public static final int decoBlockArmorStandID = DecoAddonManager.getBlockID("decoBlockArmorStandID");
	public static final int decoBlockBannerID = DecoAddonManager.getBlockID("decoBlockBannerID");
	public static final int decoBlockBannisterID = DecoAddonManager.getBlockID("decoBlockBannisterID");
	public static final int decoBlockBookshelfID = DecoAddonManager.getBlockID("decoBlockBookshelfID");
	public static final int decoBlockBookcaseID = DecoAddonManager.getBlockID("decoBlockBookcaseID");
	public static final int decoBlockCabinetID = DecoAddonManager.getBlockID("decoBlockCabinetID");
	public static final int decoBlockChairWoodID = DecoAddonManager.getBlockID("decoBlockChairWoodID");
	
	public static final int decoBlockChairBlackStoneID = DecoAddonManager.getBlockID("decoBlockChairBlackStoneID");
	public static final int decoBlockChairBlackStoneBrickID = DecoAddonManager.getBlockID("decoBlockChairBlackStoneBrickID");
	public static final int decoBlockChairBrickID = DecoAddonManager.getBlockID("decoBlockChairBrickID");
	public static final int decoBlockChairBrimstoneID = DecoAddonManager.getBlockID("decoBlockChairBrimstoneID");
	public static final int decoBlockChairCharredNetherBrickID = DecoAddonManager.getBlockID("decoBlockChairCharredNetherBrickID");
	public static final int decoBlockChairDuskboundID = DecoAddonManager.getBlockID("decoBlockChairDuskboundID");
	public static final int decoBlockChairEbonstoneID = DecoAddonManager.getBlockID("decoBlockChairEbonstoneID");
	public static final int decoBlockChairEbonstoneBrickID = DecoAddonManager.getBlockID("decoBlockChairEbonstoneBrickID");
	public static final int decoBlockChairFroststoneID = DecoAddonManager.getBlockID("decoBlockChairFroststoneID");
	public static final int decoBlockChairNetherBrickID = DecoAddonManager.getBlockID("decoBlockChairNetherBrickID");
	public static final int decoBlockChairNetherWroughtBrickID = DecoAddonManager.getBlockID("decoBlockChairNetherWroughtBrickID");
	public static final int decoBlockChairObsidianID = DecoAddonManager.getBlockID("decoBlockChairObsidianID");
	public static final int decoBlockChairRedSandstoneID = DecoAddonManager.getBlockID("decoBlockChairRedSandstoneID");
	public static final int decoBlockChairRedSandstoneBrickID = DecoAddonManager.getBlockID("decoBlockChairRedSandstoneBrickID");
	public static final int decoBlockChairSandstoneID = DecoAddonManager.getBlockID("decoBlockChairSandstoneID");
	public static final int decoBlockChairSandstoneBrickID = DecoAddonManager.getBlockID("decoBlockChairSandstoneBrickID");
	public static final int decoBlockChairSoulSandstoneID = DecoAddonManager.getBlockID("decoBlockChairSoulSandstoneID");
	public static final int decoBlockChairStoneID = DecoAddonManager.getBlockID("decoBlockChairStoneID");
	public static final int decoBlockChairStoneBrickID = DecoAddonManager.getBlockID("decoBlockChairStoneBrickID");
	public static final int decoBlockChairWhiteStoneID = DecoAddonManager.getBlockID("decoBlockChairWhiteStoneID");
	public static final int decoBlockChairWhiteStoneBrickID = DecoAddonManager.getBlockID("decoBlockChairWhiteStoneBrickID");
	
	public static final int decoBlockLecternWoodID = DecoAddonManager.getBlockID("decoBlockLecternWoodID");
	public static final int decoBlockKegSmallID = DecoAddonManager.getBlockID("decoBlockKegSmallID");
	public static final int decoBlockShelfID = DecoAddonManager.getBlockID("decoBlockShelfID");
	public static final int decoBlockShieldID = DecoAddonManager.getBlockID("decoBlockShieldID");
	public static final int decoBlockTableWoodID = DecoAddonManager.getBlockID("decoBlockTableWoodID");
	public static final int decoBlockTableStoneID = DecoAddonManager.getBlockID("decoBlockTableStoneID");
	public static final int decoBlockTableStone2ID = DecoAddonManager.getBlockID("decoBlockTableStone2ID");
	public static final int decoBlockTableStone3ID = DecoAddonManager.getBlockID("decoBlockTableStone3ID");
	public static final int decoBlockToolRackID = DecoAddonManager.getBlockID("decoBlockToolRackID");
	public static final int decoBlockWardrobeID = DecoAddonManager.getBlockID("decoBlockWardrobeID");
	
	public DecoSubModuleFurniture()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Furniture");
		
		this.decoBlockBannister = new DecoBlockBannister(this.decoBlockBannisterID);
		
		for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
		{
			this.decoBlockBookcases[index] = new DecoBlockBookcase(this.decoBlockBookcaseID + index, Material.wood, DecoUtilsStrings.WOOD_TAGS[index]);
			this.decoBlockCabinets[index] = new DecoBlockCabinet(this.decoBlockCabinetID + index, DecoUtilsStrings.WOOD_TAGS[index]);
			this.decoBlockChairs[index] = new DecoBlockChairWood(this.decoBlockChairWoodID + index, Material.wood, DecoUtilsStrings.WOOD_TAGS[index]);
			this.decoBlockShelves[index] = new DecoBlockShelf(this.decoBlockShelfID + index, DecoUtilsStrings.WOOD_TAGS[index]);
			this.decoBlockWardrobes[index] = new DecoBlockWardrobe(this.decoBlockWardrobeID + index, DecoUtilsStrings.WOOD_TAGS[index]);
		}
		
		this.decoBlockChairBlackStone = new DecoBlockChairStone(this.decoBlockChairBlackStoneID, Block.blockNetherQuartz);
		this.decoBlockChairBlackStoneBrick = new DecoBlockChairStone(this.decoBlockChairBlackStoneBrickID, DecoModuleBuilding.decoSubModuleBlackStoneBrick.decoBlockBlackStoneBrick);
		this.decoBlockChairBrick = new DecoBlockChairStone(this.decoBlockChairBrickID, Block.brick);
		this.decoBlockChairBrimstone = new DecoBlockChairStone(this.decoBlockChairBrimstoneID, DecoModuleBuilding.decoSubModuleBrimstone.decoBlockBrimstone);
		this.decoBlockChairCharredNetherBrick = new DecoBlockChairStone(this.decoBlockChairCharredNetherBrickID, DecoModuleBuilding.decoSubModuleCharredNetherBrick.decoBlockCharredNetherBrick);
		this.decoBlockChairDuskbound = new DecoBlockChairStone(this.decoBlockChairDuskboundID, DecoModuleBuilding.decoSubModuleDuskbound.decoBlockDuskbound);
		this.decoBlockChairEbonstone = new DecoBlockChairStone(this.decoBlockChairEbonstoneID, DecoModuleBuilding.decoSubModuleEbonstoneAndBrick.decoBlockEbonstone);
		this.decoBlockChairEbonstoneBrick = new DecoBlockChairStone(this.decoBlockChairEbonstoneBrickID, DecoModuleBuilding.decoSubModuleEbonstoneAndBrick.decoBlockEbonstoneBrick);
		this.decoBlockChairNetherBrick = new DecoBlockChairStone(this.decoBlockChairNetherBrickID, Block.netherBrick);
		this.decoBlockChairNetherWroughtBrick = new DecoBlockChairStone(this.decoBlockChairNetherWroughtBrickID, DecoModuleBuilding.decoSubModuleNetherWroughtStone.decoBlockNetherWroughtStone);
		this.decoBlockChairObsidian = new DecoBlockChairStone(this.decoBlockChairObsidianID, DecoModuleTweaks.decoBlockObsidian);
		this.decoBlockChairRedSandstone = new DecoBlockChairStone(this.decoBlockChairRedSandstoneID, DecoModuleBuilding.decoSubModuleRedSandstone.decoBlockRedSandstone);
		this.decoBlockChairRedSandstoneBrick = new DecoBlockChairStone(this.decoBlockChairRedSandstoneBrickID, DecoModuleBuilding.decoSubModuleRedSandstoneBrick.decoBlockRedSandstoneBrick);
		
		//this.decoBlockChairSandstone = new DecoBlockChairStone(this.decoBlockChairSandstoneID, Block.sandStone);
		
		this.decoBlockChairSandstoneBrick = new DecoBlockChairStone(this.decoBlockChairSandstoneBrickID, DecoModuleBuilding.decoSubModuleSandstoneBrick.decoBlockSandstoneBrick);
		this.decoBlockChairStone = new DecoBlockChairStone(this.decoBlockChairStoneID, Block.stone);
		
		//this.decoBlockChairStoneBrick = new DecoBlockChairStone(this.decoBlockChairStoneBrickID, Block.stoneBrick);
		//this.decoBlockChairWhiteStone = new DecoBlockChairStone(this.decoBlockChairWhiteStoneID, FCBetterThanWolves.fcAestheticOpaque, 9);
		
		this.decoBlockChairWhiteStoneBrick = new DecoBlockChairStone(this.decoBlockChairWhiteStoneBrickID, DecoModuleBuilding.decoSubModuleWhiteStoneBrick.decoBlockWhiteStoneBrick);
		
		this.decoBlockKegSmall = new DecoBlockKeg(this.decoBlockKegSmallID);
		
		this.decoBlockTableWood = new DecoBlockTableWood(this.decoBlockTableWoodID);
		this.decoBlockTableStone = new DecoBlockTableStone(this.decoBlockTableStoneID, DecoUtilsStrings.STONE_TAGS, DecoUtilsStrings.STONE_NAMES);
		this.decoBlockTableStone2 = new DecoBlockTableStone(this.decoBlockTableStone2ID, DecoUtilsStrings.STONE_TAGS_2, DecoUtilsStrings.STONE_NAMES_2);
		this.decoBlockTableStone3 = new DecoBlockTableStone(this.decoBlockTableStone3ID, DecoUtilsStrings.STONE_TAGS_3, DecoUtilsStrings.STONE_NAMES_3);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
		{
			DecoAddonManager.register(this.decoBlockBookcases[index], DecoUtilsStrings.WOOD_NAMES[index] + " Bookcase");
			DecoAddonManager.register(this.decoBlockCabinets[index], DecoUtilsStrings.WOOD_NAMES[index] + " Cabinet");
			DecoAddonManager.register(this.decoBlockChairs[index], DecoUtilsStrings.WOOD_NAMES[index] + " Chair");
			DecoAddonManager.register(this.decoBlockShelves[index], DecoUtilsStrings.WOOD_NAMES[index] + " Shelf");
			DecoAddonManager.register(this.decoBlockWardrobes[index], DecoUtilsStrings.WOOD_NAMES[index] + " Wardrobe");
		}
		
		DecoAddonManager.register(this.decoBlockKegSmall, "Small Wooden Keg");
		
		DecoAddonManager.register(this.decoBlockChairBlackStone, "Black Stone Throne");
		DecoAddonManager.register(this.decoBlockChairBlackStoneBrick, "Black Stone Brick Throne");
		DecoAddonManager.register(this.decoBlockChairBrick, "Brick Throne");
		DecoAddonManager.register(this.decoBlockChairBrimstone, " Brimstone Throne");
		DecoAddonManager.register(this.decoBlockChairCharredNetherBrick, "Charred Nether Brick Throne");
		DecoAddonManager.register(this.decoBlockChairDuskbound, "Duskbound Throne");
		DecoAddonManager.register(this.decoBlockChairEbonstone, "Ebonstone Throne");
		DecoAddonManager.register(this.decoBlockChairEbonstoneBrick, "Ebonstone Brick Throne");
		DecoAddonManager.register(this.decoBlockChairNetherBrick, "Nether Brick Throne");
		DecoAddonManager.register(this.decoBlockChairNetherWroughtBrick, "Netherwrought Brick Throne");
		DecoAddonManager.register(this.decoBlockChairObsidian, "Obsidian Throne");
		DecoAddonManager.register(this.decoBlockChairRedSandstone, "Red Sandstone Throne");
		DecoAddonManager.register(this.decoBlockChairRedSandstoneBrick, "Red Sandstone Brick Throne");
		//DecoAddonManager.register(this.decoBlockChairSandstone, "Sandstone Throne");
		DecoAddonManager.register(this.decoBlockChairSandstoneBrick, "Sandstone Brick Throne");
		DecoAddonManager.register(this.decoBlockChairStone, "Stone Throne");
		
		//DecoAddonManager.register(this.decoBlockChairStoneBrick, "Stone Brick Throne");
		//DecoAddonManager.register(this.decoBlockChairWhiteStone, "White Stone Throne");
		DecoAddonManager.register(this.decoBlockChairWhiteStoneBrick, "White Stone Brick Throne");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[0], 2),
				new Object[] { "###", "X X", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0), 'X', Item.stick });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[1], 2),
				new Object[] { "###", "X X", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1), 'X', Item.stick });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[2], 2), 
				new Object[] { "###", "X X", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2), 'X', Item.stick });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[3], 2),
				new Object[] { "###", "X X", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3), 'X', Item.stick });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[4], 2),
				new Object[] { "###", "X X", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 4), 'X', Item.stick });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[0]),
				new Object[] { "#X#", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[1]),
				new Object[] { "#X#", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[2]), 
				new Object[] { "#X#", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[3]),
				new Object[] { "#X#", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookcases[3]),
				new Object[] { "#X#", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 4), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 4) });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCabinets[0]),
				new Object[] { "###", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCabinets[1]),
				new Object[] { "###", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCabinets[2]), 
				new Object[] { "###", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockCabinets[3]),
				new Object[] { "###", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3) });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockChairs[0], 4),
				new Object[] { "#  ", "###", "X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockChairs[1], 4),
				new Object[] { "#  ", "###", "X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockChairs[2], 4), 
				new Object[] { "#  ", "###", "X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockChairs[3], 4),
				new Object[] { "#  ", "###", "X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3) });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockShelves[0], 2),
				new Object[] { "###", "X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockShelves[1], 2),
				new Object[] { "###", "X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockShelves[2], 2), 
				new Object[] { "###", "X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockShelves[3], 2),
				new Object[] { "###", "X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3) });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWardrobes[0]),
				new Object[] { "###", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWardrobes[1]),
				new Object[] { "###", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWardrobes[2]), 
				new Object[] { "###", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWardrobes[3]),
				new Object[] { "###", "#X#", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3) });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockBannister.SetAxesEffectiveOn(true);
		
		for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
		{
			this.decoBlockBookcases[index].SetAxesEffectiveOn(true);
			this.decoBlockCabinets[index].SetAxesEffectiveOn(true);
			this.decoBlockChairs[index].SetAxesEffectiveOn(true);
			this.decoBlockShelves[index].SetAxesEffectiveOn(true);
			this.decoBlockWardrobes[index].SetAxesEffectiveOn(true);
		}
		
		this.decoBlockChairBlackStone.SetPicksEffectiveOn(true);
		this.decoBlockChairBlackStoneBrick.SetPicksEffectiveOn(true);
		this.decoBlockChairBrimstone.SetPicksEffectiveOn(true);
		this.decoBlockChairCharredNetherBrick.SetPicksEffectiveOn(true);
		this.decoBlockChairDuskbound.SetPicksEffectiveOn(true);
		//this.decoBlockChairFroststone.SetPicksEffectiveOn(true);
		//this.decoBlockChairMidori.SetPicksEffectiveOn(true);
		this.decoBlockChairNetherBrick.SetPicksEffectiveOn(true);
		this.decoBlockChairNetherWroughtBrick.SetPicksEffectiveOn(true);
		this.decoBlockChairObsidian.SetPicksEffectiveOn(true);
		this.decoBlockChairRedSandstone.SetPicksEffectiveOn(true);
		this.decoBlockChairRedSandstoneBrick.SetPicksEffectiveOn(true);
		//this.decoBlockChairSandstone.SetPicksEffectiveOn(true);
		this.decoBlockChairSandstoneBrick.SetPicksEffectiveOn(true);
		//this.decoBlockChairSoulSandstone.SetPicksEffectiveOn(true);
		this.decoBlockChairStone.SetPicksEffectiveOn(true);
		//this.decoBlockChairStoneBrick.SetPicksEffectiveOn(true);
		//this.decoBlockChairWhiteStone.SetPicksEffectiveOn(true);
		this.decoBlockChairWhiteStoneBrick.SetPicksEffectiveOn(true);
		
		this.decoBlockKegSmall.SetAxesEffectiveOn(true);
		
		this.decoBlockTableStone.SetPicksEffectiveOn(true);
		this.decoBlockTableStone2.SetPicksEffectiveOn(true);
		this.decoBlockTableStone3.SetPicksEffectiveOn(true);
		this.decoBlockTableWood.SetAxesEffectiveOn(true);
	}
}