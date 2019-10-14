package teamhollow.deepercaverns.util;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class MapperType {
	public static Mapper<Biome, Integer> BIOME = Mapper.of(
					((ForgeRegistry<Biome>) ForgeRegistries.BIOMES)::getValue,
					((ForgeRegistry<Biome>) ForgeRegistries.BIOMES)::getID
	);
}