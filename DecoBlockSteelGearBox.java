package net.minecraft.src;

public class DecoBlockSteelGearBox extends FCBlockGearBox
{
	private Icon m_IconInput;
    private Icon m_IconOutput;
	
	protected DecoBlockSteelGearBox(int id) 
	{
		super(id);
		this.setUnlocalizedName("decoBlockSteelGearBox");
		this.setHardness(FCBetterThanWolves.fcSoulforgedSteelBlock.blockHardness);
		this.setResistance(FCBetterThanWolves.fcSoulforgedSteelBlock.blockResistance);
		this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setStepSound(Block.soundMetalFootstep);
	}
	
	public void Overpower(World var1, int var2, int var3, int var4) { }
	
	public void BreakGearBox(World world, int x, int y, int z) { }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        super.registerIcons(register);
        this.m_IconInput = register.registerIcon("decoBlockSteelGearBox_input");
        this.m_IconOutput = register.registerIcon("decoBlockSteelGearBox_output");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return side == 3 ? this.m_IconInput : this.blockIcon;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess bAccess, int x, int y, int z, int side)
    {
        int direction = this.GetFacing(bAccess, x, y, z);

        if (side == direction)
        {
            return this.m_IconInput;
        }
        else
        {
            FCUtilsBlockPos var7 = new FCUtilsBlockPos(x, y, z);
            var7.AddFacingAsOffset(side);
            return bAccess.getBlockId(var7.i, var7.j, var7.k) == DecoModuleMechanical.decoBlockSteelAxleID && ((DecoBlockSteelAxle)DecoModuleMechanical.decoBlockSteelAxle).IsAxleOrientedTowardsFacing(bAccess, var7.i, var7.j, var7.k, side) ? this.m_IconOutput : this.blockIcon;
        }
    }

}
