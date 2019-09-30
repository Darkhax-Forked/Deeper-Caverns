package teamhollow.deepercaverns.datagen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;

//derived from McJty's tutorial
public abstract class BaseLootTableProvider<T> extends LootTableProvider
{
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	protected final Map<T,LootTable.Builder> lootTables = new HashMap<>();
	private final DataGenerator generator;

	public BaseLootTableProvider(DataGenerator dataGenerator)
	{
		super(dataGenerator);

		generator = dataGenerator;
	}

	protected abstract void addTables();
	protected abstract ResourceLocation getResourceLocation(Map.Entry<T,LootTable.Builder> entry);
	protected abstract LootTable getLootTable(Map.Entry<T,LootTable.Builder> entry);

	@Override
	public void act(DirectoryCache cache)
	{
		Map<ResourceLocation,LootTable> tables = new HashMap<>();

		addTables();

		for(Map.Entry<T,LootTable.Builder> entry : lootTables.entrySet())
		{
			tables.put(getResourceLocation(entry), getLootTable(entry));
		}

		writeTables(cache, tables);
	}

	private void writeTables(DirectoryCache cache, Map<ResourceLocation,LootTable> tables)
	{
		tables.forEach((key, lootTable) -> {
			try
			{
				IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), generator.getOutputFolder().resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}

	@Override
	public String getName()
	{
		return "DeeperCavernsLootTables";
	}
}
