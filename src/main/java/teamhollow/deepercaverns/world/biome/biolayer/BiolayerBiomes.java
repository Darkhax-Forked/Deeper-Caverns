package teamhollow.deepercaverns.world.biome.biolayer;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.util.RegistryUtil;
import teamhollow.deepercaverns.world.biome.SpawnEntry;
import teamhollow.deepercaverns.world.biome.SpawnEntryGroup;

@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class BiolayerBiomes {
	public static final Biome BIOLAYER_HELL = RegistryUtil.injected();
	// Soullands
	public static final Biome SOULSTONE_HILLS = RegistryUtil.injected();
	public static final Biome PALE_SAND_DESERT = RegistryUtil.injected();
	public static final Biome PALE_SAND_DESERT_HILLS = RegistryUtil.injected();
	public static final Biome PALE_SAND_DESERT_LAKES = RegistryUtil.injected();

	// Fricking Forge and not having an @ObjectHolder.Exclude annotation...
	public static class SpawnEntryGroups {
		public static final SpawnEntryGroup<Biome> SURFACE = new SpawnEntryGroup<>();
		public static final SpawnEntryGroup<Biome> SURFACE_HILLS = new SpawnEntryGroup<>();
	}

	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
		// TODO figure out good weights
		SpawnEntryGroups.SURFACE.add(
				new SpawnEntry<>(BIOLAYER_HELL, 6),
				new SpawnEntry<>(SOULSTONE_HILLS, 4),
				new SpawnEntry<>(PALE_SAND_DESERT, 5),
				new SpawnEntry<>(PALE_SAND_DESERT_HILLS, 1),
				new SpawnEntry<>(PALE_SAND_DESERT_LAKES, 2)
				);

		SpawnEntryGroups.SURFACE_HILLS.add(
				new SpawnEntry<>(SOULSTONE_HILLS, 5),
				new SpawnEntry<>(PALE_SAND_DESERT_HILLS, 3)
				);
	}
}