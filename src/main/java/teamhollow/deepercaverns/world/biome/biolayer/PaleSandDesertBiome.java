package teamhollow.deepercaverns.world.biome.biolayer;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import teamhollow.deepercaverns.misc.CustomFillerBlockType;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.EntityRegistrar;
import teamhollow.deepercaverns.world.generation.feature.DeeperCavernsBiomeFeatures;
import teamhollow.deepercaverns.world.generation.feature.DeeperCavernsFeatures;
import teamhollow.deepercaverns.world.generation.surfacebuilder.DeeperCavernsSurfaceBuilders;

public class PaleSandDesertBiome extends Biome {
	public PaleSandDesertBiome() {
		this(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, DeeperCavernsSurfaceBuilders.Config.PALE_SAND)
				.category(Category.NETHER)
				.depth(0.125F).scale(0.05F).temperature(2)
				.precipitation(RainType.NONE).downfall(0).waterColor(0x3f76e4).waterFogColor(0x50533)
				.parent(null));
	}

	protected PaleSandDesertBiome(Biome.Builder builder) {
		super(builder);
		DefaultBiomeFeatures.addCarvers(this);
		addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.LAVA.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(80 / 8))); // TODO possibly reduce the chance. A higher integer = lower chance
		DefaultBiomeFeatures.addMushrooms(this);
		addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.LAVA.getDefaultState()), Placement.COUNT_VERY_BIASED_RANGE, new CountRangeConfig(20, 8, 16, 256)));
		DeeperCavernsBiomeFeatures.addLavaWells(this, DeeperCavernsSurfaceBuilders.BlockStates.NETHER_BRICK_SLAB, DeeperCavernsSurfaceBuilders.BlockStates.NETHER_BRICKS, BlockRegistrar.PALE_SAND);
		DeeperCavernsBiomeFeatures.addDryWells(this, DeeperCavernsSurfaceBuilders.BlockStates.NETHER_BRICK_SLAB, DeeperCavernsSurfaceBuilders.BlockStates.NETHER_BRICKS, BlockRegistrar.PALE_SAND);
		addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(Feature.FOSSIL, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_PASSTHROUGH, new ChanceConfig(64 * 10000)));
		addFeature(Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(DeeperCavernsFeatures.BLUE_FIRE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.HELL_FIRE, new FrequencyConfig(10)));
		addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(CustomFillerBlockType.SOULSTONE, BlockRegistrar.ONYX_ORE.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 118)));
		addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityRegistrar.WITHER_CRUSHER, 1, 2, 5));
	}
}