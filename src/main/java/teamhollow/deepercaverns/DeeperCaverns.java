package teamhollow.deepercaverns;

import net.minecraftforge.fml.common.Mod;

@Mod(DeeperCaverns.MODID)
public class DeeperCaverns {
	public static final String MODID = "deepercaverns";
	private static DeeperCaverns instance;

	public static DeeperCaverns getInstance() {
		return instance;
	}

	public DeeperCaverns() {
		instance = this;
	}
}
