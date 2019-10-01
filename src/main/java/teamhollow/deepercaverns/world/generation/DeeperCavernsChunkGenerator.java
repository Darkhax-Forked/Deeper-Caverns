package teamhollow.deepercaverns.world.generation;

import mcp.MethodsReturnNonnullByDefault;

import net.minecraft.entity.EntityClassification;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DeeperCavernsChunkGenerator extends NoiseChunkGenerator<DeeperCavernsGenerationSettings> {
	private static final float[] BIOME_WEIGHTS = Util.make(new float[25], (weights) -> {
		for (int x = -2; x <= 2; ++x) {
			for (int z = -2; z <= 2; ++z) {
				float weight = 10.0F / MathHelper.sqrt((float) (x * x + z * z) + 0.2F);
				weights[x + 2 + (z + 2) * 5] = weight;
			}
		}
	});

	public static final int SURFACE_LEVEL = 61;
	public static final int SEA_LEVEL = SURFACE_LEVEL + 2;

	private final OctavesNoiseGenerator depthNoise;
	private final boolean amplified;
	//private final PhantomSpawner phantomSpawner = new PhantomSpawner();
	//private final PatrolSpawner patrolSpawner = new PatrolSpawner();
	//private final CatSpawner catSpawner = new CatSpawner();
	//private final VillageSiege field_225495_n = new VillageSiege();

	public DeeperCavernsChunkGenerator(World world, BiomeProvider biomeProvider, DeeperCavernsGenerationSettings settings) {
		super(world, biomeProvider, 4, 8, 256, settings, true);

		depthNoise = new OctavesNoiseGenerator(randomSeed, 16);
		amplified = world.getWorldInfo().getGenerator() == WorldType.AMPLIFIED;
	}

	// Start noise generation

	/**
	 * fillNoiseColumn
	 */
	@Override
	protected void func_222548_a(double[] noise, int chunkX, int chunkZ) {
		// TODO replace with our own noise generator
		double coordinateScale = 684.412;
		double heightScale = 684.412;
		double extraCoordinateScale = 8.555149841308594;
		double extraHeightScale = 4.277574920654297;
		int slideDividend = 3;
		int slideTooBigUpperBound = -10;
		func_222546_a(noise, chunkX, chunkZ, coordinateScale, heightScale, extraCoordinateScale, extraHeightScale, slideDividend, slideTooBigUpperBound);
	}

	/**
	 * getYOffset
	 */
	@Override
	protected double func_222545_a(double x, double z, int index) {
		// TODO unsupported with custom noise generator
		double d0 = 8.5D;
		double yOffset = ((double) index - (d0 + x * d0 / 8.0 * 4.0)) * 12.0 * 128.0 / 256.0 / z;
		if (yOffset < 0.0D) {
			yOffset *= 4.0D;
		}
		return yOffset;
	}

	/**
	 * getDepthAndScale
	 */
	@Override
	protected double[] func_222549_a(int x, int z) {
		// TODO unsupported with custom noise generator
		double[] depthAndScale = new double[2];
		float scale = 0.0F;
		float depth = 0.0F;
		float biomeWeight = 0.0F;

		int sampleSize = 2;
		float biomeDepth = biomeProvider.func_222366_b(x, z).getDepth();

		for (int sampleX = -sampleSize; sampleX <= sampleSize; sampleX++) {
			for (int sampleZ = -sampleSize; sampleZ <= sampleSize; sampleZ++) {
				Biome sampleBiome = biomeProvider.func_222366_b(x + sampleX, z + sampleZ);
				float sampleBiomeDepth = sampleBiome.getDepth();
				float sampleBiomeScale = sampleBiome.getScale();
				if (amplified && sampleBiomeDepth > 0.0F) {
					sampleBiomeDepth = 1.0F + sampleBiomeDepth * 2.0F;
					sampleBiomeScale = 1.0F + sampleBiomeScale * 4.0F;
				}

				float sampleBiomeWeight = BIOME_WEIGHTS[sampleX + 2 + (sampleZ + 2) * 5] / (sampleBiomeDepth + 2.0F);
				if (sampleBiome.getDepth() > biomeDepth) {
					sampleBiomeWeight /= 2.0F;
				}

				scale += sampleBiomeScale * sampleBiomeWeight;
				depth += sampleBiomeDepth * sampleBiomeWeight;
				biomeWeight += sampleBiomeWeight;
			}
		}

		scale = scale / biomeWeight;
		depth = depth / biomeWeight;
		scale = scale * 0.9F + 0.1F;
		depth = (depth * 4.0F - 1.0F) / 8.0F;
		depthAndScale[0] = (double) depth + getNoiseDepth(x, z);
		depthAndScale[1] = scale;
		return depthAndScale;
	}

	private double getNoiseDepth(int x, int z) {
		double noise = depthNoise.func_215462_a(x * 200, 10.0, z * 200, 1.0, 0.0, true) / 8000.0D;
		if (noise < 0.0D) {
			noise = -noise * 0.3D;
		}

		noise = noise * 3.0D - 2.0D;
		if (noise < 0.0D) {
			noise = noise / 28.0D;
		} else {
			if (noise > 1.0D) {
				noise = 1.0D;
			}
			noise = noise / 40.0D;
		}

		return noise;
	}

	// End noise generation

	@Override
	public void spawnMobs(WorldGenRegion region) {
		int chunkX = region.getMainChunkX();
		int chunkZ = region.getMainChunkZ();
		Biome biome = region.getChunk(chunkX, chunkZ).getBiomes()[0];
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setDecorationSeed(region.getSeed(), chunkX << 4, chunkZ << 4);
		WorldEntitySpawner.performWorldGenSpawning(region, biome, chunkX, chunkZ, sharedseedrandom);
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EntityClassification creatureType, BlockPos pos) {
		/*
		if (Feature.SWAMP_HUT.func_202383_b(world, pos)) {
			if (creatureType == EntityClassification.MONSTER) {
				return Feature.SWAMP_HUT.getSpawnList();
			}

			if (creatureType == EntityClassification.CREATURE) {
				return Feature.SWAMP_HUT.getCreatureSpawnList();
			}
		} else if (creatureType == EntityClassification.MONSTER) {
			if (Feature.PILLAGER_OUTPOST.isPositionInStructure(world, pos)) {
				return Feature.PILLAGER_OUTPOST.getSpawnList();
			}

			if (Feature.OCEAN_MONUMENT.isPositionInStructure(world, pos)) {
				return Feature.OCEAN_MONUMENT.getSpawnList();
			}
		}*/

		return super.getPossibleCreatures(creatureType, pos);
	}

	@Override
	public void spawnMobs(ServerWorld worldIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {
		//phantomSpawner.tick(worldIn, spawnHostileMobs, spawnPeacefulMobs);
		//patrolSpawner.tick(worldIn, spawnHostileMobs, spawnPeacefulMobs);
		//catSpawner.tick(worldIn, spawnHostileMobs, spawnPeacefulMobs);
		//field_225495_n.func_225477_a(worldIn, spawnHostileMobs, spawnPeacefulMobs);
	}

	@Override
	public int getGroundHeight() {
		return world.getSeaLevel() + 1;
	}

	@Override
	public int getSeaLevel() {
		return SEA_LEVEL;
	}
}
