package net.minecraft.src;

public class DecoModuleEquipment implements DecoIModule
{
	public static DecoSubModuleToolMallet decoSubModuleToolMallets;
	public static DecoSubModuleToolScythe decoSubModuleToolScythes;
	public static DecoSubModuleToolSling decoSubModuleToolSling;
	public static DecoSubModuleToolSpyglass decoSubModuleToolSpyglass;
	
	public static Item decoItemStonecuttersHammerIron;
	public static final int decoItemStonecuttersHammerIronID = DecoAddonManager.getBlockID("decoItemStonecuttersHammerIronID");
	
	public DecoModuleEquipment()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: Equipment");
		
		if (DecoAddonManager.getConfigOption("enableToolMallets"))
			this.decoSubModuleToolMallets = new DecoSubModuleToolMallet();
		
		if (DecoAddonManager.getConfigOption("enableToolScythes"))
			this.decoSubModuleToolScythes = new DecoSubModuleToolScythe();
		
		if (DecoAddonManager.getConfigOption("enableToolSling"))
			this.decoSubModuleToolSling = new DecoSubModuleToolSling();
		
		if (DecoAddonManager.getConfigOption("enableToolSpyglass"))
			this.decoSubModuleToolSpyglass = new DecoSubModuleToolSpyglass();
		
		this.decoItemStonecuttersHammerIron = new DecoItemStonecuttersHammer(this.decoItemStonecuttersHammerIronID);
	}
	
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoItemStonecuttersHammerIron, "Iron Stonecutter\'s Hammer");
	}
	
	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoItemStonecuttersHammerIron), 
				new Object[] { "X", "S", 'X', Item.ingotIron, 'S', Item.stick });
	}
	
	public void changeVanillaItems() {}
	public void setupCustomToolProperties() {}
}
