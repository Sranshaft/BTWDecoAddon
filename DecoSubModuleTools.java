package net.minecraft.src;

public class DecoSubModuleTools implements DecoISubModule
{
	public static Item decoItemChiselDiamond;
	public static Item decoItemChiselGold;
	public static Item decoItemChiselIron;
	public static Item decoItemChiselSoulforgedSteel;
	public static Item decoItemHammerDiamond;
	public static Item decoItemHammerGold;
	public static Item decoItemHammerIron;
	public static Item decoItemHammerSoulforgedSteel;
	public static Item decoItemScytheDiamond;
	public static Item decoItemScytheGold;
	public static Item decoItemScytheIron;
	public static Item decoItemScytheSoulforgedSteel;
	public static Item decoItemSpyglass;
	
	public static final int decoItemChiselDiamondID = DecoAddonManager.getBlockID("decoItemChiselDiamondID");
	public static final int decoItemChiselGoldID = DecoAddonManager.getBlockID("decoItemChiselGoldID");
	public static final int decoItemChiselIronID = DecoAddonManager.getBlockID("decoItemChiselIronID");
	public static final int decoItemChiselSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemChiselSoulforgedSteelID");
	public static final int decoItemHammerDiamondID = DecoAddonManager.getBlockID("decoItemHammerDiamondID");
	public static final int decoItemHammerGoldID = DecoAddonManager.getBlockID("decoItemHammerGoldID");
	public static final int decoItemHammerIronID = DecoAddonManager.getBlockID("decoItemHammerIronID");
	public static final int decoItemHammerSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemHammerSoulforgedSteelID");
	public static final int decoItemScytheDiamondID = DecoAddonManager.getBlockID("decoItemScytheDiamondID");
	public static final int decoItemScytheGoldID = DecoAddonManager.getBlockID("decoItemScytheGoldID");
	public static final int decoItemScytheIronID = DecoAddonManager.getBlockID("decoItemScytheIronID");
	public static final int decoItemScytheSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemScytheSoulforgedSteelID");
	public static final int decoItemSpyglassID = DecoAddonManager.getBlockID("decoItemSpyglassID");

	public DecoSubModuleTools()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Tools");
		
		// REPLACE VANILLA ITEMS
		Item.m_bSuppressConflictWarnings = true;
		
		Item.hoeDiamond = new DecoItemHoe(Item.hoeDiamond.itemID, EnumToolMaterial.EMERALD).setUnlocalizedName("hoeDiamond");
		Item.hoeGold = new DecoItemHoe(Item.hoeGold.itemID, EnumToolMaterial.GOLD).setUnlocalizedName("hoeGold");
		Item.hoeIron = new DecoItemHoe(Item.hoeIron.itemID, EnumToolMaterial.IRON).setUnlocalizedName("hoeIron");
		
		Item.shovelDiamond = new DecoItemSpade(Item.shovelDiamond.itemID, EnumToolMaterial.EMERALD).setUnlocalizedName("shovelDiamond");
		Item.shovelGold = new DecoItemSpade(Item.shovelGold.itemID, EnumToolMaterial.GOLD).setUnlocalizedName("shovelGold");
		Item.shovelIron = new DecoItemSpade(Item.shovelIron.itemID, EnumToolMaterial.IRON).setUnlocalizedName("shovelIron");
		
		Item.m_bSuppressConflictWarnings = false;
		
		this.decoItemChiselDiamond = new DecoItemChisel(this.decoItemChiselDiamondID, EnumToolMaterial.EMERALD, "diamond");
		this.decoItemChiselGold = new DecoItemChisel(this.decoItemChiselGoldID, EnumToolMaterial.GOLD, "gold");
		this.decoItemChiselIron = new DecoItemChisel(this.decoItemChiselIronID, EnumToolMaterial.IRON, "iron");
		this.decoItemChiselSoulforgedSteel = new DecoItemChisel(this.decoItemChiselSoulforgedSteelID, EnumToolMaterial.EMERALD, "soulforgedSteel");
		
