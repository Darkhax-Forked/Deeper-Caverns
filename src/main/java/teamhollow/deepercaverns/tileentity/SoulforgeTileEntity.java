package teamhollow.deepercaverns.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import teamhollow.deepercaverns.container.SoulforgeContainer;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.TileEntityTypeRegistrar;

public class SoulforgeTileEntity extends TileEntity implements INamedContainerProvider
{
	private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(this::createItemHandler);

	public SoulforgeTileEntity()
	{
		super(TileEntityTypeRegistrar.SOULFORGE);
	}

	@Override
	public void read(CompoundNBT tag)
	{
		super.read(tag);

		itemHandler.ifPresent(h -> ((ItemStackHandler)h).deserializeNBT(tag.getCompound("Inventory")));
	}

	@Override
	public CompoundNBT write(CompoundNBT tag)
	{
		itemHandler.ifPresent(h -> tag.put("Inventory", ((ItemStackHandler)h).serializeNBT()));
		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return itemHandler.cast();
		return super.getCapability(cap, side);
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player)
	{
		return new SoulforgeContainer(windowId, playerInv, pos);
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TranslationTextComponent(BlockRegistrar.SOULFORGE.getTranslationKey());
	}

	private IItemHandler createItemHandler()
	{
		return new ItemStackHandler(4) {
			@Override
			protected void onContentsChanged(int slot)
			{
				markDirty();
			}
		};
	}
}
