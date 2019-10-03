package teamhollow.deepercaverns.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class GlurkerEntity extends MonsterEntity
{
	public static final String NAME = "glurker";

	public GlurkerEntity(EntityType<? extends GlurkerEntity> type, World world)
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
	}
}
