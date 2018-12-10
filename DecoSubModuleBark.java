package net.minecraft.src;

public class DecoSubModuleBark implements DecoISubModule 
{
	public static Block[] decoBlockBarkStairs;
	
	public static Block decoBlockBark;
	public static DecoBlockSlab decoBlockBarkSlab;
	public static DecoBlockSlab decoBlockBarkSlabTop;
	public static Block decoBlockBarkWall;

	public static Item decoItemBarkSlab;
	public static Item decoItemBarkSlabTop;

	public static final int decoBlockBarkID = DecoAddonManager.getBlockID("decoBlockBarkID");
	public static final int decoBlockBarkSlabID = DecoAddonManager.getBlockID("decoBlockBarkSlabID");
	public static final int decoBlockBarkSlabTopID = DecoAddonManager.getBlockID("decoBlockBarkSlabTopID");
	public static final int decoBlockBarkStairsID = DecoAddonManager.getBlockID("decoBlockBarkStairsID");
	public static final int decoBlockBarkWallID = DecoAddonManager.getBlockID("decoBlockBarkWallID");

	public DecoSubModuleBark()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Bark");

		this.decoBlockBark = new DecoBlockBark(this.decoBlockBarkID);

		this.decoBlockBarkSlab = new DecoBlockSlab(this.decoBlockBarkSlabID, this.decoBlockBark, false, 
				this.decoBlockBarkSlab, this.decoBlockBarkSlabTop, DecoUtilsStrings.TREE_TAGS, DecoUtilsStrings.TREE_NAMES, DecoUtilsStrings.TREE_SIDE_TEXTURE_PATHS, " Bark");

		this.decoBlockBarkSlabTop = new DecoBlockSlab(this.decoBlockBarkSlabTopID, this.decoBlockBark, true, 
				this.decoBlockBarkSlab, this.decoBlockBarkSlabTop, DecoUtilsStrings.TREE_TAGS, DecoUtilsStrings.TREE_NAMES, DecoUtilsStrings.TREE_SIDE_TEXTURE_PATHS, " Bark");

		this.decoBlockBarkStairs = new Block[DecoUtilsStrings.TREE_TAGS.length];
		for (int index = 0; index < DecoUtilsStrings.TREE_TAGS.length; index++)
		{
			this.decoBlockBarkStairs[index] = new DecoBlockStair(this.decoBlockBarkStairsID + index, this.decoBlockBark, DecoUtilsStrings.TREE_TAGS[index], index);
		}

		this.decoBlockBarkWall = new DecoBlockWall(this.decoBlockBarkWallID, this.decoBlockBark, 
				DecoUtilsStrings.TREE_TAGS, DecoUtilsStrings.TREE_NAMES, DecoUtilsStrings.TREE_SIDE_TEXTURE_PATHS);

		this.decoItemBarkSlab = new DecoItemSlab(this.decoBlockBarkSlabID - 256, 
				this.decoBlockBarkSlab, this.decoBlockBarkSlabTop, false).setUnlocalizedName(this.decoBlockBark.getUnlocalizedName() + ".slab");
		this.decoItemBarkSlabTop = new DecoItemSlab(this.decoBlockBarkSlabTopID - 256, 
				this.decoBlockBarkSlab, this.decoBlockBarkSlabTop, true).setUnlocalizedName(this.decoBlockBark.getUnlocalizedName() + ".slab");

		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks()
	{
		DecoAddonManager.replaceItem(this.decoBlockBarkSlabID, this.decoItemBarkSlab);
		DecoAddonManager.replaceItem(this.decoBlockBarkSlabTopID, this.decoItemBarkSlabTop);
		
		for (int index = 0; index < DecoUtilsStrings.TREE_TAGS.length; index++)
		{
			DecoAddonManager.register(this.decoBlockBarkStairs[index], DecoUtilsStrings.TREE_NAMES[index] + " Bark Stairs");
		}
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
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockBark, 1, 4), 
				new Object [] { "XXX", "X#X", "XXX", 'X', new ItemStack(FCBetterThanWolves.fcItemBark, 1, FCItemBark.m_iSubtypeBloodWood), '#', FCBetterThanWolves.fcGlue });
		
		DecoUtilsRecipes.addStairsRecipe(new ItemStack(this.decoBlockBark, 1, 0), this.decoBlockBarkStairs[0], 4);
		DecoUtilsRecipes.addStairsRecipe(new ItemStack(this.decoBlockBark, 1, 1), this.decoBlockBarkStairs[1], 4);
		DecoUtilsRecipes.addStairsRecipe(new ItemStack(this.decoBlockBark, 1, 2), this.decoBlockBarkStairs[2], 4);
		DecoUtilsRecipes.addStairsRecipe(new ItemStack(this.decoBlockBark, 1, 3), this.decoBlockBarkStairs[3], 4);
		DecoUtilsRecipes.addStairsRecipe(new ItemStack(this.decoBlockBark, 1, 4), this.decoBlockBarkStairs[4], 4);
		DecoUtilsRecipes.addStairsRecipe(new ItemStack(this.decoBlockBark, 1, 5), this.decoBlockBarkStairs[5], 4);
		DecoUtilsRecipes.addStairsRecipe(new ItemStack(this.decoBlockBark, 1, 6), this.decoBlockBarkStairs[6], 4);

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
		this.decoBlockBark.SetAxesEffectiveOn(true);
		this.decoBlockBarkSlab.SetAxesEffectiveOn(true);
		this.decoBlockBarkSlabTop.SetAxesEffectiveOn(true);
		this.decoBlockBarkWall.SetAxesEffectiveOn(true);
		
		for (int index = 0; index < DecoUtilsStrings.TREE_TAGS.length; index++)
		{
			this.decoBlockBarkStairs[index].SetAxesEffectiveOn(true);
		}
	}
}
