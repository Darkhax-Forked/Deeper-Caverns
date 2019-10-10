package teamhollow.deepercaverns.tileentity;

import net.minecraft.block.Block;
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
import teamhollow.deepercaverns.block.SoulforgeBlock;
import teamhollow.deepercaverns.container.SoulforgeContainer;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.TileEntityTypeRegistrar;

public class SoulforgeTileEntity extends TileEntity implements INamedContainerProvider
{
	private final LazyOptional<IItemHandler> leftInputItemHandler = LazyOptional.of(this::createItemHandler);
	private final LazyOptional<IItemHandler> rightInputItemHandler = LazyOptional.of(this::createItemHandler);
	private final LazyOptional<IItemHandler> fuelItemHandler = LazyOptional.of(this::createItemHandler);
	private final LazyOptional<IItemHandler> outputItemHandler = LazyOptional.of(this::createItemHandler);

	public SoulforgeTileEntity()
	{
		super(TileEntityTypeRegistrar.SOULFORGE);
	}

	@Override
	public void read(CompoundNBT tag)
	{
		super.read(tag);

		leftInputItemHandler.ifPresent(h -> ((ItemStackHandler)h).deserializeNBT(tag.getCompound("LeftInputSlot")));
		rightInputItemHandler.ifPresent(h -> ((ItemStackHandler)h).deserializeNBT(tag.getCompound("RightInputSlot")));
		fuelItemHandler.ifPresent(h -> ((ItemStackHandler)h).deserializeNBT(tag.getCompound("FuelSlot")));
		outputItemHandler.ifPresent(h -> ((ItemStackHandler)h).deserializeNBT(tag.getCompound("OutputSlot")));
	}

	@Override
	public CompoundNBT write(CompoundNBT tag)
	{
		leftInputItemHandler.ifPresent(h -> tag.put("LeftInputSlot", ((ItemStackHandler)h).serializeNBT()));
		rightInputItemHandler.ifPresent(h -> tag.put("RightInputSlot", ((ItemStackHandler)h).serializeNBT()));
		fuelItemHandler.ifPresent(h -> tag.put("FuelSlot", ((ItemStackHandler)h).serializeNBT()));
		outputItemHandler.ifPresent(h -> tag.put("OutputSlot", ((ItemStackHandler)h).serializeNBT()));
		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			Direction facing = getBlockState().get(SoulforgeBlock.HORIZONTAL_FACING);

			if(side == facing || side == facing.getOpposite())
				return fuelItemHandler.cast();
			else if(side == facing.rotateY())
				return leftInputItemHandler.cast();
			else if(side == facing.rotateYCCW())
				return rightInputItemHandler.cast();
			else if(side == Direction.DOWN)
				return outputItemHandler.cast();
		}

		return super.getCapability(cap, side);
	}

	@Override
	public void remove()
	{
		super.remove();

		leftInputItemHandler.ifPresent(h -> Block.spawnAsEntity(world, pos, h.getStackInSlot(0)));
		rightInputItemHandler.ifPresent(h -> Block.spawnAsEntity(world, pos, h.getStackInSlot(0)));
		fuelItemHandler.ifPresent(h -> Block.spawnAsEntity(world, pos, h.getStackInSlot(0)));
		outputItemHandler.ifPresent(h -> Block.spawnAsEntity(world, pos, h.getStackInSlot(0)));
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
		return new ItemStackHandler(1) {
			@Override
			protected void onContentsChanged(int slot)
			{
				markDirty();
			}
		};
	}
}
