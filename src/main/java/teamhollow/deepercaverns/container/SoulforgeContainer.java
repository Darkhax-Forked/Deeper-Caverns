package teamhollow.deepercaverns.container;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import teamhollow.deepercaverns.DCTags;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.ContainerTypeRegistrar;
import teamhollow.deepercaverns.tileentity.SoulforgeTileEntity;

public class SoulforgeContainer extends Container
{
	private SoulforgeTileEntity te;

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

		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
			addSlot(new SlotItemHandler(h, 0, 28, 17));
			addSlot(new SlotItemHandler(h, 1, 64, 17));
			addSlot(new SlotItemHandler(h, 2, 46, 53) {
				@Override
				public boolean isItemValid(@Nonnull ItemStack stack)
				{
					return stack.getItem().isIn(DCTags.Items.FUEL_SOULFORGE);
				}
			});
			addSlot(new SlotItemHandler(h, 3, 128, 35));
		});
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
