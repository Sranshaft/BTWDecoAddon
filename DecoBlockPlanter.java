package net.minecraft.src;

import java.util.Random;

public class DecoBlockPlanter extends FCBlockPlanter
{
	public DecoBlockPlanter(int id)
	{
		super(id);
		this.setHardness(0.6F);
		this.setStepSound(soundGlassFootstep);
		this.setUnlocalizedName("fcBlockPlanter");
		this.setTickRandomly(true);
		setCreativeTab(CreativeTabs.tabDecorations);
		
		Block.useNeighborBrightness[id] = true;
		ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this);
	}

	public void updateTick(World CurrentWorld, int X, int Y, int Z, Random itemRand)
	{
		int var6 = this.GetPlanterType(CurrentWorld, X, Y, Z);

		if (var6 == 9 || var6 == 11 || var6 == 13 || var6 == 15)
		{
			int GrowthState = this.GetGrassGrowthState(CurrentWorld, X, Y, Z);
			int var8 = 0;
			int var9;
			
			if (CurrentWorld.isAirBlock(X, Y + 1, Z) && CurrentWorld.getBlockLightValue(X, Y + 1, Z) >= 8)
			{
				var8 = GrowthState + 1;
				if (var8 > 3)
				{
					var8 = 0;
					int rnd = itemRand.nextInt(27);
					switch (rnd)
					{
						case 0:
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
						case 6:
						case 7:
						case 8:
						case 9:
						case 10:
						case 11:
						case 12:
						case 13:
						case 14:
							CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, DecoSubModuleFlowers.decoBlockFlowerExtended.blockID, rnd);
							break;
						case 15:
							CurrentWorld.setBlockWithNotify(X, Y + 1, Z, Block.plantYellow.blockID);
							break;
						case 16:
							CurrentWorld.setBlockWithNotify(X, Y + 1, Z, Block.plantRed.blockID);
							break;
						case 17:
						case 18:
						case 19:
						case 20:
							CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, DecoSubModuleFlowers.decoBlockFlowerTulip.blockID, rnd - 17);
							break;
						default:
							CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 1);
					}
				}
			}
			
			if (CurrentWorld.getBlockLightValue(X, Y + 1, Z) >= 9)
			{
				for (var9 = 0; var9 < 4; ++var9)
				{
					int CurrentWorld0 = X + itemRand.nextInt(3) - 1;
					int CurrentWorld1 = Y + itemRand.nextInt(5) - 3;
					int CurrentWorld2 = Z + itemRand.nextInt(3) - 1;
					int CurrentWorld3 = CurrentWorld.getBlockId(CurrentWorld0, CurrentWorld1 + 1, CurrentWorld2);

					if (CurrentWorld.getBlockId(CurrentWorld0, CurrentWorld1, CurrentWorld2) == Block.dirt.blockID && CurrentWorld.getBlockLightValue(CurrentWorld0, CurrentWorld1 + 1, CurrentWorld2) >= 4 && Block.lightOpacity[CurrentWorld3] <= 2)CurrentWorld.setBlockWithNotify(CurrentWorld0, CurrentWorld1, CurrentWorld2, Block.grass.blockID);
				}
			}
			
			if (var8 != GrowthState)
				this.SetGrassGrowthState(CurrentWorld, X, Y, Z, var8);
		}
	}
}
