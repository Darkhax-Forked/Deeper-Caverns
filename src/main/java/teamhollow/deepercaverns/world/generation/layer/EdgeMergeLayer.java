package teamhollow.deepercaverns.world.generation.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

import teamhollow.deepercaverns.util.Mapper;

public class EdgeMergeLayer<T> implements IAreaTransformer2, IDimOffset0Transformer {
	private final Predicate<T> target;
	private final Mapper<T, Integer> mapper;
	private final T replacement;

	public EdgeMergeLayer(Predicate<T> target, Mapper<T, Integer> mapper, T replacement) {
		this.target = target;
		this.mapper = mapper;
		this.replacement = replacement;
	}

	@Override
	@ParametersAreNonnullByDefault
	public int apply(INoiseRandom context, IArea mainSampler, IArea edgeSampler, int x, int z) {
		int main = mainSampler.getValue(func_215721_a(x), func_215722_b(z));
		int edge = edgeSampler.getValue(func_215721_a(x), func_215722_b(z));
		return edge == 1 && target.test(mapper.unmap(main)) ? mapper.map(replacement) : main;
	}
}