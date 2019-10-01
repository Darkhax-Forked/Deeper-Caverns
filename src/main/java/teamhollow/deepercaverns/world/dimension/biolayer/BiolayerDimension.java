package teamhollow.deepercaverns.world.dimension.biolayer;

import mcp.MethodsReturnNonnullByDefault;
import teamhollow.deepercaverns.world.biome.provider.DeeperCavernsBiomeProviderTypes;
import teamhollow.deepercaverns.world.generation.DeeperCavernsChunkGeneratorTypes;
import teamhollow.deepercaverns.world.generation.DeeperCavernsGenerationSettings;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BiolayerDimension extends Dimension {
	public BiolayerDimension(World world, DimensionType dimensionType) {
		super(world, dimensionType);
		doesWaterVaporize = true;
		// TODO find out what this does
		//nether = true;
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		DeeperCavernsGenerationSettings settings = DeeperCavernsChunkGeneratorTypes.DEEPER_CAVERNS.createSettings();
		settings.setDefaultBlock(Blocks.NETHERRACK.getDefaultState());
		settings.setDefaultFluid(Blocks.LAVA.getDefaultState());
		return DeeperCavernsChunkGeneratorTypes.DEEPER_CAVERNS.create(world, DeeperCavernsBiomeProviderTypes.BIOLAYER.create(DeeperCavernsBiomeProviderTypes.BIOLAYER.createSettings()), settings);
	}

	@Nullable
	@Override
	public BlockPos findSpawn(ChunkPos chunkPos, boolean checkValid) {
		return null;
	}

	@Nullable
	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
		return null;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.5F;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		return new Vec3d(0.2, 0.03, 0.03);
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean doesXZShowFog(int x, int z) {
		return true;
	}
}