package teamhollow.deepercaverns.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import teamhollow.deepercaverns.misc.SoulbrynExplosion;

public class SoulbrynBlock extends Block
{
	public SoulbrynBlock(Properties properties)
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

			world.getPendingBlockTicks().scheduleTick(pos, this, 80);
			return true;
		}

		return false;
	}

	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random)
	{
		Explosion explosion = new SoulbrynExplosion(world, pos, 5);

		if(ForgeEventFactory.onExplosionStart(world, explosion))
			return;

		explosion.doExplosionA();
		explosion.doExplosionB(true);
		world.createExplosion(null, null, pos.getX(), pos.getY(), pos.getZ(), 12, true, Mode.NONE);

		if(world.getBlockState(pos).getBlock() == this)
			world.removeBlock(pos, false);
	}
}
