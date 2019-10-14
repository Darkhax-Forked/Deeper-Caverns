package teamhollow.deepercaverns.world.biome;

import java.util.Arrays;
import java.util.function.Predicate;

public class SpawnEntry<T> {
	private final T value;
	private final int weight;
	private Predicate<T> canReplace;

	public SpawnEntry(T value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	public T getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}

	public SpawnEntry<T> canReplace(Predicate<T> canReplace) {
		this.canReplace = canReplace;
		return this;
	}

	/**
	 * Sorry other coders, but Java requires it to be <code>final</code> for the {@link SafeVarargs} annotation...
	 *
	 * Sets the <code>canReplace</code> {@link Predicate} so that it can replace only what was passed to this method. Use {@link #canAlsoReplace(T[])} to add additional values it may replace.
	 *
	 * @param canReplace the values it may replace
	 * @return the same object, useful for method chaining.
	 */
	@SafeVarargs
	public final SpawnEntry<T> canReplace(T... canReplace) {
		this.canReplace = Arrays.asList(canReplace)::contains;
		return this;
	}

	/**
	 * Sorry other coders, but Java requires it to be <code>final</code> for the {@link SafeVarargs} annotation...
	 *
	 * Sets the <code>canReplace</code> {@link Predicate} so that it can also replace what was passed to this method.
	 *
	 * @param canReplace the values it may also replace
	 * @return the same object, useful for method chaining.
	 */
	@SafeVarargs
	public final SpawnEntry<T> canAlsoReplace(T... canReplace) {
		this.canReplace = this.canReplace.or(Arrays.asList(canReplace)::contains);
		return this;
	}

	public boolean testCanReplace(T value) {
		return canReplace == null || canReplace.test(value);
	}
}