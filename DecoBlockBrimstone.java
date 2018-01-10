package net.minecraft.src;

public class DecoBlockBrimstone extends Block implements DecoIBlock
{
	public DecoBlockBrimstone(int id)
	{
		super(id, Material.rock);

		this.setUnlocalizedName("decoBlockBrimstone");
		this.setHardness(Block.stone.blockHardness);
		this.setResistance(Block.stone.blockResistance);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int idPicked(World world, int x, int y, int z) 
	{
		return world.getBlockId(x, y, z);
	}
}
