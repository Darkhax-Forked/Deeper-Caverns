package teamhollow.deepercaverns.world.generation.surfacebuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import com.mojang.datafixers.Dynamic;

// TODO Do we want to move Config classes to inner classes of the thing they are the config of?
public class SoullandsSurfaceBuilderConfig extends SurfaceBuilderConfig {
	public SoullandsSurfaceBuilderConfig(BlockState topMaterial, BlockState underMaterial, BlockState underWaterMaterial) {
		super(topMaterial, underMaterial, underWaterMaterial);
	}

	public static SoullandsSurfaceBuilderConfig deserialize(Dynamic<?> serialisedData) {
		BlockState topMaterial = serialisedData.get("top_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		BlockState underMaterial = serialisedData.get("under_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		BlockState underwaterMaterial = serialisedData.get("underwater_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new SoullandsSurfaceBuilderConfig(topMaterial, underMaterial, underwaterMaterial);
	}
}