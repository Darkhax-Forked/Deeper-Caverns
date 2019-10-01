package teamhollow.deepercaverns.reg;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class ItemRegistrar
{
	public static final Item IGNEOUS_GOLEM_SPAWN_EGG = null;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.IGNEOUS_GOLEM, 0xEC5300, 0xFFB605));
	}

	private static SpawnEggItem makeSpawnEgg(EntityType<?> type, int mainEggColor, int secondaryEggColor)
	{
		return (SpawnEggItem)new SpawnEggItem(type, mainEggColor, secondaryEggColor, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName(new ResourceLocation(DeeperCaverns.MODID, type.getRegistryName().getPath() + "_spawn_egg"));
	}
}
