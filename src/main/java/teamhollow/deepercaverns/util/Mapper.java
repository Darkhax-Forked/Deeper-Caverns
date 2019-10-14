package teamhollow.deepercaverns.util;

import java.util.function.Function;

public interface Mapper<O, M> {
	O unmap(M mapped);

	M map(O original);

	static <O, M> Mapper<O, M> of(Function<M, O> unmap, Function<O, M> map) {
		return new Mapper<O, M>() {
			@Override
			public O unmap(M mapped) {
				return unmap.apply(mapped);
			}

			@Override
			public M map(O original) {
				return map.apply(original);
			}
		};
	}
}
