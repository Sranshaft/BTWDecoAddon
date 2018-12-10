package net.minecraft.src;

public class DecoItemLeaves extends ItemLeaves 
{
	public DecoItemLeaves(int id)
	{
		super(id);

		this.setUnlocalizedName("leaves");
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	/**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        return DecoModuleTweaks.decoSubModuleExtendedWoodBlocks.decoBlockLeaves.getIcon(0, par1);
    }
}
