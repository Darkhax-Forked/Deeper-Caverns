package teamhollow.deepercaverns.world.biome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpawnEntryGroup<T> {
	public interface Pool<T> {
		@Nullable
		SpawnEntry<T> selectWeight(Function<Integer, Integer> randomIntWithBound);

		@Nullable
		SpawnEntry<T> selectChance(Function<Integer, Integer> randomIntWithBound);
	}

	public static class ListPool<T> implements Pool<T> {
		private final List<SpawnEntry<T>> spawnEntries;
		private final int totalWeight;

		public ListPool(List<SpawnEntry<T>> spawnEntries) {
			this.spawnEntries = spawnEntries;
			totalWeight = spawnEntries.stream().mapToInt(SpawnEntry::getWeight).sum();
		}

		@Nullable
		@Override
		public SpawnEntry<T> selectWeight(Function<Integer, Integer> randomIntWithBound) {
			int weight = randomIntWithBound.apply(totalWeight);
			for (SpawnEntry<T> spawnEntry : spawnEntries) if ((weight -= spawnEntry.getWeight()) <= 0) return spawnEntry;
			return null;
		}

		@Nullable
		@Override
		public SpawnEntry<T> selectChance(Function<Integer, Integer> randomIntWithBound) {
			for (SpawnEntry<T> spawnEntry : spawnEntries) if (randomIntWithBound.apply(spawnEntry.getWeight()) == 0) return spawnEntry;
			return null;
		}
	}

	public static class EmptyPool<T> implements Pool<T> {
		@Nullable
		@Override
		public SpawnEntry<T> selectWeight(Function<Integer, Integer> randomIntWithBound) {
			return null;
		}

		@Nullable
		@Override
		public SpawnEntry<T> selectChance(Function<Integer, Integer> randomIntWithBound) {
			return null;
		}
	}

	private final List<SpawnEntry<T>> spawnEntries = new ArrayList<>();
	private final Map<T, Pool<T>> pools = new HashMap<>();
	private List<T> entryValuesList;
	private List<Integer> entryWeightsList;
	private Pool<T> globalPool;

	/**
	 * Sorry other coders, but Java requires it to be <code>final</code> for the {@link SafeVarargs} annotation...
	 *
	 * Adds entries, which will then appear in the pool(s).
	 *
	 * @param spawnEntries the entries to add
	 * @return the same object, useful for method chaining.
	 */
	@SafeVarargs
	public final SpawnEntryGroup<T> add(SpawnEntry<T>... spawnEntries) {
		Collections.addAll(this.spawnEntries, spawnEntries);
		pools.clear();
		globalPool = null;
		return this;
	}

	@Nonnull
	public Pool<T> getGlobalPool() {
		if (globalPool == null) globalPool = spawnEntries.isEmpty() ? new EmptyPool<>() : new ListPool<>(spawnEntries);
		return globalPool;
	}

	@Nonnull
	public Pool<T> getPoolFor(T value) {
		Pool<T> pool = pools.get(value);
		if (pool == null) pools.put(value, pool = createPool(value));
		return pool;
	}

	public Pool<T> createPool(T value) {
		List<SpawnEntry<T>> applicableEntries = spawnEntries.stream()
						.filter(entry -> entry.testCanReplace(value))
						.collect(Collectors.toList());
		return applicableEntries.isEmpty() ? new EmptyPool<>() : new ListPool<>(applicableEntries);
	}

	public List<T> getEntryValuesList() {
		if (entryValuesList == null) entryValuesList = spawnEntries.stream().map(SpawnEntry::getValue).collect(Collectors.toList());
		return entryValuesList;
	}

	public List<Integer> getEntryWeightsList() {
		if (entryWeightsList == null) entryWeightsList = spawnEntries.stream().map(SpawnEntry::getWeight).collect(Collectors.toList());
		return entryWeightsList;
	}
}