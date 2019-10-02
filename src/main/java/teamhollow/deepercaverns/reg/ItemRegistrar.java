package teamhollow.deepercaverns.reg;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
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
	public static final Item ARCANE_SPAWN_EGG = null;
	public static final Item IGNEOUS_GOLEM_SPAWN_EGG = null;
	public static final Item ROCK_GOLEM_SPAWN_EGG = null;
	public static final Item WITHER_CRUSHER_SPAWN_EGG = null;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.ARCANE, 0x3C1361, 0x660066));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.IGNEOUS_GOLEM, 0xEC5300, 0xFFB605));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.ROCK_GOLEM, 0x505050, 0x107414));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.WITHER_CRUSHER, 0x333333, 0x999999));
	}

	private static SpawnEggItem makeSpawnEgg(EntityType<?> type, int mainEggColor, int secondaryEggColor)
	{
		return (SpawnEggItem)new SpawnEggItem(type, mainEggColor, secondaryEggColor, new Item.Properties().group(DeeperCaverns.ITEM_GROUP))
				.setRegistryName(new ResourceLocation(DeeperCaverns.MODID, type.getRegistryName().getPath() + "_spawn_egg"));
	}
}
