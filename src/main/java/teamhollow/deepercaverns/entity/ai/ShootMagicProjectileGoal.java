package teamhollow.deepercaverns.entity.ai;

import java.util.EnumSet;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import teamhollow.deepercaverns.entity.ArcaneEntity;
import teamhollow.deepercaverns.entity.ArcaneProjectileEntity;

//copied from RangedBowAttackGoal
public class ShootMagicProjectileGoal extends Goal
{
	private static final int DEFAULT_ATTACK_COOLDOWN = 120;
	private final ArcaneEntity arcane;
	private final double moveSpeedAmp;
	private final float maxAttackDistance;
	private int seeTime;
	private boolean strafingClockwise;
	private boolean strafingBackwards;
	private int strafingTime = -1;
	private int attackCooldown = DEFAULT_ATTACK_COOLDOWN;

	public ShootMagicProjectileGoal(ArcaneEntity arcane, double moveSpeedAmp, float maxAttackDistance)
	{
		this.arcane = arcane;
		this.moveSpeedAmp = moveSpeedAmp;
		this.maxAttackDistance = maxAttackDistance * maxAttackDistance;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute()
	{
		return !(arcane.getAttackTarget() instanceof ArcaneEntity); //buy one instanceof, get one null check for free
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return (shouldExecute() || !arcane.getNavigator().noPath());
	}

	@Override
	public void startExecuting()
	{
		super.startExecuting();

		arcane.setAggroed(true);
	}

	@Override
	public void resetTask()
	{
		super.resetTask();

		arcane.setAggroed(false);
		seeTime = 0;
	}

	@Override
	public void tick()
	{
		attackCooldown--;

		if(attackCooldown == 0)
		{
			LivingEntity attackTarget = arcane.getAttackTarget();

			if(attackTarget != null)
			{
				double distanceToTarget = arcane.getDistanceSq(attackTarget.posX, attackTarget.getBoundingBox().minY, attackTarget.posZ);
				boolean canSeeTarget = arcane.getEntitySenses().canSee(attackTarget);
				boolean flag1 = seeTime > 0;

				if(canSeeTarget != flag1)
					seeTime = 0;

				if(canSeeTarget)
					++seeTime;
				else
					--seeTime;

				if(!(distanceToTarget > maxAttackDistance) && seeTime >= 20)
				{
					arcane.getNavigator().clearPath();
					++strafingTime;
				}
				else
				{
					arcane.getNavigator().tryMoveToEntityLiving(attackTarget, moveSpeedAmp);
					strafingTime = -1;
				}

				if(strafingTime >= 20)
				{
					if(arcane.getRNG().nextFloat() < 0.3D)
						strafingClockwise = !strafingClockwise;

					if(arcane.getRNG().nextFloat() < 0.3D)
						strafingBackwards = !strafingBackwards;

					strafingTime = 0;
				}

				if(strafingTime > -1)
				{
					if(distanceToTarget > maxAttackDistance * 0.75F)
						strafingBackwards = false;
					else if(distanceToTarget < maxAttackDistance * 0.25F)
						strafingBackwards = true;

					arcane.getMoveHelper().strafe(strafingBackwards ? -0.5F : 0.5F, strafingClockwise ? 0.5F : -0.5F);
					arcane.faceEntity(attackTarget, 30.0F, 30.0F);
				}
				else
					arcane.getLookController().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);

				if(canSeeTarget)
				{
					ArcaneProjectileEntity projectile = new ArcaneProjectileEntity(arcane.world, arcane);

					projectile.shoot(arcane, arcane.rotationPitch, arcane.rotationYaw, 0.0F, 2.0F, 0.0F);
					//TODO: sound?
					arcane.world.addEntity(projectile);
					attackCooldown = DEFAULT_ATTACK_COOLDOWN;
				}
			}
		}
	}
}