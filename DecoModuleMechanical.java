package net.minecraft.src;

public class DecoModuleMechanical 
{
	public static DecoSubModuleCompostBin decoSubModuleCompostBin;
	public static DecoSubModuleScaffold decoSubModuleScaffold;
	
	public static Block[] decoBlockDoorLarge;
	
	public static Block decoBlockSmoker;
	public static Block decoBlockTrapDoorIron;
	public static Block decoBlockSteelGearBox;
	public static Block decoBlockSteelAxle;
	public static Block decoBlockSteelAxlePowerSource;
	
	public static Item[] decoItemDoorLarge;
	
	public static final int decoBlockDoorLargeOakID = DecoAddonManager.getBlockID("decoBlockDoorLargeOakID");
	public static final int decoBlockSmokerID = DecoAddonManager.getBlockID("decoBlockSmokerID");
	public static final int decoBlockTrapDoorIronID = DecoAddonManager.getBlockID("decoBlockTrapDoorIronID");
	public static final int decoBlockSteelGearBoxID = DecoAddonManager.getBlockID("decoBlockSteelGearBoxID");
	public static final int decoBlockSteelAxleID = DecoAddonManager.getBlockID("decoBlockSteelAxleID");
	public static final int decoBlockSteelAxlePowerSourceID = DecoAddonManager.getBlockID("decoBlockSteelAxlePowerSourceID");
	
	public static final int decoItemDoorLargeOakID = DecoAddonManager.getBlockID("decoItemDoorLargeOakID");
	
	
	public DecoModuleMechanical()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Mechanical");
		
		if (DecoAddonManager.getConfigOption("enableCompostBin"))
			this.decoSubModuleCompostBin = new DecoSubModuleCompostBin();
		
		if (DecoAddonManager.getConfigOption("enableScaffold"))
			this.decoSubModuleScaffold = new DecoSubModuleScaffold();
		
		this.decoBlockDoorLarge = new Block[7];
		for (int index = 0; index < this.decoBlockDoorLarge.length; index++)
		{
			this.decoBlockDoorLarge[index] = new DecoBlockDoor(this.decoBlockDoorLargeOakID + index, DecoUtilsStrings.WOOD_TAGS[index], index);
		}
		
		this.decoBlockSmoker = new DecoBlockSmoker(this.decoBlockSmokerID);
		this.decoBlockSteelAxle = new DecoBlockSteelAxle(this.decoBlockSteelAxleID);
		this.decoBlockSteelAxlePowerSource = new DecoBlockSteelAxlePowerSource(this.decoBlockSteelAxlePowerSourceID);
		this.decoBlockSteelGearBox = new DecoBlockSteelGearBox(this.decoBlockSteelGearBoxID);
		this.decoBlockTrapDoorIron = new DecoBlockTrapDoor(this.decoBlockTrapDoorIronID, Block.blockIron, "iron");
		
		this.decoItemDoorLarge = new Item[7];
		for (int index = 0; index < this.decoItemDoorLarge.length; index++)
		{
			this.decoItemDoorLarge[index] = new DecoItemDoorLarge(this.decoItemDoorLargeOakID + index, DecoUtilsStrings.WOOD_TAGS[index], index);
		}
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockSmoker, "Smoker");
		DecoAddonManager.register(this.decoBlockTrapDoorIron, "Iron Trapdoor");
		DecoAddonManager.register(this.decoBlockSteelGearBox, "Steel Gearbox");
		DecoAddonManager.register(this.decoBlockSteelAxle, "Steel Axle");
		
		for (int index = 0; index < this.decoItemDoorLarge.length; index++)
		{
			DecoAddonManager.register(this.decoItemDoorLarge[index], DecoUtilsStrings.WOOD_NAMES[index] + " Door");
		}
	}
	
	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockTrapDoorIron, 2), 
				new Object[] { "XXX", "XXX", 'X', Block.blockIron });
		
		FCRecipes.AddVanillaRecipe(new ItemStack (Block.hopperBlock, 1), 
				new Object [] { "X X", "X#X", " X ", 'X', Item.ingotIron, '#', Block.chest });
	}
	
	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties() 
	{
		this.decoBlockSmoker.SetPicksEffectiveOn();
		this.decoBlockSteelAxle.SetPicksEffectiveOn();
		this.decoBlockSteelAxlePowerSource.SetPicksEffectiveOn();
		this.decoBlockSteelGearBox.SetPicksEffectiveOn();
		this.decoBlockTrapDoorIron.SetPicksEffectiveOn();
	}
}
