package teamhollow.deepercaverns.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
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
	public void livingTick()
	{
		super.livingTick();

		if(world.isRemote)
		{
			IParticleData data = ParticleTypes.EFFECT;

			int particleColor = 0x2F0076;
			float r = (particleColor >> 16 & 255) / 255.0F;
			float g = (particleColor >> 8 & 255) / 255.0F;
			float b = (particleColor >> 0 & 255) / 255.0F;

			for(int i = 0; i < 5; i++)
			{
				double velocityMultiplier = rand.nextDouble() * 0.5D;
				double angle = rand.nextDouble() * Math.PI * 0.5D;
				double xSpeed = Math.cos(angle);
				double ySpeed = rand.nextDouble() * 0.5D;
				double zSpeed = Math.sin(angle);
				Particle particle = Minecraft.getInstance().particles.addParticle(data, posX + xSpeed * 0.1D, posY + 0.3D, posZ + zSpeed * 0.1D, xSpeed, ySpeed, zSpeed);

				if(particle != null)
				{
					float colorOffset = 0.75F + rand.nextFloat() * 0.25F;

					particle.setColor(r * colorOffset, g * colorOffset, b * colorOffset);
					particle.multiplyVelocity((float)velocityMultiplier);
				}
			}
		}
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}
}
