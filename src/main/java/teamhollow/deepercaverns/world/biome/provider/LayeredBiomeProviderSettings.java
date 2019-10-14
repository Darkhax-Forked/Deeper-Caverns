package teamhollow.deepercaverns.world.biome.provider;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import teamhollow.deepercaverns.util.layer.AreaLayer;

public class LayeredBiomeProviderSettings implements IBiomeProviderSettings {
	public List<Biome> biomes = new ArrayList<>();
	public AreaLayer<Biome> biomeResolver;

	public LayeredBiomeProviderSettings setBiomes(List<Biome> biomes) {
		this.biomes = biomes;
		return this;
	}

	public LayeredBiomeProviderSettings addBiomes(List<Biome> biomes) {
		this.biomes.addAll(biomes);
		return this;
	}

	public LayeredBiomeProviderSettings addBiomes(Biome... biomes) {
		this.biomes.addAll(Arrays.asList(biomes));
		return this;
	}

	public LayeredBiomeProviderSettings setBiomeResolver(AreaLayer<Biome> biomeResolver) {
		this.biomeResolver = biomeResolver;
		return this;
	}

	public List<Biome> getBiomes() {
		return biomes;
	}

	public AreaLayer<Biome> getBiomeResolver() {
		return biomeResolver;
	}
}