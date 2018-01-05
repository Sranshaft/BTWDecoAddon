package net.minecraft.src;

import java.util.Random;

import net.minecraft.client.Minecraft;

public class DecoBlockGrassPath extends Block
{
	public static final String[] PATH_TEXTURE_PATHS = new String[] { "decoBlockGrassPath", "decoBlockGrassPath_coarse", "decoBlockGrassPath_dried", "decoBlockGrassPath_rich" };
	
	private Icon[] m_IconPathSideByMetadataArray = new Icon[this.PATH_TEXTURE_PATHS.length];
	private Icon iconPathTop;
    private Icon iconPathSideOverlay;
    
    
    public DecoBlockGrassPath(int id)
    {
    	super(id, Material.grass);
    	
    	this.setUnlocalizedName("decoBlockGrassPath");
    	this.setHardness(Block.grass.blockHardness);
		this.setResistance(Block.grass.blockResistance);
		this.setCreativeTab(CreativeTabs.tabBlock);
        this.setStepSound(Block.soundGrassFootstep);
    	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setLightOpacity(255);
    	
        DecoAddonManager.register(this, DecoUtilsStrings.DIRT_TAGS, DecoUtilsStrings.DIRT_NAMES, " Grass Path");
    }
    
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
    {
        super.onNeighborBlockChange(world, x, y, z, neighbourBlockID);
        Material blockMaterial = world.getBlockMaterial(x, y + 1, z);

        if (blockMaterial.isSolid())
        {
        	int metadata = world.getBlockMetadata(x, y, z);
        	world.setBlockAndMetadataWithNotify(x, y, z, Block.dirt.blockID, this.getMetadata(metadata));
        }
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
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
        return world.getBlockMetadata(x, y, z);
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 0), (double)(par2 + 1), (double)(par3 + 1), (double)(par4 + 1));
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return side == 1 ? this.iconPathTop : (side == 0 ? DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt.getIcon(side, metadata) : this.m_IconPathSideByMetadataArray[metadata]);
    }
    
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess bAccess, int x, int y, int z, int side)
    {
    	int metadata = bAccess.getBlockMetadata(x, y, z);
        return side == 1 ? this.iconPathTop : (side == 0 ? DecoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt.getIcon(side, metadata) : this.m_IconPathSideByMetadataArray[metadata]);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	for (int index = 0; index < this.PATH_TEXTURE_PATHS.length; index++)
		{
			this.m_IconPathSideByMetadataArray[index] = register.registerIcon(this.PATH_TEXTURE_PATHS[index]);
		}
    	
        this.iconPathTop = register.registerIcon("decoBlockGrassPath_top_overlay");
        this.iconPathSideOverlay = register.registerIcon("decoBlockGrassPath_side_overlay");
    }
    
    public int getBlockColor()
    {
        double temp = 1.0D;
        double humidity = 0.0D;
        return ColorizerGrass.getGrassColor(temp, humidity);
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
    	int tintRed = 192;
	    int tintGreen = 192;
	    int tintBlue = 77;
	
	    return (tintRed & 255) << 16 | (tintGreen & 255) << 8 | tintBlue & 255;
    }
    
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
        IBlockAccess bAccess = render.blockAccess;
        render.setRenderBoundsFromBlock(this);
        
        int var6 = this.colorMultiplier(bAccess, x, y, z);
        float red = (float)(var6 >> 16 & 255) / 255;
        float green = (float)(var6 >> 8 & 255) / 255;
        float blue = (float)(var6 & 255) / 255;
        return Minecraft.isAmbientOcclusionEnabled() ? render.renderGrassBlockWithAmbientOcclusion(this, x, y, z, red, green, blue, this.iconPathSideOverlay) 
        		: render.renderGrassBlockWithColorMultiplier(this, x, y, z, red, green, blue, this.iconPathSideOverlay);
    }
    
    private int getMetadata(int metadata)
	{
		int result = metadata > 3 ? (metadata / 2) - 2 : metadata;
		return result < 0 || result > 16 ? 0 : result;		
	}
}
