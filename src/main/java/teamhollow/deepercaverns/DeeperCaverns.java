package teamhollow.deepercaverns;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import teamhollow.deepercaverns.reg.ItemRegistrar;

@Mod(DeeperCaverns.MODID)
public class DeeperCaverns
{
	public static final String MODID = "deepercaverns";
	public static final String PREFIX = MODID + ":";
	public static final ItemGroup ITEM_GROUP = new ItemGroup(ItemGroup.GROUPS.length, MODID) {
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(ItemRegistrar.ARCANE_SPAWN_EGG);
		}
	};

	public DeeperCaverns()
	{
	}
}
