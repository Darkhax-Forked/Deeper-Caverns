package teamhollow.deepercaverns.util.layer;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.LazyArea;

import java.util.function.IntFunction;
import java.util.function.LongFunction;

// TODO move to registry
public class AreaLayerType<T> {
	public interface AreaFactory {
		<A extends IArea, C extends IExtendedNoiseRandom<A>> Area<A> create(LongFunction<C> contextFactory);
	}

	private static final int MAX_CACHE_SIZE = 25;

	private final Class<T> type;
	private final AreaFactory areaFactory;
	private final IntFunction<T> resolver;
	private final T defaultValue;

	public AreaLayerType(Class<T> type, AreaFactory areaFactory, IntFunction<T> resolver, T defaultValue) {
		this.type = type;
		this.areaFactory = areaFactory;
		this.resolver = resolver;
		this.defaultValue = defaultValue;
	}

	public AreaLayer<T> make(long seed) {
		Area<LazyArea> area = areaFactory.create(value -> new LazyAreaLayerContext(MAX_CACHE_SIZE, seed, value));
		return new AreaLayer<>(new Layer<>(type, area.noise, resolver, defaultValue), new Layer<>(type, area.block, resolver, defaultValue));
	}
}