package teamhollow.deepercaverns.world.biome.biolayer;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HellLavaConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import teamhollow.deepercaverns.misc.CustomFillerBlockType;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.EntityRegistrar;
import teamhollow.deepercaverns.world.generation.surfacebuilder.DeeperCavernsSurfaceBuilders;

public class BiolayerHellBiome extends Biome {
	public BiolayerHellBiome() {
		super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, DeeperCavernsSurfaceBuilders.Config.HELL)
				.category(Category.NETHER)
				.depth(-0.6F).scale(0.5F).temperature(2) // TODO find out if these values for depth and scale are good
				.precipitation(RainType.NONE).downfall(0).waterColor(0x3f76e4).waterFogColor(0x50533)
				.parent(null));
		//addStructure(Feature.DESERT_PYRAMID, IFeatureConfig.NO_FEATURE_CONFIG);
		//addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004, Type.NORMAL));
		//addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
		addCarver(Carving.AIR, createCarver(WorldCarver.HELL_CAVE, new ProbabilityConfig(0.2F * 2)));
		addCarver(Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F * 2)));
		addCarver(Carving.AIR, Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.02F * 2)));
		//DefaultBiomeFeatures.addStructures(this);
		//DefaultBiomeFeatures.addMonsterRooms(this);
		//DefaultBiomeFeatures.addStoneVariants(this);
		// TODO DefaultBiomeFeatures.addOres(this);
		//DefaultBiomeFeatures.addSedimentDisks(this);
		// TODO ? DefaultBiomeFeatures.addDefaultFlowers(this);
		addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.LAVA.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(80 / 8))); // TODO possibly reduce the chance. A higher integer = lower chance
		addFeature(Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.LAVA.getDefaultState()), Placement.COUNT_VERY_BIASED_RANGE, new CountRangeConfig(20, 8, 16, 256)));
		//DefaultBiomeFeatures.addDeadBushes(this);
		DefaultBiomeFeatures.addMushrooms(this);
		//DefaultBiomeFeatures.addExtraReedsPumpkinsCactus(this);
		//DefaultBiomeFeatures.addSprings(this);
		//addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.NETHER_BRIDGE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.NETHER_SPRING, new HellLavaConfig(false), Placement.COUNT_RANGE, new CountRangeConfig(8, 4, 8, 128)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.HELL_FIRE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.HELL_FIRE, new FrequencyConfig(10)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.GLOWSTONE_BLOB, IFeatureConfig.NO_FEATURE_CONFIG, Placement.LIGHT_GEM_CHANCE, new FrequencyConfig(10)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.GLOWSTONE_BLOB, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_RANGE, new CountRangeConfig(10, 0, 0, 128)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.BROWN_MUSHROOM.getDefaultState()), Placement.CHANCE_RANGE, new ChanceRangeConfig(0.5F, 0, 0, 128)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.BUSH, new BushConfig(Blocks.RED_MUSHROOM.getDefaultState()), Placement.CHANCE_RANGE, new ChanceRangeConfig(0.5F, 0, 0, 128)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 14), Placement.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NETHERRACK, Blocks.MAGMA_BLOCK.getDefaultState(), 33), Placement.MAGMA, new FrequencyConfig(4)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.NETHER_SPRING, new HellLavaConfig(true), Placement.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128)));
		addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(CustomFillerBlockType.SOULSTONE, BlockRegistrar.SOUL_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 118)));
		addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(CustomFillerBlockType.SOULSTONE, BlockRegistrar.ONYX_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 118)));
		//addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.GHAST, 50, 4, 4));
		//addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_PIGMAN, 100, 4, 4));
		addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.MAGMA_CUBE, 50, 4, 4));
		addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityRegistrar.IGNEOUS_GOLEM, 10, 1, 3));
		//DefaultBiomeFeatures.addDesertFeatures(this);
		//DefaultBiomeFeatures.addFreezeTopLayer(this);
	}
}