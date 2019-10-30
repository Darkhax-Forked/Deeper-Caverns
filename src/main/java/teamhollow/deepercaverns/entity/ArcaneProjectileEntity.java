package teamhollow.deepercaverns.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import teamhollow.deepercaverns.misc.CustomDamageSources;
import teamhollow.deepercaverns.reg.EntityRegistrar;

public class ArcaneProjectileEntity extends ThrowableEntity
{
	public static final String NAME = "arcane_projectile";

	public ArcaneProjectileEntity(EntityType<? extends ThrowableEntity> type, World world)
	{
		super(type, world);
	}

	public ArcaneProjectileEntity(World world, LivingEntity shooter)
	{
		super(EntityRegistrar.ARCANE_PROJECTILE, shooter, world);
	}

	@Override
	protected void onImpact(RayTraceResult baseResult)
	{
		if(!world.isRemote)
		{
			if(baseResult.getType() == Type.ENTITY)
			{
				EntityRayTraceResult result = (EntityRayTraceResult)baseResult;

				//if player is not survival mode
				if(result.getEntity() instanceof ServerPlayerEntity && ((ServerPlayerEntity)result.getEntity()).interactionManager.getGameType() != GameType.SURVIVAL)
					return;

				if(result.getEntity() instanceof LivingEntity && (LivingEntity)result.getEntity() != getThrower())
					result.getEntity().attackEntityFrom(CustomDamageSources.ARCANE_PROJECTILE, 4.0F);
			}
		}

		remove();
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.0F;
	}

	@Override
	protected void registerData() {}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
