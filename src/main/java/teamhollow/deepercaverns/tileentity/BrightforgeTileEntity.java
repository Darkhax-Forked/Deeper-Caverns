package teamhollow.deepercaverns.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.EmptyHandler;
import teamhollow.deepercaverns.container.BrightforgeContainer;
import teamhollow.deepercaverns.recipe.BrightforgeRecipe;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.TileEntityTypeRegistrar;

public class BrightforgeTileEntity extends TileEntity implements INamedContainerProvider, ITickableTileEntity
{
	private final LazyOptional<IItemHandler> inputItemHandler = LazyOptional.of(this::createItemHandler);
	private final LazyOptional<IItemHandler> fuelItemHandler = LazyOptional.of(this::createFuelItemHandler);
	private final LazyOptional<IItemHandler> outputItemHandler = LazyOptional.of(this::createItemHandler);
	private int burnTime;
	private int cookTime;
	private int cookTimeNeeded;

	public BrightforgeTileEntity()
	{
		super(TileEntityTypeRegistrar.BRIGHTFORGE);
	}

	@Override
	public void tick()
	{
		boolean isBurningAtStartOfTick = isBurning();
		boolean shouldMarkDirty = false;

		if(isBurning())
			--burnTime;

		ItemStack fuelStack = getFuelStack();

		if(isBurning() || !fuelStack.isEmpty() && !getInputStack().isEmpty())
		{
			BrightforgeRecipe recipe = BrightforgeRecipe.getMatchingRecipe(getInputStack());

			if(!isBurning() && canSmelt(recipe))
			{
				burnTime = getDefaultBurnTime();
				cookTimeNeeded = 200;

				if(isBurning())
				{
					shouldMarkDirty = true;

					if(fuelStack.hasContainerItem())
						setFuelStack(fuelStack.getContainerItem());
					else if(!fuelStack.isEmpty())
					{
						fuelStack.shrink(1);

						if(fuelStack.isEmpty())
							setFuelStack(fuelStack.getContainerItem());
					}
				}
			}

			if(isBurning() && canSmelt(recipe))
			{
				++cookTime;

				if(cookTime == cookTimeNeeded)
				{
					cookTime = 0;
					cookTimeNeeded = 0;
					updatePostProcessing(recipe);
					shouldMarkDirty = true;
				}
			}
			else
				cookTime = 0;
		}
		else if(!isBurning() && cookTime > 0)
			cookTime = MathHelper.clamp(cookTime - 2, 0, cookTimeNeeded);

		if(isBurningAtStartOfTick != isBurning())
			shouldMarkDirty = true;

		if(shouldMarkDirty)
			markDirty();
	}

	public boolean isBurning()
	{
		return burnTime > 0;
	}

	public int getBurnTime()
	{
		return burnTime;
	}

	public int getCookTime()
	{
		return cookTime;
	}

	public int getCookTimeNeeded()
	{
		return cookTimeNeeded;
	}

	public int getDefaultBurnTime()
	{
		return 200;
	}

	private boolean canSmelt(BrightforgeRecipe recipe)
	{
		if(!getInputStack().isEmpty() && recipe != null)
		{
			ItemStack recipeOutput = recipe.getOutput();

			if(recipeOutput.isEmpty())
				return false;
			else
			{
				ItemStack outputStack = getOutputStack();

				if(outputStack.isEmpty())
					return true;
				else if(!outputStack.isItemEqual(recipeOutput))
					return false;
				else if(outputStack.getCount() + recipeOutput.getCount() <= 64 && outputStack.getCount() + recipeOutput.getCount() <= outputStack.getMaxStackSize())
					return true;
				else return outputStack.getCount() + recipeOutput.getCount() <= recipeOutput.getMaxStackSize();
			}
		}
		else return false;
	}

	private void updatePostProcessing(BrightforgeRecipe recipe)
	{
		if(recipe != null && canSmelt(recipe))
		{
			ItemStack leftStack = getInputStack();
			ItemStack recipeOutput = recipe.getOutput();
			ItemStack currentOutput = getOutputStack();

			if(currentOutput.isEmpty())
				setOutputStack(recipeOutput.copy());
			else if(currentOutput.getItem() == recipeOutput.getItem())
				currentOutput.grow(recipeOutput.getCount());

			leftStack.shrink(1);
		}
	}

