package net.minecraft.src;

import java.util.Random;

public class DecoBlockGlass extends BlockGlass implements DecoIBlockWithMetadata
{
	public DecoBlockGlass(int id)
	{
		super(id, Material.glass, false);
		
		this.setUnlocalizedName("glass");
		this.setHardness(0.3F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		this.SetPicksEffectiveOn();
	}
	
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float var6)
	{
		if (!world.isRemote && entity.fallDistance > 3.0F && DecoAddonManager.getConfigOption("enableGlassBreaking"))
		{
			world.playAuxSFX(2001, x, y, z, this.blockID);
			world.destroyBlock(x, y, z, true);
		}
	}
	
	public int idDropped(int metadata, Random random, int fortune)
	{
		if (!DecoAddonManager.getConfigOption("enableGlassShards")) return 0;
		return DecoModuleTweaks.decoItemGlassShard.itemID;
	}
	
	public int quantityDropped(Random random)
	{
		if (!DecoAddonManager.getConfigOption("enableGlassShards")) return 0;
		return random.nextInt(3);
	}
	
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		if (!DecoAddonManager.getConfigOption("enableGlassShards")) return 0;
		return Math.min(4, this.quantityDropped(random) + random.nextInt(fortune + 1));
	}
	
	/**
	 * When this method is called, your block should register all the icons it needs
	 * with the given IconRegister. This is the only chance you get to register
	 * icons.
	 */
	public void registerIcons(IconRegister register)
	{
		super.registerIcons(register);
		Block.glass.registerIcons(register);
	}
	
	public boolean CanContainPistonPackingToFacing(World world, int x, int y, int z, int side)
	{
		return true;
	}
}
