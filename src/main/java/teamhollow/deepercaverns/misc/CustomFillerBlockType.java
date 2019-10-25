package teamhollow.deepercaverns.misc;

import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import teamhollow.deepercaverns.reg.BlockRegistrar;

public class CustomFillerBlockType
{
	public static final FillerBlockType SOULSTONE = FillerBlockType.create("soulstone", "soulstone", new BlockMatcher(BlockRegistrar.SOULSTONE));
}
