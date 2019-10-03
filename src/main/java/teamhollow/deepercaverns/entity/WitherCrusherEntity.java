package teamhollow.deepercaverns.entity;

import javax.annotation.Nullable;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class WitherCrusherEntity extends WitherSkeletonEntity
{
	public static final String NAME = "wither_crusher";

	public WitherCrusherEntity(EntityType<? extends WitherCrusherEntity> type, World world)
	{
		super(type, world);
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(36.0D);
	}

	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
	{
		int diffOrdinal = difficulty.getDifficulty().ordinal();

		if(diffOrdinal > 0)
		{
			//the higher the local difficulty, the higher the chance for equipped armor
			float modifiedLocalDiff = difficulty.getAdditionalDifficulty() * 0.35F;

			if(rand.nextFloat() < modifiedLocalDiff)
			{
				ItemStack helmet = new ItemStack(Items.IRON_HELMET);

				helmet.addEnchantment(Enchantments.PROTECTION, diffOrdinal); //the higher the overall difficulty, the higher the protection level
				setItemStackToSlot(EquipmentSlotType.HEAD, helmet);
			}

			if(rand.nextFloat() < modifiedLocalDiff)
			{
				ItemStack chestplate = new ItemStack(Items.IRON_CHESTPLATE);

				chestplate.addEnchantment(Enchantments.PROTECTION, diffOrdinal);
				setItemStackToSlot(EquipmentSlotType.CHEST, chestplate);
			}

			if(rand.nextFloat() < modifiedLocalDiff)
			{
				ItemStack leggings = new ItemStack(Items.IRON_LEGGINGS);

				leggings.addEnchantment(Enchantments.PROTECTION, diffOrdinal);
				setItemStackToSlot(EquipmentSlotType.LEGS, leggings);
			}

			if(rand.nextFloat() < modifiedLocalDiff)
			{
				ItemStack boots = new ItemStack(Items.IRON_BOOTS);

				boots.addEnchantment(Enchantments.PROTECTION, diffOrdinal);
				setItemStackToSlot(EquipmentSlotType.FEET, boots);
			}
		}
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
	{
		ILivingEntityData data = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);

		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.5F);
		return data;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size)
	{
		return 2.8F;
	}

	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHit) {} //disable wither skull drop

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {} //no weapons
}
