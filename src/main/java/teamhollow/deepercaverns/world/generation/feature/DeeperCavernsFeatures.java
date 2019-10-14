package teamhollow.deepercaverns.world.generation.feature;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import teamhollow.deepercaverns.DeeperCaverns;

@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class DeeperCavernsFeatures {
	public static final Feature<ConfigurableWellConfig> CONFIGURABLE_WELL = new ConfigurableWellFeature(ConfigurableWellConfig::deserialize);

	@SubscribeEvent
	public static void registerChunkGeneratorTypes(RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().register(CONFIGURABLE_WELL.setRegistryName("configurable_well"));
	}
}
