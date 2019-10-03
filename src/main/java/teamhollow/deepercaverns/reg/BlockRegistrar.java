package teamhollow.deepercaverns.reg;

import net.minecraft.block.Block;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.block.BrightforgeBlock;
import teamhollow.deepercaverns.util.RegistryUtil;

@EventBusSubscriber(modid=DeeperCaverns.MODID, bus=Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class BlockRegistrar
{
	public static final Block BRIGHTFORGE = RegistryUtil.injected();
	public static final Block BRIMSTONE = RegistryUtil.injected();
	public static final Block GLOWSTONE_LANTERN = RegistryUtil.injected();
	public static final Block PALE_GLASS = RegistryUtil.injected();
	public static final Block PALE_SAND = RegistryUtil.injected();
	public static final Block SOULGLASS = RegistryUtil.injected();
	public static final Block SOUL_ORE = RegistryUtil.injected();
	public static final Block SOULSTONE = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(withItemBlock(new BrightforgeBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "brightforge"))));
		event.getRegistry().register(withItemBlock(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "brimstone"))));
		event.getRegistry().register(withItemBlock(new Block(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).lightValue(15)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "glowstone_lantern"))));
		event.getRegistry().register(withItemBlock(new GlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "pale_glass"))));
		event.getRegistry().register(withItemBlock(new SandBlock(0x5B4538, Block.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "pale_sand"))));
		event.getRegistry().register(withItemBlock(new GlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "soulglass"))));
		event.getRegistry().register(withItemBlock(new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "soul_ore"))));
		event.getRegistry().register(withItemBlock(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "soulstone"))));
	}

	private static Block withItemBlock(Block block)
	{
		ItemRegistrar.registerItemBlock(block);
		return block;
	}
}
