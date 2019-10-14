package teamhollow.deepercaverns.world.generation.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

import javax.annotation.ParametersAreNonnullByDefault;

public class ProduceOutlineLayer implements ICastleTransformer {
	@Override
	@ParametersAreNonnullByDefault
	public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
		return west != center || south != center ? 1 : 0;
	}
}
