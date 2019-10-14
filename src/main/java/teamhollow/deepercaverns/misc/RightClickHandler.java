package teamhollow.deepercaverns.misc;

import net.minecraft.block.BlockState;
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
import teamhollow.deepercaverns.reg.BlockRegistrar;

@EventBusSubscriber(modid=DeeperCaverns.MODID)
public class RightClickHandler
{
	@SubscribeEvent
	public static void onRightClickBlock(RightClickBlock event)
	{
		ItemStack stack = event.getItemStack();

		if(stack.getItem() == Items.FLINT_AND_STEEL)
		{
			World world = event.getWorld();
			BlockPos pos = event.getPos();
			BlockState state = world.getBlockState(pos);
			PlayerEntity player = event.getPlayer();

			if(state.getBlock() == BlockRegistrar.CHISELED_OBSIDIAN && world.dimension.getType() == DimensionType.OVERWORLD && ((BiolayerPortalBlock)BlockRegistrar.BIOLAYER_PORTAL).trySpawnPortal(world, pos.offset(event.getFace())) && player.isCreative())
			{
				stack.damageItem(1, player, p -> p.sendBreakAnimation(event.getHand()));
				player.swingArm(event.getHand());
			}
		}
	}
}
