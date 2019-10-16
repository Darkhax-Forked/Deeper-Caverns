package teamhollow.deepercaverns.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.reg.ItemRegistrar;

public class SoulEssenceCauldronRecipe
{
	public static final Map<ResourceLocation,SoulEssenceCauldronRecipe> RECIPES = new HashMap<>();

	static {
		register(new SoulEssenceCauldronRecipe(ItemRegistrar.RAW_SOUL, ItemRegistrar.REFINED_SOUL).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "refined_soul")));
	}

	private ResourceLocation registryName;
	private Ingredient input;
	private ItemStack output;

	public SoulEssenceCauldronRecipe(IItemProvider input, IItemProvider output)
	{
		this(Ingredient.fromItems(input), new ItemStack(output));
	}

	public SoulEssenceCauldronRecipe(IItemProvider input, ItemStack output)
	{
		this(Ingredient.fromItems(input), output);
	}

	public SoulEssenceCauldronRecipe(Ingredient input, IItemProvider output)
	{
		this(input, new ItemStack(output));
	}

	public SoulEssenceCauldronRecipe(Ingredient input, ItemStack output)
	{
		this.input = input;
		this.output = output;
	}

	public SoulEssenceCauldronRecipe setRegistryName(ResourceLocation registryName)
	{
		this.registryName = registryName;
		return this;
	}

	public ResourceLocation getRegistryName()
	{
		return registryName;
	}

	public Ingredient getInput()
	{
		return input;
	}

	public ItemStack getOutput()
	{
		return output;
	}

	public static SoulEssenceCauldronRecipe getMatchingRecipe(ItemStack input)
	{
		for(SoulEssenceCauldronRecipe recipe : RECIPES.values())
		{
			if(recipe.getInput().test(input))
				return recipe;
		}

		return null;
	}

	public static void register(SoulEssenceCauldronRecipe recipe)
	{
		if(recipe.registryName == null)
			throw new IllegalStateException("Soul essence cauldron recipe cannot have null registry name!");
		else if(recipe.registryName.getNamespace().equals("minecraft"))
			throw new IllegalStateException("Soul essence cauldron recipe cannot be registered under the \"minecraft\" namespace!");
		else if(RECIPES.containsKey(recipe.registryName))
			throw new IllegalStateException(String.format("Soul essence cauldron recipe with registry name %s already exists!", recipe.registryName.toString()));
		else if((recipe.input == null || recipe.input.hasNoMatchingItems()))
			throw new IllegalStateException("Soul essence cauldron recipe's input cannot be null and cannot have no matching stacks!");
		else if(recipe.output == null || recipe.output.isEmpty())
			throw new IllegalStateException("Soul essence cauldron recipe's output cannot be null or empty!");

		RECIPES.put(recipe.getRegistryName(), recipe);
	}
}
