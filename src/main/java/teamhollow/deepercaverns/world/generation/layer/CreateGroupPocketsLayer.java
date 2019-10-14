package teamhollow.deepercaverns.world.generation.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

import teamhollow.deepercaverns.util.Mapper;
import teamhollow.deepercaverns.world.biome.SpawnEntry;
import teamhollow.deepercaverns.world.biome.SpawnEntryGroup;

public class CreateGroupPocketsLayer<T> implements IC1Transformer {
	private final SpawnEntryGroup<T> spawnEntryGroup;
	private final Mapper<T, Integer> spawnEntryMapper;

	public CreateGroupPocketsLayer(SpawnEntryGroup<T> spawnEntryGroup, Mapper<T, Integer> spawnEntryMapper) {
		this.spawnEntryGroup = spawnEntryGroup;
		this.spawnEntryMapper = spawnEntryMapper;
	}

	@Override
	public int apply(INoiseRandom context, int value) {
		SpawnEntryGroup.Pool<T> pool = spawnEntryGroup.getPoolFor(spawnEntryMapper.unmap(value));
		SpawnEntry<T> entry = pool.selectChance(context::random);
		return entry != null ? spawnEntryMapper.map(entry.getValue()) : value;
	}
}