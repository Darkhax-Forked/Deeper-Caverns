package teamhollow.deepercaverns.reg;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.misc.ArmorMaterial;
import teamhollow.deepercaverns.util.RegistryUtil;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class ItemRegistrar
{
	private static List<Block> blocksWithItem = new ArrayList<>();
	public static final Item ARCANE_SPAWN_EGG = RegistryUtil.injected();
	public static final Item BRIMSTONE_POWDER = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_BOOTS = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_CHESTPLATE = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_HELMET = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_LEGGINGS = RegistryUtil.injected();
	public static final Item GLURKER_SPAWN_EGG = RegistryUtil.injected();
	public static final Item IGNEOUS_GOLEM_SPAWN_EGG = RegistryUtil.injected();
	public static final Item ONYX_INGOT = RegistryUtil.injected();
	public static final Item PALE_GLASS_SHARDS = RegistryUtil.injected();
	public static final Item RAW_SOUL = RegistryUtil.injected();
	public static final Item ROCK_GOLEM_SPAWN_EGG = RegistryUtil.injected();
	public static final Item SOULBRYN = RegistryUtil.injected();
	public static final Item SURGEFLY_SPAWN_EGG = RegistryUtil.injected();
	public static final Item WITHER_CRUSHER_SPAWN_EGG = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		for(Block block : blocksWithItem)
		{
			event.getRegistry().register(new BlockItem(block, defaultItemProperties()).setRegistryName(block.getRegistryName()));
		}

		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.ARCANE, 0x3C1361, 0x660066));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "brimstone_powder")));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "glowstone_crystal")));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GLOWSTONE_CRYSTAL, EquipmentSlotType.FEET, defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "glowstone_crystal_boots")));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GLOWSTONE_CRYSTAL, EquipmentSlotType.CHEST, defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "glowstone_crystal_chestplate")));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GLOWSTONE_CRYSTAL, EquipmentSlotType.HEAD, defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "glowstone_crystal_helmet")));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GLOWSTONE_CRYSTAL, EquipmentSlotType.LEGS, defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "glowstone_crystal_leggings")));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.GLURKER, 0xFF5733, 0xC70039));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.IGNEOUS_GOLEM, 0xEC5300, 0xFFB605));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "onyx_ingot")));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "pale_glass_shards")));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "raw_soul")));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.ROCK_GOLEM, 0x505050, 0x107414));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "soulbryn")));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.SURGEFLY, 0xE9D700, 0xA98600));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.WITHER_CRUSHER, 0x333333, 0x999999));
		blocksWithItem = null;
	}

	private static SpawnEggItem makeSpawnEgg(EntityType<?> type, int mainEggColor, int secondaryEggColor)
	{
		return (SpawnEggItem)new SpawnEggItem(type, mainEggColor, secondaryEggColor, defaultItemProperties())
				.setRegistryName(new ResourceLocation(DeeperCaverns.MODID, type.getRegistryName().getPath() + "_spawn_egg"));
	}

	private static Item.Properties defaultItemProperties()
	{
		return new Item.Properties().group(DeeperCaverns.ITEM_GROUP);
	}

	public static void registerItemBlock(Block block)
	{
		blocksWithItem.add(block);
	}
}
