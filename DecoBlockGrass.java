
package net.minecraft.src;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;

public class DecoBlockGrass extends FCBlockGrass
{
	public static final String[] GRASS_TEXTURE_PATHS = new String[] { "grass_side", "decoBlockGrass_coarse", "decoBlockGrass_dried", "decoBlockGrass_rich" };
	public static final String[] SNOW_TEXTURE_PATHS = new String[] { "snow_side", "decoBlockSnow_coarse", "decoBlockSnow_dried", "decoBlockSnow_rich" };
	
	public static final int m_iGrassSpreadFromLightLevel = 11;
    public static final int m_iGrassSpreadToLightLevel = 11;
    public static final int m_iGrassSurviveMinimumLightLevel = 9;
    
    private boolean m_bTempHasSnowOnTop;
	private Icon iconGrassTop;
    private Icon iconGrassDriedTop;
	private Icon iconSnowSide;
    
	private Icon iconGrassDriedTopOverlay;
    private Icon iconGrassDriedSideOverlay;
    private Icon iconGrassSideOverlay;
    
    private Icon[] m_IconGrassSideByMetadataArray = new Icon[this.GRASS_TEXTURE_PATHS.length];
    private Icon[] m_IconSnowSideByMetadataArray = new Icon[this.GRASS_TEXTURE_PATHS.length];
	
