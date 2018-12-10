package net.minecraft.src;

public class DecoEntityRock extends EntityThrowable implements FCIEntityPacketHandler
{
	public DecoEntityRock(World world)
	{
		super(world);
	}

	public DecoEntityRock(World world, EntityLiving entity)
	{
		super(world, entity);
		motionX *= 0.5D;
		motionY *= 0.5D;
		motionZ *= 0.5D;
	}

	public DecoEntityRock(World world, EntityLiving entity, double strength)
	{
		super(world, entity);
		motionX *= strength;
		motionY *= strength;
		motionZ *= strength;
	}

	public DecoEntityRock(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	protected void onImpact(MovingObjectPosition mop)
	{
		if (mop.entityHit != null)
		{
			// Usual sling shot velocity: weakest 0.2, strongest 1.1
			double velocity = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
			int damage = (velocity < 0.2) ? 0 : (int)(velocity * 5);
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		}
		
		if (!worldObj.isRemote)
		{
			if (mop.typeOfHit == EnumMovingObjectType.TILE)
			{
				int x = mop.blockX;
				int y = mop.blockY;
				int z = mop.blockZ;
				int id = worldObj.getBlockId(x, y, z);
				Block block = Block.blocksList[id];
				if (block instanceof BlockGlass || (block instanceof DecoBlockPane && worldObj.getBlockMaterial(x, y, z) == Material.glass))
				{
					worldObj.playAuxSFX(2001, x, y, z, Block.glass.blockID);
					worldObj.destroyBlock(x, y, z, true);
				}
				else if (block instanceof FCBlockVase)
				{
					((FCBlockVase)block).BreakVase(worldObj, x, y, z);
				}
			}
			
			FCUtilsItem.EjectSingleItemWithRandomVelocity(worldObj, (float)posX, (float)posY, (float)posZ, FCBetterThanWolves.fcItemStone.itemID, 0);
			setDead();
		}
	}

	public int GetTrackerViewDistance() { return 64; }
	public int GetTrackerUpdateFrequency() { return 10; }
	public boolean GetTrackMotion() { return true; }
	public boolean ShouldServerTreatAsOversized() { return false; }

	public static int getVehicleSpawnPacketType() { return DecoModuleEquipment.decoSubModuleToolSling.decoEntityRockVehicleSpawnType; }

	public Packet GetSpawnPacketForThisEntity()
	{
		return new Packet23VehicleSpawn(this, getVehicleSpawnPacketType(), 0);
	}

}
