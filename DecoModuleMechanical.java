package net.minecraft.src;

public class DecoModuleMechanical 
{
	public static Block decoBlockCompostBin;
	public static Block decoBlockFenceGate;
	public static Block decoBlockScaffoldMetal;
	public static Block decoBlockScaffoldWood;
	public static Block decoBlockSmoker;
	public static Block decoBlockTrapDoorIron;
	public static Block decoBlockSteelGearBox;
	public static Block decoBlockSteelAxle;
	public static Block decoBlockSteelAxlePowerSource;
	
	public static final int decoBlockCompostBinID = DecoAddonManager.getBlockID("decoBlockCompostBinID");
	public static final int decoBlockScaffoldMetalID = DecoAddonManager.getBlockID("decoBlockScaffoldMetalID");
	public static final int decoBlockScaffoldWoodID = DecoAddonManager.getBlockID("decoBlockScaffoldWoodID");
	public static final int decoBlockSmokerID = DecoAddonManager.getBlockID("decoBlockSmokerID");
	public static final int decoBlockTrapDoorIronID = DecoAddonManager.getBlockID("decoBlockTrapDoorIronID");
	public static final int decoBlockSteelGearBoxID = DecoAddonManager.getBlockID("decoBlockSteelGearBoxID");
	public static final int decoBlockSteelAxleID = DecoAddonManager.getBlockID("decoBlockSteelAxleID");
	public static final int decoBlockSteelAxlePowerSourceID = DecoAddonManager.getBlockID("decoBlockSteelAxlePowerSourceID");
	
	public static final int decoCompostBinContainerID = 300;
	
	public DecoModuleMechanical()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Mechanical");
		
		if (DecoAddonManager.getConfigOption("enableCompostBin"))
		{
			TileEntity.addMapping(DecoTileEntityCompostBin.class, "Compost Bin");
			this.decoBlockCompostBin = new DecoBlockCompostBin(this.decoBlockCompostBinID);
		}
		
		this.decoBlockScaffoldMetal = new DecoBlockScaffold(this.decoBlockScaffoldMetalID, Material.iron, "iron");
		this.decoBlockScaffoldWood = new DecoBlockScaffold(this.decoBlockScaffoldWoodID, Material.wood, "wood");
		this.decoBlockSmoker = new DecoBlockSmoker(this.decoBlockSmokerID);
		this.decoBlockSteelAxle = new DecoBlockSteelAxle(this.decoBlockSteelAxleID);
		this.decoBlockSteelAxlePowerSource = new DecoBlockSteelAxlePowerSource(this.decoBlockSteelAxlePowerSourceID);
		this.decoBlockSteelGearBox = new DecoBlockSteelGearBox(this.decoBlockSteelGearBoxID);
		this.decoBlockTrapDoorIron = new DecoBlockTrapDoor(this.decoBlockTrapDoorIronID, Block.blockIron, "iron");
		
		registerBlocks();
		addRecipes();
	}
	
	public void registerBlocks()
	{
		if (DecoAddonManager.getConfigOption("enableCompostBin"))
			DecoAddonManager.register(this.decoBlockCompostBin, "Compost Bin");
		
		DecoAddonManager.register(this.decoBlockScaffoldMetal, "Iron Scaffold");
		DecoAddonManager.register(this.decoBlockScaffoldWood, "Wood Scaffold");
		DecoAddonManager.register(this.decoBlockSmoker, "Smoker");
		DecoAddonManager.register(this.decoBlockTrapDoorIron, "Iron Trapdoor");
		DecoAddonManager.register(this.decoBlockSteelGearBox, "Steel Gearbox");
		DecoAddonManager.register(this.decoBlockSteelAxle, "Steel Axle");
	}
	
	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(Block.wood, 1, 0), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });
		
		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(Block.wood, 1, 1), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });
		
		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(Block.wood, 1, 2), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });
		
		FCRecipes.AddVanillaRecipe(new ItemStack (this.decoBlockCompostBin, 1), 
				new Object [] { "XXX", "X#X", "X@X", 'X', new ItemStack(Block.wood, 1, 3), '#', Block.dirt, '@', FCBetterThanWolves.fcGear });
		
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoorIron, 2), 
				new Object[] { "XXX", "XXX", 'X', Block.blockIron });
		
		FCRecipes.AddVanillaRecipe(new ItemStack (Block.hopperBlock, 1), 
				new Object [] { "X X", "X#X", " X ", 'X', Item.ingotIron, '#', Block.chest });
		
		if (DecoAddonManager.getConfigOption("enableCompostBin"))
		{
			DecoUtilsRecipes.addCompostBinRecipe( new ItemStack(Block.dirt), new ItemStack(Block.leaves, 1, 0));
			DecoUtilsRecipes.addCompostBinRecipe( new ItemStack(Block.dirt), new ItemStack(Block.leaves, 1, 1));
			DecoUtilsRecipes.addCompostBinRecipe( new ItemStack(Block.dirt), new ItemStack(Block.leaves, 1, 2));
			DecoUtilsRecipes.addCompostBinRecipe( new ItemStack(Block.dirt), new ItemStack(Block.leaves, 1, 3));
		}
	}
}
