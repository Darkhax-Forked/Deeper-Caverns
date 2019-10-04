package teamhollow.deepercaverns;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

//superfluous naming to counteract vanilla name duplicate
public class DCTags
{
	public static class Blocks
	{
	}

	public static class Items
	{
		public static final Tag<Item> FUEL_SOULFORGE = tag("fuel/soulforge");

		private static Tag<Item> tag(String name)
		{
			return new ItemTags.Wrapper(new ResourceLocation(DeeperCaverns.MODID, name));
		}
	}
}
