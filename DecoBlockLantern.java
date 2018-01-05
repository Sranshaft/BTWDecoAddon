package net.minecraft.src;

public class DecoBlockLantern extends Block implements FCIBlock
{
	private Icon m_IconSide;
	private Icon m_IconTop;
	private String m_Tag;
	private boolean m_IsAnimated;
	
	public DecoBlockLantern(int id, Material material, float hardness)
	{
		this(id, material, "", hardness, 1.0F, false);
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
	
	public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
	{
		setBlockBoundsBasedOnState(var1, var2, var3,var4);
		return SetFacingInMetadata(var9, var5);
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess Access, int X, int Y, int Z)
	{
		switch (GetFacing(Access, X, Y, Z))
		{
			default:
			case 0: setBlockBounds(.3125F,.5F,.3125F,.6875F,1F,.6875F); break;
			case 1: setBlockBounds(.3125F,0F,.3125F,.6875F,.5F,.6875F); break;
			case 2: setBlockBounds(.3125F,0F,.625F,.6875F,.5F,1F);   break;
			case 3: setBlockBounds(.3125F,0F,0F,.6875F,.5F,.375F);   break;
			case 4: setBlockBounds(.625F,0F,.3125F,1F,.5F,.6875F);   break;
			case 5: setBlockBounds(0F,0F,.3125F,.375F,.5F,.6875F);   break;
		}
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World CurrentWorld, int X, int Y, int Z)
	{
		switch (GetFacing(CurrentWorld, X, Y, Z))
		{
			default:
			case 0: return AxisAlignedBB.getAABBPool().getAABB(X + .3125D, Y + .5D,	Z + .3125D,	X + .6875D,	Y + 1D,	Z + .6875D);
			case 1: return AxisAlignedBB.getAABBPool().getAABB(X + .3125D, Y, Z + .3125D, X + .6875D, Y + .5D, Z + .6875D);
			case 2: return AxisAlignedBB.getAABBPool().getAABB(X + .3125D, Y, Z + .625D, X + .6875D, Y + .5D, Z + 1D);
			case 3: return AxisAlignedBB.getAABBPool().getAABB(X + .3125D, Y, Z, X+.6875D, Y + .5D,	Z + .375D);
			case 4: return AxisAlignedBB.getAABBPool().getAABB(X + .625D, Y, Z + .3125D, X + 1D, Y + .5D, Z + .6875D);
			case 5: return AxisAlignedBB.getAABBPool().getAABB(X, Y, Z + .3125D, X + .375D,	Y + .5D, Z + .6875D);
		}
	}
	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(.3125F, 0F, .3125F, .6875F, .5F, .6875F);
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public int GetFacing(IBlockAccess var1, int var2, int var3, int var4)
	{
		return var1.getBlockMetadata(var2, var3, var4);
	}
	
	public void SetFacing(World var1, int var2, int var3, int var4, int var5)
	{
		var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
	}
	
	public int GetFacingFromMetadata(int var1)
	{
		return var1;
	}
	
	public int SetFacingInMetadata(int var1, int var2)
	{
		return var2;
	}
	
	public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
	{
		return false;
	}
	
	public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
	{
		return false;
	}
	
	public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
	{
		return false;
	}
	
	public void RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5) { }
	
	public int RotateMetadataAroundJAxis(int var1, boolean var2)
	{
		return 0;
	}
	
	public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
	{
		return false;
	}
	
	public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
	{
		return var5 == 0 && this.minY > 0.0D ? true : (var5 == 1 && this.maxY < 1.0D ? true : (var5 == 2 && this.minZ > 0.0D ? true : (var5 == 3 && this.maxZ < 1.0D ? true : (var5 == 4 && this.minX > 0.0D ? true : (var5 == 5 && this.maxX < 1.0D ? true : !var1.isBlockOpaqueCube(var2, var3, var4))))));
	}
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
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
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		return side < 2 ? this.m_IconTop : this.m_IconSide;
	}
}