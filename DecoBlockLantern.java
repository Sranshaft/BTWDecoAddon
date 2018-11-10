package net.minecraft.src;

public class DecoBlockLantern extends Block
{
	private Icon m_IconSide;
	private Icon m_IconTop;
	private String m_Tag;
	private boolean m_IsAnimated;
	
	public DecoBlockLantern(int id, Material material, float hardness)
	{
		this(id, material, "", hardness, 10.0F, false);
	}
	
	public DecoBlockLantern(int id, Material material, String tag, float hardness, float lightValue, boolean animate)
	{
		super(id, material);
		
		setUnlocalizedName("decoBlockLantern" + tag);
		setHardness(hardness);
		setLightValue(lightValue);
		setStepSound(Block.soundWoodFootstep);
		setCreativeTab(CreativeTabs.tabDecorations);
		
		this.m_IsAnimated = animate;
	}
	
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int facing)
	{
		setBlockBoundsBasedOnState(world, x, y,z);
		return SetFacingInMetadata(facing, side);
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
	{
		switch (GetFacing(bAccess, x, y, z))
		{
			default:
			case 0: setBlockBounds(0.3125F, 0.5F, 0.3125F, 0.6875F, 1.0F, 0.6875F); break;
			case 1: setBlockBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.5F, 0.6875F); break;
			case 2: setBlockBounds(0.3125F, 0.0F, 0.625F, 0.6875F, 0.5F, 1.0F);   break;
			case 3: setBlockBounds(0.3125F, 0.0F, 0.0F, 0.6875F, 0.5F, 0.375F);   break;
			case 4: setBlockBounds(0.625F, 0.0F, 0.3125F, 1.0F, 0.5F, 0.6875F);   break;
			case 5: setBlockBounds(0.0F, 0.0F, 0.3125F, 0.375F, 0.5F, 0.6875F);   break;
		}
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int Z)
	{
		switch (GetFacing(world, x, y, Z))
		{
			default:
			case 0: return AxisAlignedBB.getAABBPool().getAABB(x + 0.3125D, y + 0.5D,	Z + 0.3125D,	x + 0.6875D,	y + 1.0D,	Z + 0.6875D);
			case 1: return AxisAlignedBB.getAABBPool().getAABB(x + 0.3125D, y, Z + 0.3125D, x + 0.6875D, y + 0.5D, Z + 0.6875D);
			case 2: return AxisAlignedBB.getAABBPool().getAABB(x + 0.3125D, y, Z + 0.625D, x + 0.6875D, y + 0.5D, Z + 1D);
			case 3: return AxisAlignedBB.getAABBPool().getAABB(x + 0.3125D, y, Z, x + 0.6875D, y + 0.5D,	Z + 0.375D);
			case 4: return AxisAlignedBB.getAABBPool().getAABB(x + 0.625D, y, Z + 0.3125D, x + 1D, y + 0.5D, Z + 0.6875D);
			case 5: return AxisAlignedBB.getAABBPool().getAABB(x, y, Z + 0.3125D, x + 0.375D,	y + 0.5D, Z + 0.6875D);
		}
	}
	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(0.3125F, 0F, 0.3125F, 0.6875F, 0.5F, 0.6875F);
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public int GetFacing(IBlockAccess bAccess, int x, int y, int z)
	{
		return bAccess.getBlockMetadata(x, y, z);
	}
	
	public void SetFacing(World world, int x, int y, int z, int metadata)
	{
		world.setBlockMetadataWithNotify(x, y, z, metadata);
	}
	
	public int GetFacingFromMetadata(int metadata)
	{
		return metadata;
	}
	
	public int SetFacingInMetadata(int facing, int metadata)
	{
		return metadata;
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
	
	public boolean RotateAroundJAxis(World world, int x, int y, int z, boolean var5) 
	{ 
		return FCUtilsMisc.StandardRotateAroundJ(this, world, x, y, z, var5);
	}
	
	public int RotateMetadataAroundJAxis(int metadata, boolean var2)
	{
		return 0;
	}
	
	public boolean ToggleFacing(World world, int x, int y, int z, boolean canToggle)
	{
		return false;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
	{
		return side == 0 && this.minY > 0.0D ? true : (side == 1 && this.maxY < 10.0D ? true : 
			  (side == 2 && this.minZ > 0.0D ? true : (side == 3 && this.maxZ < 10.0D ? true : 
		      (side == 4 && this.minX > 0.0D ? true : (side == 5 && this.maxX < 10.0D ? true : 
		    	  !bAccess.isBlockOpaqueCube(x, y, z))))));
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister0. This
     * is the only chance you get to register icons0.
     */
	public void registerIcons(IconRegister register)
	{
		if (this.m_Tag != null)
		{
			this.m_IconSide = register.registerIcon("decoBlockLantern" + this.m_Tag + "_side" + (this.m_IsAnimated ? "_animated" : ""));
			this.m_IconTop = register.registerIcon("decoBlockLantern" + this.m_Tag + "_top");
		}
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture0. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		return side < 2 ? this.m_IconTop : this.m_IconSide;
	}
}