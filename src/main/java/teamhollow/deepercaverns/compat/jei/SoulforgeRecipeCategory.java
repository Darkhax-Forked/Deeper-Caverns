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
import teamhollow.deepercaverns.client.screen.SoulforgeScreen;
import teamhollow.deepercaverns.recipe.SoulforgeRecipe;
import teamhollow.deepercaverns.reg.BlockRegistrar;

public class SoulforgeRecipeCategory implements IRecipeCategory<SoulforgeRecipe>
{
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableAnimated animatedFlame;
	private final IDrawableAnimated arrow;

	public SoulforgeRecipeCategory(IGuiHelper helper)
	{
		IDrawableStatic staticFlame = helper.createDrawable(SoulforgeScreen.TEXTURE, 176, 0, 14, 14);

		background = helper.createDrawable(SoulforgeScreen.TEXTURE, 27, 16, 122, 54);
		icon = helper.createDrawableIngredient(new ItemStack(BlockRegistrar.SOULFORGE));
		animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, StartDirection.TOP, true);
		arrow = helper.drawableBuilder(SoulforgeScreen.TEXTURE, 176, 14, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public ResourceLocation getUid()
	{
		return JEICompat.SOULFORGE_RECIPES;
	}

	@Override
	public Class<? extends SoulforgeRecipe> getRecipeClass()
	{
		return SoulforgeRecipe.class;
	}

	@Override
	public String getTitle()
	{
		return I18n.format(BlockRegistrar.SOULFORGE.getTranslationKey());
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
	public void draw(SoulforgeRecipe recipe, double mouseX, double mouseY)
	{
		animatedFlame.draw(19, 20);
		arrow.draw(63, 18);
	}

	@Override
	public void setIngredients(SoulforgeRecipe recipe, IIngredients ingredients)
	{
		ingredients.setInputIngredients(Arrays.asList(recipe.getLeft(), recipe.getRight(), recipe.getFuel()));
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SoulforgeRecipe recipe, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 0, 0);
		guiItemStacks.init(1, true, 36, 0);
		guiItemStacks.init(2, true, 18, 36);
		guiItemStacks.init(3, false, 100, 18);
		guiItemStacks.set(ingredients);
	}
}
