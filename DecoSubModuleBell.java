package net.minecraft.src;

public class DecoSubModuleBell implements DecoISubModule {

	public static Block decoBlockBellGold;
	public static Block decoBlockBellIron;
	public static Block decoBlockBellSoulforgedSteel;
	
	public static Item decoItemBellGold;
	public static Item decoItemBellIron;
	public static Item decoItemBellSoulforgedSteel;
	
	public static final int decoBlockBellGoldID = DecoAddonManager.getBlockID("decoBlockBellGoldID");
	public static final int decoBlockBellIronID = DecoAddonManager.getBlockID("decoBlockBellIronID");
	public static final int decoBlockBellSoulforgedSteelID = DecoAddonManager.getBlockID("decoBlockBellSoulforgedSteelID");
	
	public static final int decoItemBellGoldID = DecoAddonManager.getBlockID("decoItemBellGoldID");
	public static final int decoItemBellIronID = DecoAddonManager.getBlockID("decoItemBellIronID");
	public static final int decoItemBellSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemBellSoulforgedSteelID");
	
	public DecoSubModuleBell()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Bell");
		
		this.decoBlockBellGold = new DecoBlockBell(this.decoBlockBellGoldID, Material.iron, "gold");
		this.decoBlockBellIron = new DecoBlockBell(this.decoBlockBellIronID, Material.iron, "iron");
		this.decoBlockBellSoulforgedSteel = new DecoBlockBell(this.decoBlockBellSoulforgedSteelID, FCBetterThanWolves.fcMaterialSoulforgedSteel, "soulforgedSteel");
		
		this.decoItemBellGold = new DecoItemBell(this.decoItemBellGoldID, this.decoBlockBellGoldID, "gold");
		this.decoItemBellIron = new DecoItemBell(this.decoItemBellIronID, this.decoBlockBellIronID, "iron");
		this.decoItemBellSoulforgedSteel = new DecoItemBell(this.decoItemBellSoulforgedSteelID, this.decoBlockBellSoulforgedSteelID, "soulforgedSteel");
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockBellGold, "Gold Bell");
		DecoAddonManager.register(this.decoBlockBellIron, "Iron Bell");
		DecoAddonManager.register(this.decoBlockBellSoulforgedSteel, "Soulforged Steel Bell");
		
		DecoAddonManager.register(this.decoItemBellGold, "Gold Bell");
		DecoAddonManager.register(this.decoItemBellIron, "Iron Bell");
		DecoAddonManager.register(this.decoItemBellSoulforgedSteel, "Soulforged Steel Bell");
	}
	
	public void addRecipes() {}
	public void changeVanillaItems() {}
	public void setupCustomToolProperties() {}

}
