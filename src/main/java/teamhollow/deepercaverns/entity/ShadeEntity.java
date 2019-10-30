package teamhollow.deepercaverns.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class ShadeEntity extends MonsterEntity
{
	public static final String NAME = "shade";

	public ShadeEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
	}
}
