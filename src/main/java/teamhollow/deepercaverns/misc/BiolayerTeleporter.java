package teamhollow.deepercaverns.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import teamhollow.deepercaverns.world.dimension.DeeperCavernsDimensions;

//derived from the midnight's teleporter
public class BiolayerTeleporter
{
	public static ServerWorld teleport(Entity entity)
	{
		MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);

		if(server == null)
			return null;

		DimensionType destDim = getEndpointDimension(entity.dimension);
		ServerWorld destWorld = server.getWorld(destDim);
		BlockPos pos = entity.getPosition();
		IChunk chunk = destWorld.getChunk(pos);
		int surfaceY = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 1;
		Vec3d destPos = new Vec3d(pos.getX() + 0.5, surfaceY, pos.getZ() + 0.5);
		Entity teleportedEntity = teleportEntity(entity, destWorld, destPos);

		teleportedEntity.fallDistance = 0.0F;
		return destWorld;
	}

	private static Entity teleportEntity(Entity entity, ServerWorld destWorld, Vec3d destPos)
	{
		if(entity instanceof ServerPlayerEntity)
		{
			ServerPlayerEntity player = (ServerPlayerEntity)entity;

			player.teleport(destWorld, destPos.x, destPos.y, destPos.z, entity.rotationYaw, entity.rotationPitch);
			return player;
		}

		entity.detach();
		entity.dimension = destWorld.dimension.getType();

		Entity teleportedEntity = entity.getType().create(destWorld);

		if(teleportedEntity == null)
			return entity;

		teleportedEntity.copyDataFromOld(entity);
		teleportedEntity.setLocationAndAngles(destPos.x, destPos.y, destPos.z, entity.rotationYaw, entity.rotationPitch);
		teleportedEntity.setRotationYawHead(entity.rotationYaw);
		destWorld.func_217460_e(teleportedEntity);
		return teleportedEntity;
	}

	private static DimensionType getEndpointDimension(DimensionType source)
	{
		return source.getModType() == DeeperCavernsDimensions.BIOLAYER ? DimensionType.OVERWORLD : DimensionType.byName(DeeperCavernsDimensions.BIOLAYER_NAME);
	}
}
