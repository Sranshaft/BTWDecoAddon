package net.minecraft.src;

public class DecoItemStonecuttersHammer extends FCItemTool
{
	private static Block[] blocksEffectiveAgainst = new Block[0];
	
	public DecoItemStonecuttersHammer(int id)
	{
		super(id, 1, EnumToolMaterial.IRON);
		
		this.setUnlocalizedName("decoItemStonecuttersHammer");
		this.setMaxDamage(128);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
    
    public boolean canHarvestBlock(ItemStack itemStack, World world, Block block, int x, int y, int z)
    {
        return block instanceof DecoBlockStoneBrick ? this.toolMaterial.getHarvestLevel() >= block.GetHarvestToolLevel(world, x, y, z) : false;
    }
    
    protected boolean IsToolTypeEffecientVsBlockType(Block block)
    {
        return block instanceof DecoBlockStoneBrick;
    }

    public boolean IsEffecientVsBlock(ItemStack itemStack, World world, Block block, int x, int y, int z)
    {
        if (block.GetIsProblemToRemove(world, x, y, z)) return false;
        else
        {
            int harvestLevel = this.toolMaterial.getHarvestLevel();
            int toolLevel = block.GetEffecientToolLevel(world, x, y, z);
            return toolLevel > harvestLevel ? false : block instanceof DecoBlockStoneBrick;
        }
    }
    
    public int getItemEnchantability()
	{
		return 0;
	}
    
    public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon("decoItemStonecuttersHammer_iron");
    }
}
