package net.minecraft.src;

public class DecoFixItemMapID extends ItemMap
{
	protected DecoFixItemMapID(int ID)
	{
		super(ID);
		
		this.setUnlocalizedName("map");
		this.SetBellowsBlowDistance(3);
		this.SetBuoyancy(1.0F);
	}

	public void updateMapData(World world, Entity entity, MapData mapData)
	{
		if (world.provider.dimensionId == mapData.dimension && entity instanceof EntityPlayer && mapData.IsEntityLocationVisibleOnMap(entity))
		{
			_updateMapData(world, entity, mapData);
		}
	}
	
	public void _updateMapData(World world, Entity entity, MapData mapData)
	{
		if (world.provider.dimensionId == mapData.dimension && entity instanceof EntityPlayer)
		{
			short xDistance = 128;
			short zDistance = 128;
			int scale = 1 << mapData.scale;
			int xCenter = mapData.xCenter;
			int zCenter = mapData.zCenter;
			int var9 = MathHelper.floor_double(entity.posX - (double)xCenter) / scale + xDistance / 2;
			int var10 = MathHelper.floor_double(entity.posZ - (double)zCenter) / scale + zDistance / 2;
			int var11 = 128 / scale;

			if (world.provider.hasNoSky) var11 /= 2;

			MapInfo var12 = mapData.func_82568_a((EntityPlayer)entity);
			++var12.field_82569_d;

			for (int var13 = var9 - var11 + 1; var13 < var9 + var11; ++var13)
			{
				if ((var13 & 15) == (var12.field_82569_d & 15))
				{
					int var14 = 255;
					int var15 = 0;
					double var16 = 0.0D;

					for (int var18 = var10 - var11 - 1; var18 < var10 + var11; ++var18)
					{
						if (var13 >= 0 && var18 >= -1 && var13 < xDistance && var18 < zDistance)
						{
							int var19 = var13 - var9;
							int var20 = var18 - var10;
							boolean var21 = var19 * var19 + var20 * var20 > (var11 - 2) * (var11 - 2);
							int var22 = (xCenter / scale + var13 - xDistance / 2) * scale;
							int var23 = (zCenter / scale + var18 - zDistance / 2) * scale;
							int[] var24 = new int[4096];
							Chunk var25 = world.getChunkFromBlockCoords(var22, var23);

							if (!var25.isEmpty())
							{
								int var26 = var22 & 15;
								int var27 = var23 & 15;
								int var28 = 0;
								double var29 = 0.0D;
								int var31;
								int var32;
								int var33;
								int var36;

								if (world.provider.hasNoSky)
								{
									var31 = var22 + var23 * 231871;
									var31 = var31 * var31 * 31287121 + var31 * 11;

									if ((var31 >> 20 & 1) == 0)
									{
										var24[Block.dirt.blockID] += 10;
									}
									else
									{
										var24[Block.stone.blockID] += 10;
									}
									var29 = 100.0D;
								}
								else
								{
									for (var31 = 0; var31 < scale; ++var31)
									{
										for (var32 = 0; var32 < scale; ++var32)
										{
											var33 = var25.getHeightValue(var31 + var26, var32 + var27) + 1;
											int var34 = 0;

											if (var33 > 1)
											{
												boolean var35;

												do
												{
													var35 = true;
													var34 = var25.getBlockID(var31 + var26, var33 - 1, var32 + var27);

													if (var34 == 0)
													{
														var35 = false;
													}
													else if (var33 > 0 && var34 > 0 && Block.blocksList[var34].blockMaterial.materialMapColor == MapColor.airColor)
													{
														var35 = false;
													}

													if (!var35)
													{
														--var33;

														if (var33 <= 0) break;

														var34 = var25.getBlockID(var31 + var26, var33 - 1, var32 + var27);
													}
												}while (var33 > 0 && !var35);

												if (var33 > 0 && var34 != 0 && Block.blocksList[var34].blockMaterial.isLiquid())
												{
													var36 = var33 - 1;
													boolean var37 = false;
													int var43;

													do
													{
														var43 = var25.getBlockID(var31 + var26, var36--, var32 + var27);
														++var28;
													}while (var36 > 0 && var43 != 0 && Block.blocksList[var43].blockMaterial.isLiquid());
												}
											}

											var29 += (double)var33 / (double)(scale * scale);
											++var24[var34];
										}
									}
								}

								var28 /= scale * scale;
								var31 = 0;
								var32 = 0;

								for (var33 = 0; var33 < 4096; ++var33)
								{
									if (var24[var33] > var31)
									{
										var32 = var33;
										var31 = var24[var33];
									}
								}

								double var40 = (var29 - var16) * 4.0D / (double)(scale + 4) + ((double)(var13 + var18 & 1) - 0.5D) * 0.4D;
								byte var39 = 1;

								if (var40 > 0.6D) var39 = 2;

								if (var40 < -0.6D) var39 = 0;

								var36 = 0;

								if (var32 > 0)
								{
									MapColor var42 = Block.blocksList[var32].blockMaterial.materialMapColor;

									if (var42 == MapColor.waterColor)
									{
										var40 = (double)var28 * 0.1D + (double)(var13 + var18 & 1) * 0.2D;
										var39 = 1;

										if (var40 < 0.5D) var39 = 2;

										if (var40 > 0.9D) var39 = 0;
									}

									var36 = var42.colorIndex;
								}

								var16 = var29;

								if (var18 >= 0 && var19 * var19 + var20 * var20 < var11 * var11 && (!var21 || (var13 + var18 & 1) != 0))
								{
									byte var41 = mapData.colors[var13 + var18 * xDistance];
									byte var38 = (byte)(var36 * 4 + var39);

									if (var41 != var38)
									{
										if (var14 > var18) var14 = var18;

										if (var15 < var18) var15 = var18;

										mapData.colors[var13 + var18 * xDistance] = var38;
									}
								}
							}
						}
					}

					if (var14 <= var15)
						mapData.setColumnDirty(var13, var14, var15);
				}
			}
		}
	}
}
