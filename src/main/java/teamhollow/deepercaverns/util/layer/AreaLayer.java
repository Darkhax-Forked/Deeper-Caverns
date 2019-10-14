package teamhollow.deepercaverns.util.layer;

public class AreaLayer<T> {
	public final Layer<T> block;
	public final Layer<T> noise;

	public AreaLayer(Layer<T> block, Layer<T> noise) {
		this.block = block;
		this.noise = noise;
	}
}