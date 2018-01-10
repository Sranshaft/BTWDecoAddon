package net.minecraft.src;

public class DecoSubModuleExtendedWoodBlocks implements DecoISubModule 
{
	public static Block decoBlockBookshelf;
	
	public static Block[] decoBlockDoors;
	
	public static Block decoBlockTrapDoorBirch;
	public static Block decoBlockTrapDoorBloodwood;
	public static Block decoBlockTrapDoorEbonwood;
	public static Block decoBlockTrapDoorIronwood;
	public static Block decoBlockTrapDoorJungle;
	public static Block decoBlockTrapDoorSpruce;
	public static Block decoBlockWood;
	public static Block decoBlockWoodSlab;
	public static Block decoBlockWoodSlabTop;
	public static Block decoBlockWoodOakStairs;
	public static Block decoBlockWoodSpruceStairs;
	public static Block decoBlockWoodBirchStairs;
	public static Block decoBlockWoodJungleStairs;
	public static Block decoBlockWorkbench;
	
	public static Item decoItemDoorBirch;
	public static Item decoItemDoorBloodwood;
	public static Item decoItemDoorEbonwood;
	public static Item decoItemDoorIronwood;
	public static Item decoItemDoorJungle;
	public static Item decoItemDoorSpruce;
	
	public static final int decoBlockDoorOakID = DecoAddonManager.getBlockID("decoBlockDoorOakID");
	public static final int decoBlockDoorBirchID = DecoAddonManager.getBlockID("decoBlockDoorBirchID");
	public static final int decoBlockDoorBloodwoodID = DecoAddonManager.getBlockID("decoBlockDoorBloodwoodID");
	public static final int decoBlockDoorEbonwoodID = DecoAddonManager.getBlockID("decoBlockDoorEbonwoodID");
	public static final int decoBlockDoorIronwoodID = DecoAddonManager.getBlockID("decoBlockDoorIronwoodID");
	public static final int decoBlockDoorJungleID =  DecoAddonManager.getBlockID("decoBlockDoorJungleID");
	public static final int decoBlockDoorSpruceID =  DecoAddonManager.getBlockID("decoBlockDoorSpruceID");
	
	public static final int decoBlockTrapDoorBirchID =  DecoAddonManager.getBlockID("decoBlockTrapDoorBirchID");
	public static final int decoBlockTrapDoorBloodwoodID =  DecoAddonManager.getBlockID("decoBlockTrapDoorBloodwoodID");
	public static final int decoBlockTrapDoorEbonwoodID =  DecoAddonManager.getBlockID("decoBlockTrapDoorEbonwoodID");
	public static final int decoBlockTrapDoorIronwoodID =  DecoAddonManager.getBlockID("decoBlockTrapDoorIronwoodID");
	public static final int decoBlockTrapDoorJungleID =  DecoAddonManager.getBlockID("decoBlockTrapDoorJungleID");
	public static final int decoBlockTrapDoorSpruceID =  DecoAddonManager.getBlockID("decoBlockTrapDoorSpruceID");
	
	public static final int decoBlockWoodID = DecoAddonManager.getBlockID("decoBlockWoodID");
	
	public static final int decoItemDoorBirchID = DecoAddonManager.getBlockID("decoItemDoorBirchID");
	public static final int decoItemDoorBloodwoodID = DecoAddonManager.getBlockID("decoItemDoorBloodwoodID");
	public static final int decoItemDoorEbonwoodID = DecoAddonManager.getBlockID("decoItemDoorEbonwoodID");
	public static final int decoItemDoorIronwoodID = DecoAddonManager.getBlockID("decoItemDoorIronwoodID");
	public static final int decoItemDoorJungleID = DecoAddonManager.getBlockID("decoItemDoorJungleID");
	public static final int decoItemDoorSpruceID = DecoAddonManager.getBlockID("decoItemDoorSpruceID");
	
	public DecoSubModuleExtendedWoodBlocks()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Extended Wood Blocks");
		
		this.decoBlockWood = new DecoBlockWood(this.decoBlockWoodID);
		
		this.decoBlockDoors = new Block[DecoUtilsStrings.WOOD_TAGS.length];
		for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
		{
			this.decoBlockDoors[index] = new DecoBlockDoor(this.decoBlockDoorOakID + index, Material.wood, DecoUtilsStrings.WOOD_TAGS[index], index);
		}
		
		this.decoBlockTrapDoorSpruce = new DecoBlockTrapDoor(this.decoBlockTrapDoorSpruceID, Block.planks, "spruce");
		this.decoBlockTrapDoorBirch = new DecoBlockTrapDoor(this.decoBlockTrapDoorBirchID, Block.planks, "birch");
		this.decoBlockTrapDoorJungle = new DecoBlockTrapDoor(this.decoBlockTrapDoorJungleID, Block.planks, "jungle");
		this.decoBlockTrapDoorBloodwood = new DecoBlockTrapDoor(this.decoBlockTrapDoorBloodwoodID, Block.planks, "bloodwood");
		this.decoBlockTrapDoorEbonwood = new DecoBlockTrapDoor(this.decoBlockTrapDoorEbonwoodID, Block.planks, "ebonwood");
		this.decoBlockTrapDoorIronwood = new DecoBlockTrapDoor(this.decoBlockTrapDoorIronwoodID, Block.planks, "ironwood");
		
