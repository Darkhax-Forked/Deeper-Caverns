package teamhollow.deepercaverns.world.generation.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

import javax.annotation.ParametersAreNonnullByDefault;

import teamhollow.deepercaverns.util.Mapper;
import teamhollow.deepercaverns.world.biome.SpawnEntry;
import teamhollow.deepercaverns.world.biome.SpawnEntryGroup;

public class AddHillsLayer<T> implements IC1Transformer {
	private final SpawnEntryGroup<T> spawnEntryGroup;
	private final Mapper<T, Integer> spawnEntryMapper;
	private final int chance;

	public AddHillsLayer(SpawnEntryGroup<T> spawnEntryGroup, Mapper<T, Integer> spawnEntryMapper, int chance) {
		this.spawnEntryGroup = spawnEntryGroup;
		this.spawnEntryMapper = spawnEntryMapper;
		this.chance = chance;
	}

	@Override
	@ParametersAreNonnullByDefault
	public int apply(INoiseRandom context, int value) {
		if (context.random(chance) == 0) {
			SpawnEntryGroup.Pool<T> pool = spawnEntryGroup.getPoolFor(spawnEntryMapper.unmap(value));
			SpawnEntry<T> spawnEntry = pool.selectWeight(context::random);
			if (spawnEntry != null) return spawnEntryMapper.map(spawnEntry.getValue());
		}
		return value;
	}
}