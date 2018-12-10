package net.minecraft.src;

public class DecoBlockSteelAxle extends FCBlockAxle 
{
	public DecoBlockSteelAxle(int id)
	{
		super(id);
		this.setUnlocalizedName("decoBlockSteelAxle");
		this.setHardness(FCBetterThanWolves.fcSoulforgedSteelBlock.blockHardness);
		this.setResistance(FCBetterThanWolves.fcSoulforgedSteelBlock.blockResistance);
		this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setStepSound(Block.soundMetalFootstep);
	}
	
	protected void ValidatePowerLevel(World world, int x, int y, int z)
    {
        int powerLevel = this.GetPowerLevel(world, x, y, z);
        int alignment = this.GetAxisAlignment(world, x, y, z);
        int var7 = 0;
        int var8 = 0;
        int var9;

        for (var9 = 0; var9 < 2; ++var9)
        {
            int var10 = m_AxleFacingsForAlignment[alignment][var9];
            FCUtilsBlockPos var11 = new FCUtilsBlockPos(x, y, z, var10);
            int var12 = world.getBlockId(var11.i, var11.j, var11.k);

            if (var12 != 0)
            {
                Block var13 = Block.blocksList[var12];
                int var14 = var13.GetMechanicalPowerLevelProvidedToAxleAtFacing(world, var11.i, var11.j, var11.k, FCUtilsMisc.GetOppositeFacing(var10));

                if (var14 > var7)
                {
                    var7 = var14;
                }

                if (var14 > powerLevel)
                {
                    ++var8;
                }
            }
        }

        if (var8 >= 2)
        {
            this.BreakAxle(world, x, y, z);
        }
        else
        {
            if (var7 > powerLevel)
            {
                if (var7 == 1)
                {
                    this.BreakAxle(world, x, y, z);
                    return;
                }

                var9 = var7 - 1;
            }
            else
            {
                var9 = 0;
            }

            if (var9 != powerLevel)
            {
                this.SetPowerLevel(world, x, y, z, var9);
            }
        }
    }
	
	public void BreakAxle(World world, int x, int y, int z)
    {
        if (world.getBlockId(x, y, z) == this.blockID)
        {
        	world.playAuxSFX(2235, x, y, z, 0);
            world.setBlockWithNotify(x, y, z, 0);
        }
    }
	
	private void OverpowerBlockToFacing(World world, int x, int y, int z, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(x, y, z);
        var7.AddFacingAsOffset(var6);
        int var8 = world.getBlockId(var7.i, var7.j, var7.k);

        if (var8 != DecoModuleMechanical.decoBlockSteelAxleID && var8 != DecoModuleMechanical.decoBlockSteelAxlePowerSourceID)
        {
            if (Block.blocksList[var8] instanceof FCIBlockMechanical)
            {
                FCIBlockMechanical var10 = (FCIBlockMechanical)((FCIBlockMechanical)Block.blocksList[var8]);

                if (var10.CanInputAxlePowerToFacing(world, var7.i, var7.j, var7.k, FCUtilsMisc.GetOppositeFacing(var6)))
                {
                    var10.Overpower(world, var7.i, var7.j, var7.k);
                }
            }
        }
        else
        {
            int var9 = this.GetAxisAlignment(world, var7.i, var7.j, var7.k);

            if (var9 == var5)
            {
                this.OverpowerBlockToFacing(world, var7.i, var7.j, var7.k, var5, var6);
            }
        }
    }
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("decoBlockSteelAxle_end");
        this.m_IconSide = register.registerIcon("decoBlockSteelAxle_side");
        this.m_IconSideOn = register.registerIcon("decoBlockSteelAxle_side_on");
        this.m_IconSideOnOverpowered = register.registerIcon("decoBlockSteelAxle_side_on_fast");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return side != 2 && side != 3 ? this.m_IconSide : this.blockIcon;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess bAccess, int x, int y, int z, int side)
    {
        int var6 = this.GetAxisAlignment(bAccess, x, y, z);

        if (var6 == 0)
        {
            if (side >= 2)
            {
                return this.GetAxleSideTextureForOnState(this.m_bIsPowerOnForCurrentRender);
            }
        }
        else if (var6 == 1)
        {
            if (side != 2 && side != 3)
            {
                return this.GetAxleSideTextureForOnState(this.m_bIsPowerOnForCurrentRender);
            }
        }
        else if (side < 4)
        {
            return this.GetAxleSideTextureForOnState(this.m_bIsPowerOnForCurrentRender);
        }

        return this.blockIcon;
    }

    public Icon GetAxleSideTextureForOnState(boolean isPowered)
    {
        return isPowered ? this.m_IconSideOn : this.m_IconSide;
    }
    
    public boolean ClientCheckIfPowered(IBlockAccess bAccess, int x, int y, int z)
    {
        this.GetPowerLevel(bAccess, x, y, z);
        int var6 = this.GetAxisAlignment(bAccess, x, y, z);
        int var7 = 0;

        while (var7 < 2)
        {
            FCUtilsBlockPos var8 = new FCUtilsBlockPos(x, y, z);
            int var9 = m_AxleFacingsForAlignment[var6][var7];
            int var10 = 1;

            while (true)
            {
                if (var10 <= 3)
                {
                    var8.AddFacingAsOffset(var9);
                    int var11 = bAccess.getBlockId(var8.i, var8.j, var8.k);

                    if (var11 == this.blockID && this.GetAxisAlignment(bAccess, var8.i, var8.j, var8.k) == var6)
                    {
                        ++var10;
                        continue;
                    }

                    if (var11 == DecoModuleMechanical.decoBlockSteelAxlePowerSourceID && this.GetAxisAlignment(bAccess, var8.i, var8.j, var8.k) == var6)
                        return true;
                    
                    //if (var11 == DecoModuleMechanical.decoBlockSteelAxleID && ((DecoBlockSteelGearBox)DecoModuleMechanical.decoBlockSteelAxle).IsGearBoxOn(bAccess, var8.i, var8.j, var8.k))
                    //    return true;
                }

                ++var7;
                break;
            }
        }

        return false;
    }
}
