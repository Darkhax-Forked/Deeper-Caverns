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
import teamhollow.deepercaverns.entity.ArcaneEntity;
import teamhollow.deepercaverns.entity.IgneousGolemEntity;
import teamhollow.deepercaverns.entity.RockGolemEntity;
import teamhollow.deepercaverns.entity.SurgeflyEntity;
import teamhollow.deepercaverns.entity.WitherCrusherEntity;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
public class EntityRegistrar
{
	public static final PlacementType ON_LAVA = PlacementType.create("ON_LAVA", (world, pos, type) -> {
		BlockPos posUp = pos.up();

		return world.getFluidState(pos).isTagged(FluidTags.LAVA) && !world.getBlockState(posUp).isAir(world, posUp);
	});
	public static final EntityType<ArcaneEntity> ARCANE = (EntityType<ArcaneEntity>)EntityType.Builder.<ArcaneEntity>create(ArcaneEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + ArcaneEntity.NAME).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, ArcaneEntity.NAME));
	public static final EntityType<IgneousGolemEntity> IGNEOUS_GOLEM = (EntityType<IgneousGolemEntity>)EntityType.Builder.<IgneousGolemEntity>create(IgneousGolemEntity::new, EntityClassification.MONSTER).immuneToFire().size(1.25F, 2.5F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + IgneousGolemEntity.NAME).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, IgneousGolemEntity.NAME));
	public static final EntityType<RockGolemEntity> ROCK_GOLEM = (EntityType<RockGolemEntity>)EntityType.Builder.<RockGolemEntity>create(RockGolemEntity::new, EntityClassification.MONSTER).size(1.0F, 1.0F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + RockGolemEntity.NAME).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, RockGolemEntity.NAME));
	public static final EntityType<SurgeflyEntity> SURGEFLY = (EntityType<SurgeflyEntity>)EntityType.Builder.<SurgeflyEntity>create(SurgeflyEntity::new, EntityClassification.MONSTER).immuneToFire().size(3.5F, 1.5F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + SurgeflyEntity.NAME).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, SurgeflyEntity.NAME));
	public static final EntityType<WitherCrusherEntity> WITHER_CRUSHER = (EntityType<WitherCrusherEntity>)EntityType.Builder.<WitherCrusherEntity>create(WitherCrusherEntity::new, EntityClassification.MONSTER).immuneToFire().size(1.5F, 3.3F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + WitherCrusherEntity.NAME).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, WitherCrusherEntity.NAME));

	@SubscribeEvent
	public static void onRegisterEntityTypes(RegistryEvent.Register<EntityType<?>> event)
	{
		event.getRegistry().register(ARCANE);
		event.getRegistry().register(IGNEOUS_GOLEM);
		event.getRegistry().register(ROCK_GOLEM);
		event.getRegistry().register(SURGEFLY);
		event.getRegistry().register(WITHER_CRUSHER);

		EntitySpawnPlacementRegistry.register(IGNEOUS_GOLEM, ON_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityRegistrar::notPeaceful);
	}

	@SubscribeEvent
	public static void onFMLLoadComplete(FMLLoadCompleteEvent event)
	{
		Biomes.NETHER.getSpawns(EntityClassification.MONSTER).add(new SpawnListEntry(IGNEOUS_GOLEM, 50, 1, 1)); //TODO: not quite working yet
	}

	private static boolean notPeaceful(EntityType<? extends Entity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random)
	{
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}
}
