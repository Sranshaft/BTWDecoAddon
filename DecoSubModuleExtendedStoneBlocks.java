package net.minecraft.src;

public class DecoSubModuleExtendedStoneBlocks implements DecoISubModule
{
	public static Block decoBlockStoneBrick;
	public static Block decoBlockBlackStoneWall;
	public static Block decoBlockBrickWall;
	public static Block decoBlockCobblestoneWall;
	public static Block decoBlockMossyCobbleWall;
	public static Block decoBlockNetherBrickWall;
	public static Block decoBlockSandstoneWall;
	public static Block decoBlockStoneBrickWall;
	public static Block decoBlockStoneStairs;
	public static Block decoBlockStoneWall;
	public static Block decoBlockWhiteStoneWall;
	
	public static final int decoBlockBlackStoneWallID = DecoAddonManager.getBlockID("decoBlockBlackStoneWallID");
	public static final int decoBlockBrickWallID = DecoAddonManager.getBlockID("decoBlockBrickWallID");
	public static final int decoBlockNetherBrickWallID = DecoAddonManager.getBlockID("decoBlockNetherBrickWallID");
	public static final int decoBlockSandstoneWallID = DecoAddonManager.getBlockID("decoBlockSandstoneWallID");
	public static final int decoBlockStoneBrickWallID = DecoAddonManager.getBlockID("decoBlockStoneBrickWallID");
	public static final int decoBlockStoneStairsID = DecoAddonManager.getBlockID("decoBlockStoneStairsID");
	public static final int decoBlockStoneWallID = DecoAddonManager.getBlockID("decoBlockStoneWallID");
	public static final int decoBlockWhiteStoneWallID = DecoAddonManager.getBlockID("decoBlockWhiteStoneWallID");
	
	public DecoSubModuleExtendedStoneBlocks()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Extended Stone Blocks");
		
		this.decoBlockBlackStoneWall = new DecoBlockWall(this.decoBlockBlackStoneWallID, Block.blockNetherQuartz);
		this.decoBlockBrickWall = new DecoBlockWall(this.decoBlockBrickWallID, Block.brick);
		this.decoBlockNetherBrickWall = new DecoBlockWall(this.decoBlockNetherBrickWallID, Block.netherBrick);
		this.decoBlockSandstoneWall = new DecoBlockWall(this.decoBlockSandstoneWallID, Block.sandStone);
		this.decoBlockStoneWall = new DecoBlockWall(this.decoBlockStoneWallID, Block.stone);
		this.decoBlockStoneBrickWall = new DecoBlockWall(this.decoBlockStoneBrickWallID, Block.stoneBrick);
		this.decoBlockStoneStairs = new DecoBlockStair(this.decoBlockStoneStairsID, Block.stone, 0);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockBlackStoneWall, "Black Stone Wall");
		DecoAddonManager.register(this.decoBlockBrickWall, "Brick Wall");
		DecoAddonManager.register(this.decoBlockNetherBrickWall, "Nether Brick Wall");
		DecoAddonManager.register(this.decoBlockSandstoneWall, "Sandstone Wall");
		DecoAddonManager.register(this.decoBlockStoneBrickWall, "Stone Brick Wall");
		DecoAddonManager.register(this.decoBlockStoneWall, "Stone Wall");
		DecoAddonManager.register(this.decoBlockStoneStairs, "Stone Stairs");
	}

	public void addRecipes() 
	{
		DecoUtilsRecipes.addWallRecipe(Block.blockNetherQuartz, this.decoBlockBlackStoneWall, 2);
		DecoUtilsRecipes.addWallRecipe(Block.brick, this.decoBlockBrickWall, 2);
		DecoUtilsRecipes.addWallRecipe(Block.netherBrick, this.decoBlockNetherBrickWall, 2);
		DecoUtilsRecipes.addWallRecipe(Block.sandStone, this.decoBlockSandstoneWall, 2);
		DecoUtilsRecipes.addWallRecipe(Block.stoneBrick, this.decoBlockStoneBrickWall, 2);
		DecoUtilsRecipes.addWallRecipe(Block.stone, this.decoBlockStoneBrickWall, 2);
		DecoUtilsRecipes.addStairsRecipe(Block.stone, this.decoBlockStoneStairs, 4);	
	}

	public void changeVanillaItems() {
		// TODO Auto-generated method stub
		
	}

	public void setupCustomToolProperties() {
		// TODO Auto-generated method stub
		
	}

}
