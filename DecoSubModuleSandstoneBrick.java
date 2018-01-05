package net.minecraft.src;

public class DecoSubModuleSandstoneBrick implements DecoISubModule 
{
	public static Block decoBlockSandstoneBrick;
	public static Block decoBlockSandstoneBrickMouldingAndDecorative;
	public static Block decoBlockSandstoneBrickSidingAndCorner;
	public static Block decoBlockSandstoneBrickSlab;
	public static Block decoBlockSandstoneBrickSlabTop;
	public static Block decoBlockSandstoneBrickStairs;
	public static Block decoBlockSandstoneBrickWall;
	
	public static final int decoBlockSandstoneBrickID = DecoAddonManager.getBlockID("decoBlockSandstoneBrickID");
	public static final int decoBlockSandstoneBrickSlabID = DecoAddonManager.getBlockID("decoBlockSandstoneBrickSlabID");
	public static final int decoBlockSandstoneBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockSandstoneBrickSlabTopID");
	public static final int decoBlockSandstoneBrickStairsID = DecoAddonManager.getBlockID("decoBlockSandstoneBrickStairsID");
	public static final int decoBlockSandstoneBrickWallID = DecoAddonManager.getBlockID("decoBlockSandstoneBrickWallID");
	
	public DecoSubModuleSandstoneBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Sandstone Brick");
		
		this.decoBlockSandstoneBrick = new DecoBlockStoneBrick(this.decoBlockSandstoneBrickID, "decoBlockSandstoneBrick", "Sandstone Brick", 2.0F, 2.5F);
		this.decoBlockSandstoneBrickSlab = new DecoBlockSlab(this.decoBlockSandstoneBrickSlabID, this.decoBlockSandstoneBrick, false, 
				this.decoBlockSandstoneBrickSlabID, this.decoBlockSandstoneBrickSlabTopID);
		this.decoBlockSandstoneBrickSlabTop = new DecoBlockSlab(this.decoBlockSandstoneBrickSlabTopID, this.decoBlockSandstoneBrick, true, 
				this.decoBlockSandstoneBrickSlabID, this.decoBlockSandstoneBrickSlabTopID);
		this.decoBlockSandstoneBrickStairs = new DecoBlockStair(this.decoBlockSandstoneBrickStairsID, this.decoBlockSandstoneBrick, 0);
		this.decoBlockSandstoneBrickWall = new DecoBlockWall(this.decoBlockSandstoneBrickWallID, this.decoBlockSandstoneBrick);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockSandstoneBrickSlab, "Sandstone Brick Slab");
		DecoAddonManager.register(this.decoBlockSandstoneBrickSlabTop, "Sandstone Brick Slab");
		DecoAddonManager.register(this.decoBlockSandstoneBrickStairs, "Sandstone Brick Stairs");
		DecoAddonManager.register(this.decoBlockSandstoneBrickWall, "Sandstone Brick Wall");
	}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockSandstoneBrick, Block.sandStone, 4);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockSandstoneBrick, this.decoBlockSandstoneBrick, 1, 4);
		DecoUtilsRecipes.addPillarRecipe(this.decoBlockSandstoneBrick, this.decoBlockSandstoneBrick, 4, 2);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockSandstoneBrick, this.decoBlockSandstoneBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockSandstoneBrick, this.decoBlockSandstoneBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockSandstoneBrick, this.decoBlockSandstoneBrickWall, 2);
	}

	public void changeVanillaItems() 
	{
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.sandStone, 1), new Object [] { "X", "X", 'X', new ItemStack(Block.stoneSingleSlab, 1) });
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.sandStone, 2), new Object [] { "XX", "XX", 'X', new ItemStack(Block.sandStone, 0) });
	}

	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandstoneBrick);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandstoneBrickSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandstoneBrickSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandstoneBrickStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockSandstoneBrickWall);
	}
}
