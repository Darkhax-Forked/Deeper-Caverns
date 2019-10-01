package teamhollow.deepercaverns.world.biome.provider;

import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.util.RegistryUtil;

import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class DeeperCavernsBiomeProviderTypes {
	public static final BiomeProviderType<BiolayerBiomeProviderSettings, BiolayerBiomeProvider> BIOLAYER = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerChunkGeneratorTypes(RegistryEvent.Register<BiomeProviderType<?, ?>> event) {
		event.getRegistry().register(new BiomeProviderType<>(BiolayerBiomeProvider::new, BiolayerBiomeProviderSettings::new).setRegistryName("biolayer"));
	}

}
