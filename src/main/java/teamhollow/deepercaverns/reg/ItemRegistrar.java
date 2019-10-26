package teamhollow.deepercaverns.reg;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.misc.ArmorMaterial;
import teamhollow.deepercaverns.misc.ItemTier;
import teamhollow.deepercaverns.util.RegistryUtil;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class ItemRegistrar
{
	private static List<Block> blocksWithItem = new ArrayList<>();
	public static final Item ARCANE_SPAWN_EGG = RegistryUtil.injected();
	public static final Item BRIMSTONE_POWDER = RegistryUtil.injected();
	public static final Item CHALONITE_INGOT = RegistryUtil.injected();
	public static final Item CHALONITE_SWORD = RegistryUtil.injected();
	public static final Item GHOSTSOUL_AXE = RegistryUtil.injected();
	public static final Item GHOSTSOUL_BOOTS = RegistryUtil.injected();
	public static final Item GHOSTSOUL_CHESTPLATE = RegistryUtil.injected();
	public static final Item GHOSTSOUL_HELMET = RegistryUtil.injected();
	public static final Item GHOSTSOUL_HOE = RegistryUtil.injected();
	public static final Item GHOSTSOUL_INGOT = RegistryUtil.injected();
	public static final Item GHOSTSOUL_LEGGINGS = RegistryUtil.injected();
	public static final Item GHOSTSOUL_PICKAXE = RegistryUtil.injected();
	public static final Item GHOSTSOUL_SHOVEL = RegistryUtil.injected();
	public static final Item GHOSTSOUL_SWORD = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_AXE = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_BOOTS = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_CHESTPLATE = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_HELMET = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_HOE = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_LEGGINGS = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_PICKAXE = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_SHOVEL = RegistryUtil.injected();
	public static final Item GLOWSTONE_CRYSTAL_SWORD = RegistryUtil.injected();
	public static final Item GLURKER_SPAWN_EGG = RegistryUtil.injected();
	public static final Item IGNEOUS_GOLEM_SPAWN_EGG = RegistryUtil.injected();
	public static final Item ONYX_ARROW = RegistryUtil.injected();
	public static final Item ONYX_AXE = RegistryUtil.injected();
	public static final Item ONYX_HOE = RegistryUtil.injected();
	public static final Item ONYX_GEM = RegistryUtil.injected();
	public static final Item ONYX_PICKAXE = RegistryUtil.injected();
	public static final Item ONYX_SHOVEL = RegistryUtil.injected();
	public static final Item ONYX_SWORD = RegistryUtil.injected();
	public static final Item PALE_GLASS_SHARDS = RegistryUtil.injected();
	public static final Item ROCK_GOLEM_SPAWN_EGG = RegistryUtil.injected();
	public static final Item SOULBRYN = RegistryUtil.injected();
	public static final Item SOUL_INGOT = RegistryUtil.injected();
	public static final Item SURGEFLY_SPAWN_EGG = RegistryUtil.injected();
	public static final Item WITHER_CRUSHER_SPAWN_EGG = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		for(Block block : blocksWithItem)
		{
			event.getRegistry().register(new BlockItem(block, defaultItemProperties()).setRegistryName(block.getRegistryName()));
		}

		/*spawn eggs*/
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.ARCANE, 0x3C1361, 0x660066));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.GLURKER, 0xFF5733, 0xC70039));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.IGNEOUS_GOLEM, 0xEC5300, 0xFFB605));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.ROCK_GOLEM, 0x505050, 0x107414));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.SURGEFLY, 0xE9D700, 0xA98600));
		event.getRegistry().register(makeSpawnEgg(EntityRegistrar.WITHER_CRUSHER, 0x333333, 0x999999));
		/*random items*/
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName("brimstone_powder"));
		event.getRegistry().register(new ArrowItem(defaultItemProperties()).setRegistryName("onyx_arrow"));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName("pale_glass_shards"));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName("soulbryn"));
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName("soul_ingot"));
		/*armor, tools, and their materials*/ //TODO: change tool values, currently they're like gold by default
		//ghostsoul
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName("ghostsoul_ingot"));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GHOSTSOUL, EquipmentSlotType.HEAD, defaultItemProperties()).setRegistryName("ghostsoul_helmet"));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GHOSTSOUL, EquipmentSlotType.CHEST, defaultItemProperties()).setRegistryName("ghostsoul_chestplate"));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GHOSTSOUL, EquipmentSlotType.LEGS, defaultItemProperties()).setRegistryName("ghostsoul_leggings"));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GHOSTSOUL, EquipmentSlotType.FEET, defaultItemProperties()).setRegistryName("ghostsoul_boots"));
		event.getRegistry().register(new SwordItem(ItemTier.GHOSTSOUL, 3, -2.4F, defaultItemProperties()).setRegistryName("ghostsoul_sword"));
		event.getRegistry().register(new ShovelItem(ItemTier.GHOSTSOUL, 1.5F, -3.0F, defaultItemProperties()).setRegistryName("ghostsoul_shovel"));
		event.getRegistry().register(new PickaxeItem(ItemTier.GHOSTSOUL, 1, -2.8F, defaultItemProperties()).setRegistryName("ghostsoul_pickaxe"));
		event.getRegistry().register(new AxeItem(ItemTier.GHOSTSOUL, 6.0F, -3.0F, defaultItemProperties()).setRegistryName("ghostsoul_axe"));
		event.getRegistry().register(new HoeItem(ItemTier.GHOSTSOUL, -3.0F, defaultItemProperties()).setRegistryName("ghostsoul_hoe"));
		//glowstone crystal
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName("glowstone_crystal"));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GLOWSTONE_CRYSTAL, EquipmentSlotType.HEAD, defaultItemProperties()).setRegistryName("glowstone_crystal_helmet"));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GLOWSTONE_CRYSTAL, EquipmentSlotType.CHEST, defaultItemProperties()).setRegistryName("glowstone_crystal_chestplate"));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GLOWSTONE_CRYSTAL, EquipmentSlotType.LEGS, defaultItemProperties()).setRegistryName("glowstone_crystal_leggings"));
		event.getRegistry().register(new ArmorItem(ArmorMaterial.GLOWSTONE_CRYSTAL, EquipmentSlotType.FEET, defaultItemProperties()).setRegistryName("glowstone_crystal_boots"));
		event.getRegistry().register(new SwordItem(ItemTier.GLOWSTONE_CRYSTAL, 3, -2.4F, defaultItemProperties()).setRegistryName("glowstone_crystal_sword"));
		event.getRegistry().register(new ShovelItem(ItemTier.GLOWSTONE_CRYSTAL, 1.5F, -3.0F, defaultItemProperties()).setRegistryName("glowstone_crystal_shovel"));
		event.getRegistry().register(new PickaxeItem(ItemTier.GLOWSTONE_CRYSTAL, 1, -2.8F, defaultItemProperties()).setRegistryName("glowstone_crystal_pickaxe"));
		event.getRegistry().register(new AxeItem(ItemTier.GLOWSTONE_CRYSTAL, 6.0F, -3.0F, defaultItemProperties()).setRegistryName("glowstone_crystal_axe"));
		event.getRegistry().register(new HoeItem(ItemTier.GLOWSTONE_CRYSTAL, -3.0F, defaultItemProperties()).setRegistryName("glowstone_crystal_hoe"));
		//onyx
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName("onyx_gem"));
		event.getRegistry().register(new SwordItem(ItemTier.ONYX, 3, -2.4F, defaultItemProperties()).setRegistryName("onyx_sword"));
		event.getRegistry().register(new ShovelItem(ItemTier.ONYX, 1.5F, -3.0F, defaultItemProperties()).setRegistryName("onyx_shovel"));
		event.getRegistry().register(new PickaxeItem(ItemTier.ONYX, 1, -2.8F, defaultItemProperties()).setRegistryName("onyx_pickaxe"));
		event.getRegistry().register(new AxeItem(ItemTier.ONYX, 6.0F, -3.0F, defaultItemProperties()).setRegistryName("onyx_axe"));
		event.getRegistry().register(new HoeItem(ItemTier.ONYX, -3.0F, defaultItemProperties()).setRegistryName("onyx_hoe"));
		//chalonite
		event.getRegistry().register(new Item(defaultItemProperties()).setRegistryName("chalonite_ingot"));
		event.getRegistry().register(new SwordItem(ItemTier.CHALONITE, 3, -2.4F, defaultItemProperties()).setRegistryName("chalonite_sword"));
		blocksWithItem = null;
	}

	private static SpawnEggItem makeSpawnEgg(EntityType<?> type, int mainEggColor, int secondaryEggColor)
	{
		return (SpawnEggItem)new SpawnEggItem(type, mainEggColor, secondaryEggColor, defaultItemProperties())
				.setRegistryName(type.getRegistryName().getPath() + "_spawn_egg");
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
