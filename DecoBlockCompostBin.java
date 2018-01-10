package net.minecraft.src;

import java.util.Random;

public class DecoBlockCompostBin extends BlockContainer implements FCIBlockMechanical, FCIBlock
{
	private static int m_TickRate = 10;
	private Icon[] m_IconBySideArray = new Icon[7];
	
	public static int currentState = 0;
	
	public DecoBlockCompostBin(int id) 
	{
		super(id, Material.wood);
		
		this.setUnlocalizedName("decoBlockCompostBin");
		this.setHardness(1.0F);
        this.setTickRandomly(true);
        this.setStepSound(soundWoodFootstep);
        this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	/**
     * How many world ticks before ticking
     */
    public int tickRate(World world)
    {
        return m_TickRate;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate(world));
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            DecoTileEntityCompostBin entity = (DecoTileEntityCompostBin)world.getBlockTileEntity(x, y, z);

            if (player instanceof EntityPlayerMP)
            {
                DecoContainerCompostBin container = new DecoContainerCompostBin(player.inventory, entity);
                DecoUtilsPacketHandler.ServerOpenCustomInterface((EntityPlayerMP)player, container, DecoModuleMechanical.decoCompostBinContainerID);
            }
        }

        return true;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World world, int x, int y, int z, int neighbourID, int flags)
    {
        FCUtilsInventory.EjectInventoryContents(world, x, y, z, (IInventory)world.getBlockTileEntity(x, y, z));
        super.breakBlock(world, x, y, z, neighbourID, flags);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World world)
    {
        return new DecoTileEntityCompostBin();
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        boolean var6 = this.IsInputtingMechanicalPower(world, x, y, z);
        boolean var7 = this.IsBlockMechanicalOn(world, x, y, z);

        if (var7 != var6)
        {
            this.SetMechanicalOn(world, x, y, z, var6);
        }
    }

    public void RandomUpdateTick(World world, int x, int y, int z, Random random)
    {
        if (!this.IsCurrentStateValid(world, x, y, z) && !world.IsUpdateScheduledForBlock(x, y, z, this.blockID))
        {
            world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate(world));
        }
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID)  
    {
        if (!this.IsCurrentStateValid(world, x, y, z))
        {
            world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate(world));
        }
    }
    
    public boolean IsCurrentStateValid(World world, int x, int y, int z)
    {
        boolean var5 = this.IsInputtingMechanicalPower(world, x, y, z);
        boolean var6 = this.IsBlockMechanicalOn(world, x, y, z);
        return var6 == var5;
    }

	public int GetFacing(IBlockAccess bAccess, int x, int y, int z) 
	{
		return 0;
	}

	public void SetFacing(World world, int x, int y, int z, int side) {}

	public int GetFacingFromMetadata(int side) 
	{
		return 0;
	}

	public int SetFacingInMetadata(int side, int metadata) 
	{
		return side;
	}

	public boolean CanRotateOnTurntable(IBlockAccess bAccess, int x, int y, int z) 
	{
		return false;
	}

	public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess bAccess, int x, int y, int z) 
	{
		return false;
	}

	public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess bAccess, int x, int y, int z) 
	{
		return false;
	}

	public void RotateAroundJAxis(World world, int x, int y, int z, boolean isRotated) {}

	public int RotateMetadataAroundJAxis(int metatdata, boolean isRotated) 
	{
		return 0;
	}

	public boolean ToggleFacing(World world, int x, int y, int z, boolean isRotated)
    {
        return false;
    }

    public boolean CanOutputMechanicalPower()
    {
        return false;
    }

    public boolean CanInputMechanicalPower()
    {
        return true;
    }

    public boolean IsInputtingMechanicalPower(World world, int x, int y, int z)
    {
    	return FCUtilsMechPower.IsBlockPoweredByAxleToSide(world, x, y, z, 0);
    }

    public boolean CanInputAxlePowerToFacing(World world, int x, int y, int z, int side)
    {
        return side == 0;
    }

    public boolean IsOutputtingMechanicalPower(World world, int x, int y, int z)
    {
        return false;
    }

    public void Overpower(World world, int x, int y, int z)
    {
        this.BreakCompostBin(world, x, y, z);
    }

    public boolean IsBlockMechanicalOn(IBlockAccess bAccess, int x, int y, int z)
    {
    	return (bAccess.getBlockMetadata(x, y, z) & 1) > 0;
    }

    public void SetMechanicalOn(World world, int x, int y, int z, boolean shouldUpdate)
    {
        int var6 = world.getBlockMetadata(x, y, z) & -2;

        if (shouldUpdate)
            var6 |= 1;
        
        world.setBlockMetadataWithNotify(x, y, z, var6);
    }

    public int GetCurrentCompostingType(IBlockAccess bAccess, int x, int y, int z)
    {
        return this.GetCurrentCompostingTypeFromMetadata(bAccess.getBlockMetadata(x, y, z));
    }

    public void SetCurrentCompostingType(World world, int x, int y, int z, int var5)
    {
        int var6 = world.getBlockMetadata(x, y, z) & -7;
        var6 |= var5 << 1;
        world.setBlockMetadataWithClient(x, y, z, var6);
    }

    public int GetCurrentCompostingTypeFromMetadata(int var1)
    {
        return (var1 & 6) >> 1;
    }

    private void BreakCompostBin(World world, int x, int y, int z)
    {
        for (int index = 0; index < 8; ++index)
        {
            FCUtilsItem.EjectSingleItemWithRandomOffset(world, x, y, z, Block.wood.blockID, 0);
        }

        world.playAuxSFX(2235, x, y, z, 0);
        world.setBlockWithNotify(x, y, z, 0);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("wood");
        this.m_IconBySideArray[0] = register.registerIcon("decoBlockCompostBin_bottom");
        this.m_IconBySideArray[1] = register.registerIcon("decoBlockCompostBin_top");
        this.m_IconBySideArray[2] = register.registerIcon("decoBlockCompostBin_side");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        switch (side)
        {
        	case 0:
        		return this.m_IconBySideArray[0];
        	case 1:
        		return this.m_IconBySideArray[1];
        	case 2:
        	case 3:
        	case 4:
        	case 5:
        		return this.m_IconBySideArray[2];
        	default:
        		return this.blockIcon;
        }
    }
    
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (this.IsBlockMechanicalOn(world, x, y, z) && this.currentState > 0)
        {
        	for (int index = 0; index < 5; ++index)
            {
                float var7 = (float)x + random.nextFloat();
                float var8 = (float)y + random.nextFloat() * 0.5F + 1.0F;
                float var9 = (float)z + random.nextFloat();
                world.spawnParticle("townaura", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
