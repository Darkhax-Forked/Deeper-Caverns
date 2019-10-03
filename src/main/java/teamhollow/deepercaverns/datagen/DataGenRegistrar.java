package teamhollow.deepercaverns.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import teamhollow.deepercaverns.DeeperCaverns;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
public class DataGenRegistrar
{
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();

		generator.addProvider(new BlockLootTableGenerator(generator));
		generator.addProvider(new EntityLootTableGenerator(generator));
		generator.addProvider(new RecipeGenerator(generator));
	}
}
