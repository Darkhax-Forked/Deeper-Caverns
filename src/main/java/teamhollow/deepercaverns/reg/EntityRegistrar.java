package teamhollow.deepercaverns.reg;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.entity.IgneousGolemEntity;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
public class EntityRegistrar
{
	public static final PlacementType ON_LAVA = PlacementType.create("ON_LAVA", (world, pos, type) -> {
		BlockPos posUp = pos.up();

		return world.getFluidState(pos).isTagged(FluidTags.LAVA) && !world.getBlockState(posUp).isAir(world, posUp);
	});
	public static final EntityType<IgneousGolemEntity> IGNEOUS_GOLEM = (EntityType<IgneousGolemEntity>)EntityType.Builder.<IgneousGolemEntity>create(IgneousGolemEntity::new, EntityClassification.MONSTER).immuneToFire().size(1.25F, 2.5F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + IgneousGolemEntity.NAME).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, IgneousGolemEntity.NAME));

	@SubscribeEvent
	public static void onRegisterEntityTypes(RegistryEvent.Register<EntityType<?>> event)
	{
		event.getRegistry().register(IGNEOUS_GOLEM);

		EntitySpawnPlacementRegistry.register(IGNEOUS_GOLEM, ON_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityRegistrar::notPeaceful);
	}

	@SubscribeEvent
	public static void onLoadComplete(FMLLoadCompleteEvent event)
	{
		//TODO: not quite working yet
		Biomes.NETHER.getSpawns(EntityClassification.MONSTER).add(new SpawnListEntry(IGNEOUS_GOLEM, 50, 1, 1));
	}

	private static boolean notPeaceful(EntityType<? extends Entity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random)
	{
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}
}
