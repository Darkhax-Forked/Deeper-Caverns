package teamhollow.deepercaverns.datagen;

import java.util.function.Consumer;

import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.Tags;
import teamhollow.deepercaverns.reg.BlockRegistrar;

public class RecipeGenerator extends RecipeProvider
{
	public RecipeGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
	{
		ShapedRecipeBuilder.shapedRecipe(BlockRegistrar.BRIGHTFORGE)
		.patternLine("NNN")
		.patternLine("N N")
		.patternLine("MMM")
		.key('N', Tags.Items.NETHERRACK)
		.key('M', Blocks.MAGMA_BLOCK)
		.addCriterion("nether_entered", ChangeDimensionTrigger.Instance.changedDimensionTo(DimensionType.THE_NETHER))
		.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(BlockRegistrar.GLOWSTONE_LANTERN)
		.patternLine("QQQ")
		.patternLine("QGQ")
		.patternLine("QQQ")
		.key('Q', Tags.Items.GEMS_QUARTZ)
		.key('G', Blocks.GLOWSTONE)
		.addCriterion("nether_entered", ChangeDimensionTrigger.Instance.changedDimensionTo(DimensionType.THE_NETHER))
		.build(consumer);
	}
}
