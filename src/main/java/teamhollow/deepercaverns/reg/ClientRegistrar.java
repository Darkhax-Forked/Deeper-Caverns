package teamhollow.deepercaverns.reg;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.client.renderer.ArcaneRenderer;
import teamhollow.deepercaverns.client.renderer.ShadeRenderer;
import teamhollow.deepercaverns.client.renderer.SoulfurBlockEntityRenderer;
import teamhollow.deepercaverns.client.renderer.WitherCrusherRenderer;
import teamhollow.deepercaverns.client.screen.BrightforgeScreen;
import teamhollow.deepercaverns.client.screen.SoulforgeScreen;
import teamhollow.deepercaverns.entity.ArcaneEntity;
import teamhollow.deepercaverns.entity.ShadeEntity;
import teamhollow.deepercaverns.entity.SoulfurBlockEntity;
import teamhollow.deepercaverns.entity.WitherCrusherEntity;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientRegistrar
{
	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(SoulfurBlockEntity.class, SoulfurBlockEntityRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(WitherCrusherEntity.class, WitherCrusherRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ShadeEntity.class, ShadeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ArcaneEntity.class, ArcaneRenderer::new);
		ScreenManager.registerFactory(ContainerTypeRegistrar.BRIGHTFORGE, BrightforgeScreen::new);
		ScreenManager.registerFactory(ContainerTypeRegistrar.SOULFORGE, SoulforgeScreen::new);
	}
}
