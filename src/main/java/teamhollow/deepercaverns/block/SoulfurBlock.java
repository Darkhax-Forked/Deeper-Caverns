package teamhollow.deepercaverns.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import teamhollow.deepercaverns.entity.SoulfurBlockEntity;

public class SoulfurBlock extends Block
{
	public SoulfurBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		if(player.getHeldItem(hand).getItem() == Items.BLAZE_POWDER)
		{
			if(!player.isCreative())
				player.getHeldItem(hand).shrink(1);

			explode(world, pos);
			return true;
		}

		return false;
	}

	@Override
	public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion)
	{
		explode(world, pos);
	}

	@Override
	public void onProjectileCollision(World world, BlockState state, BlockRayTraceResult hit, Entity projectile)
	{
		if(!world.isRemote && projectile instanceof AbstractArrowEntity)
		{
			if(((AbstractArrowEntity)projectile).isBurning())
				explode(world, hit.getPos());
		}
	}

	@Override
	public boolean canDropFromExplosion(BlockState state, IBlockReader world, BlockPos pos, Explosion explosion)
	{
		return false;
	}

	private void explode(World world, BlockPos pos)
	{
		if(!world.isRemote)
		{
			SoulfurBlockEntity entity = new SoulfurBlockEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);

			world.removeBlock(pos, false);
			world.addEntity(entity);
		}
	}
}
