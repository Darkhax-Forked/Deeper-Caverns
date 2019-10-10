package teamhollow.deepercaverns.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.DCTags;

public class SoulforgeRecipe
{
	public static final Map<ResourceLocation,SoulforgeRecipe> RECIPES = new HashMap<>();

	static {
	}

	private ResourceLocation registryName;
	private Ingredient left, right, fuel = Ingredient.fromTag(DCTags.Items.FUEL_SOULFORGE);
	private ItemStack output;

	public SoulforgeRecipe(Ingredient left, Ingredient right, ItemStack output)
	{
		this.left = left;
		this.right = right;
		this.output = output;
	}

	public SoulforgeRecipe setRegistryName(ResourceLocation registryName)
	{
		this.registryName = registryName;
		return this;
	}

	public ResourceLocation getRegistryName()
	{
		return registryName;
	}

	public Ingredient getLeft()
	{
		return left;
	}

	public Ingredient getRight()
	{
		return right;
	}

	public ItemStack getOutput()
	{
		return output;
	}

	public Ingredient getFuel()
	{
		return fuel;
	}

	private static void register(SoulforgeRecipe recipe)
	{
		if(recipe.registryName == null)
			throw new IllegalStateException("Soulforge recipe cannot have null registry name!");
		else if(recipe.registryName.getNamespace().equals("minecraft"))
			throw new IllegalStateException("Soulforge recipe cannot be registered under the \"minecraft\" namespace!");
		else if(RECIPES.containsKey(recipe.registryName))
			throw new IllegalStateException(String.format("Soulforge recipe with registry name %s already exists!", recipe.registryName.toString()));
		else if((recipe.left == null || recipe.left.hasNoMatchingItems()))
			throw new IllegalStateException("Soulforge recipe's left ingredient cannot be null and cannot have no matching stacks!");
		else if((recipe.right == null || recipe.right.hasNoMatchingItems()))
			throw new IllegalStateException("Soulforge recipe's right ingredient cannot be null and cannot have no matching stacks!");
		else if(recipe.output == null || recipe.output.isEmpty())
			throw new IllegalStateException("Soulforge recipe's output cannot be null or empty!");

		RECIPES.put(recipe.getRegistryName(), recipe);
	}
}
