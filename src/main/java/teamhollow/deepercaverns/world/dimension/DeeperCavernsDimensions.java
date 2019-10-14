package teamhollow.deepercaverns.world.dimension;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.util.RegistryUtil;
import teamhollow.deepercaverns.world.dimension.biolayer.BiolayerDimension;

public class DeeperCavernsDimensions {
	private static final String biolayerId = DeeperCaverns.MODID + ":" + "biolayer";
	public static final ResourceLocation BIOLAYER_NAME = new ResourceLocation(biolayerId);
	@ObjectHolder(biolayerId)
	public static final ModDimension BIOLAYER = RegistryUtil.injected();
	public static DimensionType BIOLAYER_TYPE;

	@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.MOD)
	public static class ModEvents {
		@SubscribeEvent
		public static void registerModDimensions(RegistryEvent.Register<ModDimension> event) {
			event.getRegistry().register(new DeeperCavernsModDimension<>(BIOLAYER_NAME, BiolayerDimension::new));
		}
	}

	@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.FORGE)
	public static class ForgeEvents {
		@SubscribeEvent
		public static void registerDimensions(RegisterDimensionsEvent event) {
			//			if (DimensionType.byName(BIOLAYER_NAME) == null)
			BIOLAYER_TYPE = DimensionManager.registerDimension(BIOLAYER_NAME, BIOLAYER, null, false);
		}
	}
}