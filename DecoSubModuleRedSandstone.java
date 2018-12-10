package net.minecraft.src;

public class DecoSubModuleRedSandstone implements DecoISubModule 
{
	public static Block decoBlockRedSand;
	public static Block decoBlockRedSandstone;
	public static DecoBlockSlab decoBlockRedSandstoneSlab;
	public static DecoBlockSlab decoBlockRedSandstoneSlabTop;
	public static Block decoBlockRedSandstoneStairs;
	public static Block decoBlockRedSandstoneWall;
	
	public static Item decoItemPileRedSand;
	
	public static Item decoItemRedSandstoneSlab;
	public static Item decoItemRedSandstoneSlabTop;
	
	public static final int decoBlockRedSandID = DecoAddonManager.getBlockID("decoBlockRedSandID");
	public static final int decoBlockRedSandstoneID = DecoAddonManager.getBlockID("decoBlockRedSandstoneID");
	public static final int decoBlockRedSandstoneSlabID = DecoAddonManager.getBlockID("decoBlockRedSandstoneSlabID");
	public static final int decoBlockRedSandstoneSlabTopID = DecoAddonManager.getBlockID("decoBlockRedSandstoneSlabTopID");
	public static final int decoBlockRedSandstoneStairsID = DecoAddonManager.getBlockID("decoBlockRedSandstoneStairsID");
	public static final int decoBlockRedSandstoneWallID = DecoAddonManager.getBlockID("decoBlockRedSandstoneWallID");
	
	public static final int decoItemPileRedSandID = DecoAddonManager.getBlockID("decoItemPileRedSandID");
	
	public DecoSubModuleRedSandstone()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Red Sandstone");
		
		this.decoBlockRedSand = new DecoBlockRedSand(this.decoBlockRedSandID);
		this.decoBlockRedSandstone = new DecoBlockRedSandstone(this.decoBlockRedSandstoneID);
		this.decoBlockRedSandstoneSlab = new DecoBlockSlab(this.decoBlockRedSandstoneSlabID, this.decoBlockRedSandstone, false, 
				this.decoBlockRedSandstoneSlab, this.decoBlockRedSandstoneSlabTop);
		this.decoBlockRedSandstoneSlabTop = new DecoBlockSlab(this.decoBlockRedSandstoneSlabTopID, this.decoBlockRedSandstone, true, 
				this.decoBlockRedSandstoneSlab, this.decoBlockRedSandstoneSlabTop);
		this.decoBlockRedSandstoneStairs = new DecoBlockStair(this.decoBlockRedSandstoneStairsID, this.decoBlockRedSandstone, 0);
		this.decoBlockRedSandstoneWall = new DecoBlockWall(this.decoBlockRedSandstoneWallID, this.decoBlockRedSandstone);
		
		this.decoItemPileRedSand = new DecoItemPileRedSand(this.decoItemPileRedSandID);
		
		this.decoItemRedSandstoneSlab = new DecoItemSlab(this.decoBlockRedSandstoneSlabID - 256, 
				this.decoBlockRedSandstoneSlab, this.decoBlockRedSandstoneSlabTop, false).setUnlocalizedName(this.decoBlockRedSandstone.getUnlocalizedName() + ".slab");
		this.decoItemRedSandstoneSlabTop = new DecoItemSlab(this.decoBlockRedSandstoneSlabTopID - 256, 
				this.decoBlockRedSandstoneSlab, this.decoBlockRedSandstoneSlabTop, true).setUnlocalizedName(this.decoBlockRedSandstone.getUnlocalizedName() + ".slab");
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockRedSand, "Red Sand");
		DecoAddonManager.register(this.decoBlockRedSandstoneSlab, "Red Sandstone Slab");
		DecoAddonManager.register(this.decoBlockRedSandstoneSlabTop, "Red Sandstone Slab");
		DecoAddonManager.register(this.decoBlockRedSandstoneStairs, "Red Sandstone Stairs");
		DecoAddonManager.register(this.decoBlockRedSandstoneWall, "Red Sandstone Wall");
		
		DecoAddonManager.register(this.decoItemPileRedSand, "Pile of Red Sand");
		
		DecoAddonManager.replaceItem(this.decoBlockRedSandstoneSlabID, decoItemRedSandstoneSlab);
		DecoAddonManager.replaceItem(this.decoBlockRedSandstoneSlabTopID, decoItemRedSandstoneSlabTop);
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockRedSand, 4), 
				new Object[] { "X#", "#X", 'X', Block.sand, '#', FCBetterThanWolves.fcGroundNetherrack });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockRedSand, 4), 
				new Object[] { "#X", "X#", 'X', Block.sand, '#', FCBetterThanWolves.fcGroundNetherrack });
		
		DecoUtilsRecipes.addBlockRecipe(this.decoItemPileRedSand, this.decoBlockRedSand, 1);
		
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockRedSand, this.decoBlockRedSandstone, 1);
		DecoUtilsRecipes.addBlockRecipe(this.decoBlockRedSandstone, this.decoBlockRedSandstone, 2, 1);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockRedSandstone, this.decoBlockRedSandstone, 1, 4);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockRedSandstone, this.decoBlockRedSandstoneSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockRedSandstone, this.decoBlockRedSandstoneStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockRedSandstone, this.decoBlockRedSandstoneWall, 2);
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties()
	{
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockRedSand);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstone);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneSlab);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneSlabTop);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneStairs);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockRedSandstoneWall);
	}
}
