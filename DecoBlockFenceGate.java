package net.minecraft.src;

public class DecoBlockFenceGate extends FCBlockFenceGate
{
	public DecoBlockFenceGate(int id)
	{
		super(id);
		
		Block.useNeighborBrightness[id] = true;
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
        return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(side, 0);
    }
}
