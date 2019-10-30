package teamhollow.deepercaverns.reg;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.entity.ArcaneEntity;
import teamhollow.deepercaverns.entity.ArcaneProjectileEntity;
import teamhollow.deepercaverns.entity.GlurkerEntity;
import teamhollow.deepercaverns.entity.IgneousGolemEntity;
import teamhollow.deepercaverns.entity.RockGolemEntity;
import teamhollow.deepercaverns.entity.ShadeEntity;
import teamhollow.deepercaverns.entity.SoulbrynBlockEntity;
import teamhollow.deepercaverns.entity.SurgeflyEntity;
import teamhollow.deepercaverns.entity.WitherCrusherEntity;

@EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.MOD)
public class EntityRegistrar {
	public static final PlacementType ON_LAVA = PlacementType.create("ON_LAVA", (world, pos, type) -> {
		BlockPos posUp = pos.up();

		return world.getFluidState(pos).isTagged(FluidTags.LAVA) && !world.getBlockState(posUp).isAir(world, posUp);
	});
	public static final EntityType<ArcaneEntity> ARCANE = (EntityType<ArcaneEntity>)EntityType.Builder.<ArcaneEntity>create(ArcaneEntity::new, EntityClassification.MONSTER).size(0.9F, 2.2F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + ArcaneEntity.NAME).setRegistryName(ArcaneEntity.NAME);
	public static final EntityType<ArcaneProjectileEntity> ARCANE_PROJECTILE = (EntityType<ArcaneProjectileEntity>)EntityType.Builder.<ArcaneProjectileEntity>create(ArcaneProjectileEntity::new, EntityClassification.MONSTER).size(0.1F, 0.1F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + ArcaneProjectileEntity.NAME).setRegistryName(ArcaneProjectileEntity.NAME);
	public static final EntityType<GlurkerEntity> GLURKER = (EntityType<GlurkerEntity>)EntityType.Builder.<GlurkerEntity>create(GlurkerEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + GlurkerEntity.NAME).setRegistryName(GlurkerEntity.NAME);
	public static final EntityType<IgneousGolemEntity> IGNEOUS_GOLEM = (EntityType<IgneousGolemEntity>)EntityType.Builder.<IgneousGolemEntity>create(IgneousGolemEntity::new, EntityClassification.MONSTER).immuneToFire().size(1.25F, 2.5F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + IgneousGolemEntity.NAME).setRegistryName(IgneousGolemEntity.NAME);
	public static final EntityType<RockGolemEntity> ROCK_GOLEM = (EntityType<RockGolemEntity>)EntityType.Builder.<RockGolemEntity>create(RockGolemEntity::new, EntityClassification.MONSTER).size(1.0F, 1.0F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + RockGolemEntity.NAME).setRegistryName(RockGolemEntity.NAME);
	public static final EntityType<SoulbrynBlockEntity> SOULBRYN_BLOCK = (EntityType<SoulbrynBlockEntity>)EntityType.Builder.<SoulbrynBlockEntity>create(SoulbrynBlockEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) -> new SoulbrynBlockEntity(world)).immuneToFire().size(0.98F, 0.98F).build(DeeperCaverns.PREFIX + SoulbrynBlockEntity.NAME).setRegistryName(SoulbrynBlockEntity.NAME);
	public static final EntityType<ShadeEntity> SHADE = (EntityType<ShadeEntity>)EntityType.Builder.<ShadeEntity>create(ShadeEntity::new, EntityClassification.MONSTER).immuneToFire().size(0.5F, 1.1F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + ShadeEntity.NAME).setRegistryName(ShadeEntity.NAME);
	public static final EntityType<SurgeflyEntity> SURGEFLY = (EntityType<SurgeflyEntity>)EntityType.Builder.<SurgeflyEntity>create(SurgeflyEntity::new, EntityClassification.MONSTER).immuneToFire().size(3.5F, 1.5F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + SurgeflyEntity.NAME).setRegistryName(SurgeflyEntity.NAME);
	public static final EntityType<WitherCrusherEntity> WITHER_CRUSHER = (EntityType<WitherCrusherEntity>)EntityType.Builder.<WitherCrusherEntity>create(WitherCrusherEntity::new, EntityClassification.MONSTER).immuneToFire().size(1.5F, 3.3F).setTrackingRange(128).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).build(DeeperCaverns.PREFIX + WitherCrusherEntity.NAME).setRegistryName(WitherCrusherEntity.NAME);

	@SubscribeEvent
	public static void onRegisterEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(ARCANE);
		event.getRegistry().register(ARCANE_PROJECTILE);
		event.getRegistry().register(GLURKER);
		event.getRegistry().register(IGNEOUS_GOLEM);
		event.getRegistry().register(ROCK_GOLEM);
		event.getRegistry().register(SOULBRYN_BLOCK);
		event.getRegistry().register(SHADE);
		event.getRegistry().register(SURGEFLY);
		event.getRegistry().register(WITHER_CRUSHER);

		// TODO see if we need more specific entity placement conditions.
		EntitySpawnPlacementRegistry.register(ARCANE, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityRegistrar::notPeaceful);
		EntitySpawnPlacementRegistry.register(IGNEOUS_GOLEM, ON_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityRegistrar::notPeaceful);
		EntitySpawnPlacementRegistry.register(WITHER_CRUSHER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityRegistrar::notPeaceful);
	}

	private static boolean notPeaceful(EntityType<? extends Entity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}
}