		this.decoItemDoorSpruce = new DecoItemDoor(this.decoItemDoorSpruceID, "spruce", 1);
		this.decoItemDoorBirch = new DecoItemDoor(this.decoItemDoorBirchID, "birch", 2);
		this.decoItemDoorJungle = new DecoItemDoor(this.decoItemDoorJungleID, "jungle", 3);
		this.decoItemDoorBloodwood = new DecoItemDoor(this.decoItemDoorBloodwoodID, "bloodwood", 4);
		this.decoItemDoorEbonwood = new DecoItemDoor(this.decoItemDoorEbonwoodID, "ebonwood", 5);
		this.decoItemDoorIronwood = new DecoItemDoor(this.decoItemDoorIronwoodID, "ironwood", 6);
		
		// REPLACE VANILLA BLOCKS
		this.decoBlockBookshelf = new DecoBlockBookshelf(DecoAddonManager.replaceBlockID(Block.bookShelf));
		this.decoBlockWorkbench = new DecoBlockWorkbench(DecoAddonManager.replaceBlockID(Block.workbench));
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		for (int index = 0; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
		{
			DecoAddonManager.register(this.decoBlockDoors[index], DecoUtilsStrings.WOOD_NAMES[index] + " Door");
		}
		
		DecoAddonManager.register(this.decoBlockTrapDoorBirch, "Birch Trapdoor");
		DecoAddonManager.register(this.decoBlockTrapDoorBloodwood, "Bloodwood Trapdoor");
		DecoAddonManager.register(this.decoBlockTrapDoorEbonwood, "Ebonwood Trapdoor");
		DecoAddonManager.register(this.decoBlockTrapDoorIronwood, "Ironwood Trapdoor");
		DecoAddonManager.register(this.decoBlockTrapDoorJungle, "Jungle Trapdoor");
		DecoAddonManager.register(this.decoBlockTrapDoorSpruce, "Spruce Trapdoor");
		
		DecoAddonManager.register(this.decoItemDoorBirch, "Birch Door");
		DecoAddonManager.register(this.decoItemDoorBloodwood, "Bloodwood Door");
		DecoAddonManager.register(this.decoItemDoorEbonwood, "Ebonwood Door");
		DecoAddonManager.register(this.decoItemDoorIronwood, "Ironwood Door");
		DecoAddonManager.register(this.decoItemDoorJungle, "Jungle Door");
		DecoAddonManager.register(this.decoItemDoorSpruce, "Spruce Door");
	}

	public void addRecipes() 
	{
		// BOOKSHELF RECIPES
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 0),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(Block.planks, 1, 0), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 1),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(Block.planks, 1, 1), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 2),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(Block.planks, 1, 2), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 3),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(Block.planks, 1, 3), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 4), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 4), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 5), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 5), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 6), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 6), 'B', Item.book });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 0),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 1),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 2),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 3),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 4), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 4), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 5), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 5), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 6), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 6), 'B', Item.book });
		
		// TRAPDOOR RECIPES
		FCRecipes.AddVanillaRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoorSpruce, 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoorBirch, 2),
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoorJungle, 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoorBloodwood, 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 4) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoorEbonwood, 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 5) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoorIronwood, 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(this.decoBlockWood, 1, 6) });
		
		// WORKBENCH RECIPES
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 0), 
				new Object[] { "XX", "XX", 'X', new ItemStack(Block.planks, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 1), 
				new Object[] { "XX", "XX", 'X', new ItemStack(Block.planks, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 2), 
				new Object[] { "XX", "XX", 'X', new ItemStack(Block.planks, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 3), 
				new Object[] { "XX", "XX", 'X', new ItemStack(Block.planks, 1, 3) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 4), 
				new Object[] { "XX", "XX", 'X', new ItemStack(this.decoBlockWood, 1, 4) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 5), 
				new Object[] { "XX", "XX", 'X', new ItemStack(this.decoBlockWood, 1, 5) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 6), 
				new Object[] { "XX", "XX", 'X', new ItemStack(this.decoBlockWood, 1, 6) });
	}

	public void changeVanillaItems() 
	{
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.bookShelf),
				new Object[] { "XXX", "BBB", "XXX", 'X', Block.planks, 'B', Item.book });
		
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.bookShelf),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'B', Item.book });
		
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767) });
		
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.workbench),
				new Object[] { "XX", "XX", 'X', Block.planks });
	}
	
	public void setupCustomToolProperties() 
	{
		for (int index = 1; index < DecoUtilsStrings.WOOD_TAGS.length; index++)
		{
			ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockDoors[index - 1]);
		}
		
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockTrapDoorBirch);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockTrapDoorBloodwood);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockTrapDoorEbonwood);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockTrapDoorIronwood);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockTrapDoorJungle);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockTrapDoorSpruce);
	}

}
