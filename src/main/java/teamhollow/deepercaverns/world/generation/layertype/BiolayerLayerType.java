package teamhollow.deepercaverns.world.generation.layertype;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.VoroniZoomLayer;
import net.minecraft.world.gen.layer.ZoomLayer;

import java.util.function.LongFunction;

import teamhollow.deepercaverns.util.MapperType;
import teamhollow.deepercaverns.util.layer.Area;
import teamhollow.deepercaverns.util.layer.AreaLayerType;
import teamhollow.deepercaverns.world.biome.biolayer.BiolayerBiomes.SpawnEntryGroups;
import teamhollow.deepercaverns.world.generation.layer.AddHillsLayer;
import teamhollow.deepercaverns.world.generation.layer.CellSeedLayer;
import teamhollow.deepercaverns.world.generation.layer.EdgeMergeLayer;
import teamhollow.deepercaverns.world.generation.layer.ProduceOutlineLayer;
import teamhollow.deepercaverns.world.generation.layer.SeedGroupLayer;

public class BiolayerLayerType {
	public static final AreaLayerType<Biome> INSTANCE = new AreaLayerType<>(
					Biome.class,
					BiolayerLayerType::buildArea,
					MapperType.BIOME::unmap,
					Biomes.DEFAULT
	);

	private static <A extends IArea, C extends IExtendedNoiseRandom<A>> Area buildArea(LongFunction<C> contextFactory) {
		// TODO properly create this, right now it is mainly copy-pasted Vanilla code

		// TODO ridges IAreaFactory<A> ridgeLayer = buildEdgeHighlightLayer(contextFactory, 100);
		// TODO ridges ridgeLayer = new ReplaceRandomLayer(0, 11).apply(contextFactory.apply(110), ridgeLayer);
		IAreaFactory<A> valleyLayer = buildEdgeHighlightLayer(contextFactory, 200);

		IAreaFactory<A> layer = new SeedGroupLayer<>(SpawnEntryGroups.SURFACE, MapperType.BIOME).apply(contextFactory.apply(0));
		layer = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), layer);

		layer = new AddHillsLayer<>(SpawnEntryGroups.SURFACE_HILLS, MapperType.BIOME, 5).apply(contextFactory.apply(2000), layer);

		layer = ZoomLayer.NORMAL.apply(contextFactory.apply(3000), layer);

		//layer = new CreateGroupPocketsLayer(BiolayerBiomes.SURFACE_POCKET).apply(contextFactory.apply(5000), layer);
		layer = ZoomLayer.FUZZY.apply(contextFactory.apply(6000), layer);
		layer = SmoothLayer.INSTANCE.apply(contextFactory.apply(7000), layer);

		// TODO ridges layer = new EdgeMergeLayer<Biome>(biome -> biome != Biomes.PLATEAU && biome != Biomes.PEAK, Biomes.RIDGE).apply(contextFactory.apply(8000), layer, ridgeLayer);

		layer = LayerUtil.repeat(9000, ZoomLayer.NORMAL, layer, 2, contextFactory);

		layer = new EdgeMergeLayer<>(biome -> biome == Biomes.BADLANDS_PLATEAU, MapperType.BIOME, Biomes.BADLANDS).apply(contextFactory.apply(10000), layer, valleyLayer);

		int biomeSize = 4;
		for (int index = 0; index < biomeSize; index++) {
			layer = ZoomLayer.NORMAL.apply(contextFactory.apply(1000 + index), layer);
		}

		layer = ZoomLayer.NORMAL.apply(contextFactory.apply(11000), layer);
		layer = SmoothLayer.INSTANCE.apply(contextFactory.apply(12000), layer);
		return Area.of(layer, contextFactory);
	}

	private static <A extends IArea, C extends IExtendedNoiseRandom<A>> IAreaFactory<A> buildEdgeHighlightLayer(LongFunction<C> contextFactory, long seed) {
		IAreaFactory<A> valleyLayer = new CellSeedLayer().apply(contextFactory.apply(10 + seed));
		valleyLayer = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(20 + seed), valleyLayer);
		valleyLayer = LayerUtil.repeat(30 + seed, ZoomLayer.NORMAL, valleyLayer, 2, contextFactory);
		valleyLayer = new ProduceOutlineLayer().apply(contextFactory.apply(40 + seed), valleyLayer);

		return valleyLayer;
	}
}