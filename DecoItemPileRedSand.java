package net.minecraft.src;

public class DecoItemPileRedSand extends Item
{
	public DecoItemPileRedSand(int id)
    {
        super(id);
        this.setUnlocalizedName("decoItemPileRedSand");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 4;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return DecoSubModuleRedSandstone.decoBlockRedSandID;
    }
}
