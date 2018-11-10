package net.minecraft.src;

public class DecoBlockFenceGate extends FCBlockFenceGate
{
	private String m_Tag;
	
	public DecoBlockFenceGate(int id, String tag)
	{
		super(id);
		
		this.setUnlocalizedName("decoBlockFenceGate." + tag);
		this.setCreativeTab(CreativeTabs.tabRedstone);
		
		this.m_Tag = tag;
		
		Block.useNeighborBrightness[id] = true;
	}
	
	//CLIENT ONLY
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	public Icon getIcon(int side, int metadata)
	{
		if (this.m_Tag == "spruce") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(0, 1);
		if (this.m_Tag == "birch") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(0, 2);
		if (this.m_Tag == "jungle") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(0, 3);
		if (this.m_Tag == "bloodwood") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(0, 4);
		if (this.m_Tag == "ebonwood") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(0, 5);
		if (this.m_Tag == "ironwood") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockWood.getIcon(0, 6);
		
		return this.blockIcon;
	}
}
