package teamhollow.deepercaverns.block;


import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import teamhollow.deepercaverns.DeeperCaverns;

@ObjectHolder(DeeperCaverns.MODID)
@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.MOD)
public class DeeperCavernsBlocks {
	//public static final Block SOULSTONE = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		// TODO move blocks registration to this class
		//event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).hardnessAndResistance(0.4F)).setRegistryName("soulstone"));
	}
}