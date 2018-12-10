package net.minecraft.src;

public class DecoSubModuleCompostBin implements DecoISubModule 
{
	public static Block decoBlockCompostBin;
	public static Block decoBlockPodzol;

	public static final int decoBlockCompostBinID = DecoAddonManager.getBlockID("decoBlockCompostBinID");
	public static final int decoBlockPodzolID = DecoAddonManager.getBlockID("decoBlockPodzolID");
	
	public static final int decoContainerCompostBinID = 300;

	public DecoSubModuleCompostBin()
	{
		TileEntity.addMapping(DecoTileEntityCompostBin.class, "Compost Bin");
		this.decoBlockCompostBin = new DecoBlockCompostBin(this.decoBlockCompostBinID);
		
		this.decoBlockPodzol = new DecoBlockPodzol(this.decoBlockPodzolID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}

	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockCompostBin, "Compost Bin");
		DecoAddonManager.register(this.decoBlockPodzol, "Podzol");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(Block.planks, 1, 0), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });

		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(Block.planks, 1, 1), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });

		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(Block.planks, 1, 2), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });

		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(Block.planks, 1, 3), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });
		
		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(FCBetterThanWolves.fcBloodWood, 1, 0), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });
		
		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockPlanks, 1, 0), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });
		
		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockPlanks, 1, 1), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });
		
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.leaves, 4, 0));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.leaves, 4, 1));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.leaves, 4, 2));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.leaves, 4, 3));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(FCBetterThanWolves.fcBlockBloodLeaves, 4, 0));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLeaves, 4, 0));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLeaves, 4, 1));
		
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.sapling, 16, 0));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.sapling, 16, 1));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.sapling, 16, 2));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.sapling, 16, 3));
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(FCBetterThanWolves.fcAestheticVegetation, 16, 2));
		
		DecoUtilsRecipes.addCompostBinRecipe(new ItemStack(this.decoBlockPodzol), new ItemStack(Block.tallGrass, 16, 0));
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockCompostBin.SetAxesEffectiveOn(true);
		this.decoBlockPodzol.SetShovelsEffectiveOn(true);
	}

}
