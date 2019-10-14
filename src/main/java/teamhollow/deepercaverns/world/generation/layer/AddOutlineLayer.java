package teamhollow.deepercaverns.world.generation.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

import javax.annotation.ParametersAreNonnullByDefault;

public class AddOutlineLayer implements ICastleTransformer {
	private final int outline;

	public AddOutlineLayer(int outline) {
		this.outline = outline;
	}

	@Override
	@ParametersAreNonnullByDefault
	public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
		if (west != center || south != center) {
			return outline;
		} else {
			return center;
		}
	}
}
