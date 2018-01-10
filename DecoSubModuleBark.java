package net.minecraft.src;

public class DecoSubModuleBark implements DecoISubModule 
{
	public static Block decoBlockBark;
	public static Block decoBlockBarkSlab;
	public static Block decoBlockBarkSlabTop;
	public static Block decoBlockBarkWall;
	
	public static Block decoBlockBarkBirchStairs;
	public static Block decoBlockBarkEbonwoodStairs;
	public static Block decoBlockBarkIronwoodStairs;
	public static Block decoBlockBarkJungleStairs;
	public static Block decoBlockBarkOakStairs;
	public static Block decoBlockBarkSpruceStairs;
	
	public static final int decoBlockBarkID = DecoAddonManager.getBlockID("decoBlockBarkID");
	public static final int decoBlockBarkSlabID = DecoAddonManager.getBlockID("decoBlockBarkSlabID");
	public static final int decoBlockBarkSlabTopID = DecoAddonManager.getBlockID("decoBlockBarkSlabTopID");
	public static final int decoBlockBarkWallID = DecoAddonManager.getBlockID("decoBlockBarkWallID");
	
	public static final int decoBlockBarkBirchStairsID = DecoAddonManager.getBlockID("decoBlockBarkBirchStairsID");
	public static final int decoBlockBarkEbonwoodStairsID = DecoAddonManager.getBlockID("decoBlockBarkEbonwoodStairsID");
	public static final int decoBlockBarkIronwoodStairsID = DecoAddonManager.getBlockID("decoBlockBarkIronwoodStairsID");
	public static final int decoBlockBarkJungleStairsID = DecoAddonManager.getBlockID("decoBlockBarkJungleStairsID");
	public static final int decoBlockBarkOakStairsID = DecoAddonManager.getBlockID("decoBlockBarkOakStairsID");
	public static final int decoBlockBarkSpruceStairsID = DecoAddonManager.getBlockID("decoBlockBarkSpruceStairsID");
	
	public DecoSubModuleBark()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Bark");
		
		this.decoBlockBark = new DecoBlockBark(this.decoBlockBarkID);
		
		this.decoBlockBarkWall = new DecoBlockWall(this.decoBlockBarkWallID, this.decoBlockBark, 
				DecoUtilsStrings.TREE_TAGS, DecoUtilsStrings.TREE_NAMES, DecoUtilsStrings.TREE_SIDE_TEXTURE_PATHS);
		
		this.decoBlockBarkOakStairs = new DecoBlockStair(this.decoBlockBarkOakStairsID, this.decoBlockBark, "oak", 0);
		this.decoBlockBarkSpruceStairs = new DecoBlockStair(this.decoBlockBarkSpruceStairsID, this.decoBlockBark, "spruce", 1);
		this.decoBlockBarkBirchStairs = new DecoBlockStair(this.decoBlockBarkBirchStairsID, this.decoBlockBark, "birch", 2);
		this.decoBlockBarkJungleStairs = new DecoBlockStair(this.decoBlockBarkJungleStairsID, this.decoBlockBark, "jungle", 3);
		this.decoBlockBarkEbonwoodStairs = new DecoBlockStair(this.decoBlockBarkEbonwoodStairsID, this.decoBlockBark, "ebonwood", 4);
		this.decoBlockBarkIronwoodStairs = new DecoBlockStair(this.decoBlockBarkIronwoodStairsID, this.decoBlockBark, "ironwood", 5);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockBarkOakStairs, "Oak Bark Stairs");
		DecoAddonManager.register(this.decoBlockBarkSpruceStairs, "Spruce Bark Stairs");
		DecoAddonManager.register(this.decoBlockBarkBirchStairs, "Birch Bark Stairs");
		DecoAddonManager.register(this.decoBlockBarkJungleStairs, "Jungle Bark Stairs");
		DecoAddonManager.register(this.decoBlockBarkEbonwoodStairs, "Ebonwood Bark Stairs");
		DecoAddonManager.register(this.decoBlockBarkIronwoodStairs, "Ironwood Bark Stairs");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBark, 1, 0), 
				new Object [] { "XXX", "X#X", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcItemBark, 1, FCItemBark.m_iSubtypeOak), '#', FCBetterThanWolves.fcGlue });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBark, 1, 1), 
				new Object [] { "XXX", "X#X", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcItemBark, 1, FCItemBark.m_iSubtypeSpruce), '#', FCBetterThanWolves.fcGlue });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBark, 1, 2), 
				new Object [] { "XXX", "X#X", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcItemBark, 1, FCItemBark.m_iSubtypeBirch), '#', FCBetterThanWolves.fcGlue });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBark, 1, 3), 
				new Object [] { "XXX", "X#X", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcItemBark, 1, FCItemBark.m_iSubtypeJungle), '#', FCBetterThanWolves.fcGlue });
		
		/**DecoUtilsRecipes.addBlockRecipe(this.decoBlockBark, this.decoBlockBark, 2, 1);
		DecoUtilsRecipes.addChiseledRecipe(this.decoBlockBark, this.decoBlockBark, 1, 4);
		DecoUtilsRecipes.addSlabRecipe(this.decoBlockBark, this.decoBlockBarkSlab, 6);
		DecoUtilsRecipes.addStairsRecipe(this.decoBlockBark, this.decoBlockBarkStairs, 4);
		DecoUtilsRecipes.addWallRecipe(this.decoBlockBark, this.decoBlockBarkWall, 2);*/
	}

	@Override
	public void changeVanillaItems() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupCustomToolProperties() 
	{
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBark);
		//ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkSlab);
		//ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkSlabTop);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkWall);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkBirchStairs);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkEbonwoodStairs);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkIronwoodStairs);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkJungleStairs);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkOakStairs);
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockBarkSpruceStairs);
	}
}
