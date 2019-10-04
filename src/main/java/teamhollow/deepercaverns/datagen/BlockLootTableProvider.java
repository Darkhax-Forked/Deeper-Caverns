package teamhollow.deepercaverns.datagen;

import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTable.Builder;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

public abstract class BlockLootTableProvider extends BaseLootTableProvider<Block>
{
	public BlockLootTableProvider(DataGenerator dataGenerator)
	{
		super(dataGenerator);
	}

	protected final LootTable.Builder createStandardBlockLootTable(Block block)
	{
		LootPool.Builder builder = LootPool.builder()
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block))
				.acceptCondition(SurvivesExplosion.builder());
		return LootTable.builder().addLootPool(builder);
	}

	@Override
	protected final ResourceLocation getResourceLocation(Entry<Block,Builder> entry)
	{
		return entry.getKey().getLootTable();
	}

	@Override
	protected final LootTable getLootTable(Entry<Block,Builder> entry)
	{
		return entry.getValue().setParameterSet(LootParameterSets.BLOCK).build();
	}
}
