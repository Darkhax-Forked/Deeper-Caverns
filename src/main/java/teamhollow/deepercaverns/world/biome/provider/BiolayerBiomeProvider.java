package teamhollow.deepercaverns.world.biome.provider;

import com.google.common.collect.Sets;
import mcp.MethodsReturnNonnullByDefault;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

@MethodsReturnNonnullByDefault
public class BiolayerBiomeProvider extends BiomeProvider {
	private List<Biome> biomes;
	private int size;

	public BiolayerBiomeProvider(BiolayerBiomeProviderSettings settings) {
		biomes = settings.getBiomes();
		size = settings.getSize() + 4;
	}

	// TODO correctly implement this

	@Override
	public Biome getBiome(int x, int y) {
		return biomes.get(0);
	}

	@Override
	public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
		Biome[] biomes = new Biome[width * length];
		Arrays.fill(biomes, 0, width * length, this.biomes.get(0));
		return biomes;
	}

	@Override
	public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
		return Sets.newHashSet(biomes.get(0));
	}

	@Nullable
	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
		return biomes.contains(this.biomes.get(0)) ?
		       new BlockPos(x - range + random.nextInt(range * 2 + 1), 0, z - range + random.nextInt(range * 2 + 1))
		                                           : null;
	}

	@Override
	public boolean hasStructure(Structure<?> structure) {
		return hasStructureCache.computeIfAbsent(structure, biomes.get(0)::hasStructure);
	}

	@Override
	public Set<BlockState> getSurfaceBlocks() {
		if (topBlocksCache.isEmpty()) topBlocksCache.add(biomes.get(0).getSurfaceBuilderConfig().getTop());
		return topBlocksCache;
	}
}
