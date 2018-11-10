package net.minecraft.src;

public class DecoBlockFarmland extends FCBlockFarmland
{
	private Icon m_IconTop_wet;
    private Icon m_IconTop_dry;

    public DecoBlockFarmland(int id)
	{
		super(id);
		this.blockMaterial = Material.ground;
	}
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.m_IconTop_wet = par1IconRegister.registerIcon("farmland_wet");
        this.m_IconTop_dry = par1IconRegister.registerIcon("farmland_dry");
    }
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
    	if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
    		return par1 == 1 ? (par2 > 0 ? this.m_IconTop_wet : this.m_IconTop_dry) : DecoModuleTweaks.decoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt.getBlockTextureFromSide(par1);
    	else
    		return par1 == 1 ? (par2 > 0 ? this.m_IconTop_wet : this.m_IconTop_dry) : Block.dirt.getBlockTextureFromSide(par1);
    }
}
