package teamhollow.deepercaverns.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.recipe.BrightforgeRecipe;
import teamhollow.deepercaverns.recipe.SoulforgeRecipe;
import teamhollow.deepercaverns.reg.BlockRegistrar;

@JeiPlugin
public class JEICompat implements IModPlugin
{
	public static final ResourceLocation BRIGHTFORGE_RECIPES = new ResourceLocation(DeeperCaverns.MODID, "brightforge");
	public static final ResourceLocation SOULFORGE_RECIPES = new ResourceLocation(DeeperCaverns.MODID, "soulforge");

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration)
	{
		registration.addRecipeCategories(new BrightforgeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new SoulforgeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
	{
		registration.addRecipeCatalyst(new ItemStack(BlockRegistrar.BRIGHTFORGE), BRIGHTFORGE_RECIPES);
		registration.addRecipeCatalyst(new ItemStack(BlockRegistrar.SOULFORGE), SOULFORGE_RECIPES);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration)
	{
		registration.addRecipes(BrightforgeRecipe.RECIPES.values(), BRIGHTFORGE_RECIPES);
		registration.addRecipes(SoulforgeRecipe.RECIPES.values(), SOULFORGE_RECIPES);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return new ResourceLocation(DeeperCaverns.MODID, DeeperCaverns.MODID);
	}
}
