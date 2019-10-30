package teamhollow.deepercaverns.world.generation.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import teamhollow.deepercaverns.reg.BlockRegistrar;

public class BlueFireFeature extends Feature<NoFeatureConfig>
{
	public BlueFireFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> func)
	{
		super(func);
	}

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		for(int i = 0; i < 64; ++i)
		{
			BlockPos randomPos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if(world.isAirBlock(randomPos) && world.getBlockState(randomPos.down()).isSolid())
				world.setBlockState(randomPos, BlockRegistrar.BLUE_FIRE.getDefaultState(), 2);
		}

		return true;
	}
}