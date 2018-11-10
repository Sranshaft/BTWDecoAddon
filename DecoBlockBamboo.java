package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockBamboo extends Block implements DecoIBlock, DecoILiving, DecoIPlant
{
	private Icon m_IconSide, m_IconTop, m_IconLeaves;
	private static int m_GrowthChance = 50;
	private static int m_MaxHeight = 30;

	public DecoBlockBamboo(int id)
	{
		super(id, Material.wood);

		this.setUnlocalizedName("decoBlockBamboo");
		this.setHardness(1.5F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);

		this.setTickRandomly(true);
		this.setBlockBounds(0.35F, 0.0F, 0.35F, 0.65F, 1.0F, 0.65F);
	}

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public int idPicked(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y, z);
	}
	
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		float growthMultiplier = 0.025F * FCUtilsMisc.GetBlockGrowthMultiplier(world, x, y - 1, z, this);
		
		if (random.nextFloat() <= growthMultiplier && canThisPlantGrowOnThisBlockID(world, x, y - 1, z, true))
    		this.growPlant(world, x, y, z, random);
    	
        super.updateTick(world, x, y, z, random);
	}
	
	private void growPlant(World world, int x, int y, int z, Random random)
	{
    	int currentHeight;
		for (currentHeight = 1; !world.isAirBlock(x, y + currentHeight, z); currentHeight++) { ; }
		
		if (currentHeight <= this.m_MaxHeight)
			world.setBlockWithNotify(x, y + 1, z, this.blockID);
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
	{
		if (!this.canBlockStay(world, x, y, z)) world.destroyBlock(x, y, z, true);
		
		super.onNeighborBlockChange(world, x, y, z, neighbourBlockID);
	}
	
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && canThisPlantGrowOnThisBlockID(world, x, y - 1, z, true);
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canThisPlantGrowOnThisBlockID(world, x, y - 1, z, false);
	}

	protected boolean canThisPlantGrowOnThisBlockID(World world, int x, int y, int z, boolean flag)
	{
		int id = world.getBlockId(x, y, z);

		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.sand.blockID || id == FCBetterThanWolves.fcPlanter.blockID || (id == blockID && flag);
	}

	public boolean isBlockReplaceable(World world, int x, int y, int z) 
	{
		return false;
	}

	public boolean isShearable(ItemStack item, World world, int x, int y, int z) 
	{
		return false;
	}
	
	public ItemStack onSheared(ItemStack item, World world, int x, int y, int z, int fortune) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isBlockFoliage(World world, int x, int y, int z) 
	{
		return true;
	}

	public boolean isOpaqueCube() 
	{
		return false;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}

	public Icon getIcon(int side, int metadata) 
	{
		if (side < 2) return this.m_IconTop;
		else return this.m_IconSide;
	}

	public void registerIcons(IconRegister register)
	{
		this.m_IconLeaves = register.registerIcon("decoBlockBamboo_leaves");
		this.m_IconSide = register.registerIcon("decoBlockBamboo_side");
		this.m_IconTop = register.registerIcon("decoBlockBamboo_top");
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + 0.35F), (double) ((float) y), 			(double) ((float) z + 0.35F), 
											       (double) ((float) x + 0.65F), (double) ((float) y + 1.0F),   (double) ((float) z + 0.65F));
	}

	public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
	{
		render.setRenderBounds(0.35D, 0.0D, 0.35D, 0.65D, 1.0D, 0.65D);
		render.renderStandardBlock(this, x, y, z);

		DecoUtilsRender.drawCrossedSquares(this, x, y, z, render, 0.5D, 1.0D, 0, this.m_IconLeaves);

		return true;
	}
}
