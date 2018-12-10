package net.minecraft.src;

public class DecoSubModuleToolScythe implements DecoISubModule 
{
	public static Item decoItemScytheDiamond;
	public static Item decoItemScytheGold;
	public static Item decoItemScytheIron;
	public static Item decoItemScytheSoulforgedSteel;
	
	public static final int decoItemScytheDiamondID = DecoAddonManager.getBlockID("decoItemScytheDiamondID");
	public static final int decoItemScytheGoldID = DecoAddonManager.getBlockID("decoItemScytheGoldID");
	public static final int decoItemScytheIronID = DecoAddonManager.getBlockID("decoItemScytheIronID");
	public static final int decoItemScytheSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemScytheSoulforgedSteelID");
	
	public DecoSubModuleToolScythe()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Scythes");
		
		this.decoItemScytheDiamond = new DecoItemScythe(this.decoItemScytheDiamondID, EnumToolMaterial.EMERALD, "diamond", 2);
		this.decoItemScytheGold = new DecoItemScythe(this.decoItemScytheGoldID, EnumToolMaterial.GOLD, "gold", 1);
		this.decoItemScytheIron = new DecoItemScythe(this.decoItemScytheIronID, EnumToolMaterial.IRON, "iron", 1);
		this.decoItemScytheSoulforgedSteel = new DecoItemScythe(this.decoItemScytheSoulforgedSteelID, EnumToolMaterial.EMERALD, "soulforgedSteel", 3);
		
		this.registerBlocks();
		this.addRecipes();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemScytheDiamond, "Diamond Scythe");
		DecoAddonManager.register(this.decoItemScytheGold, "Gold Scythe");
		DecoAddonManager.register(this.decoItemScytheIron, "Iron Scythe");
		DecoAddonManager.register(this.decoItemScytheSoulforgedSteel, "Soulforged Steel Scythe");
	}

	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemScytheDiamond), 
				new Object[] { "II ", "I I", "IH ", 'I', FCBetterThanWolves.fcItemIngotDiamond, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemScytheGold), 
				new Object[] { "II ", "I I", "IH ", 'I', Item.ingotGold, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemScytheIron), 
				new Object[] { "II ", "I I", "IH ", 'I', Item.ingotIron, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemScytheSoulforgedSteel), 
				new Object[] { "II ", "I I", "IH ", 'I', FCBetterThanWolves.fcSteel, 'H', FCBetterThanWolves.fcHaft });
	}

	public void changeVanillaItems() {}
	public void setupCustomToolProperties() {}
}
