package teamhollow.deepercaverns.datagen;

import java.util.Map.Entry;

import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTable.Builder;

public abstract class EntityLootTableProvider extends BaseLootTableProvider<EntityType<?>>
{
	public EntityLootTableProvider(DataGenerator dataGenerator)
	{
		super(dataGenerator);
	}

	@Override
	protected final ResourceLocation getResourceLocation(Entry<EntityType<?>,Builder> entry)
	{
		return entry.getKey().getLootTable();
	}

	@Override
	protected final LootTable getLootTable(Entry<EntityType<?>,Builder> entry)
	{
		return entry.getValue().setParameterSet(LootParameterSets.ENTITY).build();
	}
}
