package teamhollow.deepercaverns.world.generation.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

import javax.annotation.ParametersAreNonnullByDefault;

public class CellSeedLayer implements IAreaTransformer0 {
	@Override
	@ParametersAreNonnullByDefault
	public int apply(INoiseRandom context, int x, int z) {
		return context.random(50);
	}
}