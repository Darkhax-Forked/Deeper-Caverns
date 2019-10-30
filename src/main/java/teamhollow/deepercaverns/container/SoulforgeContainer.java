package teamhollow.deepercaverns.container;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.block.SoulforgeBlock;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.ContainerTypeRegistrar;
import teamhollow.deepercaverns.reg.ItemRegistrar;
import teamhollow.deepercaverns.tileentity.SoulforgeTileEntity;

public class SoulforgeContainer extends Container
{
	public final SoulforgeTileEntity te;

	public SoulforgeContainer(int windowId, PlayerInventory playerInv, BlockPos pos)
	{
		super(ContainerTypeRegistrar.SOULFORGE, windowId);

		te = (SoulforgeTileEntity)playerInv.player.world.getTileEntity(pos);

		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++)
		{
			addSlot(new Slot(playerInv, i, 8 + i * 18, 142));
		}

		Direction facing = te.getBlockState().get(SoulforgeBlock.HORIZONTAL_FACING);

		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.rotateY()).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 28, 17)));
		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.rotateYCCW()).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 64, 17)));
		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 46, 53) {
			@Override
			public boolean isItemValid(@Nonnull ItemStack stack)
			{
				return h.isItemValid(0, stack);
			}
		}));
		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 128, 35) {
			@Override
			public ItemStack onTake(PlayerEntity player, ItemStack stack)
			{
				if(player instanceof ServerPlayerEntity)
				{
					if(stack.getItem() == ItemRegistrar.CHALONITE_INGOT)
						DeeperCaverns.CREATE_CHALONITE_TRIGGER.trigger((ServerPlayerEntity)player);
					else if(stack.getItem() == ItemRegistrar.GHOSTSOUL_INGOT)
						DeeperCaverns.CREATE_GHOSTSOUL_TRIGGER.trigger((ServerPlayerEntity)player);
				}

				return stack;
			}

			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return false;
			}
		}));
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) //TODO: fix shiftclicking
	{
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canInteractWith(PlayerEntity player)
	{
		return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), player, BlockRegistrar.SOULFORGE);
	}
}
