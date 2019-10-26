package teamhollow.deepercaverns.reg;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.tileentity.BrightforgeTileEntity;
import teamhollow.deepercaverns.tileentity.SoulforgeTileEntity;
import teamhollow.deepercaverns.util.RegistryUtil;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class TileEntityTypeRegistrar
{
	public static final TileEntityType<SoulforgeTileEntity> BRIGHTFORGE = RegistryUtil.injected();
	public static final TileEntityType<SoulforgeTileEntity> SOULFORGE = RegistryUtil.injected();

	@SubscribeEvent
	public static void onRegisterTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event)
	{
		event.getRegistry().register(TileEntityType.Builder.create(BrightforgeTileEntity::new, BlockRegistrar.BRIGHTFORGE).build(null).setRegistryName("brightforge"));
		event.getRegistry().register(TileEntityType.Builder.create(SoulforgeTileEntity::new, BlockRegistrar.SOULFORGE).build(null).setRegistryName("soulforge"));
	}
}
