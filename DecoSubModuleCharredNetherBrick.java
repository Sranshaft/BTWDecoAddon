package net.minecraft.src;

public class DecoSubModuleCharredNetherBrick implements DecoISubModule 
{
	public static Block decoBlockCharredNetherBrick;
	public static DecoBlockSlab decoBlockCharredNetherBrickSlab;
	public static DecoBlockSlab decoBlockCharredNetherBrickSlabTop;
	public static Block decoBlockCharredNetherBrickStairs;
	public static Block decoBlockCharredNetherBrickWall;

	public static Item decoItemCharredNetherBrickSlab;
	public static Item decoItemCharredNetherBrickSlabTop;

	public static final int decoBlockCharredNetherBrickID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickID");
	public static final int decoBlockCharredNetherBrickSlabID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickSlabID");
	public static final int decoBlockCharredNetherBrickSlabTopID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickSlabTopID");
	public static final int decoBlockCharredNetherBrickStairsID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickStairsID");
	public static final int decoBlockCharredNetherBrickWallID = DecoAddonManager.getBlockID("decoBlockCharredNetherBrickWallID");

	public DecoSubModuleCharredNetherBrick()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Charred Nether Brick");

		this.decoBlockCharredNetherBrick = new DecoBlockCharredNetherBrick(this.decoBlockCharredNetherBrickID);

		this.decoBlockCharredNetherBrickSlab = new DecoBlockSlab(this.decoBlockCharredNetherBrickSlabID, this.decoBlockCharredNetherBrick, false,
				this.decoBlockCharredNetherBrickSlab, this.decoBlockCharredNetherBrickSlabTop);

		this.decoBlockCharredNetherBrickSlabTop = new DecoBlockSlab(this.decoBlockCharredNetherBrickSlabTopID, this.decoBlockCharredNetherBrick, true,
				this.decoBlockCharredNetherBrickSlab, this.decoBlockCharredNetherBrickSlabTop);

		this.decoBlockCharredNetherBrickStairs = new DecoBlockStair(this.decoBlockCharredNetherBrickStairsID, this.decoBlockCharredNetherBrick, 0);
		this.decoBlockCharredNetherBrickWall = new DecoBlockWall(this.decoBlockCharredNetherBrickWallID, this.decoBlockCharredNetherBrick);

		this.decoItemCharredNetherBrickSlab = new DecoItemSlab(this.decoBlockCharredNetherBrickSlabID - 256, 
				this.decoBlockCharredNetherBrickSlab, this.decoBlockCharredNetherBrickSlabTop, false).setUnlocalizedName(this.decoBlockCharredNetherBrick.getUnlocalizedName() + ".slab");
		this.decoItemCharredNetherBrickSlabTop = new DecoItemSlab(this.decoBlockCharredNetherBrickSlabTopID - 256, 
				this.decoBlockCharredNetherBrickSlab, this.decoBlockCharredNetherBrickSlabTop, true).setUnlocalizedName(this.decoBlockCharredNetherBrick.getUnlocalizedName() + ".slab");

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	@Override
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockCharredNetherBrick, "Charred Nether Bricks");
		DecoAddonManager.register(this.decoBlockCharredNetherBrickSlab, "Charred Nether Brick Slab");
		DecoAddonManager.register(this.decoBlockCharredNetherBrickSlabTop, "Charred Nether Brick Slab");
		DecoAddonManager.register(this.decoBlockCharredNetherBrickStairs, "Charred Nether Brick Stairs");
		DecoAddonManager.register(this.decoBlockCharredNetherBrickWall, "Charred Nether Brick Wall");

		DecoAddonManager.replaceItem(this.decoBlockCharredNetherBrickSlabID, decoItemCharredNetherBrickSlab);
		DecoAddonManager.replaceItem(this.decoBlockCharredNetherBrickSlabTopID, decoItemCharredNetherBrickSlabTop);
	}

	public void addRecipes() 
	{
		Block.netherBrick.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(this.decoBlockCharredNetherBrick.blockID);

		DecoUtilsRecipes.addSlabRecipe(this.decoBlockCharredNetherBrick, this.decoBlockCharredNetherBrickSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockCharredNetherBrick, this.decoBlockCharredNetherBrickStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockCharredNetherBrick, this.decoBlockCharredNetherBrickWall, 2);	
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockCharredNetherBrick.SetPicksEffectiveOn(true);
		this.decoBlockCharredNetherBrickSlab.SetPicksEffectiveOn(true);
		this.decoBlockCharredNetherBrickSlabTop.SetPicksEffectiveOn(true);
		this.decoBlockCharredNetherBrickStairs.SetPicksEffectiveOn(true);
		this.decoBlockCharredNetherBrickWall.SetPicksEffectiveOn(true);
	}

}
