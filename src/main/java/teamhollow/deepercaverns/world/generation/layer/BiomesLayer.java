package teamhollow.deepercaverns.world.generation.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.List;

public class BiomesLayer implements IAreaTransformer0 {
	private final List<Biome> biomes;

	public BiomesLayer(List<Biome> biomes) {
		this.biomes = biomes;
	}

	@Override
	public int apply(INoiseRandom random, int x, int z) {
		Biome biome = biomes.get(random.random(biomes.size()));
		return biome != null ? ((ForgeRegistry<Biome>) GameRegistry.findRegistry(Biome.class)).getID(biome) : 0;
	}
}