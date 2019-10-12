package teamhollow.deepercaverns.compat.jei;

import java.util.Arrays;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.client.screen.BrightforgeScreen;
import teamhollow.deepercaverns.recipe.BrightforgeRecipe;
import teamhollow.deepercaverns.reg.BlockRegistrar;

public class BrightforgeRecipeCategory implements IRecipeCategory<BrightforgeRecipe>
{
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableAnimated animatedFlame;
	private final IDrawableAnimated arrow;

	public BrightforgeRecipeCategory(IGuiHelper helper)
	{
		IDrawableStatic staticFlame = helper.createDrawable(BrightforgeScreen.TEXTURE, 176, 0, 14, 14);

		background = helper.createDrawable(BrightforgeScreen.TEXTURE, 55, 16, 108, 53);
		icon = helper.createDrawableIngredient(new ItemStack(BlockRegistrar.BRIGHTFORGE));
		animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, StartDirection.TOP, true);
		arrow = helper.drawableBuilder(BrightforgeScreen.TEXTURE, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public ResourceLocation getUid()
	{
		return JEICompat.BRIGHTFORGE_RECIPES;
	}

	@Override
	public Class<? extends BrightforgeRecipe> getRecipeClass()
	{
		return BrightforgeRecipe.class;
	}

	@Override
	public String getTitle()
	{
		return I18n.format(BlockRegistrar.BRIGHTFORGE.getTranslationKey());
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
	public void draw(BrightforgeRecipe recipe, double mouseX, double mouseY)
	{
		animatedFlame.draw(1, 20);
		arrow.draw(24, 18);
	}

	@Override
	public void setIngredients(BrightforgeRecipe recipe, IIngredients ingredients)
	{
		ingredients.setInputIngredients(Arrays.asList(recipe.getInput(), recipe.getFuel()));
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, BrightforgeRecipe recipe, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 0, 0);
		guiItemStacks.init(1, true, 0, 36);
		guiItemStacks.init(3, false, 60, 18);
		guiItemStacks.set(ingredients);
	}
}
