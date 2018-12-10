package net.minecraft.src;

public class DecoSubModuleNetherWroughtStone implements DecoISubModule 
{
	public static Block decoBlockNetherWroughtStone;
	public static DecoBlockSlab decoBlockNetherWroughtStoneSlab;
	public static DecoBlockSlab decoBlockNetherWroughtStoneSlabTop;
	public static Block decoBlockNetherWroughtStoneStairs;
	public static Block decoBlockNetherWroughtStoneWall;
	
	public static Item decoItemNetherWroughtStoneSlab;
	public static Item decoItemNetherWroughtStoneSlabTop;
	
	public static final int decoBlockNetherWroughtStoneID = DecoAddonManager.getBlockID("decoBlockNetherWroughtStoneID");
	public static final int decoBlockNetherWroughtStoneSlabID = DecoAddonManager.getBlockID("decoBlockNetherWroughtStoneSlabID");
	public static final int decoBlockNetherWroughtStoneSlabTopID = DecoAddonManager.getBlockID("decoBlockNetherWroughtStoneSlabTopID");
	public static final int decoBlockNetherWroughtStoneStairsID = DecoAddonManager.getBlockID("decoBlockNetherWroughtStoneStairsID");
	public static final int decoBlockNetherWroughtStoneWallID = DecoAddonManager.getBlockID("decoBlockNetherWroughtStoneWallID");
	
	public DecoSubModuleNetherWroughtStone()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Nether Wrought Stone");
		
		this.decoBlockNetherWroughtStone = new DecoBlockNetherWroughtStone(this.decoBlockNetherWroughtStoneID);
		this.decoBlockNetherWroughtStoneSlab = new DecoBlockSlab(this.decoBlockNetherWroughtStoneSlabID, this.decoBlockNetherWroughtStone, false,
				this.decoBlockNetherWroughtStoneSlab, this.decoBlockNetherWroughtStoneSlabTop);
		this.decoBlockNetherWroughtStoneSlabTop = new DecoBlockSlab(this.decoBlockNetherWroughtStoneSlabTopID, this.decoBlockNetherWroughtStone, true,
				this.decoBlockNetherWroughtStoneSlab, this.decoBlockNetherWroughtStoneSlabTop);
		this.decoBlockNetherWroughtStoneStairs = new DecoBlockStair(this.decoBlockNetherWroughtStoneStairsID, this.decoBlockNetherWroughtStone, 0);
		this.decoBlockNetherWroughtStoneWall = new DecoBlockWall(this.decoBlockNetherWroughtStoneWallID, this.decoBlockNetherWroughtStone);
		
		this.decoItemNetherWroughtStoneSlab = new DecoItemSlab(this.decoBlockNetherWroughtStoneSlabID - 256, 
				this.decoBlockNetherWroughtStoneSlab, this.decoBlockNetherWroughtStoneSlabTop, false).setUnlocalizedName(this.decoBlockNetherWroughtStone.getUnlocalizedName() + ".slab");
		this.decoItemNetherWroughtStoneSlabTop = new DecoItemSlab(this.decoBlockNetherWroughtStoneSlabTopID - 256, 
				this.decoBlockNetherWroughtStoneSlab, this.decoBlockNetherWroughtStoneSlabTop, true).setUnlocalizedName(this.decoBlockNetherWroughtStone.getUnlocalizedName() + ".slab");
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockNetherWroughtStoneSlab, "Nether Wrought Stone Slab");
		DecoAddonManager.register(this.decoBlockNetherWroughtStoneSlabTop, "Nether Wrought Stone Slab");
		DecoAddonManager.register(this.decoBlockNetherWroughtStoneStairs, "Nether Wrought Stone Stairs");
		DecoAddonManager.register(this.decoBlockNetherWroughtStoneWall, "Nether Wrought Stone Wall");
		
		DecoAddonManager.replaceItem(this.decoBlockNetherWroughtStoneSlabID, decoItemNetherWroughtStoneSlab);
		DecoAddonManager.replaceItem(this.decoBlockNetherWroughtStoneSlabTopID, decoItemNetherWroughtStoneSlabTop);
	}

	@Override
	public void addRecipes() 
	{
		DecoUtilsRecipes.addBlockRecipe(Block.netherrack, this.decoBlockNetherWroughtStone, 1);
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockNetherWroughtStone, this.decoBlockNetherWroughtStone, 2, 1);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockNetherWroughtStone, this.decoBlockNetherWroughtStone, 1, 4);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockNetherWroughtStone, this.decoBlockNetherWroughtStoneSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockNetherWroughtStone, this.decoBlockNetherWroughtStoneStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockNetherWroughtStone, this.decoBlockNetherWroughtStoneWall, 2);
	}

	@Override
	public void changeVanillaItems() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupCustomToolProperties() 
	{
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockNetherWroughtStone);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockNetherWroughtStoneSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockNetherWroughtStoneSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockNetherWroughtStoneStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockNetherWroughtStoneWall);
	}

}
