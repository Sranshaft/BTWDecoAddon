package net.minecraft.src;

public class DecoSubModuleExtendedDirtAndGrassBlocks implements DecoISubModule 
{
	public static Block decoBlockDirt;
	public static Block decoBlockDirtCoarseSlab;
	public static Block decoBlockDirtCoarseSlabTop;
	public static Block decoBlockDirtDriedSlab;
	public static Block decoBlockDirtDriedSlabTop;
	public static Block decoBlockFarmland;
	public static Block decoBlockFarmlandFertilized;
	public static Block decoBlockGrass;
	public static Block decoBlockGrassPath;
	
	public static Item decoItemHoeDiamond;
	public static Item decoItemHoeGold;
	public static Item decoItemHoeIron;
	
	public static Item decoItemSpadeDiamond;
	public static Item decoItemSpadeGold;
	public static Item decoItemSpadeIron;
	
	public static final int decoBlockDirtCoarseSlabID = DecoAddonManager.getBlockID("decoBlockDirtCoarseSlabID");
	public static final int decoBlockDirtCoarseSlabTopID = DecoAddonManager.getBlockID("decoBlockDirtCoarseSlabTopID");
	public static final int decoBlockDirtDriedSlabID = DecoAddonManager.getBlockID("decoBlockDirtDriedSlabID");
	public static final int decoBlockDirtDriedSlabTopID = DecoAddonManager.getBlockID("decoBlockDirtDriedSlabTopID");
	public static final int decoBlockGrassPathID = DecoAddonManager.getBlockID("decoBlockGrassPathID");
	
	public DecoSubModuleExtendedDirtAndGrassBlocks()
	{
		FCAddOnHandler.LogMessage("[INFO]: Loading submodule: Extended Dirt and Grass Blocks");
		
		// REPLACE VANILLA BLOCKS
		this.decoBlockDirt = new DecoBlockDirt(DecoAddonManager.replaceBlockID(Block.dirt));
		this.decoBlockFarmland = new DecoBlockFarmland(DecoAddonManager.replaceBlockID(Block.tilledField));
		this.decoBlockGrass = new DecoBlockGrass(DecoAddonManager.replaceBlockID(Block.grass));
		
		// REPLACE BTW BLOCKS
		this.decoBlockFarmlandFertilized = new DecoBlockFarmlandFertilized(DecoAddonManager.replaceBlockID(FCBetterThanWolves.fcBlockFarmlandFertilized));
		
		// ADD DECO BLOCKS
		this.decoBlockDirtCoarseSlab = new DecoBlockSlab(this.decoBlockDirtCoarseSlabID, this.decoBlockDirt, false, this.decoBlockDirtCoarseSlabID, this.decoBlockDirtCoarseSlabTopID, 1);
		this.decoBlockDirtCoarseSlabTop = new DecoBlockSlab(this.decoBlockDirtCoarseSlabTopID, this.decoBlockDirt, true, this.decoBlockDirtCoarseSlabID, this.decoBlockDirtCoarseSlabTopID, 1);
		
		this.decoBlockDirtDriedSlab = new DecoBlockSlab(this.decoBlockDirtDriedSlabID, this.decoBlockDirt, false, this.decoBlockDirtDriedSlabID, this.decoBlockDirtDriedSlabTopID, 2);
		this.decoBlockDirtDriedSlabTop = new DecoBlockSlab(this.decoBlockDirtDriedSlabTopID, this.decoBlockDirt, true, this.decoBlockDirtDriedSlabID, this.decoBlockDirtDriedSlabTopID, 2);
		
		this.decoBlockGrassPath = new DecoBlockGrassPath(this.decoBlockGrassPathID);
		
		this.registerBlocks();
		this.addRecipes();
		this.changeVanillaItems();
		this.setupCustomToolProperties();
	}
	
	@Override
	public void registerBlocks() 
	{
		DecoAddonManager.register(this.decoBlockDirtCoarseSlab, "Coarse Dirt Slab");
		DecoAddonManager.register(this.decoBlockDirtCoarseSlabTop, "Coarse Dirt Slab");
		
		DecoAddonManager.register(this.decoBlockDirtDriedSlab, "Dried Dirt Slab");
		DecoAddonManager.register(this.decoBlockDirtDriedSlabTop, "Dried Dirt Slab");	
	}

	@Override
	public void addRecipes() 
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockDirt, 4, 1), 
				new Object[] { "X#", "#X", 'X', Block.gravel, '#', Block.dirt });
		FCRecipes.AddVanillaRecipe(new ItemStack(this.decoBlockDirt, 4, 1), 
				new Object[] { "#X", "X#", 'X', Block.gravel, '#', Block.dirt });	
	}

	@Override
	public void changeVanillaItems() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupCustomToolProperties() 
	{
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockDirt);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockDirtCoarseSlab);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockDirtCoarseSlabTop);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockDirtDriedSlab);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockDirtDriedSlabTop);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockFarmland);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockFarmlandFertilized);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockGrass);
		ItemSpade.SetAllShovelsToBeEffectiveVsBlock(this.decoBlockGrassPath);
	}
}
