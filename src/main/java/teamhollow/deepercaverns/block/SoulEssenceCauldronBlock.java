package teamhollow.deepercaverns.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import teamhollow.deepercaverns.reg.FluidRegistrar;

public class SoulEssenceCauldronBlock extends CauldronBlock
{
	public SoulEssenceCauldronBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		int level = state.get(LEVEL);

		if(!world.isRemote && entity instanceof LivingEntity && level > 0 && entity.getBoundingBox().minY <= (pos.getY() + (6.0F + 3 * level) / 16.0F))
			((LivingEntity)entity).clearActivePotions();
	}

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		ItemStack stack = player.getHeldItem(hand);

		if(!stack.isEmpty())
		{
			int level = state.get(LEVEL);
			Item item = stack.getItem();
			Item essenceBucket = FluidRegistrar.SOUL_ESSENCE_BUCKET.get();

			if(item == essenceBucket)
			{
				if(level < 3 && !world.isRemote)
					fillCompletely(state, world, pos, player, hand);
			}
			else if(item == Items.BUCKET)
			{
				if(level == 3 && !world.isRemote)
				{
					if(!player.isCreative())
					{
						stack.shrink(1);

						if(stack.isEmpty())
							player.setHeldItem(hand, new ItemStack(essenceBucket));
						else if (!player.inventory.addItemStackToInventory(new ItemStack(essenceBucket)))
							player.dropItem(new ItemStack(essenceBucket), false);
					}

					world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
					world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}
			}
			else return false;
		}

		return true;
	}

	public void fillCompletely(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand)
	{
		if(!player.isCreative())
			player.setHeldItem(hand, new ItemStack(Items.BUCKET));

		setWaterLevel(world, pos, state, 3);
		world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	@Override
	public String getTranslationKey()
	{
		return Blocks.CAULDRON.getTranslationKey();
	}

	@Override
	public Item asItem()
	{
		return Blocks.CAULDRON.asItem();
	}

	@Override
	public void fillWithRain(World world, BlockPos pos) {}
}
