package teamhollow.deepercaverns.datagen;

import java.util.function.Consumer;

import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.Tags;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.ItemRegistrar;

public class RecipeGenerator extends RecipeProvider
{
	public RecipeGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
	{
		//smelting recipes
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockRegistrar.PALE_SAND), BlockRegistrar.PALE_GLASS, 0.1F, 200)
		.addCriterion("has_pale_sand", hasItem(BlockRegistrar.PALE_SAND))
		.build(consumer);

		//shaped recipes
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
		ShapedRecipeBuilder.shapedRecipe(ItemRegistrar.SOULBRYN)
		.patternLine("BSB")
		.patternLine("SBS")
		.patternLine("BSB")
		.key('B', ItemRegistrar.BRIMSTONE_POWDER)
		.key('S', Ingredient.fromItems(BlockRegistrar.PALE_SAND, Blocks.SOUL_SAND))
		.addCriterion("has_brimstone_powder", hasItem(ItemRegistrar.BRIMSTONE_POWDER))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(BlockRegistrar.SOULBRYN_BLOCK)
		.patternLine("SSS")
		.patternLine("SSS")
		.patternLine("SSS")
		.key('S', ItemRegistrar.SOULBRYN)
		.addCriterion("has_soulbryn", hasItem(ItemRegistrar.SOULBRYN))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(BlockRegistrar.SOULFORGE)
		.patternLine("SSS")
		.patternLine("S S")
		.patternLine("SSS")
		.key('S', BlockRegistrar.SOULBRYN_BLOCK)
		.addCriterion("has_soulbryn_block", hasItem(BlockRegistrar.SOULBRYN_BLOCK))
		.build(consumer);
	}
}
