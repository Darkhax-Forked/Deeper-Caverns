package teamhollow.deepercaverns.world.generation.surfacebuilder;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

// TODO currently not really used
@ParametersAreNonnullByDefault
public class SoullandsSurfaceBuilder extends SurfaceBuilder<SoullandsSurfaceBuilderConfig> {
	private static final BlockState WHITE_TERRACOTTA = Blocks.WHITE_TERRACOTTA.getDefaultState();
	private static final BlockState ORANGE_TERRACOTTA = Blocks.ORANGE_TERRACOTTA.getDefaultState();
	private static final BlockState TERRACOTTA = Blocks.TERRACOTTA.getDefaultState();
	private static final BlockState YELLOW_TERRACOTTA = Blocks.YELLOW_TERRACOTTA.getDefaultState();
	private static final BlockState BROWN_TERRACOTTA = Blocks.BROWN_TERRACOTTA.getDefaultState();
	private static final BlockState RED_TERRACOTTA = Blocks.RED_TERRACOTTA.getDefaultState();
	private static final BlockState LIGHT_GRAY_TERRACOTTA = Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState();
	protected BlockState[] colouredTerracottaMap;
	protected long seed;
	protected PerlinNoiseGenerator fourLevelNoise;
	protected PerlinNoiseGenerator singleLevelNoise;
	protected PerlinNoiseGenerator colouredTerracottaNoise;

	public SoullandsSurfaceBuilder(Function<Dynamic<?>, ? extends SoullandsSurfaceBuilderConfig> deserializer) {
		super(deserializer);
	}

	@Override
	public void buildSurface(Random random, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SoullandsSurfaceBuilderConfig config) {
		int chunkX = x & 15;
		int chunkZ = z & 15;
		BlockState topMaterial = WHITE_TERRACOTTA;
		BlockState underMaterial = biome.getSurfaceBuilderConfig().getUnder();
		int caveChance = (int) (noise / 3 + 3 + random.nextDouble() * 0.25);
		boolean plainTerracotta = Math.cos(noise / 3 * Math.PI) > 0;
		int undergroundOrSomething = -1;
		boolean orangeTerracotta = false;
		int heightCounter = 0;
		MutableBlockPos blockPos = new MutableBlockPos();

		for (int y = startHeight; y >= 0; y--) {
			if (heightCounter < 15) {
				blockPos.setPos(chunkX, y, chunkZ);
				BlockState currentBlockState = chunk.getBlockState(blockPos);
				if (!currentBlockState.isAir(chunk, blockPos)) {
					undergroundOrSomething = -1;
				} else if (currentBlockState.getBlock() == defaultBlock.getBlock()) {
					if (undergroundOrSomething == -1) {
						orangeTerracotta = false;
						if (caveChance <= 0) {
							topMaterial = Blocks.AIR.getDefaultState();
							underMaterial = defaultBlock;
						} else if (y >= seaLevel - 4 && y <= seaLevel + 1) {
							topMaterial = WHITE_TERRACOTTA;
							underMaterial = biome.getSurfaceBuilderConfig().getUnder();
						}

						if (y < seaLevel && topMaterial.isAir(chunk, blockPos)) {
							topMaterial = defaultFluid;
						}

						undergroundOrSomething = caveChance + Math.max(0, y - seaLevel);
						if (y >= seaLevel - 1) {
							if (y > seaLevel + 3 + caveChance) {
								BlockState blockState;
								if (y >= 64 && y <= 127) {
									if (plainTerracotta) {
										blockState = TERRACOTTA;
									} else {
										blockState = getColouredTerracotta(x, y, z);
									}
								} else {
									blockState = ORANGE_TERRACOTTA;
								}

								chunk.setBlockState(blockPos, blockState, false);
							} else {
								chunk.setBlockState(blockPos, biome.getSurfaceBuilderConfig().getTop(), false);
								orangeTerracotta = true;
							}
						} else {
							chunk.setBlockState(blockPos, underMaterial, false);
							Block underMaterialBlock = underMaterial.getBlock();
							if (underMaterialBlock == Blocks.WHITE_TERRACOTTA || underMaterialBlock == Blocks.ORANGE_TERRACOTTA || underMaterialBlock == Blocks.MAGENTA_TERRACOTTA || underMaterialBlock == Blocks.LIGHT_BLUE_TERRACOTTA || underMaterialBlock == Blocks.YELLOW_TERRACOTTA || underMaterialBlock == Blocks.LIME_TERRACOTTA || underMaterialBlock == Blocks.PINK_TERRACOTTA || underMaterialBlock == Blocks.GRAY_TERRACOTTA || underMaterialBlock == Blocks.LIGHT_GRAY_TERRACOTTA || underMaterialBlock == Blocks.CYAN_TERRACOTTA || underMaterialBlock == Blocks.PURPLE_TERRACOTTA || underMaterialBlock == Blocks.BLUE_TERRACOTTA || underMaterialBlock == Blocks.BROWN_TERRACOTTA || underMaterialBlock == Blocks.GREEN_TERRACOTTA || underMaterialBlock == Blocks.RED_TERRACOTTA || underMaterialBlock == Blocks.BLACK_TERRACOTTA) {
								chunk.setBlockState(blockPos, ORANGE_TERRACOTTA, false);
							}
						}
					} else if (undergroundOrSomething > 0) {
						undergroundOrSomething--;
						if (orangeTerracotta) {
							chunk.setBlockState(blockPos, ORANGE_TERRACOTTA, false);
						} else {
							chunk.setBlockState(blockPos, getColouredTerracotta(x, y, z), false);
						}
					}
					heightCounter++;
				}
			}
		}
	}

