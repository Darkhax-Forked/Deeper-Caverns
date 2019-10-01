package teamhollow.deepercaverns.world.generation;

import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.util.RegistryUtil;

import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class DeeperCavernsChunkGeneratorTypes {
	public static final ChunkGeneratorType<DeeperCavernsGenerationSettings, DeeperCavernsChunkGenerator> DEEPER_CAVERNS = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerChunkGeneratorTypes(RegistryEvent.Register<ChunkGeneratorType<?, ?>> event) {
		event.getRegistry().register(new ChunkGeneratorType<>(DeeperCavernsChunkGenerator::new, true, DeeperCavernsGenerationSettings::new).setRegistryName("deeper_caverns"));
	}
}