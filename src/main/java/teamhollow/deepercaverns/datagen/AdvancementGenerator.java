package teamhollow.deepercaverns.datagen;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.advancements.criterion.DamageSourcePredicate;
import net.minecraft.advancements.criterion.EnterBlockTrigger;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.KilledTrigger;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PositionTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.advancement.CreateChaloniteTrigger;
import teamhollow.deepercaverns.advancement.CreateGhostsoulTrigger;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.EntityRegistrar;
import teamhollow.deepercaverns.reg.FluidRegistrar;
import teamhollow.deepercaverns.reg.ItemRegistrar;
import teamhollow.deepercaverns.world.biome.DeeperCavernsBiomes;
import teamhollow.deepercaverns.world.dimension.DeeperCavernsDimensions;

public class AdvancementGenerator extends AdvancementProvider
{
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	private final DataGenerator generator;

	public AdvancementGenerator(DataGenerator generator)
	{
		super(generator);

		this.generator = generator;
	}

	@Override //TODO: translate all advancements, including the manually made ones
	public void act(DirectoryCache cache) throws IOException
	{
		Path outputFolder = generator.getOutputFolder();
		Set<ResourceLocation> set = Sets.newHashSet();
		Consumer<Advancement> consumer = a -> {
			if (!set.add(a.getId()))
				throw new IllegalStateException("Duplicate advancement " + a.getId());
			else
			{
				Path savePath = getPath(outputFolder, a);

				try
				{
					IDataProvider.save(GSON, cache, a.copy().serialize(), savePath);
				}
				catch (IOException ioexception)
				{
					LOGGER.error("Couldn't save advancement {}", savePath, ioexception);
				}

			}
		};

		Advancement root = Advancement.Builder.builder()
				.withDisplay(BlockRegistrar.SOULSTONE, new StringTextComponent("Lush Fire"), new StringTextComponent("Enter the Biolayer"), new ResourceLocation(DeeperCaverns.MODID, "textures/block/soulstone.png"), FrameType.TASK, false, false, false)
				.withCriterion("entered_biolayer", ChangeDimensionTrigger.Instance.changedDimensionTo(DeeperCavernsDimensions.BIOLAYER_TYPE))
				.register(consumer, DeeperCaverns.MODID + ":biolayer/root");
		Advancement wrongSideOfHeaven = Advancement.Builder.builder()
				.withParent(root)
				.withDisplay(Blocks.NETHERRACK, new StringTextComponent("Wrong Side of Heaven"), new StringTextComponent("Enter hell."), null, FrameType.TASK, true, true, false)
				.withCriterion("entered_hell", PositionTrigger.Instance
						.forLocation(LocationPredicate.forBiome(DeeperCavernsBiomes.BIOLAYER_HELL)))
				.register(consumer, DeeperCaverns.MODID + ":biolayer/wrong_side_of_heaven");
		Advancement refreshingDive = Advancement.Builder.builder()
				.withParent(wrongSideOfHeaven)
				.withDisplay(FluidRegistrar.SOUL_ESSENCE_BUCKET.get(), new StringTextComponent("Refreshing Dive"), new StringTextComponent("Enter Soul Essence."), null, FrameType.TASK, true, true, false)
				.withCriterion("entered_soul_essence", EnterBlockTrigger.Instance.forBlock(FluidRegistrar.SOUL_ESSENCE_BLOCK.get()))
				.register(consumer, DeeperCaverns.MODID + ":biolayer/refreshing_dive");
		Advancement voidOfSoul = Advancement.Builder.builder()
				.withParent(root)
				.withDisplay(Blocks.SOUL_SAND, new StringTextComponent("Void of Soul"), new StringTextComponent("Enter the soullands."), null, FrameType.TASK, true, true, false)
				.withCriterion("entered_soullands", PositionTrigger.Instance
						.forLocation(LocationPredicate.forBiome(DeeperCavernsBiomes.SOULSTONE_HILLS)))
				.register(consumer, DeeperCaverns.MODID + ":biolayer/void_of_soul");
		Advancement withHeatAndSoul = Advancement.Builder.builder()
				.withParent(voidOfSoul)
				.withDisplay(BlockRegistrar.SOULFORGE, new StringTextComponent("With Head And Soul"), new StringTextComponent("Craft a Soulforge"), null, FrameType.TASK, true, true, false)
				.withCriterion("has_soulforge", InventoryChangeTrigger.Instance.forItems(BlockRegistrar.SOULFORGE))
				.register(consumer, DeeperCaverns.MODID + ":biolayer/with_heat_and_soul");
		Advancement tasteOfYourOwnMedicine = Advancement.Builder.builder()
				.withParent(withHeatAndSoul)
				.withDisplay(ItemRegistrar.GHOSTSOUL_INGOT, new StringTextComponent("Taste Of Your Own Medicine"), new StringTextComponent("Forge a Ghostsoul Ingot"), null, FrameType.TASK, true, true, false)
				.withCriterion("created_ghostsoul_ingot", new CreateGhostsoulTrigger.Instance())
				.register(consumer, DeeperCaverns.MODID + ":biolayer/taste_of_your_own_medicine");
		Advancement ghostbusters = Advancement.Builder.builder()
				.withParent(withHeatAndSoul)
				.withDisplay(ItemRegistrar.CHALONITE_INGOT, new StringTextComponent("Ghostbusters"), new StringTextComponent("Forge a Chalonite Ingot"), null, FrameType.TASK, true, true, false)
				.withCriterion("created_chalonite_ingot", new CreateChaloniteTrigger.Instance())
				.register(consumer, DeeperCaverns.MODID + ":biolayer/ghostbusters");
		Advancement unseeableEnemy = Advancement.Builder.builder()
				.withParent(voidOfSoul)
				.withDisplay(ItemRegistrar.SHADE_SPAWN_EGG, new StringTextComponent("Unseeable Enemy"), new StringTextComponent("Get ambushed by a shade in the fog"), null, FrameType.TASK, true, true, false)
				.withCriterion("killed_by_shade", new KilledTrigger.Instance(CriteriaTriggers.ENTITY_KILLED_PLAYER.getId(), EntityPredicate.Builder.create().type(EntityRegistrar.SHADE).build(), DamageSourcePredicate.ANY))
				.register(consumer, DeeperCaverns.MODID + ":biolayer/unseeable_enemy");
	}

	private static Path getPath(Path path, Advancement advancement)
	{
		return path.resolve("data/" + advancement.getId().getNamespace() + "/advancements/" + advancement.getId().getPath() + ".json");
	}
}
