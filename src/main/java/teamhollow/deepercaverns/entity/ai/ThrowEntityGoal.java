package teamhollow.deepercaverns.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import teamhollow.deepercaverns.entity.ArcaneEntity;

public class ThrowEntityGoal extends Goal
{
	private static final int DEFAULT_THROW_DELAY = 20 * 5; //wait 5 seconds before throwing
	private static final Vec3d THROW_MULTIPLIER = new Vec3d(25.0D, 1.0D, 25.0D); //seem to be good numbers
	private final ArcaneEntity arcane;
	private int throwDelay = DEFAULT_THROW_DELAY;

	public ThrowEntityGoal(ArcaneEntity arcane)
	{
		this.arcane = arcane;
	}

	@Override
	public void tick()
	{
		if(throwDelay == 0)
		{
			LivingEntity target = arcane.getAttackTarget();
			Vec3d toAttacker = arcane.getPositionVec().add(arcane.getLookVec()).mul(-1.0D, 1.0D, -1.0D).add(target.getPositionVec());

			target.setMotion(toAttacker.normalize().mul(THROW_MULTIPLIER));
			arcane.setAttackTarget(null);
		}
		else
			throwDelay--;
	}

	@Override
	public boolean shouldExecute()
	{
		LivingEntity target = arcane.getAttackTarget();

		return target != null && target.isAlive() && !(target instanceof ArcaneEntity);
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		LivingEntity target = arcane.getAttackTarget();

		if(target == null || !target.isAlive() || target instanceof ArcaneEntity)
			return false;
		else return !(target instanceof PlayerEntity) || !target.isSpectator() && !((PlayerEntity)target).isCreative();
	}

	@Override
	public void resetTask()
	{
		throwDelay = DEFAULT_THROW_DELAY;
	}
}
