package net.minecraft.src;

public enum DecoEnumRenderType
{	
	NO_RENDER(-1),
	CUBE(0),
	CROSSED(1),
	TORCH(2),
	FIRE(3),
	FLUID(4),
	REDSTONE_WIRE(5),
	CROP(6),
	DOOR(7),
	LADDER(8),
	TRACK(9),
	STAIR(10),
	FENCE(11),
	LEVER(12),
	CACTUS(13),
	BED(14),
	REDSTONE_REPEATER(15),
	PISTON_BASE(16),
	PISTON_EXTENSION(17),
	PANE(18),
	STEM(19),
	VINE(20),
	FENCE_GATE(21),
	CHEST(22),
	LILY_PAD(23),
	CAULDRON(24),
	BREWING_STAND(25),
	END_PORTAL_FRAME(26),
	DRAGON_EGG(27),
	COCOA(28),
	TRIP_WIRE_SOURCE(29),
	TRIP_WIRE(30),
	LOG(31),
	WALL(32),
	FLOWER_POT(33),
	BEACON(34),
	ANVIL(35);
	
	private int m_RenderType;
	
	DecoEnumRenderType(int type) { this.m_RenderType = type; }
	
	public int getRenderType() { return this.m_RenderType; }
}
