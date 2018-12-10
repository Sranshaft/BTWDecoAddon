package net.minecraft.src;

public class DecoSubModuleToolMallet implements DecoISubModule 
{
	public static Item decoItemMalletDiamond;
	public static Item decoItemMalletGold;
	public static Item decoItemMalletIron;
	public static Item decoItemMalletSoulforgedSteel;
	
	public static final int decoItemMalletDiamondID = DecoAddonManager.getBlockID("decoItemMalletDiamondID");
	public static final int decoItemMalletGoldID = DecoAddonManager.getBlockID("decoItemMalletGoldID");
	public static final int decoItemMalletIronID = DecoAddonManager.getBlockID("decoItemMalletIronID");
	public static final int decoItemMalletSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemMalletSoulforgedSteelID");
	
	public DecoSubModuleToolMallet()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Mallets");
		
		this.decoItemMalletDiamond = new DecoItemMallet(this.decoItemMalletDiamondID, EnumToolMaterial.EMERALD, "diamond", 2);
		this.decoItemMalletGold = new DecoItemMallet(this.decoItemMalletGoldID, EnumToolMaterial.GOLD, "gold", 1);
		this.decoItemMalletIron = new DecoItemMallet(this.decoItemMalletIronID, EnumToolMaterial.IRON, "iron", 1);
		this.decoItemMalletSoulforgedSteel = new DecoItemMallet(this.decoItemMalletSoulforgedSteelID, EnumToolMaterial.EMERALD, "soulforgedSteel", 3);
		
		this.registerBlocks();
		this.addRecipes();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemMalletDiamond, "Diamondium Mallet");
		DecoAddonManager.register(this.decoItemMalletGold, "Gold Mallet");
		DecoAddonManager.register(this.decoItemMalletIron, "Iron Mallet");
		DecoAddonManager.register(this.decoItemMalletSoulforgedSteel, "Soulforged Steel Mallet");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemMalletDiamond), 
				new Object[] { " B ", " HB", "H  ", 'B', Block.blockDiamond, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemMalletGold), 
				new Object[] { " B ", " HB", "H  ", 'B', Block.blockGold, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemMalletIron), 
				new Object[] { " B ", " HB", "H  ", 'B', Block.blockIron, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemMalletSoulforgedSteel), 
				new Object[] { " B ", " HB", "H  ", 'B', FCBetterThanWolves.fcSoulforgedSteelBlock, 'H', FCBetterThanWolves.fcHaft });
	}

	public void changeVanillaItems() {}
	public void setupCustomToolProperties() {}
}
