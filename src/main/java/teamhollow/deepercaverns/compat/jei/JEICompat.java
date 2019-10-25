package teamhollow.deepercaverns.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.client.screen.BrightforgeScreen;
import teamhollow.deepercaverns.client.screen.SoulforgeScreen;
import teamhollow.deepercaverns.recipe.BrightforgeRecipe;
import teamhollow.deepercaverns.recipe.SoulEssenceCauldronRecipe;
import teamhollow.deepercaverns.recipe.SoulforgeRecipe;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.FluidRegistrar;

@JeiPlugin
public class JEICompat implements IModPlugin
{
	public static final ResourceLocation BRIGHTFORGE_RECIPES = new ResourceLocation(DeeperCaverns.MODID, "brightforge");
	public static final ResourceLocation SOUL_ESSENCE_CAULDRON_RECIPES = new ResourceLocation(DeeperCaverns.MODID, "soul_essence_cauldron");
	public static final ResourceLocation SOULFORGE_RECIPES = new ResourceLocation(DeeperCaverns.MODID, "soulforge");

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration)
	{
		registration.addRecipeCategories(new BrightforgeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new SoulEssenceCauldronRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new SoulforgeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
	{
		registration.addRecipeCatalyst(new ItemStack(BlockRegistrar.BRIGHTFORGE), BRIGHTFORGE_RECIPES);
		registration.addRecipeCatalyst(new ItemStack(Blocks.CAULDRON), SOUL_ESSENCE_CAULDRON_RECIPES);
		registration.addRecipeCatalyst(new ItemStack(FluidRegistrar.SOUL_ESSENCE_BUCKET.get()), SOUL_ESSENCE_CAULDRON_RECIPES);
		registration.addRecipeCatalyst(new ItemStack(BlockRegistrar.SOULFORGE), SOULFORGE_RECIPES);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration)
	{
		registration.addRecipes(BrightforgeRecipe.RECIPES.values(), BRIGHTFORGE_RECIPES);
		registration.addRecipes(SoulEssenceCauldronRecipe.RECIPES.values(), SOUL_ESSENCE_CAULDRON_RECIPES);
		registration.addRecipes(SoulforgeRecipe.RECIPES.values(), SOULFORGE_RECIPES);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		registration.addRecipeClickArea(BrightforgeScreen.class, 78, 32, 28, 23, BRIGHTFORGE_RECIPES);
		registration.addRecipeClickArea(SoulforgeScreen.class, 89, 32, 28, 23, SOULFORGE_RECIPES);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return new ResourceLocation(DeeperCaverns.MODID, DeeperCaverns.MODID);
	}
}
