package teamhollow.deepercaverns.reg;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.container.SoulforgeContainer;
import teamhollow.deepercaverns.util.RegistryUtil;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class ContainerTypeRegistrar
{
	public static final ContainerType<SoulforgeContainer> SOULFORGE = RegistryUtil.injected();

	@SubscribeEvent
	public static void onRegisterContainerTypes(RegistryEvent.Register<ContainerType<?>> event)
	{
		event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> new SoulforgeContainer(windowId, inv, data.readBlockPos())).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "soulforge")));
	}
}
