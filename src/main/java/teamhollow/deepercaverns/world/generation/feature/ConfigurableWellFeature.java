package teamhollow.deepercaverns.world.generation.feature;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

@ParametersAreNonnullByDefault
public class ConfigurableWellFeature extends Feature<ConfigurableWellConfig> {
	private static final BlockStateMatcher IS_SAND = BlockStateMatcher.forBlock(Blocks.SAND);

	public ConfigurableWellFeature(Function<Dynamic<?>, ? extends ConfigurableWellConfig> deserializer) {
		super(deserializer);
	}

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos blockPos, ConfigurableWellConfig config) {
		blockPos = blockPos.up();
		while (world.isAirBlock(blockPos) && blockPos.getY() > 2) blockPos = blockPos.down();

		if (!config.isValidBottomBlock.test(world.getBlockState(blockPos))) {
			return false;
		} else {
			for (int x = -2; x <= 2; ++x) {
				for (int z = -2; z <= 2; ++z) {
					if (world.isAirBlock(blockPos.add(x, -1, z)) && world.isAirBlock(blockPos.add(x, -2, z))) {
						return false;
					}
				}
			}

			for (int x = -2; x <= 2; x++) {
				for (int y = -1; y <= 0; y++) {
					for (int z = -2; z <= 2; ++z) {
						world.setBlockState(blockPos.add(x, y, z), config.block, 2);
					}
				}
			}

			if (config.hasFluid) {
				world.setBlockState(blockPos, config.fluid, 2);

				for (Direction direction : Direction.Plane.HORIZONTAL) {
					world.setBlockState(blockPos.offset(direction), config.fluid, 2);
				}
			}

			for (int x = -2; x <= 2; x++) {
				for (int z = -2; z <= 2; z++) {
					if (x == -2 || x == 2 || z == -2 || z == 2) {
						world.setBlockState(blockPos.add(x, 1, z), config.block, 2);
					}
				}
			}

			world.setBlockState(blockPos.add(2, 1, 0), config.slab, 2);
			world.setBlockState(blockPos.add(-2, 1, 0), config.slab, 2);
			world.setBlockState(blockPos.add(0, 1, 2), config.slab, 2);
			world.setBlockState(blockPos.add(0, 1, -2), config.slab, 2);

			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					if (x == 0 && z == 0) {
						world.setBlockState(blockPos.add(x, 4, z), config.block, 2);
					} else {
						world.setBlockState(blockPos.add(x, 4, z), config.slab, 2);
					}
				}
			}

			for (int y = 1; y <= 3; y++) {
				world.setBlockState(blockPos.add(-1, y, -1), config.block, 2);
				world.setBlockState(blockPos.add(-1, y, 1), config.block, 2);
				world.setBlockState(blockPos.add(1, y, -1), config.block, 2);
				world.setBlockState(blockPos.add(1, y, 1), config.block, 2);
			}

			return true;
		}
	}
}
