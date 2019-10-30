package teamhollow.deepercaverns.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class BlueFireBlock extends FireBlock
{
	public BlueFireBlock(Block.Properties builder)
	{
		super(builder);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return !world.getBlockState(pos.down()).isAir(world, pos.down());
	}
}
