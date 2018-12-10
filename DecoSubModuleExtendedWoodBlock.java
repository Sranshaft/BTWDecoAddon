package net.minecraft.src;

public class DecoSubModuleExtendedWoodBlock implements DecoISubModule 
{
	public static Block[] decoBlockDoors;
	public static Block[] decoBlockFenceGates;
	public static Block[] decoBlockLadders;
	public static Block[] decoBlockTrapDoors;
	
	public static Block decoBlockBookshelf;
	public static Block decoBlockButtonWood;
	public static Block decoBlockChest;
	public static Block decoBlockLeaves;
	public static Block decoBlockLog;
	public static Block decoBlockPlanks;
	public static Block decoBlockWood;
	public static Block decoBlockWorkbench;
	
	public static Item[] decoItemDoors;
	
	public static Item decoItemLeaves;
	
	public static final int decoBlockDoorSpruceID = DecoAddonManager.getBlockID("decoBlockDoorSpruceID");
	public static final int decoBlockFenceGateSpruceID = DecoAddonManager.getBlockID("decoBlockFenceGateSpruceID");
	public static final int decoBlockLadderSpruceID = DecoAddonManager.getBlockID("decoBlockLadderSpruceID");
	public static final int decoBlockTrapDoorSpruceID = DecoAddonManager.getBlockID("decoBlockTrapDoorSpruceID");
	
	public static final int decoBlockIronwoodLeavesID = DecoAddonManager.getBlockID("decoBlockIronwoodLeavesID");
	public static final int decoBlockIronwoodPlankID = DecoAddonManager.getBlockID("decoBlockIronwoodPlankID");
	public static final int decoBlockWoodID = DecoAddonManager.getBlockID("decoBlockWoodID");
	
	public static final int decoItemDoorSpruceID = DecoAddonManager.getBlockID("decoItemDoorSpruceID");
	
	public DecoSubModuleExtendedWoodBlock()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Extended Wood Blocks");
		
		this.decoBlockDoors = new Block[6];
		for (int index = 0; index < this.decoBlockDoors.length; index++)
		{
			this.decoBlockDoors[index] = new DecoBlockDoor(this.decoBlockDoorSpruceID + index, DecoUtilsStrings.WOOD_TAGS[index + 1], index);
		}
		
		this.decoBlockFenceGates = new Block[6];
		for (int index = 0; index < this.decoBlockDoors.length; index++)
		{
			this.decoBlockFenceGates[index] = new DecoBlockFenceGate(this.decoBlockFenceGateSpruceID + index, DecoUtilsStrings.WOOD_TAGS[index + 1]);
		}
		
		this.decoBlockLadders = new Block[6];
		for (int index = 0; index < this.decoBlockLadders.length; index++)
		{
			this.decoBlockLadders[index] = new DecoBlockLadderWood(this.decoBlockLadderSpruceID + index, DecoUtilsStrings.WOOD_TAGS[index + 1]);
		}
		
		this.decoBlockTrapDoors = new Block[6];
		for (int index = 0; index < this.decoBlockTrapDoors.length; index++)
		{
			this.decoBlockTrapDoors[index] = new DecoBlockTrapDoor(this.decoBlockTrapDoorSpruceID + index, Block.planks, DecoUtilsStrings.WOOD_TAGS[index + 1]);
		}
		
		this.decoBlockLeaves = new DecoBlockLeaves(this.decoBlockIronwoodLeavesID);
		this.decoBlockPlanks = new DecoBlockPlank(this.decoBlockIronwoodPlankID);
		this.decoBlockWood = new DecoBlockWood(this.decoBlockWoodID);
		
		this.decoItemDoors = new Item[6];
		for (int index = 0; index < this.decoItemDoors.length; index++)
		{
			this.decoItemDoors[index] = new DecoItemDoor(this.decoItemDoorSpruceID + index, DecoUtilsStrings.WOOD_TAGS[index + 1], index);
		}
		
