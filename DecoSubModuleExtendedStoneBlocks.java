package net.minecraft.src;

public class DecoSubModuleExtendedStoneBlocks implements DecoISubModule
{
	public static Block decoBlockStoneBrick;
	
	public static Block decoBlockWallBlackStone;
	public static Block decoBlockWallBrick;
	public static Block decoBlockWallNetherBrick;
	public static Block decoBlockWallSandstone;
	public static Block decoBlockWallStone;
	public static Block decoBlockWallStoneBrick;
	public static Block decoBlockWallWhiteStone;
	
	public static final int decoBlockWallBlackStoneID = DecoAddonManager.getBlockID("decoBlockWallBlackStoneID");
	public static final int decoBlockWallBrickID = DecoAddonManager.getBlockID("decoBlockWallBrickID");
	public static final int decoBlockWallNetherBrickID = DecoAddonManager.getBlockID("decoBlockWallNetherBrickID");
	public static final int decoBlockWallSandstoneID = DecoAddonManager.getBlockID("decoBlockWallSandstoneID");
	public static final int decoBlockWallStoneID = DecoAddonManager.getBlockID("decoBlockWallStoneID");
	public static final int decoBlockWallStoneBrickID = DecoAddonManager.getBlockID("decoBlockWallStoneBrickID");
	public static final int decoBlockWallWhiteStoneID = DecoAddonManager.getBlockID("decoBlockWallWhiteStoneID");
	
	public DecoSubModuleExtendedStoneBlocks()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Extended Stone Blocks");
		
		this.decoBlockWallBlackStone = new DecoBlockWall(this.decoBlockWallBlackStoneID, Block.blockNetherQuartz);
		this.decoBlockWallBrick = new DecoBlockWall(this.decoBlockWallBrickID, Block.brick);
		this.decoBlockWallNetherBrick = new DecoBlockWall(this.decoBlockWallNetherBrickID, Block.netherBrick);
		this.decoBlockWallSandstone = new DecoBlockWall(this.decoBlockWallSandstoneID, Block.sandStone);
		this.decoBlockWallStone = new DecoBlockWall(this.decoBlockWallStoneID, Block.stone);
		this.decoBlockWallStoneBrick = new DecoBlockWall(this.decoBlockWallStoneBrickID, Block.stoneBrick);
		//this.decoBlockWallWhiteStone = new DecoBlockWall(this.decoBlockWallWhiteStoneID, FCBetterThanWolves.fcAestheticNonOpaque);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockWallBlackStone, "Black Stone Wall");
		DecoAddonManager.register(this.decoBlockWallBrick, "Brick Wall");
		DecoAddonManager.register(this.decoBlockWallNetherBrick, "Nether Brick Wall");
		DecoAddonManager.register(this.decoBlockWallSandstone, "Sandstone Wall");
		DecoAddonManager.register(this.decoBlockWallStone, "Stone Wall");
		DecoAddonManager.register(this.decoBlockWallStoneBrick, "Stone Brick Wall");
	}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addWallRecipe(Block.blockNetherQuartz, this.decoBlockWallBlackStone, 2);
		DecoUtilsRecipes.addWallRecipe(Block.brick, this.decoBlockWallBrick, 2);
		DecoUtilsRecipes.addWallRecipe(Block.netherBrick, this.decoBlockWallNetherBrick, 2);
		DecoUtilsRecipes.addWallRecipe(Block.sandStone, this.decoBlockWallSandstone, 2);
		DecoUtilsRecipes.addWallRecipe(Block.stoneBrick, this.decoBlockWallStone, 2);
		DecoUtilsRecipes.addWallRecipe(Block.stone, this.decoBlockWallStoneBrick, 2);
	}

	public void changeVanillaItems() {
		// TODO Auto-generated method stub
		
	}

	public void setupCustomToolProperties() {
		// TODO Auto-generated method stub
		
	}

}
