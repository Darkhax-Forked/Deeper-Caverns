package teamhollow.deepercaverns.misc;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import teamhollow.deepercaverns.reg.ItemRegistrar;

public enum ItemTier implements IItemTier
{
	//all are the same as gold for now
	CHALONITE(0, 32, 12.0F, 0.0F, 22, Ingredient.fromItems(ItemRegistrar.ONYX_INGOT)),
	GLOWSTONE_CRYSTAL(0, 32, 12.0F, 0.0F, 22, Ingredient.fromItems(ItemRegistrar.GLOWSTONE_CRYSTAL)),
	GHOSTSOUL(0, 32, 12.0F, 0.0F, 22, Ingredient.fromItems(ItemRegistrar.GHOSTSOUL_INGOT)),
	ONYX(0, 32, 12.0F, 0.0F, 22, Ingredient.fromItems(ItemRegistrar.ONYX_INGOT));

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final Ingredient repairMaterial;

	private ItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Ingredient repairMaterial)
	{
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public int getMaxUses()
	{
		return maxUses;
	}

	@Override
	public float getEfficiency()
	{
		return efficiency;
	}

	@Override
	public float getAttackDamage()
	{
		return attackDamage;
	}

	@Override
	public int getHarvestLevel()
	{
		return harvestLevel;
	}

	@Override
	public int getEnchantability()
	{
		return enchantability;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return repairMaterial;
	}
}