		this.decoItemHammerDiamond = new DecoItemHammer(this.decoItemHammerDiamondID, EnumToolMaterial.EMERALD, "diamond", 2);
		this.decoItemHammerGold = new DecoItemHammer(this.decoItemHammerGoldID, EnumToolMaterial.GOLD, "gold", 1);
		this.decoItemHammerIron = new DecoItemHammer(this.decoItemHammerIronID, EnumToolMaterial.IRON, "iron", 1);
		this.decoItemHammerSoulforgedSteel = new DecoItemHammer(this.decoItemHammerSoulforgedSteelID, EnumToolMaterial.EMERALD, "soulforgedSteel", 3);
		
		this.decoItemScytheDiamond = new DecoItemScythe(this.decoItemScytheDiamondID, EnumToolMaterial.EMERALD, "diamond", 2);
		this.decoItemScytheGold = new DecoItemScythe(this.decoItemScytheGoldID, EnumToolMaterial.GOLD, "gold", 1);
		this.decoItemScytheIron = new DecoItemScythe(this.decoItemScytheIronID, EnumToolMaterial.IRON, "iron", 1);
		this.decoItemScytheSoulforgedSteel = new DecoItemScythe(this.decoItemScytheSoulforgedSteelID, EnumToolMaterial.EMERALD, "soulforgedSteel", 3);
		
		this.decoItemSpyglass = new DecoItemSpyglass(this.decoItemSpyglassID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemChiselDiamond, "Diamond Chisel");
		DecoAddonManager.register(this.decoItemChiselGold, "Gold Chisel");
		DecoAddonManager.register(this.decoItemChiselIron, "Iron Chisel");
		DecoAddonManager.register(this.decoItemChiselSoulforgedSteel, "Soulforged Steel Chisel");
		DecoAddonManager.register(this.decoItemHammerDiamond, "Diamondium Hammer");
		DecoAddonManager.register(this.decoItemHammerGold, "Gold Hammer");
		DecoAddonManager.register(this.decoItemHammerIron, "Iron Hammer");
		DecoAddonManager.register(this.decoItemHammerSoulforgedSteel, "Soulforged Steel Hammer");
		DecoAddonManager.register(this.decoItemScytheDiamond, "Diamond Scythe");
		DecoAddonManager.register(this.decoItemScytheGold, "Gold Scythe");
		DecoAddonManager.register(this.decoItemScytheIron, "Iron Scythe");
		DecoAddonManager.register(this.decoItemScytheSoulforgedSteel, "Soulforged Steel Scythe");
		DecoAddonManager.register(this.decoItemSpyglass, "Spyglass");
	}
	
	public void addRecipes() 
	{
		// Chisel
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChiselDiamond), 
				new Object[] { "I  ", "S  ", 'I', FCBetterThanWolves.fcItemIngotDiamond, 'S', Item.stick });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChiselGold), 
				new Object[] { "I  ", "S  ", 'I', Item.ingotGold, 'S', Item.stick });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChiselIron), 
				new Object[] { "I  ", "S  ", 'I', Item.ingotIron, 'S', Item.stick });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChiselSoulforgedSteel), 
				new Object[] { "I  ", "S  ", 'I', FCBetterThanWolves.fcSteel, 'S', Item.stick });
		
		// Hammer
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChiselDiamond), 
				new Object[] { "BBB", " H ", " H ", 'B', Block.blockDiamond, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChiselGold), 
				new Object[] { "BBB", " H ", " H ", 'B', Block.blockGold, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChiselIron), 
				new Object[] { "BBB", " H ", " H ", 'B', Block.blockIron, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemChiselSoulforgedSteel), 
				new Object[] { "BBB", " H ", " H ", 'B', FCBetterThanWolves.fcSoulforgedSteelBlock, 'H', FCBetterThanWolves.fcHaft });
		
		// Spyglass
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSpyglass),
				new Object[] { "#X#", "F F", "DXD", '#', Item.ingotGold, 'X', Block.thinGlass, 'F', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'D', Item.goldNugget });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSpyglass),
				new Object[] { "#X#", "F F", "DXD", '#', Item.ingotGold, 'X', Block.thinGlass, 'F', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'D', Item.goldNugget });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSpyglass),
				new Object[] { "#X#", "F F", "DXD", '#', Item.ingotGold, 'X', Block.thinGlass, 'F', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'D', Item.goldNugget });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSpyglass),
				new Object[] { "#X#", "F F", "DXD", '#', Item.ingotGold, 'X', Block.thinGlass, 'F', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'D', Item.goldNugget });
	}

	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties() {}
}
