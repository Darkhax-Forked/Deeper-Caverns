package teamhollow.deepercaverns.misc;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.reg.ItemRegistrar;

public enum ArmorMaterial implements IArmorMaterial
{
	//all are the same as gold for now
	GLOWSTONE_CRYSTAL("glowstone_crystal", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, Ingredient.fromItems(ItemRegistrar.GLOWSTONE_CRYSTAL)),
	GHOSTSOUL("ghostsoul", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, Ingredient.fromItems(ItemRegistrar.GHOSTSOUL_INGOT));

	private final String name;
	private final int durability;
	private final float toughness;
	private final int[] damageReduction;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final Ingredient repairMaterial;

	ArmorMaterial(String name, int durability, int[] damageReduction, int enchantability, SoundEvent soundEvent, float toughness, Ingredient repairMaterial)
	{
		this.name = name;
		this.durability = durability;
		this.toughness = toughness;
		this.damageReduction = damageReduction;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public int getDurability(EquipmentSlotType slot)
	{
		return durability;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slot)
	{
		return damageReduction[slot.getIndex()];
	}

	@Override
	public int getEnchantability()
	{
		return enchantability;
	}

	@Override
	public SoundEvent getSoundEvent()
	{
		return soundEvent;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return repairMaterial;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName()
	{
		return DeeperCaverns.PREFIX + name;
	}

	@Override
	public float getToughness()
	{
		return toughness;
	}
}
