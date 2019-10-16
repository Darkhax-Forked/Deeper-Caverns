package teamhollow.deepercaverns.compat.jei;

import java.util.Arrays;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.recipe.SoulEssenceCauldronRecipe;

public class SoulEssenceCauldronRecipeCategory implements IRecipeCategory<SoulEssenceCauldronRecipe>
{
	private final IDrawable background;
	private final IDrawable icon;

	public SoulEssenceCauldronRecipeCategory(IGuiHelper helper)
	{
		background = helper.createDrawable(new ResourceLocation(DeeperCaverns.MODID, "textures/gui/soul_essence_cauldron_category.png"), 0, 0, 70, 22);
		icon = helper.createDrawableIngredient(new ItemStack(Blocks.CAULDRON));
	}

	@Override
	public ResourceLocation getUid()
	{
		return JEICompat.SOUL_ESSENCE_CAULDRON_RECIPES;
	}

	@Override
	public Class<? extends SoulEssenceCauldronRecipe> getRecipeClass()
	{
		return SoulEssenceCauldronRecipe.class;
	}

	@Override
	public String getTitle()
	{
		return I18n.format("jei.category.deepercaverns.soul_essence_cauldron");
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public IDrawable getIcon()
	{
		return icon;
	}

	@Override
	public void setIngredients(SoulEssenceCauldronRecipe recipe, IIngredients ingredients)
	{
		ingredients.setInputIngredients(Arrays.asList(recipe.getInput()));
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SoulEssenceCauldronRecipe recipe, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 2, 2);
		guiItemStacks.init(1, false, 50, 2);
		guiItemStacks.set(ingredients);
	}
}
