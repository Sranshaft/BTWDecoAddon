package net.minecraft.src;

import java.util.Random;

public class DecoBlockSmoker extends Block
{
	private Icon m_IconTop, m_IconBottom, m_IconSide;
	
	public DecoBlockSmoker(int id)
	{
		super(id, Material.iron);
		this.setUnlocalizedName("decoBlockSmoker");
		this.setHardness(Block.blockIron.blockHardness);
		this.setResistance(Block.blockIron.blockResistance);
		this.setStepSound(Block.soundMetalFootstep);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        
        this.setLightValue(2);
        this.setTickRandomly(true);
        
        ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this);
	}
	
	/**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void OnPlayerWalksOnBlock(World world, int x, int y, int z, Entity entity)
    {
    	entity.setFire(1);
    }
	
	/**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isBlockOpaqueCube(x, y + 1, z))
        {
        	for (int index = 0; index <= 5; index++)
	        {
        		double randX = random.nextDouble();
            	double randY = 1.25D + random.nextDouble();
            	double randZ = random.nextDouble();
            	
	        	world.spawnParticle("smoke", (double)(x + randX), (double)(y + randY), (double)(z + randZ), 0.0D, 0.25D, 0.0D);
	        	world.spawnParticle("largesmoke", (double)(x + randX), (double)(y + randY), (double)(z + randZ), 0.0D, 0.1D, 0.0D);
	        }
	        
	        int indexY = y + 1;
	        int hasSide = 0;
	        
	        while (indexY < 255)
	        {
	        	for (int indexX = x - 1; indexX <= x + 1; indexX++)
		        {	
		        	for (int indexZ = z - 1; indexZ <= z + 1; indexZ++)
		        	{
		        		if (!world.isAirBlock(indexX, indexY, indexZ) && world.isAirBlock(x, indexY, z))
		        			hasSide++;
		        	}
		        }
		        
		        if (hasSide < 3)
		        {
		        	for (int index = 0; index <= 25; index++)
			        {
		        		double randX = random.nextDouble();
		        		double randY = random.nextDouble();
		        		double randZ = random.nextDouble();
		            	
			        	world.spawnParticle("smoke", (double)(x + randX), (double)(indexY), (double)(z + randZ), 0.0D, 0.2D + randY, 0.025D);
			        	world.spawnParticle("largesmoke", (double)(x + randX), (double)(indexY), (double)(z + randZ), 0.0D, 0.05D + randY, 0.05D);
			        }
		        	break;
		        }
		        else
		        {
		        	hasSide = 0;
		        	indexY++;
		        	continue;
		        }
	        }
        }
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		switch (side)
		{
			case 0: return this.m_IconBottom;
			case 1: return this.m_IconTop;
			default: return this.m_IconSide;
		}
	}
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		this.m_IconBottom = register.registerIcon("decoBlockSmoker_bottom");
		this.m_IconSide = register.registerIcon("decoBlockSmoker_side");
		this.m_IconTop = register.registerIcon("decoBlockSmoker_top");
	}
}
