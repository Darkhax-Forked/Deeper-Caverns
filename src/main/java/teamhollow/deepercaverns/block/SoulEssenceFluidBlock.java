package teamhollow.deepercaverns.block;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoulEssenceFluidBlock extends FlowingFluidBlock
{
	public SoulEssenceFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties)
	{
		super(supplier, properties);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if(!(entity instanceof PlayerEntity && ((PlayerEntity)entity).isCreative()) && entity instanceof LivingEntity)
			((LivingEntity)entity).clearActivePotions();
	}
}
