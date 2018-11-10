package net.minecraft.src;

public class DecoSubModuleTools implements DecoISubModule
{
	public static Item decoItemHammerDiamond;
	public static Item decoItemHammerGold;
	public static Item decoItemHammerIron;
	public static Item decoItemHammerSoulforgedSteel;
	public static Item decoItemScytheDiamond;
	public static Item decoItemScytheGold;
	public static Item decoItemScytheIron;
	public static Item decoItemScytheSoulforgedSteel;
	public static Item decoItemSling;
	public static Item decoItemSpyglass;
	
	public static final int decoItemHammerDiamondID = DecoAddonManager.getBlockID("decoItemHammerDiamondID");
	public static final int decoItemHammerGoldID = DecoAddonManager.getBlockID("decoItemHammerGoldID");
	public static final int decoItemHammerIronID = DecoAddonManager.getBlockID("decoItemHammerIronID");
	public static final int decoItemHammerSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemHammerSoulforgedSteelID");
	public static final int decoItemScytheDiamondID = DecoAddonManager.getBlockID("decoItemScytheDiamondID");
	public static final int decoItemScytheGoldID = DecoAddonManager.getBlockID("decoItemScytheGoldID");
	public static final int decoItemScytheIronID = DecoAddonManager.getBlockID("decoItemScytheIronID");
	public static final int decoItemScytheSoulforgedSteelID = DecoAddonManager.getBlockID("decoItemScytheSoulforgedSteelID");
	public static final int decoItemSlingID = DecoAddonManager.getBlockID("decoItemSlingID");
	public static final int decoItemSpyglassID = DecoAddonManager.getBlockID("decoItemSpyglassID");
	
	public static final int decoEntityRockID = 25;
	public static final int decoEntityRockVehicleSpawnType = 120;
	
	public static Item decoItemDebugStick;
	public static final int decoItemDebugStickID = DecoAddonManager.getBlockID("decoItemDebugStickID");

	public DecoSubModuleTools()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Tools");
		
		this.decoItemHammerDiamond = new DecoItemHammer(this.decoItemHammerDiamondID, EnumToolMaterial.EMERALD, "diamond", 2);
		this.decoItemHammerGold = new DecoItemHammer(this.decoItemHammerGoldID, EnumToolMaterial.GOLD, "gold", 1);
		this.decoItemHammerIron = new DecoItemHammer(this.decoItemHammerIronID, EnumToolMaterial.IRON, "iron", 1);
		this.decoItemHammerSoulforgedSteel = new DecoItemHammer(this.decoItemHammerSoulforgedSteelID, EnumToolMaterial.EMERALD, "soulforgedSteel", 3);
		
		this.decoItemScytheDiamond = new DecoItemScythe(this.decoItemScytheDiamondID, EnumToolMaterial.EMERALD, "diamond", 2);
		this.decoItemScytheGold = new DecoItemScythe(this.decoItemScytheGoldID, EnumToolMaterial.GOLD, "gold", 1);
		this.decoItemScytheIron = new DecoItemScythe(this.decoItemScytheIronID, EnumToolMaterial.IRON, "iron", 1);
		this.decoItemScytheSoulforgedSteel = new DecoItemScythe(this.decoItemScytheSoulforgedSteelID, EnumToolMaterial.EMERALD, "soulforgedSteel", 3);
		
		this.decoItemSling = new DecoItemSling(this.decoItemSlingID);
		EntityList.addMapping(DecoEntityRock.class, "decoEntityRock", this.decoEntityRockID);
		RenderManager.AddEntityRenderer(DecoEntityRock.class, new RenderSnowball(FCBetterThanWolves.fcItemStone));
		
		this.decoItemSpyglass = new DecoItemSpyglass(this.decoItemSpyglassID);
		this.decoItemDebugStick = new DecoItemDebugStick(this.decoItemDebugStickID);
		
		this.registerBlocks();
		this.changeVanillaItems();
		this.addRecipes();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemHammerDiamond, "Diamondium Hammer");
		DecoAddonManager.register(this.decoItemHammerGold, "Gold Hammer");
		DecoAddonManager.register(this.decoItemHammerIron, "Iron Hammer");
		DecoAddonManager.register(this.decoItemHammerSoulforgedSteel, "Soulforged Steel Hammer");
		DecoAddonManager.register(this.decoItemScytheDiamond, "Diamond Scythe");
		DecoAddonManager.register(this.decoItemScytheGold, "Gold Scythe");
		DecoAddonManager.register(this.decoItemScytheIron, "Iron Scythe");
		DecoAddonManager.register(this.decoItemScytheSoulforgedSteel, "Soulforged Steel Scythe");
		DecoAddonManager.register(this.decoItemSling, "Sling");
		DecoAddonManager.register(this.decoItemSpyglass, "Spyglass");
		
		DecoAddonManager.register(this.decoItemDebugStick, "Debug stick");
	}
	
	public void changeVanillaItems() 
	{
	}
	
	public void addRecipes() 
	{
		// Hammer
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemHammerDiamond), 
				new Object[] { " B ", " HB", "H  ", 'B', Block.blockDiamond, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemHammerGold), 
				new Object[] { " B ", " HB", "H  ", 'B', Block.blockGold, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemHammerIron), 
				new Object[] { " B ", " HB", "H  ", 'B', Block.blockIron, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemHammerSoulforgedSteel), 
				new Object[] { " B ", " HB", "H  ", 'B', FCBetterThanWolves.fcSoulforgedSteelBlock, 'H', FCBetterThanWolves.fcHaft });
		
		// Scythe
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemScytheDiamond), 
				new Object[] { "II ", "I I", "IH ", 'I', FCBetterThanWolves.fcItemIngotDiamond, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemScytheGold), 
				new Object[] { "II ", "I I", "IH ", 'I', Item.ingotGold, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemScytheIron), 
				new Object[] { "II ", "I I", "IH ", 'I', Item.ingotIron, 'H', FCBetterThanWolves.fcHaft });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemScytheSoulforgedSteel), 
				new Object[] { "II ", "I I", "IH ", 'I', FCBetterThanWolves.fcSteel, 'H', FCBetterThanWolves.fcHaft });
		
		// Sling
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemSling, 1), new Object[] {"RXR", 'R', FCBetterThanWolves.fcRopeItem, 'X', FCBetterThanWolves.fcItemTannedLeatherCut});
		
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

	public void setupCustomToolProperties() {}
}
