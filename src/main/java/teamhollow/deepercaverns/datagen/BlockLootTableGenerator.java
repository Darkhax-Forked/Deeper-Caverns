package teamhollow.deepercaverns.datagen;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds.IntBound;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.storage.loot.AlternativesLootEntry;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.ItemRegistrar;

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
		lootTables.put(BlockRegistrar.BRIMSTONE, createStandardBlockLootTable(BlockRegistrar.BRIMSTONE));
		lootTables.put(BlockRegistrar.GLOWSTONE_LANTERN, createStandardBlockLootTable(BlockRegistrar.GLOWSTONE_LANTERN));
		lootTables.put(BlockRegistrar.ONYX_ORE, LootTable.builder()
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(BlockRegistrar.ONYX_ORE))
						.acceptCondition(SurvivesExplosion.builder())));
		lootTables.put(BlockRegistrar.PALE_GLASS, LootTable.builder()
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(AlternativesLootEntry.func_216149_a(
								ItemLootEntry.builder(BlockRegistrar.PALE_GLASS)
								.acceptCondition(MatchTool.builder(
										new ItemPredicate.Builder().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntBound.atLeast(1))))),
								ItemLootEntry.builder(ItemRegistrar.PALE_GLASS_SHARDS)
								.acceptCondition(SurvivesExplosion.builder())))));
		lootTables.put(BlockRegistrar.PALE_SAND, createStandardBlockLootTable(BlockRegistrar.PALE_SAND));
		lootTables.put(BlockRegistrar.SOULBRYN_BLOCK, createStandardBlockLootTable(BlockRegistrar.SOULBRYN_BLOCK));
		lootTables.put(BlockRegistrar.SOULGLASS, LootTable.builder()
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(BlockRegistrar.SOULGLASS))
						.acceptCondition(MatchTool.builder(
								new ItemPredicate.Builder().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, IntBound.atLeast(1)))))));
		lootTables.put(BlockRegistrar.SOUL_ORE, LootTable.builder()
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(ItemRegistrar.RAW_SOUL))
						.acceptCondition(SurvivesExplosion.builder())));
		lootTables.put(BlockRegistrar.SOULSTONE, createStandardBlockLootTable(BlockRegistrar.SOULSTONE));
	}
}
