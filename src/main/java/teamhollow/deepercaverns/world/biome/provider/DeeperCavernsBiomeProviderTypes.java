package teamhollow.deepercaverns.world.biome.provider;

import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.util.RegistryUtil;

@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class DeeperCavernsBiomeProviderTypes {
	public static final BiomeProviderType<LayeredBiomeProviderSettings, LayeredBiomeProvider> BIOLAYER = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerChunkGeneratorTypes(RegistryEvent.Register<BiomeProviderType<?, ?>> event) {
		event.getRegistry().register(new BiomeProviderType<>(
						LayeredBiomeProvider::new,
						LayeredBiomeProviderSettings::new
		).setRegistryName("biolayer"));
	}
}