package teamhollow.deepercaverns.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class SurgeflyEntity extends FlyingEntity implements IMob
{
	public static final String NAME = "surgefly";

	public SurgeflyEntity(EntityType<? extends SurgeflyEntity> type, World world)
	{
		super(type, world);
	}

	@Override
	public void registerGoals()
	{
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if(entity instanceof LivingEntity)
			((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 5, 10));

		return super.attackEntityAsMob(entity);
	}

	@Override
	public void tick()
	{
		super.tick();

		if(!world.isRemote && world.getDifficulty() == Difficulty.PEACEFUL)
			remove();
	}
}
