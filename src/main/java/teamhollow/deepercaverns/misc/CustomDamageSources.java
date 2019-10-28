package teamhollow.deepercaverns.misc;

import net.minecraft.util.DamageSource;
import teamhollow.deepercaverns.DeeperCaverns;

public class CustomDamageSources
{
	public static final DamageSource ARCANE_SUCKS_LIFE = new DamageSource(DeeperCaverns.MODID + ".arcane_sucks_life").setDamageBypassesArmor();
}
