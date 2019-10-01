package teamhollow.deepercaverns.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import teamhollow.deepercaverns.reg.EntityRegistrar;

public class EntityLootTableGenerator extends EntityLootTableProvider
{
	public EntityLootTableGenerator(DataGenerator dataGenerator)
	{
		super(dataGenerator);
	}

	@Override
	protected void addTables()
	{
		lootTables.put(EntityRegistrar.IGNEOUS_GOLEM, LootTable.builder()
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(Items.IRON_INGOT)
								.acceptFunction(SetCount.func_215932_a(RandomValueRange.func_215837_a(2.0F, 4.0F)))))
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(Items.IRON_NUGGET)
								.acceptFunction(SetCount.func_215932_a(RandomValueRange.func_215837_a(4.0F, 8.0F))))));
		//same as normal wither skeleton, but without the wither skeleton skull
		lootTables.put(EntityRegistrar.WITHER_CRUSHER, LootTable.builder()
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(Items.COAL)
								.acceptFunction(SetCount.func_215932_a(RandomValueRange.func_215837_a(-1.0F, 1.0F)))
								.acceptFunction(LootingEnchantBonus.func_215915_a(RandomValueRange.func_215837_a(0.0F, 1.0F)))))
				.addLootPool(LootPool.builder()
						.rolls(ConstantRange.of(1))
						.addEntry(ItemLootEntry.builder(Items.BONE)
								.acceptFunction(SetCount.func_215932_a(RandomValueRange.func_215837_a(0.0F, 2.0F)))
								.acceptFunction(LootingEnchantBonus.func_215915_a(RandomValueRange.func_215837_a(0.0F, 1.0F))))));
	}
}
