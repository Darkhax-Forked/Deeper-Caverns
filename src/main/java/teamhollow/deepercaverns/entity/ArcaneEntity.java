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
import teamhollow.deepercaverns.entity.ai.ShootMagicProjectileGoal;
import teamhollow.deepercaverns.entity.ai.SuckLifeGoal;
import teamhollow.deepercaverns.entity.ai.ThrowEntityGoal;

public class ArcaneEntity extends MonsterEntity
{
	public static final String NAME = "arcane";
	private static final IParticleData PARTICLE_DATA = ParticleTypes.EFFECT;
	private static final int PARTICLE_COLOR = 0x2F0076;
	private static final float PARTICLE_R = (PARTICLE_COLOR >> 16 & 255) / 255.0F;
	private static final float PARTICLE_G = (PARTICLE_COLOR >> 8 & 255) / 255.0F;
	private static final float PARTICLE_B = (PARTICLE_COLOR >> 0 & 255) / 255.0F;

	public ArcaneEntity(EntityType<? extends ArcaneEntity> type, World world)
	{
		super(type, world);
	}

	@Override
	public void registerGoals()
	{
		goalSelector.addGoal(0, new ShootMagicProjectileGoal(this, 1.0D, 15.0F));
		goalSelector.addGoal(3, new ThrowEntityGoal(this));
		goalSelector.addGoal(8, new SuckLifeGoal(this, 6, 8, 0.25F, 0.33F));
		goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.8D));
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
			for(int i = 0; i < 5; i++)
			{
				double velocityMultiplier = rand.nextDouble() * 0.5D;
				double angle = rand.nextDouble() * Math.PI * 0.5D;
				double xSpeed = Math.cos(angle);
				double ySpeed = rand.nextDouble() * 0.5D;
				double zSpeed = Math.sin(angle);
				Particle particle = Minecraft.getInstance().particles.addParticle(PARTICLE_DATA, posX + xSpeed * 0.1D, posY + 0.3D, posZ + zSpeed * 0.1D, xSpeed, ySpeed, zSpeed);

				if(particle != null)
				{
					float colorOffset = 0.75F + rand.nextFloat() * 0.25F;

					particle.setColor(PARTICLE_R * colorOffset, PARTICLE_G * colorOffset, PARTICLE_B * colorOffset);
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
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}
}
