package teamhollow.deepercaverns.world.generation.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;

public class DeeperCavernsBiomeFeatures {
	public static void addDryWells(Biome biome, BlockState slab, BlockState block, Block... validBottomBlocks) {
		ConfigurableWellConfig config = new ConfigurableWellConfig(slab, block, null, validBottomBlocks);
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(DeeperCavernsFeatures.CONFIGURABLE_WELL, config, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(1000 / 100))); // TODO possibly reduce the chance. A higher integer = lower chance. I think? Did not find any of these while exploring the terrain.
	}

	public static void addLavaWells(Biome biome, BlockState slab, BlockState block, Block... validBottomBlocks) {
		ConfigurableWellConfig config = new ConfigurableWellConfig(slab, block, Blocks.LAVA.getDefaultState(), validBottomBlocks);
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(DeeperCavernsFeatures.CONFIGURABLE_WELL, config, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(1000 / 100))); // TODO possibly reduce the chance. A higher integer = lower chance. I think? Did not find any of these while exploring the terrain.
	}

	public static void addWaterWells(Biome biome, BlockState slab, BlockState block, Block... validBottomBlocks) {
		ConfigurableWellConfig config = new ConfigurableWellConfig(slab, block, Blocks.WATER.getDefaultState(), validBottomBlocks);
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(DeeperCavernsFeatures.CONFIGURABLE_WELL, config, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(1000 / 100))); // TODO possibly reduce the chance. A higher integer = lower chance. I think? Did not find any of these while exploring the terrain.
	}

	public static void addWells(Biome biome, BlockState slab, BlockState block, Block... validBottomBlocks) {
		addDryWells(biome, slab, block, validBottomBlocks);
		addLavaWells(biome, slab, block, validBottomBlocks);
		addWaterWells(biome, slab, block, validBottomBlocks);
	}
}