	@Override
	public void setSeed(long seed) {
		if (this.seed != seed || colouredTerracottaMap == null) {
			generateColouredTerracottaMap(seed);
		}

		if (this.seed != seed || fourLevelNoise == null || singleLevelNoise == null) {
			Random random = new SharedSeedRandom(seed);
			fourLevelNoise = new PerlinNoiseGenerator(random, 4);
			singleLevelNoise = new PerlinNoiseGenerator(random, 1);
		}

		this.seed = seed;
	}

	protected void generateColouredTerracottaMap(long seed) {
		colouredTerracottaMap = new BlockState[64];
		Arrays.fill(colouredTerracottaMap, TERRACOTTA);
		Random random = new SharedSeedRandom(seed);
		colouredTerracottaNoise = new PerlinNoiseGenerator(random, 1);

		int i;
		for (i = 0; i < 64; ++i) {
			i += random.nextInt(5) + 1;
			if (i < 64) {
				colouredTerracottaMap[i] = ORANGE_TERRACOTTA;
			}
		}

		i = random.nextInt(4) + 2;

		int j;
		int k;
		int l;
		int m;
		for (j = 0; j < i; ++j) {
			k = random.nextInt(3) + 1;
			l = random.nextInt(64);

			for (m = 0; l + m < 64 && m < k; ++m) {
				colouredTerracottaMap[l + m] = YELLOW_TERRACOTTA;
			}
		}

		j = random.nextInt(4) + 2;

		int n;
		for (k = 0; k < j; ++k) {
			l = random.nextInt(3) + 2;
			m = random.nextInt(64);

			for (n = 0; m + n < 64 && n < l; ++n) {
				colouredTerracottaMap[m + n] = BROWN_TERRACOTTA;
			}
		}

		k = random.nextInt(4) + 2;

		for (l = 0; l < k; ++l) {
			m = random.nextInt(3) + 1;
			n = random.nextInt(64);

			for (int o = 0; n + o < 64 && o < m; ++o) {
				colouredTerracottaMap[n + o] = RED_TERRACOTTA;
			}
		}

		l = random.nextInt(3) + 3;
		m = 0;

		for (n = 0; n < l; ++n) {
			m += random.nextInt(16) + 4;

			for (int p = 0; m + p < 64 && p < 1; ++p) {
				colouredTerracottaMap[m + p] = WHITE_TERRACOTTA;
				if (m + p > 1 && random.nextBoolean()) {
					colouredTerracottaMap[m + p - 1] = LIGHT_GRAY_TERRACOTTA;
				}

				if (m + p < 63 && random.nextBoolean()) {
					colouredTerracottaMap[m + p + 1] = LIGHT_GRAY_TERRACOTTA;
				}
			}
		}
	}

	protected BlockState getColouredTerracotta(int x, int y, int z) {
		int lvt_4_1_ = (int) Math.round(colouredTerracottaNoise.getValue((double) x / 512.0D, (double) z / 512.0D) * 2.0D);
		return colouredTerracottaMap[(y + lvt_4_1_ + 64) % 64];
	}
}
