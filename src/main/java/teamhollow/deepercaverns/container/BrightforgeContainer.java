package teamhollow.deepercaverns.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.ContainerTypeRegistrar;
import teamhollow.deepercaverns.tileentity.BrightforgeTileEntity;

public class BrightforgeContainer extends Container
{
	public final BrightforgeTileEntity te;

	public BrightforgeContainer(int windowId, PlayerInventory playerInv, BlockPos pos)
	{
		super(ContainerTypeRegistrar.BRIGHTFORGE, windowId);

		te = (BrightforgeTileEntity)playerInv.player.world.getTileEntity(pos);

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

		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 56, 17)));
		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.NORTH).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 56, 53) {
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return h.isItemValid(0, stack);
			}
		}));
		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.DOWN).ifPresent(h -> addSlot(new SlotItemHandler(h, 0, 116, 35) {
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
		return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), player, BlockRegistrar.BRIGHTFORGE);
	}
}
