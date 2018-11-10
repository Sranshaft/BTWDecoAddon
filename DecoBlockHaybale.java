package net.minecraft.src;

public class DecoBlockHaybale extends Block
{
	private Icon m_IconSide;
	private Icon m_IconTop;
	
	public DecoBlockHaybale(int id, Material material)
	{
		super(id, material);
		setUnlocalizedName("decoBlockHaybale");
		setHardness(0.5F);
		setResistance(0.2F);
		setStepSound(Block.soundGrassFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public boolean isOpaqueCube()
	{
		return true;
	}
	
	public boolean renderAsNormalBlock()
	{
		return true;
	}
	
	public boolean canDropFromExplosion(Explosion var1)
	{
		return false;
	}
	
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion exp)
	{
		float v = 1.0F;

		if (exp != null)
		{
			v = 1.0F / exp.explosionSize;
		}

		for (int i=0;i<8;++i)
		{
			if (world.rand.nextFloat()<=v)
				dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.wheat));
		}
	}
	
	public int GetFacing(IBlockAccess access, int x, int y, int z)
	{
		return access.getBlockMetadata(x,y,z);
	}
	
	public void SetFacing(World world, int x, int y, int z, int facing)
	{
		world.setBlockMetadataWithNotify(x,y,z,facing);
	}
	
	public int GetFacingFromMetadata(int metadata)
	{
		return metadata;
	}
	
	public int SetFacingInMetadata(int var1, int var2)
	{
		return var2;
	}
	
	public boolean CanRotateOnTurntable(IBlockAccess access, int x, int y, int z)
	{
		return access.getBlockMetadata(x,y,z)!=0;
	}
	
	public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess access, int x, int y, int z)
	{
		return true;
	}
	public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess access, int x, int y, int z)
	{
		return true;
	}
	
	public boolean RotateAroundJAxis(World world, int x, int y, int z, boolean var5)
	{
		return FCUtilsMisc.StandardRotateAroundJ(this, world, x, y, z, var5);
	}
	
	public int RotateMetadataAroundJAxis(int var1, boolean var2)
	{
		return FCUtilsMisc.StandardRotateMetadataAroundJ(this, var1, var2);
	}
	
	public boolean ToggleFacing(World world, int x, int y, int z, boolean var5)
	{
		this.RotateAroundJAxis(world, x, y, z, var5);
		return true;
	}
	
	public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
	{
		if (var5 < 2) return 0;
		else if (var5 < 4) return 1;
		else return 2;
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
    {
        super.registerIcons(register);
        this.m_IconSide = register.registerIcon("decoBlockHaybale_side");
        this.m_IconTop = register.registerIcon("decoBlockHaybale_top");
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