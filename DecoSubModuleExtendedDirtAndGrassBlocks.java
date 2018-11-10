package net.minecraft.src;

public class DecoSubModuleExtendedDirtAndGrassBlocks implements DecoISubModule 
{
	public static Block decoBlockDirt;
	public static Block decoBlockFarmland;
	public static Block decoBlockGrass;
	
	public static Item decoItemHoeDiamond;
	public static Item decoItemHoeGold;
	public static Item decoItemHoeIron;
	
	public DecoSubModuleExtendedDirtAndGrassBlocks()
	{
		this.decoBlockDirt = new DecoBlockDirt(DecoAddonManager.replaceBlockID(Block.dirt));
		this.decoBlockFarmland = new DecoBlockFarmland(DecoAddonManager.replaceBlockID(Block.tilledField));
		this.decoBlockGrass = new DecoBlockGrass(DecoAddonManager.replaceBlockID(Block.grass));
		
		this.decoItemHoeDiamond = new DecoItemHoe(DecoAddonManager.replaceItemID(Item.hoeDiamond), EnumToolMaterial.EMERALD, "Diamond");
		this.decoItemHoeGold = new DecoItemHoe(DecoAddonManager.replaceItemID(Item.hoeGold), EnumToolMaterial.GOLD, "Gold");
		this.decoItemHoeIron = new DecoItemHoe(DecoAddonManager.replaceItemID(Item.hoeIron), EnumToolMaterial.IRON, "Iron");
	}
	
	@Override
	public void registerBlocks() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRecipes() {
		
	}

	@Override
	public void changeVanillaItems() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupCustomToolProperties() {
		// TODO Auto-generated method stub

	}

}
