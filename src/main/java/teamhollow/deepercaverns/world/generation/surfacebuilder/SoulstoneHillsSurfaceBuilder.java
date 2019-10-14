package teamhollow.deepercaverns.world.generation.surfacebuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import teamhollow.deepercaverns.reg.BlockRegistrar;

// Inspired by the Eroded Badlands
@ParametersAreNonnullByDefault
public class SoulstoneHillsSurfaceBuilder extends SoullandsSurfaceBuilder {
	private static final BlockState SOUL_SAND = Blocks.SOUL_SAND.getDefaultState();
	private static BlockState SOULSTONE;
	private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
	private static final BlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
	//protected long seed;
	//protected OctavesNoiseGenerator noiseGenerator;

	public SoulstoneHillsSurfaceBuilder(Function<Dynamic<?>, ? extends SoullandsSurfaceBuilderConfig> deserializer) {
		super(deserializer);
	}

	@Override
	public void buildSurface(Random random, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SoullandsSurfaceBuilderConfig config) {
		// Because Forge hates me for some reason
		if (SOULSTONE == null) SOULSTONE = BlockRegistrar.SOULSTONE.getDefaultState();
		double erodedHeight = 0.0D;
		double fourNoise = Math.min(Math.abs(noise), fourLevelNoise.getValue(x * 0.25, z * 0.25));
		if (fourNoise > 0.0D) {
			double noiseMultiplier = 0.001953125D;
			double singleNoise = Math.abs(singleLevelNoise.getValue(x * noiseMultiplier, z * noiseMultiplier));
			erodedHeight = fourNoise * fourNoise * 2.5D;
			double newHeight = Math.ceil(singleNoise * 50) + 14;
			if (erodedHeight > newHeight) {
				erodedHeight = newHeight;
			}

			erodedHeight += 64.0D;
		}

		int chunkX = x & 15;
		int chunkZ = z & 15;
		BlockState topMaterial = NETHERRACK;
		BlockState underMaterial = biome.getSurfaceBuilderConfig().getUnder();
		int caveChance = (int) (noise / 3 + 3 + random.nextDouble() * 0.25);
		// TODO layers? boolean soulstone = Math.cos(noise / 3 * Math.PI) > 0;
		int undergroundOrSomething = -1;
		// TODO layer boolean soulSand = false;
		MutableBlockPos blockPos = new MutableBlockPos();

		for (int y = Math.max(startHeight, (int) erodedHeight + 1); y >= 0; y--) {
			blockPos.setPos(chunkX, y, chunkZ);
			if (chunk.getBlockState(blockPos).isAir(chunk, blockPos) && y < (int) erodedHeight) { // TODO see if it works with the chunk and blockPos passed to it
				chunk.setBlockState(blockPos, defaultBlock, false);
			}

			BlockState currentBlockState = chunk.getBlockState(blockPos);
			if (currentBlockState.isAir(chunk, blockPos)) { // TODO see if it works with the chunk and blockPos passed to it
				undergroundOrSomething = -1;
			} else if (currentBlockState.getBlock() == defaultBlock.getBlock()) {
				if (undergroundOrSomething == -1) {
					// TODO layer soulSand = false;
					if (caveChance <= 0) {
						topMaterial = Blocks.AIR.getDefaultState();
						underMaterial = defaultBlock;
					} else if (y >= seaLevel - 4 && y <= seaLevel + 1) {
						topMaterial = NETHERRACK;
						underMaterial = biome.getSurfaceBuilderConfig().getUnder();
					}

					if (y < seaLevel && topMaterial.isAir(chunk, blockPos)) { // TODO see if it works with the chunk and blockPos passed to it
						topMaterial = defaultFluid;
					}

					undergroundOrSomething = caveChance + Math.max(0, y - seaLevel);
					if (y >= seaLevel - 1) {
						if (y <= seaLevel + 3 + caveChance) {
							chunk.setBlockState(blockPos, biome.getSurfaceBuilderConfig().getTop(), false);
							// TODO layer? soulSand = true;
						} else {
							BlockState blockState;
							if (y >= 64 && y <= 127) {
								// TODO layers? if (soulstone) {
								blockState = SOULSTONE; // TODO SOULSTONE
								/*} else {
									blockState = getColouredTerracotta(x, y, z);
								}*/
							} else {
								blockState = SOUL_SAND;
							}
							chunk.setBlockState(blockPos, blockState, false);
						}
					} else {
						chunk.setBlockState(blockPos, underMaterial, false);
						Block underMaterialBlock = underMaterial.getBlock();
						// TODO a better approach would be a list or something
						if (underMaterialBlock == Blocks.SOUL_SAND || underMaterialBlock == Blocks.NETHERRACK || underMaterialBlock == BlockRegistrar.SOULSTONE) {
							chunk.setBlockState(blockPos, SOUL_SAND, false);
						}
					}
				} else if (undergroundOrSomething > 0) {
					undergroundOrSomething--;
					// TODO layers? if (soulSand) {
					chunk.setBlockState(blockPos, SOUL_SAND, false);
					/*} else {
						chunk.setBlockState(blockPos, getColouredTerracotta(x, y, z), false);
					}*/
				}
			}
		}
	}

	/*@Override
	public void setSeed(long seed) {
		if (this.seed != seed || noiseGenerator == null) {
			noiseGenerator = new OctavesNoiseGenerator(new SharedSeedRandom(seed), 4);
		}

		this.seed = seed;
	}*/
}