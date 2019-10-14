package teamhollow.deepercaverns.util.layer;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.VoroniZoomLayer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.LongFunction;

public class Area<A extends IArea> {
	public final A block;
	public final A noise;

	private Area(A block, A noise) {
		this.block = block;
		this.noise = noise;
	}

	@ParametersAreNonnullByDefault
	public static <A extends IArea, C extends IExtendedNoiseRandom<A>> Area<A> of(IAreaFactory<A> noise, LongFunction<C> contextFactory) {
		IAreaFactory<A> block = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10), noise);
		return new Area<>(block.make(), noise.make());
	}
}