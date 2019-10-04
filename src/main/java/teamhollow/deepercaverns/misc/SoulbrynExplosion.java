package teamhollow.deepercaverns.misc;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class SoulbrynExplosion extends Explosion
{
	private Random r = new Random();
	private World world;

	public SoulbrynExplosion(World world, BlockPos pos, float size)
	{
		super(world, null, pos.getX(), pos.getY(), pos.getZ(), size, false, Mode.BREAK);
		this.world = world;
	}

	@Override
	public void doExplosionB(boolean spawnParticles)
	{
		super.doExplosionB(spawnParticles);

		if(r.nextFloat() >= 0.8F)
		{
			for(BlockPos pos : affectedBlockPositions)
			{
				if(world.getBlockState(pos).isAir(world, pos) && world.getBlockState(pos.down()).isOpaqueCube(world, pos.down()) && r.nextInt(3) == 0)
					world.setBlockState(pos, Blocks.LAVA.getDefaultState());
			}
		}
	}
}
