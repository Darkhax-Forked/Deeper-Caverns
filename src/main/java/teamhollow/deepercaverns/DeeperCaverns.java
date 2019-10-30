package teamhollow.deepercaverns;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import teamhollow.deepercaverns.advancement.CreateChaloniteTrigger;
import teamhollow.deepercaverns.advancement.CreateGhostsoulTrigger;
import teamhollow.deepercaverns.advancement.SmeltWithBrightforgeTrigger;
import teamhollow.deepercaverns.reg.FluidRegistrar;
import teamhollow.deepercaverns.reg.ItemRegistrar;

@Mod(DeeperCaverns.MODID)
public class DeeperCaverns {
	public static final String MODID = "deepercaverns";
	public static final String PREFIX = MODID + ":";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final CreateChaloniteTrigger CREATE_CHALONITE_TRIGGER = CriteriaTriggers.register(new CreateChaloniteTrigger());
	public static final CreateGhostsoulTrigger CREATE_GHOSTSOUL_TRIGGER = CriteriaTriggers.register(new CreateGhostsoulTrigger());
	public static final SmeltWithBrightforgeTrigger SMELT_WITH_BRIGHTFORGE_TRIGGER = CriteriaTriggers.register(new SmeltWithBrightforgeTrigger());

	private static DeeperCaverns instance;

	public static final ItemGroup ITEM_GROUP = new ItemGroup(ItemGroup.GROUPS.length, MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemRegistrar.ARCANE_SPAWN_EGG);
		}
	};

	public DeeperCaverns() {
		instance = this;
		FluidRegistrar.init();
	}

	public static DeeperCaverns getInstance() {
		return instance;
	}
}
