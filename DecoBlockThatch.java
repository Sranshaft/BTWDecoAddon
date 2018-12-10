package net.minecraft.src;

public class DecoBlockThatch extends Block
{
	private Icon m_IconSide;
	private Icon m_IconTop;
	
	public DecoBlockThatch(int id)
	{
		super(id, Material.grass);
		
		this.setUnlocalizedName("decoBlockThatch");
		this.setHardness(0.5F);
		this.setResistance(0.2F);
		this.setStepSound(Block.soundGrassFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion exp)
	{
		float v = 1.0F;

		if (exp != null)
		{
			v = 1.0F / exp.explosionSize;
		}

		for (int i = 0; i < 3; i++)
		{
			if (world.rand.nextFloat() <= v)
				dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.wheat));
		}
	}
	
	public int GetFacing(IBlockAccess access, int x, int y, int z)
	{
		return access.getBlockMetadata(x, y, z);
	}
	
	public void SetFacing(World world, int x, int y, int z, int metadata)
	{
		world.setBlockMetadataWithNotify(x, y, z, metadata);
	}
	
	public int GetFacingFromMetadata(int metadata)
	{
		return metadata;
	}
	
	public int SetFacingInMetadata(int side, int metadata)
	{
		return metadata;
	}
	
	public boolean CanRotateOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return bAccess.getBlockMetadata(x, y, z) != 0;
	}
	
	public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return true;
	}
	
	public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess bAccess, int x, int y, int z)
	{
		return true;
	}
	
	public boolean RotateAroundJAxis(World world, int x, int y, int z, boolean useOpposite)
	{
		return FCUtilsMisc.StandardRotateAroundJ(this, world, x, y, z, useOpposite);
	}
	
	public int RotateMetadataAroundJAxis(int metadata, boolean useOpposite)
	{
		return FCUtilsMisc.StandardRotateMetadataAroundJ(this, metadata, useOpposite);
	}
	
	public boolean ToggleFacing(World world, int x, int y, int z, boolean useOpposite)
	{
		this.RotateAroundJAxis(world, x, y, z, useOpposite);
		return true;
	}
	
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if (side < 2) return 0;
		else if (side < 4) return 1;
		else return 2;
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        super.registerIcons(register);

        this.m_IconSide = register.registerIcon("decoBlockThatch_side");
        this.m_IconTop = register.registerIcon("decoBlockThatch_top");
        
        this.blockIcon = this.m_IconSide;
    }

	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
    {
		switch (metadata)
        {
	        case 0:
				return (side < 2) ? this.m_IconTop : this.m_IconSide;
			case 1:
				return (side < 4 && side > 1) ? this.m_IconTop : this.m_IconSide;
			default:
				return (side > 3) ? this.m_IconTop : this.m_IconSide;
        }
    }
	
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		int direction = GetFacing(render.blockAccess, x, y, z);
		switch(direction)
		{
			case 1:
				render.SetUvRotateNorth(1);
				render.SetUvRotateSouth(1);
				break;
			case 2:
				render.SetUvRotateTop(1);
				render.SetUvRotateBottom(1);
				render.SetUvRotateWest(1);
				render.SetUvRotateEast(1);
			default:
		}
		
		render.setRenderBounds(0D, 0D, 0D, 1D, 1D, 1D);
		render.renderStandardBlock(this, x, y, z);
		render.ClearUvRotation();
		
		return true;
	}
}
