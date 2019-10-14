package teamhollow.deepercaverns.world.biome;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.Collection;
import java.util.List;

public interface CustomisableBiome {
	void add(GenerationStage.Decoration stage, ConfiguredFeature<?> feature);

	<C extends ICarverConfig> void add(GenerationStage.Carving stage, ConfiguredCarver<C> carver);

	<C extends IFeatureConfig> void add(Structure<C> structure, C config);

	void add(EntityClassification classification, Biome.SpawnListEntry entry);

	void placeFeatures(GenerationStage.Decoration stage, ChunkGenerator<? extends GenerationSettings> generator, WorldGenRegion world, long seed, SharedSeedRandom random, BlockPos origin);

	void generateSurface(SharedSeedRandom random, IChunk chunk, int x, int z, int y, double depth, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed);

	Collection<ConfiguredCarver<?>> getCarversFor(GenerationStage.Carving stage);

	List<SpawnListEntry> getSpawnsFor(EntityClassification classification);
}
