package net.minecraft.src;

public class DecoModuleWorld implements DecoISubModule
{
	public static Block decoBlockCoral;
	public static DecoBlockFoliage decoBlockFoliage;
	public static Block decoBlockMud;
	public static Block decoBlockQuicksand;
	public static Block decoBlockWaterPlant;
	public static DecoBlockWildgrass decoBlockWildgrass;
	
	public static final int decoBlockBeehiveID = DecoAddonManager.getBlockID("decoBlockBeehiveID");
	public static final int decoBlockCoralID = DecoAddonManager.getBlockID("decoBlockCoralID");
	public static final int decoBlockFoliageID = DecoAddonManager.getBlockID("decoBlockFoliageID");
	public static final int decoBlockMudID = DecoAddonManager.getBlockID("decoBlockMudID");
	public static final int decoBlockQuicksandID = DecoAddonManager.getBlockID("decoBlockQuicksandID");
	public static final int decoBlockWaterPlantID = DecoAddonManager.getBlockID("decoBlockWaterPlantID");
	public static final int decoBlockWildgrassID = DecoAddonManager.getBlockID("decoBlockWildgrassID");
	
	public DecoModuleWorld()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading module: World");
		
		this.decoBlockCoral = new DecoBlockCoral(this.decoBlockCoralID);
		this.decoBlockFoliage = new DecoBlockFoliage(this.decoBlockFoliageID);
		this.decoBlockMud = new DecoBlockMud(this.decoBlockMudID);
		this.decoBlockQuicksand = new DecoBlockQuicksand(this.decoBlockQuicksandID);
		this.decoBlockWaterPlant = new DecoBlockWaterPlant(this.decoBlockWaterPlantID);
		this.decoBlockWildgrass = new DecoBlockWildgrass(this.decoBlockWildgrassID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	public void registerBlocks()
	{
		DecoAddonManager.register(this.decoBlockMud, "Mud Block");
		DecoAddonManager.register(this.decoBlockQuicksand, "Quicksand");
		DecoAddonManager.register(this.decoBlockWildgrass, "Wild Grass");
	}
	
	public void addRecipes() 
	{
		this.decoBlockMud.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(DecoSubModuleMudstoneAndBrick.decoBlockMudstoneID);
	}
	
	public void changeVanillaItems() {}
	
	public void setupCustomToolProperties()
	{
		this.decoBlockCoral.SetPicksEffectiveOn();
		this.decoBlockFoliage.SetAxesEffectiveOn();
		this.decoBlockMud.SetShovelsEffectiveOn();
		this.decoBlockQuicksand.SetShovelsEffectiveOn();
		this.decoBlockWaterPlant.SetAxesEffectiveOn();
		this.decoBlockWildgrass.SetAxesEffectiveOn();
	}
}