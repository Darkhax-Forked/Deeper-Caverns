package teamhollow.deepercaverns.world.generation.surfacebuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.reg.BlockRegistrar;

@Mod.EventBusSubscriber(modid = DeeperCaverns.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DeeperCaverns.MODID)
public class DeeperCavernsSurfaceBuilders {
	public static class BlockStates {
		public static final BlockState AIR = Blocks.AIR.getDefaultState();
		public static final BlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
		public static final BlockState NETHERRACK = Blocks.NETHERRACK.getDefaultState();
		public static final BlockState SOUL_SAND = Blocks.SOUL_SAND.getDefaultState();
		public static final BlockState SOULSTONE = BlockRegistrar.SOULSTONE.getDefaultState();
		public static final BlockState PALE_SAND = BlockRegistrar.PALE_SAND.getDefaultState();
		public static final BlockState NETHER_BRICKS = Blocks.NETHER_BRICKS.getDefaultState();
		public static final BlockState NETHER_BRICK_SLAB = Blocks.NETHER_BRICK_SLAB.getDefaultState();
	}

	public static class Config {
		public static final SurfaceBuilderConfig HELL = new SurfaceBuilderConfig(BlockStates.NETHERRACK, BlockStates.NETHERRACK, BlockStates.NETHERRACK);
		public static final SoullandsSurfaceBuilderConfig SOULSTONE_HILLS = new SoullandsSurfaceBuilderConfig(BlockStates.SOULSTONE, BlockStates.SOULSTONE, BlockStates.GRAVEL);
		public static final SurfaceBuilderConfig PALE_SAND = new SurfaceBuilderConfig(BlockStates.PALE_SAND, BlockStates.PALE_SAND, BlockStates.PALE_SAND);
	}

	public static final SurfaceBuilder<SoullandsSurfaceBuilderConfig> SOULSTONE_HILLS = new SoulstoneHillsSurfaceBuilder(SoullandsSurfaceBuilderConfig::deserialize);

	@SubscribeEvent
	public static void registerChunkGeneratorTypes(RegistryEvent.Register<SurfaceBuilder<?>> event) {
		event.getRegistry().register(SOULSTONE_HILLS.setRegistryName("soulstone_hills"));
	}
}