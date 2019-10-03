package teamhollow.deepercaverns.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class RockGolemEntity extends MonsterEntity
{
	public static final String NAME = "rock_golem";

	public RockGolemEntity(EntityType<? extends RockGolemEntity> type, World world)
	{
		super(type, world);
	}

	@Override
	public void registerGoals()
	{
		goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		targetSelector.addGoal(2, new TargetClosePlayerGoal(this, PlayerEntity.class, true));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
	}

	class TargetClosePlayerGoal extends NearestAttackableTargetGoal<PlayerEntity>
	{
		public TargetClosePlayerGoal(MobEntity goalOwner, Class<PlayerEntity> targetClass, boolean checkSight)
		{
			super(goalOwner, targetClass, checkSight);
		}

		@Override
		protected double getTargetDistance()
		{
			return 5.0D;
		}
	}
}