	public DecoBlockGrass(int id)
	{
		super(id);
		
		this.setUnlocalizedName("grass");
		this.setHardness(0.6F);
		this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setStepSound(Block.soundGrassFootstep);
        
        DecoAddonManager.register(this, DecoUtilsStrings.DIRT_TAGS, DecoUtilsStrings.DIRT_NAMES, " Grass");
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	if (metadata == 2)
    		return side == 1 ? this.iconGrassDriedTop : (side == 0 ? DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt.getIcon(side, metadata) : this.m_IconGrassSideByMetadataArray[metadata]);
    	else
    		return side == 1 ? this.iconGrassTop : (side == 0 ? DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt.getIcon(side, metadata) : this.m_IconGrassSideByMetadataArray[metadata]);
    }
    
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess bAccess, int x, int y, int z, int side)
    {
    	int metadata = bAccess.getBlockMetadata(x, y, z);
    	
    	if (side == 1)
    		return metadata == 2 ? this.iconGrassDriedTop : this.iconGrassTop;
        else if (side == 0)
            return DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt.getIcon(side, metadata);
        else
        	return this.m_bTempHasSnowOnTop ? this.m_IconSnowSideByMetadataArray[metadata] : this.m_IconGrassSideByMetadataArray[metadata];
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	for (int index = 0; index < this.GRASS_TEXTURE_PATHS.length; index++)
		{
			this.m_IconGrassSideByMetadataArray[index] = register.registerIcon(this.GRASS_TEXTURE_PATHS[index]);
		}
    	
    	for (int index = 0; index < this.SNOW_TEXTURE_PATHS.length; index++)
		{
			this.m_IconSnowSideByMetadataArray[index] = register.registerIcon(this.SNOW_TEXTURE_PATHS[index]);
		}
    	
    	this.iconGrassTop = register.registerIcon("grass_top_overlay");
        this.iconGrassSideOverlay = register.registerIcon("grass_side_overlay");
    	this.iconGrassDriedTop = register.registerIcon("decoBlockGrass_dried_top");
    	this.iconGrassDriedTopOverlay = register.registerIcon("decoBlockGrass_dried_top_overlay");
    	this.iconGrassDriedSideOverlay = register.registerIcon("decoBlockGrass_dried_side_overlay");
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
    	for (int index = 0; index < DecoUtilsStrings.DIRT_TAGS.length; index++)
    	{
    		var3.add(new ItemStack(var1, 1, index));
    	}
    }
    
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World world, int x, int y, int z)
    {
        return world.getBlockId(x, y, z);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return this.getMetadata(world.getBlockMetadata(x, y, z));
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
	public int damageDropped(int metadata)
	{
		return this.getMetadata(metadata);
	}
    
    public int getBlockColor()
    {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrass.getGrassColor(var1, var3);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        return this.getBlockColor();
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
    {
    	int metadata = bAccess.getBlockMetadata(x, y, z);
    	
    	if (this.m_bTempHasSnowOnTop || metadata == 2) return 16777215;
    	else
    	{
		    int tintRed = 0;
		    int tintGreen = 0;
		    int tintBlue = 0;
		
		    for (int zOffset = -1; zOffset <= 1; ++zOffset)
		    {
		        for (int xOffset = -1; xOffset <= 1; ++xOffset)
		        {
		            int biomeColor = bAccess.getBiomeGenForCoords(x + xOffset, z + zOffset).getBiomeGrassColor();
		            tintRed += (biomeColor & 16711680) >> 16;
		            tintGreen += (biomeColor & 65280) >> 8;
		            tintBlue += biomeColor & 255;
		        }
		    }
		
		    return (tintRed / 9 & 255) << 16 | (tintGreen / 9 & 255) << 8 | tintBlue / 9 & 255;
    	}
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
    	int lightValue = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
        int skylightValue = lightValue - world.skylightSubtracted;
        int metadata = world.getBlockMetadata(x, y, z);
    	
        if (DecoUtilsCrops.isWaterNearby(world, x, y, z) && metadata != 1)
        	world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, 3);
        else if (!DecoUtilsCrops.isWaterNearby(world, x, y, z) && metadata == 3)
        	world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, 0);

        if (lightValue >= 9 && Block.lightOpacity[world.getBlockId(x, y + 1, z)] <= 2)
        {
            if (skylightValue >= 11 && world.provider.dimensionId != 1)
            {
                int checkX = x + random.nextInt(3) - 1;
                int checkY = y + random.nextInt(5) - 3;
                int checkZ = z + random.nextInt(3) - 1;
                int blockID = world.getBlockId(checkX, checkY, checkZ);
                int var12;

                if (blockID == Block.dirt.blockID)
                {
                	int blockMetadata = world.getBlockMetadata(checkX, checkY, checkZ);
                	
                    var12 = world.getBlockId(checkX, checkY + 1, checkZ);
                    int lightAboveValue = world.GetBlockNaturalLightValueMaximum(checkX, checkY + 1, checkZ);

                    if (lightAboveValue >= 11 && Block.lightOpacity[var12] <= 2 && (var12 != FCBetterThanWolves.fcBlockDirtSlab.blockID 
                    		|| ((FCBlockDirtSlab)((FCBlockDirtSlab)FCBetterThanWolves.fcBlockDirtSlab)).GetIsUpsideDown(world, checkX, checkY + 1, checkZ)) 
                    		&& blockMetadata != 1)
                    {
                    	world.setBlockAndMetadataWithNotify(checkX, checkY, checkZ, Block.grass.blockID, blockMetadata);
                    }
                }
                else if (blockID == Block.tilledField.blockID && random.nextInt(3) == 0 && world.isAirBlock(checkX, checkY + 1, checkZ))
                {
                    var12 = world.GetBlockNaturalLightValueMaximum(checkX, checkY + 1, checkZ);

                    if (var12 >= 11)
                    {
                    	world.setBlockAndMetadataWithNotify(checkX, checkY + 1, checkZ, Block.tallGrass.blockID, 1);
                    }
                }
            }
        }
    }
   
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
    {
        super.onNeighborBlockChange(world, x, y, z, neighbourBlockID);
        
        if (world.isBlockOpaqueCube(x, y + 1, z))
        {
        	int metadata = world.getBlockMetadata(x, y, z);
	        world.setBlockAndMetadataWithNotify(x, y, z, DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt.blockID, this.getMetadata(metadata));
        }
    }
    
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess bAccess, int x, int y, int z, int side)
    {
        return side == 1 && this.m_bTempHasSnowOnTop ? false : super.shouldSideBeRendered(bAccess, x, y, z, side);
    }

    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
        IBlockAccess bAccess = render.blockAccess;
        int metadata = bAccess.getBlockMetadata(x, y, z);
        
        render.setRenderBoundsFromBlock(this);

        this.m_bTempHasSnowOnTop = this.hasSnowOnTop(bAccess, x, y, z);

        if (this.m_bTempHasSnowOnTop || metadata == 2)
            return render.renderStandardBlock(this, x, y, z);
        else
        {
        	int var6 = this.colorMultiplier(bAccess, x, y, z);
            float var7 = (float)(var6 >> 16 & 255) / 255;
            float var8 = (float)(var6 >> 8 & 255) / 255;
            float var9 = (float)(var6 & 255) / 255;
            
            return Minecraft.isAmbientOcclusionEnabled() ? render.renderGrassBlockWithAmbientOcclusion(this, x, y, z, var7, var8, var9, this.iconGrassSideOverlay) 
            		: render.renderGrassBlockWithColorMultiplier(this, x, y, z, var7, var8, var9, this.iconGrassSideOverlay);
        }
    }
    
    private int getMetadata(int metadata)
   	{
   		int result = metadata > 3 ? (metadata / 2) - 2 : metadata;
   		return result < 0 || result > 16 ? 0 : result;		
   	}
    
    private boolean hasSnowOnTop(IBlockAccess bAccess, int x, int y, int z)
    {
        int var5 = bAccess.getBlockId(x, y + 1, z);

        if (var5 != 0)
        {
            Block var6 = blocksList[var5];
            Material var7 = var6.blockMaterial;

            if (var7 == Material.snow || var7 == Material.craftedSnow)
                return true;
            
            if (var6.SnowRestingOnVisualOffset(bAccess, x, y + 1, z) < -0.99F && bAccess.getBlockId(x, y + 2, z) == snow.blockID)
                return true;
        }

        return false;
    }
}
