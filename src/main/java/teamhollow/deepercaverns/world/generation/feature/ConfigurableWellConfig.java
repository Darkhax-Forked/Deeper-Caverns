package teamhollow.deepercaverns.world.generation.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import mcp.MethodsReturnNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ConfigurableWellConfig implements IFeatureConfig {
	public final BlockState slab;
	public final BlockState block;
	public final boolean hasFluid;
	public final BlockState fluid;
	public final List<Block> validBottomBlocks;
	public final Predicate<BlockState> isValidBottomBlock;

	public ConfigurableWellConfig(BlockState slab, BlockState block, @Nullable BlockState fluid, Block... validBottomBlocks) {
		this(slab, block, fluid != null, fluid != null ? fluid : Blocks.AIR.getDefaultState(), Arrays.asList(validBottomBlocks));
	}

	private ConfigurableWellConfig(BlockState slab, BlockState block, boolean hasFluid, BlockState fluid, List<Block> validBottomBlocks) {
		this.slab = slab;
		this.block = block;
		this.hasFluid = hasFluid;
		this.fluid = fluid;
		this.validBottomBlocks = validBottomBlocks.stream().filter(validBottomBlock -> validBottomBlock.getRegistryName() != null).collect(Collectors.toList());
		isValidBottomBlock = validBottomBlocks.stream()
						.map(validBottomBlock -> (Predicate<BlockState>) BlockStateMatcher.forBlock(validBottomBlock))
						.reduce(Predicate::or)
						.orElse(BlockStateMatcher.ANY);
	}

	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> dataGenerator) {
		return new Dynamic<>(dataGenerator, dataGenerator.createMap(ImmutableMap.of(
						dataGenerator.createString("slab"), BlockState.serialize(dataGenerator, slab).getValue(),
						dataGenerator.createString("block"), BlockState.serialize(dataGenerator, block).getValue(),
						dataGenerator.createString("hasFluid"), dataGenerator.createBoolean(hasFluid),
						dataGenerator.createString("fluid"), BlockState.serialize(dataGenerator, fluid).getValue(),
						dataGenerator.createString("validBottomBlocks"), dataGenerator.createList(validBottomBlocks.stream().map(
										block -> dataGenerator.createString(Objects.requireNonNull(block.getRegistryName()).toString())
						))
		)));
	}

	public static <T> ConfigurableWellConfig deserialize(Dynamic<T> data) {
		BlockState slab = data.get("slab").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		BlockState block = data.get("block").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		boolean hasFluid = data.get("hasFluid").asBoolean(false);
		BlockState fluid = data.get("fluid").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		List<Block> validBottomBlocks = data.get("validBottomBlocks").asList(
						blockData -> Optional.ofNullable(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockData.asString("")))).orElse(Blocks.AIR)
		);
		return new ConfigurableWellConfig(slab, block, hasFluid, fluid, validBottomBlocks);
	}
}