package teamhollow.deepercaverns.world.biome.underground;

import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class UndergroundCavernBiomeSettings {
	public ConfiguredSurfaceBuilder<?> surfaceBuilder;
	// TODO add getters instead of being public?
	public float cavernDensity = -5.0F;
	public float floorHeight = 0.0F;
	public float ceilingHeight = 1.0F;
	public float heightScale = 0.1F;
	public float pillarWeight = 1.0F;

	public <SC extends ISurfaceBuilderConfig> UndergroundCavernBiomeSettings surfaceBuilder(SurfaceBuilder<SC> surface, SC config) {
		surfaceBuilder = new ConfiguredSurfaceBuilder<>(surface, config);
		return this;
	}

	public UndergroundCavernBiomeSettings cavernDensity(float density) {
		cavernDensity = density;
		return this;
	}

	public UndergroundCavernBiomeSettings floorHeight(float floorHeight) {
		this.floorHeight = floorHeight;
		return this;
	}

	public UndergroundCavernBiomeSettings ceilingHeight(float ceilingHeight) {
		this.ceilingHeight = ceilingHeight;
		return this;
	}

	public UndergroundCavernBiomeSettings heightScale(float heightScale) {
		this.heightScale = heightScale;
		return this;
	}

	public UndergroundCavernBiomeSettings pillarWeight(float pillarWeight) {
		this.pillarWeight = pillarWeight;
		return this;
	}
}
