package net.minecraft.src;

public class DecoSubModuleLantern implements DecoISubModule 
{
	public static Block decoBlockLanternGold;
	public static Block decoBlockLanternIron;
	public static Block decoBlockLanternPaper;
	public static Block decoBlockLanternSoulforgedSteel;
	public static Block decoBlockLanternWood;
	
	public static Item decoItemLanternGold;
	public static Item decoItemLanternIron;
	public static Item decoItemLanternPaper;
	public static Item decoItemLanternSoulforgedSteel;
	public static Item decoItemLanternWood;
	
	public static final int decoBlockLanternGoldID = DecoAddonManager.getBlockID("decoBlockLanternGoldID");
	public static final int decoBlockLanternIronID = DecoAddonManager.getBlockID("decoBlockLanternIronID");
	public static final int decoBlockLanternPaperID = DecoAddonManager.getBlockID("decoBlockLanternPaperID");
	public static final int decoBlockLanternSoulforgedSteelID = DecoAddonManager.getBlockID("decoBlockLanternSoulforgedSteelID");
	public static final int decoBlockLanternWoodID = DecoAddonManager.getBlockID("decoBlockLanternWoodID");
	
	public static final int decoItemLanternGoldID = DecoAddonManager.getBlockID("decoItemLanternGoldID");
	public static final int decoItemLanternIronID = DecoAddonManager.getBlockID("decoItemLanternIronID");
	public static final int decoItemLanternPaperID = DecoAddonManager.getBlockID("decoItemLanternPaperID");
	public static final int decoItemLanternSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemLanternSoulforgedSteelID");
	public static final int decoItemLanternWoodID = DecoAddonManager.getBlockID("decoItemLanternWoodID");
	
	public DecoSubModuleLantern()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Lantern");
		
		this.decoBlockLanternGold = new DecoBlockLantern(this.decoBlockLanternGoldID, Material.iron, "gold", 1.0F, 0.85F, false);
		this.decoBlockLanternIron = new DecoBlockLantern(this.decoBlockLanternIronID, Material.iron, "iron", 2.0F, 0.75F, false);
		this.decoBlockLanternPaper = new DecoBlockLantern(this.decoBlockLanternPaperID, Material.wood, "paper", 0.3F, 0.65F, false);
		this.decoBlockLanternSoulforgedSteel = new DecoBlockLantern(this.decoBlockLanternSoulforgedSteelID, FCBetterThanWolves.fcMaterialSoulforgedSteel, "soulforgedSteel", 5.0F, 1.0F, false);
		this.decoBlockLanternWood = new DecoBlockLantern(this.decoBlockLanternWoodID, Material.wood, "wood", 0.3F, 0.65F, false);
		
		this.decoItemLanternGold = new DecoItemLantern(this.decoItemLanternGoldID, this.decoBlockLanternGoldID, "gold");
		this.decoItemLanternIron = new DecoItemLantern(this.decoItemLanternIronID, this.decoBlockLanternIronID, "iron");
		this.decoItemLanternPaper = new DecoItemLantern(this.decoItemLanternPaperID, this.decoBlockLanternPaperID, "paper");
		this.decoItemLanternSoulforgedSteel = new DecoItemLantern(this.decoItemLanternSoulforgedSteelID, this.decoBlockLanternSoulforgedSteelID, "soulforgedSteel");
		this.decoItemLanternWood = new DecoItemLantern(this.decoItemLanternWoodID, this.decoBlockLanternWoodID, "wood");
		
		DecoModuleTweaks.decoBlockAestheticNonOpaque.AddHoldableBlock(this.decoBlockLanternGold);
		DecoModuleTweaks.decoBlockAestheticNonOpaque.AddHoldableBlock(this.decoBlockLanternIron);
		DecoModuleTweaks.decoBlockAestheticNonOpaque.AddHoldableBlock(this.decoBlockLanternPaper);
		DecoModuleTweaks.decoBlockAestheticNonOpaque.AddHoldableBlock(this.decoBlockLanternSoulforgedSteel);
		DecoModuleTweaks.decoBlockAestheticNonOpaque.AddHoldableBlock(this.decoBlockLanternWood);
		
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
		DecoAddonManager.register(this.decoBlockLanternWood, "Wood Lantern");
		
		DecoAddonManager.register(this.decoItemLanternGold, "Gold Lantern");
		DecoAddonManager.register(this.decoItemLanternIron, "Iron Lantern");
		DecoAddonManager.register(this.decoItemLanternPaper, "Paper Lantern");
		DecoAddonManager.register(this.decoItemLanternSoulforgedSteel, "Soulforged Steel Lantern");
		DecoAddonManager.register(this.decoItemLanternWood, "Wood Lantern");
	}

	public void addRecipes() 
	{
		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoItemLanternGold, 1), 
				new Object[] { "XXX", "#h#", "XXX", 'h', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), '#', Block.thinGlass, 'X', Item.ingotGold });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.ingotGold, 2), 
				new ItemStack[] { new ItemStack(this.decoItemLanternGold, 1) });
	
		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoItemLanternIron, 1), 
				new Object[] { "XXX", "#h#", "XXX", 'h', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), '#', Block.thinGlass, 'X', Item.ingotIron});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 2), 
				new ItemStack[] { new ItemStack(this.decoItemLanternIron, 1) });
	
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemLanternPaper, 1),
				new Object[] { "XXX", "XhX", "XXX", 'h', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), 'X', Item.paper, });
	
		FCRecipes.AddAnvilRecipe(new ItemStack(this.decoItemLanternSoulforgedSteel, 1), 
				new Object[] { "XXX", "#h#", "XXX", 'h', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), '#', Block.thinGlass, 'X', FCBetterThanWolves.fcSteel });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcSteel, 2), 
				new ItemStack[] { new ItemStack(this.decoItemLanternSoulforgedSteel, 1) });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemLanternWood, 1),
				new Object[] { "XXX", "#h#", "XXX", 'h', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), '#', Item.paper, 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
	
	}

	public void changeVanillaItems() {}

	public void setupCustomToolProperties() 
	{
		this.decoBlockLanternGold.SetPicksEffectiveOn(true);
		this.decoBlockLanternIron.SetPicksEffectiveOn(true);
		this.decoBlockLanternPaper.SetAxesEffectiveOn(true);
		this.decoBlockLanternSoulforgedSteel.SetPicksEffectiveOn(true);
		this.decoBlockLanternWood.SetAxesEffectiveOn(true);
	}

}
