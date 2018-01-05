package net.minecraft.src;

public class DecoItemFruit extends ItemFood
{
	private int m_PotionId;
	private float m_PotionEffectProbability;
	private String m_Type;
	
	public DecoItemFruit(int id, String type, int healAmount, float saturation)
	{
		this(id, type, healAmount, saturation, 0, 0);
	}
	
	public DecoItemFruit(int id, String type, int healAmount, float saturation, int potionID, int potionChance)
	{
		super(id, healAmount, saturation, false);
		setUnlocalizedName("decoItemFruit." + type);
		this.m_PotionId = Potion.poison.id;
		this.m_PotionEffectProbability = potionChance;
        this.maxStackSize = 16;
        
        this.m_Type = type;
	}
	
	public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon("decoItemFruit_" + this.m_Type);
    }
	
	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote && this.m_PotionId > 0 && par2World.rand.nextFloat() < this.m_PotionEffectProbability)
        {
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 30, 0));
        }
    }
}