		// REPLACE VANILLA BLOCKS
		this.decoBlockBookshelf = new DecoBlockBookshelf(DecoAddonManager.replaceBlockID(Block.bookShelf));
		this.decoBlockButtonWood = new DecoBlockButton(DecoAddonManager.replaceBlockID(Block.woodenButton), true).setUnlocalizedName("button");
		this.decoBlockWorkbench = new DecoBlockWorkbench(DecoAddonManager.replaceBlockID(Block.workbench));
		
		this.decoItemLeaves = new DecoItemLeaves(this.decoBlockLeaves.blockID - 256).setUnlocalizedName("leaves");
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		for (int index = 0; index < this.decoBlockFenceGates.length; index++)
		{
			DecoAddonManager.register(this.decoBlockFenceGates[index], DecoUtilsStrings.WOOD_NAMES[index + 1] + " Fence Gate");
		}
		
		for (int index = 0; index < this.decoBlockLadders.length; index++)
		{
			DecoAddonManager.register(this.decoBlockLadders[index], DecoUtilsStrings.WOOD_NAMES[index + 1] + " Ladder");
		}
		
		for (int index = 0; index < this.decoBlockTrapDoors.length; index++)
		{
			DecoAddonManager.register(this.decoBlockTrapDoors[index], DecoUtilsStrings.WOOD_NAMES[index + 1] + " Trapdoor");
		}
		
		for (int index = 0; index < this.decoItemDoors.length; index++)
		{
			DecoAddonManager.register(this.decoItemDoors[index], DecoUtilsStrings.WOOD_NAMES[index + 1] + " Door");
		}
		
		DecoAddonManager.replaceItem(this.decoBlockLeaves.blockID, this.decoItemLeaves);
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
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBloodWood, 1, 0), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 5), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockPlanks, 1, 0), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 6), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockPlanks, 1, 1), 'B', Item.book });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 0),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 1),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 2),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 3),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 4), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner, 1, 0), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 5), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockPlanks, 1, 0), 'B', Item.book });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBookshelf, 1, 6), 
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(this.decoBlockPlanks, 1, 1), 'B', Item.book });
		
		// TRAPDOOR RECIPES
		FCRecipes.AddVanillaRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoors[0], 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoors[1], 2),
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoors[2], 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoors[3], 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoors[4], 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(this.decoBlockPlanks, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoors[5], 2), 
				new Object[] { "XXX", "XXX", 'X', new ItemStack(this.decoBlockPlanks, 1, 1) });
		
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
				new Object[] { "XX", "XX", 'X', new ItemStack(FCBetterThanWolves.fcBloodWood, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 5), 
				new Object[] { "XX", "XX", 'X', new ItemStack(this.decoBlockPlanks, 1, 0) });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockWorkbench, 1, 6), 
				new Object[] { "XX", "XX", 'X', new ItemStack(this.decoBlockPlanks, 1, 1) });
	}

	public void changeVanillaItems() 
	{
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.bookShelf),
				new Object[] { "XXX", "BBB", "XXX", 'X', Block.planks, 'B', Item.book });
		
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.bookShelf),
				new Object[] { "XXX", "BBB", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'B', Item.book });
		
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.trapdoor, 2),
				new Object[] { "IXX", "IXX", 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'I', FCBetterThanWolves.fcItemNuggetIron });
		
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.workbench),
				new Object[] { "XX", "XX", 'X', Block.planks });
	}
	
	public void setupCustomToolProperties() 
	{
		for (int index = 0; index < this.decoBlockDoors.length; index++)
		{
			ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockDoors[index]);
		}
		
		for (int index = 0; index < this.decoBlockLadders.length; index++)
		{
			ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockLadders[index]);
		}
		
		for (int index = 0; index < this.decoBlockTrapDoors.length; index++)
		{
			ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockTrapDoors[index]);
		}
	}

}
