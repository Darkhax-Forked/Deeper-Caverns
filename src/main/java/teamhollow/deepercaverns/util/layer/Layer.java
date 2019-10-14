package teamhollow.deepercaverns.util.layer;

import net.minecraft.world.gen.area.LazyArea;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.util.function.IntFunction;

import teamhollow.deepercaverns.DeeperCaverns;

public class Layer<T> {
	private final Class<T> type;
	private final LazyArea values;
	private final IntFunction<T> resolver;
	private final T defaultValue;

	public Layer(Class<T> type, LazyArea values, IntFunction<T> resolver, T defaultValue) {
		this.type = type;
		this.values = values;
		this.resolver = resolver;
		this.defaultValue = defaultValue;
	}

	// You can thank Notch for using `y` for the z-value and `z` for the y-value.

	public T get(int x, int z) {
		return resolve(values.getValue(x, z));
	}

	@SuppressWarnings("unchecked")
	public T[] get(int minX, int minZ, int width, int length) {
		if (width < 0 || length < 0) throw new IllegalArgumentException("Width and length can not be negative, but were " + width + " and " + length);

		T[] result = (T[]) Array.newInstance(type, length * width);

		for (int localZ = 0; localZ < length; localZ++) {
			int line = localZ * width;
			for (int localX = 0; localX < width; localX++) {
				result[line + localX] = resolve(values.getValue(minX + localX, minZ + localZ));
			}
		}

		return result;
	}

	@Nonnull
	private T resolve(int value) {
		T result = resolver.apply(value);
		if (result == null) {
			DeeperCaverns.LOGGER.error("Value of type \"{}\" for value \"{}\" is null", type, value);
			return defaultValue;
		}
		return result;
	}
}
