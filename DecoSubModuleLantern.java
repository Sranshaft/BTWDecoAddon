package net.minecraft.src;

public class DecoSubModuleLantern implements DecoISubModule 
{
	public static Block decoBlockLanternGold;
	public static Block decoBlockLanternIron;
	public static Block decoBlockLanternPaper;
	public static Block decoBlockLanternSoulforgedSteel;
	
	public static final int decoBlockLanternGoldID = 3337;
	public static final int decoBlockLanternIronID = 3338;
	public static final int decoBlockLanternPaperID = 3339;
	public static final int decoBlockLanternSoulforgedSteelID = 3340;
	
	public DecoSubModuleLantern()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Lantern");
		
		this.decoBlockLanternGold = new DecoBlockLantern(this.decoBlockLanternGoldID, Material.iron, "Gold", 1.0F, 0.85F, true);
		this.decoBlockLanternIron = new DecoBlockLantern(this.decoBlockLanternIronID, Material.iron, "Iron", 2.0F, 0.75F, true);
		this.decoBlockLanternPaper = new DecoBlockLantern(this.decoBlockLanternPaperID, Material.wood, "Paper", 0.3F, 0.65F, true);
		this.decoBlockLanternSoulforgedSteel = new DecoBlockLantern(this.decoBlockLanternSoulforgedSteelID, Material.iron, "SoulforgedSteel", 5.0F, 1.0F, true);
		
		DecoFixLightningRod.AddHoldableBlock(this.decoBlockLanternGold);
		DecoFixLightningRod.AddHoldableBlock(this.decoBlockLanternIron);
		DecoFixLightningRod.AddHoldableBlock(this.decoBlockLanternPaper);
		DecoFixLightningRod.AddHoldableBlock(this.decoBlockLanternSoulforgedSteel);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockLanternGold, "Gold Lantern");
		DecoAddonManager.register(this.decoBlockLanternIron, "Iron Lantern");
		DecoAddonManager.register(this.decoBlockLanternPaper, "Paper Lantern");
		DecoAddonManager.register(this.decoBlockLanternSoulforgedSteel, "Soulforged Steel Lantern");
	}

	public void addRecipes() 
	{
		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockLanternGold, 1), 
				new Object[] { "#X#", "XhX", "#X#", 'h', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), '#', Block.thinGlass, 'X', Item.goldNugget });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 1), 
				new ItemStack[] { new ItemStack(this.decoBlockLanternGold, 1) });
	
		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockLanternIron, 1), 
				new Object[] { "#X#", "XhX", "#X#", 'h', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), '#', Block.thinGlass, 'X', FCBetterThanWolves.fcItemNuggetIron});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 1), 
				new ItemStack[] { new ItemStack(this.decoBlockLanternIron, 1) });
	
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockLanternPaper, 1),
				new Object[] { "#X#", "XhX", "#X#", 'h', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), '#', Item.paper, 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
	
		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoBlockLanternSoulforgedSteel, 1), 
				new Object[] { "#X#", "XhX", "#X#", 'h',new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), '#', Block.thinGlass, 'X', FCBetterThanWolves.fcItemNuggetSteel });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 1), 
				new ItemStack[] { new ItemStack(this.decoBlockLanternSoulforgedSteel, 1) });
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this.decoBlockLanternPaper);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockLanternGold);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockLanternIron);
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this.decoBlockLanternSoulforgedSteel);
	}

}
