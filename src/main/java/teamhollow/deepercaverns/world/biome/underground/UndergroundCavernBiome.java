package teamhollow.deepercaverns.world.biome.underground;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import teamhollow.deepercaverns.world.biome.CustomisableBiome;

// With all due credits to Gegy for their original idea of this on the Midnight mod
// TODO tweak and integrate with our world generation
public abstract class UndergroundCavernBiome extends ForgeRegistryEntry<UndergroundCavernBiome> implements CustomisableBiome {
	protected final ConfiguredSurfaceBuilder<?> surfaceBuilder;
	protected final float cavernDensity;
	protected final float floorHeight;
	protected final float ceilingHeight;
	protected final float heightScale;
	protected final float pillarWeight;

	protected final Multimap<Carving, ConfiguredCarver<?>> carvers = HashMultimap.create();
	protected final Multimap<GenerationStage.Decoration, ConfiguredFeature<?>> features = HashMultimap.create();
	protected final List<ConfiguredFeature<?>> flowers = new ArrayList<>();
	protected final Map<Structure<?>, IFeatureConfig> structures = new HashMap<>();
	protected final Map<EntityClassification, List<Biome.SpawnListEntry>> spawns = new HashMap<>();

	public UndergroundCavernBiome(UndergroundCavernBiomeSettings settings) {
		Preconditions.checkNotNull(settings.surfaceBuilder, "must have surfacebuilder");

		surfaceBuilder = settings.surfaceBuilder;
		cavernDensity = settings.cavernDensity;
		floorHeight = settings.floorHeight;
		ceilingHeight = settings.ceilingHeight;
		heightScale = settings.heightScale;
		pillarWeight = settings.pillarWeight;
	}

	@Override
	public void add(GenerationStage.Decoration stage, ConfiguredFeature<?> feature) {
		if (feature.feature == Feature.DECORATED_FLOWER) {
			flowers.add(feature);
		}
		features.put(stage, feature);
	}

	@Override
	public <C extends ICarverConfig> void add(GenerationStage.Carving stage, ConfiguredCarver<C> carver) {
		carvers.put(stage, carver);
	}

	@Override
	public <C extends IFeatureConfig> void add(Structure<C> structure, C config) {
		structures.put(structure, config);
	}

	@Override
	public void add(EntityClassification classification, Biome.SpawnListEntry entry) {
		getSpawnsFor(classification).add(entry);
	}

	@Override
	public void placeFeatures(GenerationStage.Decoration stage, ChunkGenerator<? extends GenerationSettings> generator, WorldGenRegion world, long seed, SharedSeedRandom random, BlockPos origin) {
		int index = 0;

		for (ConfiguredFeature<?> feature : features.get(stage)) {
			random.setFeatureSeed(seed, index, stage.ordinal());
			feature.place(world, generator, random, origin);

			index++;
		}
	}

	@Override
	public void generateSurface(SharedSeedRandom random, IChunk chunk, int x, int z, int startY, double depth, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed) {
		surfaceBuilder.setSeed(seed);
		surfaceBuilder.buildSurface(random, chunk, Biomes.DEFAULT, x, z, startY, depth, defaultBlock, defaultFluid, seaLevel, seed);
	}

	@Override
	public Collection<ConfiguredCarver<?>> getCarversFor(GenerationStage.Carving stage) {
		return carvers.get(stage);
	}

	@Override
	public List<Biome.SpawnListEntry> getSpawnsFor(EntityClassification classification) {
		return spawns.computeIfAbsent(classification, c -> new ArrayList<>());
	}

	public float getCavernDensity() {
		return cavernDensity;
	}

	public float getFloorHeight() {
		return floorHeight;
	}

	public float getCeilingHeight() {
		return ceilingHeight;
	}

	public float getHeightScale() {
		return heightScale;
	}

	public float getPillarWeight() {
		return pillarWeight;
	}
}
