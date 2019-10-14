package teamhollow.deepercaverns.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.util.RegistryUtil;
import teamhollow.deepercaverns.world.biome.biolayer.BiolayerHellBiome;
import teamhollow.deepercaverns.world.biome.biolayer.PaleSandDesertBiome;
import teamhollow.deepercaverns.world.biome.biolayer.PaleSandDesertHillsBiome;
import teamhollow.deepercaverns.world.biome.biolayer.PaleSandDesertLakesBiome;
import teamhollow.deepercaverns.world.biome.biolayer.SoulstoneHillsBiome;

@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class DeeperCavernsBiomes {
	public static final Biome BIOLAYER_HELL = RegistryUtil.injected();
	// Soullands
	public static final Biome SOULSTONE_HILLS = RegistryUtil.injected();
	public static final Biome PALE_SAND_DESERT = RegistryUtil.injected();
	public static final Biome PALE_SAND_DESERT_HILLS = RegistryUtil.injected();
	public static final Biome PALE_SAND_DESERT_LAKES = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().register(new BiolayerHellBiome().setRegistryName("biolayer_hell"));
		event.getRegistry().register(new SoulstoneHillsBiome().setRegistryName("soulstone_hills"));
		event.getRegistry().register(new PaleSandDesertBiome().setRegistryName("pale_sand_desert"));
		event.getRegistry().register(new PaleSandDesertHillsBiome().setRegistryName("pale_sand_desert_hills"));
		event.getRegistry().register(new PaleSandDesertLakesBiome().setRegistryName("pale_sand_desert_lakes"));
	}
}