package teamhollow.deepercaverns.item;


import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Objects;

import teamhollow.deepercaverns.DeeperCaverns;

@ObjectHolder(DeeperCaverns.MODID)
@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.MOD)
public class DeeperCavernsItems {
	//public static final BlockItem SOULSTONE = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Item> event) {
		// TODO move item registration over to this class
		//event.getRegistry().register(blockItem(DeeperCavernsBlocks.SOULSTONE, ItemGroup.BUILDING_BLOCKS));
	}

	private static Item blockItem(Block block, ItemGroup group) {
		return new BlockItem(block, new Item.Properties().group(group)).setRegistryName(Objects.requireNonNull(block.getRegistryName()));
	}
}