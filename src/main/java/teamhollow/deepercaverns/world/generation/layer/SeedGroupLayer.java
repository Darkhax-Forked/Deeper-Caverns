package teamhollow.deepercaverns.world.generation.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

import teamhollow.deepercaverns.util.Mapper;
import teamhollow.deepercaverns.world.biome.SpawnEntry;
import teamhollow.deepercaverns.world.biome.SpawnEntryGroup;

public class SeedGroupLayer<T> implements IAreaTransformer0 {
	private final SpawnEntryGroup<T> spawnEntryGroup;
	private final Mapper<T, Integer> spawnEntryMapper;

	public SeedGroupLayer(SpawnEntryGroup<T> spawnEntryGroup, Mapper<T, Integer> spawnEntryMapper) {
		this.spawnEntryGroup = spawnEntryGroup;
		this.spawnEntryMapper = spawnEntryMapper;
	}

	@Override
	public int apply(INoiseRandom context, int x, int z) {
		SpawnEntryGroup.Pool<T> pool = spawnEntryGroup.getGlobalPool();
		SpawnEntry<T> entry = pool.selectWeight(context::random);
		return entry != null ? spawnEntryMapper.map(entry.getValue()) : 0;
	}
}