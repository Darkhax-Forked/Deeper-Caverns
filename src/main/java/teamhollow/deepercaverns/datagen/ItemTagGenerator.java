package teamhollow.deepercaverns.datagen;

import java.nio.file.Path;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import teamhollow.deepercaverns.DCTags;

public class ItemTagGenerator extends TagsProvider<Item>
{
	protected ItemTagGenerator(DataGenerator dataGenerator)
	{
		super(dataGenerator, Registry.ITEM);
	}

	@Override
	protected void registerTags()
	{
		getBuilder(DCTags.Items.FUEL_SOULFORGE).add(Items.LAVA_BUCKET, Items.MAGMA_BLOCK);
	}

	@Override
	protected Path makePath(ResourceLocation id)
	{
		return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/items/" + id.getPath() + ".json");
	}

	@Override
	protected void setCollection(TagCollection<Item> collection)
	{
		ItemTags.setCollection(collection);
	}

	@Override
	public String getName()
	{
		return "DeeperCavernsItemTags";
	}
}
