package teamhollow.deepercaverns.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

//TODO: fix movement speed
public class IgneousGolemEntity extends MonsterEntity
{
	public static final String NAME = "igneous_golem";

	public IgneousGolemEntity(EntityType<? extends IgneousGolemEntity> type, World world)
	{
		super(type, world);
	}

	@Override
	public void registerGoals()
	{
		goalSelector.addGoal(4, new MeleeFireAttackGoal(this, 0.6D, false));
		goalSelector.addGoal(6, new RandomSwimmingGoal(this, 1.0D, 100));
		goalSelector.addGoal(7, new RandomWalkingGoal(this, 0.6D));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
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
			double attakReachSqr = getAttackReachSqr(enemy);

			if (distToEnemySqr <= attakReachSqr && attackTick <= 0)
			{
				attackTick = 20;
				attacker.swingArm(Hand.MAIN_HAND);
				attacker.attackEntityAsMob(enemy);
				enemy.setFire(5);
			}
		}
	}
}