	@Override
	public void read(CompoundNBT tag)
	{
		super.read(tag);

		burnTime = tag.getInt("BurnTime");
		cookTime = tag.getInt("CookTime");
		cookTimeNeeded = tag.getInt("CookTimeNeeded");
		inputItemHandler.ifPresent(h -> ((ItemStackHandler)h).deserializeNBT(tag.getCompound("InputSlot")));
		fuelItemHandler.ifPresent(h -> ((ItemStackHandler)h).deserializeNBT(tag.getCompound("FuelSlot")));
		outputItemHandler.ifPresent(h -> ((ItemStackHandler)h).deserializeNBT(tag.getCompound("OutputSlot")));
	}

	@Override
	public CompoundNBT write(CompoundNBT tag)
	{
		tag.putInt("BurnTime", burnTime);
		tag.putInt("CookTime", cookTime);
		tag.putInt("CookTimeNeeded", cookTimeNeeded);
		inputItemHandler.ifPresent(h -> tag.put("InputSlot", ((ItemStackHandler)h).serializeNBT()));
		fuelItemHandler.ifPresent(h -> tag.put("FuelSlot", ((ItemStackHandler)h).serializeNBT()));
		outputItemHandler.ifPresent(h -> tag.put("OutputSlot", ((ItemStackHandler)h).serializeNBT()));
		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			if(side.getHorizontalIndex() != -1)
				return fuelItemHandler.cast();
			else if(side == Direction.UP)
				return inputItemHandler.cast();
			else if(side == Direction.DOWN)
				return outputItemHandler.cast();
		}

		return super.getCapability(cap, side);
	}

	@Override
	public void remove()
	{
		super.remove();

		inputItemHandler.ifPresent(h -> Block.spawnAsEntity(world, pos, h.getStackInSlot(0)));
		fuelItemHandler.ifPresent(h -> Block.spawnAsEntity(world, pos, h.getStackInSlot(0)));
		outputItemHandler.ifPresent(h -> Block.spawnAsEntity(world, pos, h.getStackInSlot(0)));
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player)
	{
		return new BrightforgeContainer(windowId, playerInv, pos);
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TranslationTextComponent(BlockRegistrar.BRIGHTFORGE.getTranslationKey());
	}

	public ItemStack getInputStack()
	{
		return inputItemHandler.orElse(EmptyHandler.INSTANCE).getStackInSlot(0);
	}

	public void setInputStack(ItemStack stack)
	{
		inputItemHandler.ifPresent(h -> ((ItemStackHandler)h).setStackInSlot(0, stack));
	}

	public ItemStack getFuelStack()
	{
		return fuelItemHandler.orElse(EmptyHandler.INSTANCE).getStackInSlot(0);
	}

	public void setFuelStack(ItemStack stack)
	{
		fuelItemHandler.ifPresent(h -> ((ItemStackHandler)h).setStackInSlot(0, stack));
	}

	public ItemStack getOutputStack()
	{
		return outputItemHandler.orElse(EmptyHandler.INSTANCE).getStackInSlot(0);
	}

	public void setOutputStack(ItemStack stack)
	{
		outputItemHandler.ifPresent(h -> ((ItemStackHandler)h).setStackInSlot(0, stack));
	}

	private IItemHandler createItemHandler()
	{
		return new ItemStackHandler(1) {
			@Override
			protected void onContentsChanged(int slot)
			{
				BrightforgeTileEntity.this.markDirty();
			}
		};
	}

	private IItemHandler createFuelItemHandler()
	{
		return new ItemStackHandler(1) {
			@Override
			public boolean isItemValid(int slot, ItemStack stack)
			{
				return stack.getItem() == Items.GLOWSTONE;
			}

			@Override
			protected void onContentsChanged(int slot)
			{
				BrightforgeTileEntity.this.markDirty();
			}
		};
	}
}
