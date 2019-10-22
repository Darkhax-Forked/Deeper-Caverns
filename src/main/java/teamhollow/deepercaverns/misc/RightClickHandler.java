package teamhollow.deepercaverns.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.block.BiolayerPortalBlock;
import teamhollow.deepercaverns.block.SoulEssenceCauldronBlock;
import teamhollow.deepercaverns.recipe.SoulEssenceCauldronRecipe;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.FluidRegistrar;
import teamhollow.deepercaverns.world.dimension.DeeperCavernsDimensions;

@EventBusSubscriber(modid=DeeperCaverns.MODID)
public class RightClickHandler
{
	@SubscribeEvent
	public static void onRightClickBlock(RightClickBlock event)
	{
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		BlockState state = world.getBlockState(pos);
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();

		if(stack.getItem() == Items.FLINT_AND_STEEL)
		{
			boolean isValidDim = world.dimension.getType() == DimensionType.OVERWORLD || world.dimension.getType() == DeeperCavernsDimensions.BIOLAYER_TYPE;

			if(isValidDim && state.getBlock() == BlockRegistrar.CHISELED_OBSIDIAN && ((BiolayerPortalBlock)BlockRegistrar.BIOLAYER_PORTAL).trySpawnPortal(world, pos.offset(event.getFace())) && player.isCreative())
			{
				stack.damageItem(1, player, p -> p.sendBreakAnimation(event.getHand()));
				player.swingArm(event.getHand());
			}
		}
		else if(state.getBlock() == Blocks.CAULDRON && state.get(CauldronBlock.LEVEL) == 0 && stack.getItem() == FluidRegistrar.SOUL_ESSENCE_BUCKET.get())
		{
			world.setBlockState(pos, BlockRegistrar.SOUL_ESSENCE_CAULDRON.getDefaultState());
			((SoulEssenceCauldronBlock)BlockRegistrar.SOUL_ESSENCE_CAULDRON).fillCompletely(world.getBlockState(pos), world, pos, event.getPlayer(), event.getHand());
		}
		else if(state.getBlock() == BlockRegistrar.SOUL_ESSENCE_CAULDRON && state.get(CauldronBlock.LEVEL) == 3 && !stack.isEmpty())
		{
			SoulEssenceCauldronRecipe recipe = SoulEssenceCauldronRecipe.getMatchingRecipe(stack);

			if(recipe != null)
			{
				if(!player.isCreative())
					stack.shrink(1);

				Block.spawnAsEntity(world, pos, recipe.getOutput());
				world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
			}
		}
	}
}
