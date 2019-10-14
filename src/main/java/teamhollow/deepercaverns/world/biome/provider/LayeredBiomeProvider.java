package teamhollow.deepercaverns.world.biome.provider;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;
import mcp.MethodsReturnNonnullByDefault;
import teamhollow.deepercaverns.util.layer.AreaLayer;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class LayeredBiomeProvider extends BiomeProvider {
	private final List<Biome> allBiomes;
	private final AreaLayer<Biome> biomeResolver;

	public LayeredBiomeProvider(LayeredBiomeProviderSettings settings) {
		allBiomes = settings.biomes;
		biomeResolver = settings.biomeResolver;
	}

	@Override
	public Biome getBiome(int x, int z) {
		return biomeResolver.block.get(x, z);
	}

	/**
	 * getNoiseBiome
	 */
	@Override
	public Biome func_222366_b(int chunkX, int chunkZ) {
		return biomeResolver.noise.get(chunkX, chunkZ);
	}

	@Override
	public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
		return biomeResolver.block.get(x, z, width, length);
	}

	@Override
	public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
		int minX = centerX - sideLength >> 2;
		int minZ = centerZ - sideLength >> 2;
		int maxX = centerX + sideLength >> 2;
		int maxZ = centerZ + sideLength >> 2;
		int width = maxX - minX + 1;
		int length = maxZ - minZ + 1;
		return Sets.newHashSet(biomeResolver.noise.get(minX, minZ, width, length));
	}

	@Nullable
	@Override
	public BlockPos findBiomePosition(int centerX, int centerZ, int range, List<Biome> biomesToLookFor, Random random) {
		int minX = centerX - range >> 2;
		int minY = centerZ - range >> 2;
		int maxX = centerX + range >> 2;
		int maxY = centerZ + range >> 2;
		int width = maxX - minX + 1;
		int height = maxY - minY + 1;

		Biome[] biomes = biomeResolver.noise.get(minX, minY, width, height);
		BlockPos result = null;
		int count = 0;

		for (int i = 0; i < width * height; i++) {
			int findX = minX + i % width << 2;
			int findZ = minY + i / width << 2;

			if (biomesToLookFor.contains(biomes[i])) {
				if (result == null || random.nextInt(count + 1) == 0) {
					result = new BlockPos(findX, 0, findZ);
				}

				count++;
			}
		}

		return result;
	}

	@Override
	public boolean hasStructure(Structure<?> structureToLookFor) {
		return hasStructureCache.computeIfAbsent(structureToLookFor, structure -> allBiomes.stream().anyMatch(biome -> biome.hasStructure(structure)));
	}

	@Override
	public Set<BlockState> getSurfaceBlocks() {
		if (topBlocksCache.isEmpty()) allBiomes.forEach(biome -> topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop()));
		return topBlocksCache;
	}
}
