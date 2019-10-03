package teamhollow.deepercaverns.datagen;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds.IntBound;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import teamhollow.deepercaverns.reg.BlockRegistrar;

public class BlockLootTableGenerator extends BlockLootTableProvider
{
	public BlockLootTableGenerator(DataGenerator dataGenerator)
	{
		super(dataGenerator);
	}

	@Override
	protected void addTables()
	{
		lootTables.put(BlockRegistrar.BRIGHTFORGE, createStandardBlockLootTable(BlockRegistrar.BRIGHTFORGE));
		lootTables.put(BlockRegistrar.GLOWSTONE_LANTERN, createStandardBlockLootTable(BlockRegistrar.GLOWSTONE_LANTERN));
		lootTables.put(BlockRegistrar.SOULGLASS, LootTable.builder()
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(BlockRegistrar.SOULGLASS))
						.acceptCondition(MatchTool.builder(
								new ItemPredicate.Builder().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntBound.atLeast(1)))))));
	}
}
