package teamhollow.deepercaverns.world.dimension;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

public class DeeperCavernsModDimension<D extends Dimension> extends ModDimension {
	private final BiFunction<World, DimensionType, D> factory;

	public DeeperCavernsModDimension(ResourceLocation registryName, BiFunction<World, DimensionType, D> factory) {
		setRegistryName(registryName);
		this.factory = factory;
	}

	@Override
	public BiFunction<World, DimensionType, D> getFactory() {
		return factory;
	}
}
