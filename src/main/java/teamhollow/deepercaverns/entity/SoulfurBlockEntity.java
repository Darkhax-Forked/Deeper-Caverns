package teamhollow.deepercaverns.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;
import teamhollow.deepercaverns.misc.SoulfurExplosion;
import teamhollow.deepercaverns.reg.EntityRegistrar;

public class SoulfurBlockEntity extends TNTEntity
{
	public static final String NAME = "soulfur_block";

	public SoulfurBlockEntity(World world)
	{
		this(EntityRegistrar.SOULFUR_BLOCK, world);
	}

	public SoulfurBlockEntity(EntityType<? extends TNTEntity> type, World world)
	{
		super(type, world);

		preventEntitySpawning = true;
	}

	public SoulfurBlockEntity(World world, double x, double y, double z)
	{
		this(EntityRegistrar.SOULFUR_BLOCK, world);

		double randVal = world.rand.nextDouble() * ((float)Math.PI * 2F);

		setPosition(x, y, z);
		setMotion(-Math.sin(randVal) * 0.02D, 0.2F, -Math.cos(randVal) * 0.02D);
		setFuse(80);
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
	}

	@Override
	public void explode()
	{
		BlockPos pos = getPosition();
		Explosion explosion = new SoulfurExplosion(world, pos, 5);

		if(ForgeEventFactory.onExplosionStart(world, explosion))
			return;

		explosion.doExplosionA();
		explosion.doExplosionB(true);
		world.createExplosion(null, null, pos.getX(), pos.getY(), pos.getZ(), 12, true, Mode.NONE);
	}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
