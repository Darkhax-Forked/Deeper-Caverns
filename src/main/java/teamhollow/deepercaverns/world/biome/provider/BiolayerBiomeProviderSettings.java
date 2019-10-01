package teamhollow.deepercaverns.world.biome.provider;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;

import java.util.Arrays;
import java.util.List;

public class BiolayerBiomeProviderSettings implements IBiomeProviderSettings {
	private List<Biome> biomes = Arrays.asList(Biomes.NETHER);
	private int size = 1;

	public BiolayerBiomeProviderSettings setBiomes(List<Biome> biomes) {
		this.biomes = biomes;
		return this;
	}

	public BiolayerBiomeProviderSettings addBiomes(List<Biome> biomes) {
		this.biomes.addAll(biomes);
		return this;
	}

	public BiolayerBiomeProviderSettings addBiomes(Biome... biomes) {
		this.biomes.addAll(Arrays.asList(biomes));
		return this;
	}

	public BiolayerBiomeProviderSettings setSize(int size) {
		this.size = size;
		return this;
	}

	public List<Biome> getBiomes() {
		return biomes;
	}

	public int getSize() {
		return size;
	}
}
