package net.minecraft.src;

public class DecoSubModuleStainedFlowerPot implements DecoISubModule
{
	public static Block[] decoBlockStainedFlowerPot = new Block[16];
	public static Item[] decoItemStainedFlowerPot = new Item[16];
	
	public static final int decoBlockStainedFlowerPotID = DecoAddonManager.getBlockID("decoBlockStainedFlowerPotID");
	public static final int decoItemStainedFlowerPotID = DecoAddonManager.getBlockID("decoItemStainedFlowerPotID");

	public DecoSubModuleStainedFlowerPot()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Stained Flower Pot");
		
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			this.decoBlockStainedFlowerPot[index] = new DecoBlockStainedFlowerPot(this.decoBlockStainedFlowerPotID + index, DecoUtilsStrings.COLOUR_TAGS[index], index);
			this.decoItemStainedFlowerPot[index] = new DecoItemStainedFlowerPot(this.decoItemStainedFlowerPotID + index, this.decoBlockStainedFlowerPotID + index, DecoUtilsStrings.COLOUR_TAGS[index]);
		}
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks() 
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			DecoAddonManager.register(this.decoBlockStainedFlowerPot[index], DecoUtilsStrings.COLOUR_NAMES[index] + " Stained Flower Pot");
			DecoAddonManager.register(this.decoItemStainedFlowerPot[index], DecoUtilsStrings.COLOUR_NAMES[index] + " Stained Flower Pot");
		}
	}
	
	public void addRecipes() {}
	
	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties()
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			this.decoBlockStainedFlowerPot[index].SetPicksEffectiveOn(true);
		}	
	}
}
