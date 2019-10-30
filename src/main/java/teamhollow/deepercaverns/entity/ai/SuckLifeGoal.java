package teamhollow.deepercaverns.entity.ai;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;
import teamhollow.deepercaverns.entity.ArcaneEntity;
import teamhollow.deepercaverns.misc.CustomDamageSources;

public class SuckLifeGoal extends MeleeAttackGoal
{
	private final Random rand = new Random();
	private final int minHPDrain, maxHPDrain;
	private final float minHPHealPercentage, maxHPHealPercentage;

	public SuckLifeGoal(ArcaneEntity arcane, int minHPDrain, int maxHPDrain, float minHPHealPercentage, float maxHPHealPercentage)
	{
		super(arcane, 0.8D, true);
		this.minHPDrain = Math.min(minHPDrain, maxHPDrain);
		this.maxHPDrain = Math.max(minHPDrain, maxHPDrain);
		this.minHPHealPercentage = Math.min(minHPHealPercentage, maxHPHealPercentage);
		this.maxHPHealPercentage = Math.max(minHPHealPercentage, maxHPHealPercentage);
	}

	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && !(attacker.getAttackTarget() instanceof ArcaneEntity);
	}

	@Override
	public void tick()
	{
		if(attacker.getAttackTarget() != null) //safeguard, can crash without
			super.tick();
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr)
	{
		double attackReachSquared = getAttackReachSqr(enemy);

		if(distToEnemySqr <= attackReachSquared && attackTick <= 0)
		{
			int damageAmount = rand.nextInt(maxHPDrain - minHPDrain) + minHPDrain;
			float healAmount = (minHPHealPercentage + rand.nextFloat() * (maxHPHealPercentage - minHPHealPercentage)) * damageAmount;

			attackTick = 20;
			attacker.swingArm(Hand.MAIN_HAND);
			attacker.getAttackTarget().attackEntityFrom(CustomDamageSources.ARCANE_SUCKS_LIFE, damageAmount);
			attacker.heal(healAmount);
		}
	}
}
