package teamhollow.deepercaverns.reg;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.entity.WitherCrusherEntity;
import teamhollow.deepercaverns.renderer.WitherCrusherRenderer;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientRegistrar
{
	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(WitherCrusherEntity.class, WitherCrusherRenderer::new);
	}
}
