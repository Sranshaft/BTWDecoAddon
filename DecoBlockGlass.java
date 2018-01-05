package net.minecraft.src;

import java.util.Random;

public class DecoBlockGlass extends BlockGlass
{
	public DecoBlockGlass(int id)
	{
		super(id, Material.glass, false);
		
		this.setUnlocalizedName("glass");
		this.setHardness(0.3F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this);
	}
	
	public void registerIcons(IconRegister register)
	{
		super.registerIcons(register);
		Block.glass.registerIcons(register);
	}
	
	public int idDropped(int metadata, Random random, int fortune)
	{
		if (DecoModuleTweaks.decoItemGlassShard == null) return 0;
		return DecoModuleTweaks.decoItemGlassShardID;
	}
	
	public int quantityDropped(Random random)
	{
		if (DecoModuleTweaks.decoItemGlassShard == null) return 0;
		return 2 + random.nextInt(3);
	}
	
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		if (DecoModuleTweaks.decoItemGlassShard == null) return 0;
		return Math.min(4, this.quantityDropped(random) + random.nextInt(fortune + 1));
	}
	
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float var6)
	{
		if (!DecoAddonManager.getConfigOption("enableGlassBreaking"))
			return;
		
		if (!world.isRemote && entity.fallDistance > 3.0F)
		{
			world.playAuxSFX(2001, x, y, z, Block.glass.blockID);
			world.destroyBlock(x, y, z, true);
		}
	}
	
	public boolean CanContainPistonPackingToFacing(World world, int x, int y, int z, int side)
	{
		return true;
	}
}
