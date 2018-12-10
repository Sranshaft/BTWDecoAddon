package net.minecraft.src;

public class DecoBlockAnchor extends FCBlockAnchor 
{
	private Icon m_IconRope;
	private Icon m_IconChain;
	
	public DecoBlockAnchor(int id)
	{
		super(id);
		
		this.setUnlocalizedName("fcBlockAnchor");
		this.setHardness(2.0F);
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabTransport);
	}
	
	void RetractRope(World world, int x, int y, int z, EntityPlayer player)
    {
		super.RetractRope(world, x, y, z, player);
		
        for (int yIndex = y - 1; yIndex >= 0; --yIndex)
        {
            int blockID = world.getBlockId(x, yIndex, z);

            if (blockID != DecoModuleTransportation.decoBlockChainID) break;
            
            if (world.getBlockId(x, yIndex - 1, z) != DecoModuleTransportation.decoBlockChainID)
            {
                this.AddChainToPlayerInventory(world, x, y, z, player);
                Block var8 = DecoModuleTransportation.decoBlockChain;

                if (!world.isRemote)
                {
                    world.playAuxSFX(2001, x, y, z, blockID);
                    world.setBlockWithNotify(x, yIndex, z, 0);
                }

                break;
            }
        }
    }
	
	private void AddChainToPlayerInventory(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
		ItemStack var6 = new ItemStack(DecoModuleTransportation.decoItemChain);

        if (var5.inventory.addItemStackToInventory(var6))
        {
            var1.playSoundAtEntity(var5, "random.pop", 0.2F, ((var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }
        else
        {
            FCUtilsItem.EjectStackWithRandomOffset(var1, var2, var3, var4, var6);
        }
    }
	
	/**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        
        this.m_IconRope = var1.registerIcon("fcBlockRope");
        this.m_IconChain = var1.registerIcon("decoBlockChain");
    }
    
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
        IBlockAccess var5 = render.blockAccess;
        
        float var6 = 0.5F;
        float var7 = 0.5F;
        float var8 = fAnchorBaseHeight;
        
        int direction = this.GetFacing(var5, x, y, z);

        switch (direction)
        {
            case 0:
                render.setRenderBounds((double)(0.5F - var7), (double)(1.0F - var8), (double)(0.5F - var6), (double)(0.5F + var7), 1.0D, (double)(0.5F + var6));
                break;

            case 1:
                render.setRenderBounds((double)(0.5F - var7), 0.0D, (double)(0.5F - var6), (double)(0.5F + var7), (double)var8, (double)(0.5F + var6));
                break;

            case 2:
                render.setRenderBounds((double)(0.5F - var7), (double)(0.5F - var6), (double)(1.0F - var8), (double)(0.5F + var7), (double)(0.5F + var6), 1.0D);
                break;

            case 3:
                render.setRenderBounds((double)(0.5F - var7), (double)(0.5F - var6), 0.0D, (double)(0.5F + var7), (double)(0.5F + var6), (double)var8);
                break;

            case 4:
                render.setRenderBounds((double)(1.0F - var8), (double)(0.5F - var7), (double)(0.5F - var6), 1.0D, (double)(0.5F + var7), (double)(0.5F + var6));
                break;

            default:
                render.setRenderBounds(0.0D, (double)(0.5F - var7), (double)(0.5F - var6), (double)var8, (double)(0.5F + var7), (double)(0.5F + var6));
        }

        render.renderStandardBlock(this, x, y, z);
        var6 = 0.125F;
        var7 = 0.125F;
        var8 = 0.25F;
        render.setRenderBounds((double)(0.5F - var7), (double)fAnchorBaseHeight, (double)(0.5F - var6), (double)(0.5F + var7), (double)(fAnchorBaseHeight + var8), (double)(0.5F + var6));
        FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, this.m_IconNub);
        
        var6 = 0.0625F;
        var7 = 0.0625F;
        var8 = fAnchorBaseHeight;
        
        boolean isRopeConnected = false;
        boolean isChainConnected = false;

        if (direction == 1)
        {
            int var11 = var5.getBlockId(x, y + 1, z);

            if (var11 == FCBetterThanWolves.fcRopeBlock.blockID || var11 == FCBetterThanWolves.fcPulley.blockID)
            {
                render.setRenderBounds((double)(0.5F - var7), (double)var8, (double)(0.5F - var6), (double)(0.5F + var7), 1.0D, (double)(0.5F + var6));
                isRopeConnected = true;
            }
            
            if (var11 == DecoModuleTransportation.decoBlockChainID)
            {
                render.setRenderBounds((double)(0.5F - var7), (double)var8, (double)(0.5F - var6), (double)(0.5F + var7), 1.0D, (double)(0.5F + var6));
                isChainConnected = true;
            }
        }
        else if (var5.getBlockId(x, y - 1, z) == FCBetterThanWolves.fcRopeBlock.blockID)
        {
            render.setRenderBounds((double)(0.5F - var7), 0.0D, (double)(0.5F - var6), (double)(0.5F + var7), (double)var8, (double)(0.5F + var6));
            isRopeConnected = true;
        }
        else if (var5.getBlockId(x, y - 1, z) == DecoModuleTransportation.decoBlockChainID)
        {
            render.setRenderBounds((double)(0.5F - var7), 0.0D, (double)(0.5F - var6), (double)(0.5F + var7), (double)var8, (double)(0.5F + var6));
            isChainConnected = true;
        }

        if (isRopeConnected)
        {
            FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, this.m_IconRope);
        }
        
        if (isChainConnected)
        {
            FCClientUtilsRender.RenderStandardBlockWithTexture(render, this, x, y, z, this.m_IconChain);
        }

        return true;
    }
}
