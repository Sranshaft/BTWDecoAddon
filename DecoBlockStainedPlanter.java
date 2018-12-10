package net.minecraft.src;

import java.util.List;

public class DecoBlockStainedPlanter extends Block
{
	private int m_ColorIndex;
	
	private Icon[] m_IconMetadataArray = new Icon[16];
	
	public DecoBlockStainedPlanter(int id)
	{
		super(id, Material.glass);
		
		this.setUnlocalizedName("decoBlockStainedPlanter");
		this.setHardness(0.6F);
        this.setStepSound(soundGlassFootstep);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        
        DecoAddonManager.register(this, DecoUtilsStrings.COLOUR_TAGS, DecoUtilsStrings.COLOUR_NAMES, " Planter");
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
	
	//CLIENT ONLY
    
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	public void registerIcons(IconRegister register)
	{
		for (int index = 0; index < DecoUtilsStrings.COLOUR_TAGS.length; index++)
		{
			this.m_IconMetadataArray[index] = register.registerIcon("decoBlockStainedPlanter_" + DecoUtilsStrings.COLOUR_TAGS[index]);
		}
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return this.m_IconMetadataArray[metadata];
    }

    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
        render.setRenderBounds(0.125D, 0.0D, 0.125D, 0.25D, 0.6875D, 0.75D);
        render.renderStandardBlock(this, x, y, z);
        render.setRenderBounds(0.125D, 0.0D, 0.75D, 0.75D, 0.6875D, 0.875D);
        render.renderStandardBlock(this, x, y, z);
        render.setRenderBounds(0.75D, 0.0D, 0.25D, 0.875D, 0.6875D, 0.875D);
        render.renderStandardBlock(this, x, y, z);
        render.setRenderBounds(0.25D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.25D);
        render.renderStandardBlock(this, x, y, z);
        render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
        render.renderStandardBlock(this, x, y, z);
        render.setRenderBounds(0.0D, 0.6875D, 0.0D, 0.125D, 1.0D, 0.875D);
        render.renderStandardBlock(this, x, y, z);
        render.setRenderBounds(0.0D, 0.6875D, 0.875D, 0.875D, 1.0D, 1.0D);
        render.renderStandardBlock(this, x, y, z);
        render.setRenderBounds(0.875D, 0.6875D, 0.125D, 1.0D, 1.0D, 1.0D);
        render.renderStandardBlock(this, x, y, z);
        render.setRenderBounds(0.125D, 0.6875D, 0.0D, 1.0D, 1.0D, 0.125D);
        render.renderStandardBlock(this, x, y, z);
        
        render.setRenderBounds(0.125D, 0.85D, 0.125D, 0.875D, 0.95D, 0.875D);
        
        if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
        	render.setOverrideBlockTexture(render.getBlockIcon(DecoModuleTweaks.decoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt));
        else
        	render.setOverrideBlockTexture(render.getBlockIcon(Block.dirt));
        
        render.renderStandardBlock(this, x, y, z);
        render.clearOverrideBlockTexture();
        
        return true;
    }
    
    public void RenderBlockAsItem(RenderBlocks render, int var2, float var3)
    {
    	render.setRenderBounds(0.125D, 0.0D, 0.125D, 0.25D, 0.6875D, 0.75D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.125D, 0.0D, 0.75D, 0.75D, 0.6875D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.75D, 0.0D, 0.25D, 0.875D, 0.6875D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.25D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.25D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.0D, 0.6875D, 0.0D, 0.125D, 1.0D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.0D, 0.6875D, 0.875D, 0.875D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.875D, 0.6875D, 0.125D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.setRenderBounds(0.125D, 0.6875D, 0.0D, 1.0D, 1.0D, 0.125D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        
        render.setRenderBounds(0.125D, 0.85D, 0.125D, 0.875D, 0.95D, 0.875D);
        
        if (DecoAddonManager.getConfigOption("enableExtendedDirtAndGrassBlocks"))
        	render.setOverrideBlockTexture(render.getBlockIcon(DecoModuleTweaks.decoSubModuleExtendedDirtAndGrassBlocks.decoBlockDirt));
        else
        	render.setOverrideBlockTexture(render.getBlockIcon(Block.dirt));
        
        FCClientUtilsRender.RenderInvBlockWithMetadata(render, this, -0.5F, -0.5F, -0.5F, var2);
        render.clearOverrideBlockTexture();
    }
}
