package net.minecraft.src;

import java.util.Random;

public class DecoBlockLadderWood extends FCBlockLadder
{
	private String m_Tag;
	
	public DecoBlockLadderWood(int id, String tag)
	{
		super(id);
		
		this.setUnlocalizedName("decoBlockLadder_" + tag);
		this.setHardness(0.4F);
		this.setStepSound(Block.soundLadderFootstep);
		
		ItemAxe.SetAllAxesToBeEffectiveVsBlock(this);
		
		this.m_Tag = tag;
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int metadata)
    {
    	return this.blockIcon;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
    	this.blockIcon = register.registerIcon("decoBlockLadder_" + this.m_Tag);
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        if (this.m_Tag == "spruce") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLadders[0].blockID;
        if (this.m_Tag == "birch") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLadders[1].blockID;
        if (this.m_Tag == "jungle") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLadders[2].blockID;
        if (this.m_Tag == "bloodwood") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLadders[3].blockID;
        if (this.m_Tag == "ebonwood") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLadders[4].blockID;
        if (this.m_Tag == "ironwood") return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLadders[5].blockID;
        	
        return FCBetterThanWolves.fcBlockLadder.blockID;
    }
}
