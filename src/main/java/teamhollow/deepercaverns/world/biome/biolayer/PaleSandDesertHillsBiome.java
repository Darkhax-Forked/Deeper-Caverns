package teamhollow.deepercaverns.world.biome.biolayer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import teamhollow.deepercaverns.world.generation.surfacebuilder.DeeperCavernsSurfaceBuilders;

public class PaleSandDesertHillsBiome extends PaleSandDesertBiome {
	public PaleSandDesertHillsBiome() {
		super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, DeeperCavernsSurfaceBuilders.Config.PALE_SAND)
						      .category(Category.NETHER)
						      .depth(0.45F).scale(0.3F).temperature(2)
						      .precipitation(RainType.NONE).downfall(0).waterColor(0x3f76e4).waterFogColor(0x50533)
						      .parent(null));
	}
}