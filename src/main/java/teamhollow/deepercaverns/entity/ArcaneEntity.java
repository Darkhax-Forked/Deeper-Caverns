package teamhollow.deepercaverns.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ArcaneEntity extends MonsterEntity
{
	public static final String NAME = "arcane";

	public ArcaneEntity(EntityType<? extends ArcaneEntity> type, World world)
	{
		super(type, world);
	}

	@Override
	public void registerGoals()
	{
		goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
		goalSelector.addGoal(8, new LookAtGoal(this, LivingEntity.class, 8.0F));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	@Override
	public boolean canSwim()
	{
		return true;
	}

	class MeleeFireAttackGoal extends MeleeAttackGoal
	{
		public MeleeFireAttackGoal(CreatureEntity creature, double speed, boolean useLongMemory)
		{
			super(creature, speed, useLongMemory);
		}

		@Override
		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr)
		{
			double attackReachSqr = getAttackReachSqr(enemy);

			if (distToEnemySqr <= attackReachSqr && attackTick <= 0)
			{
				attackTick = 20;
				attacker.swingArm(Hand.MAIN_HAND);
				attacker.attackEntityAsMob(enemy);
				enemy.setFire(5);
			}
		}
	}
}
