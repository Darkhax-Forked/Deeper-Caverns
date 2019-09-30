package teamhollow.deepercaverns.datagen;

import net.minecraft.data.DataGenerator;

public class BlockLootTableGenerator extends BlockLootTableProvider
{
	public BlockLootTableGenerator(DataGenerator dataGenerator)
	{
		super(dataGenerator);
	}

	@Override
	protected void addTables()
	{
		//example
		//lootTables.put(Blocks.ACACIA_BUTTON, createStandardBlockLootTable("acacia_button", Blocks.ACACIA_BUTTON));
	}
}
