package teamhollow.deepercaverns.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import teamhollow.deepercaverns.reg.ItemRegistrar;

public class ChaloniteSwordItem extends SwordItem
{
	private final Side side;

	public ChaloniteSwordItem(IItemTier tier, int attackDamage, float attackSpeed, Properties builder, Side side)
	{
		super(tier, attackDamage, attackSpeed, builder);

		this.side = side;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		CompoundNBT tag = player.getHeldItem(hand).write(new CompoundNBT());

		tag.putString("id", side.getOpposite().getItem().getRegistryName().toString());
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, ItemStack.read(tag));
	}

	public static enum Side
	{
		ONYX, QUARTZ;

		public Side getOpposite()
		{
			return this == ONYX ? QUARTZ : ONYX;
		}

		public Item getItem()
		{
			return this == ONYX ? ItemRegistrar.CHALONITE_SWORD_ONYX : ItemRegistrar.CHALONITE_SWORD_QUARTZ;
		}
	}
}
