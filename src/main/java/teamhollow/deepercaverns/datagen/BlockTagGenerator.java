package teamhollow.deepercaverns.datagen;

import java.nio.file.Path;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class BlockTagGenerator extends TagsProvider<Block>
{
	protected BlockTagGenerator(DataGenerator dataGenerator)
	{
		super(dataGenerator, Registry.BLOCK);
	}

	@Override
	protected void registerTags()
	{
	}

	@Override
	protected Path makePath(ResourceLocation id)
	{
		return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/blocks/" + id.getPath() + ".json");
	}

	@Override
	protected void setCollection(TagCollection<Block> collection)
	{
		BlockTags.setCollection(collection);
	}

	@Override
	public String getName()
	{
		return "DeeperCavernsBlockTags";
	}
}