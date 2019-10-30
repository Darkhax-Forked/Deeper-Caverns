package teamhollow.deepercaverns.reg;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.block.BiolayerPortalBlock;
import teamhollow.deepercaverns.block.BlueFireBlock;
import teamhollow.deepercaverns.block.BrightforgeBlock;
import teamhollow.deepercaverns.block.CustomGlassBlock;
import teamhollow.deepercaverns.block.CustomPaneBlock;
import teamhollow.deepercaverns.block.SoulEssenceCauldronBlock;
import teamhollow.deepercaverns.block.SoulforgeBlock;
import teamhollow.deepercaverns.block.SoulfurBlock;
import teamhollow.deepercaverns.util.RegistryUtil;

@EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class BlockRegistrar {
	public static final Block BIOLAYER_PORTAL = RegistryUtil.injected();
	public static final Block BLUE_FIRE = RegistryUtil.injected();
	public static final Block BRIGHTFORGE = RegistryUtil.injected();
	public static final Block CHISELED_OBSIDIAN = RegistryUtil.injected();
	public static final Block GLOWSTONE_LANTERN = RegistryUtil.injected();
	public static final Block ONYX_ORE = RegistryUtil.injected();
	public static final Block PALE_GLASS = RegistryUtil.injected();
	public static final Block PALE_GLASS_PANE = RegistryUtil.injected();
	public static final Block PALE_SAND = RegistryUtil.injected();
	public static final Block RAW_SULFUR = RegistryUtil.injected();
	public static final Block SOUL_ESSENCE_CAULDRON = RegistryUtil.injected();
	public static final Block SOULFORGE = RegistryUtil.injected();
	public static final Block SOULFUR_BLOCK = RegistryUtil.injected();
	public static final Block SOULGLASS = RegistryUtil.injected();
	public static final Block SOULGLASS_PANE = RegistryUtil.injected();
	public static final Block SOULSTONE = RegistryUtil.injected();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new BiolayerPortalBlock(Block.Properties.from(Blocks.NETHER_PORTAL)).setRegistryName("biolayer_portal"));
		event.getRegistry().register(new BlueFireBlock(Block.Properties.from(Blocks.FIRE)).setRegistryName("blue_fire"));
		event.getRegistry().register(withItemBlock(new BrightforgeBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F)).setRegistryName("brightforge")));
		event.getRegistry().register(withItemBlock(new Block(Block.Properties.from(Blocks.OBSIDIAN)).setRegistryName("chiseled_obsidian")));
		event.getRegistry().register(withItemBlock(new Block(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).lightValue(15)).setRegistryName("glowstone_lantern")));
		event.getRegistry().register(withItemBlock(new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)).setRegistryName("onyx_ore")));
		event.getRegistry().register(withItemBlock(new CustomGlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS)).setRegistryName("pale_glass")));
		event.getRegistry().register(withItemBlock(new CustomPaneBlock(Block.Properties.from(Blocks.GLASS_PANE)).setRegistryName("pale_glass_pane")));
		event.getRegistry().register(withItemBlock(new SandBlock(0x5B4538, Block.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)).setRegistryName("pale_sand")));
		event.getRegistry().register(withItemBlock(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)).setRegistryName("raw_sulfur")));
		event.getRegistry().register(new SoulEssenceCauldronBlock(Block.Properties.from(Blocks.CAULDRON).lootFrom(Blocks.CAULDRON)).setRegistryName("soul_essence_cauldron"));
		event.getRegistry().register(withItemBlock(new SoulforgeBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F)).setRegistryName("soulforge")));
		event.getRegistry().register(withItemBlock(new SoulfurBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 6.0F)).setRegistryName("soulfur_block")));
		event.getRegistry().register(withItemBlock(new CustomGlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS)).setRegistryName("soulglass")));
		event.getRegistry().register(withItemBlock(new CustomPaneBlock(Block.Properties.from(Blocks.GLASS_PANE)).setRegistryName("soulglass_pane")));
		event.getRegistry().register(withItemBlock(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)).setRegistryName("soulstone")));
	}

	private static Block withItemBlock(Block block) {
		ItemRegistrar.registerItemBlock(block);
		return block;
	}
}