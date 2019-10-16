package teamhollow.deepercaverns.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.DCTags;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.ItemRegistrar;

public class SoulforgeRecipe
{
	public static final Map<ResourceLocation,SoulforgeRecipe> RECIPES = new HashMap<>();

	static {
		register(new SoulforgeRecipe(ItemRegistrar.ONYX_INGOT, Items.QUARTZ, ItemRegistrar.CHALONITE_INGOT).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "chalonite_ingot")));
		register(new SoulforgeRecipe(ItemRegistrar.REFINED_SOUL, ItemRegistrar.PALE_GLASS_SHARDS, ItemRegistrar.GHOSTSOUL_INGOT).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "ghostsoul_ingot")));
		register(new SoulforgeRecipe(BlockRegistrar.ONYX_ORE, BlockRegistrar.ONYX_ORE, new ItemStack(ItemRegistrar.ONYX_INGOT, 2)).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "onyx_ingot")));
	}

	private ResourceLocation registryName;
	private Ingredient left, right;
	private final Ingredient fuel = Ingredient.fromTag(DCTags.Items.FUEL_SOULFORGE);
	private ItemStack output;

	public SoulforgeRecipe(IItemProvider left, IItemProvider right, IItemProvider output)
	{
		this(Ingredient.fromItems(left), Ingredient.fromItems(right), new ItemStack(output));
	}

	public SoulforgeRecipe(IItemProvider left, IItemProvider right, ItemStack output)
	{
		this(Ingredient.fromItems(left), Ingredient.fromItems(right), output);
	}

	public SoulforgeRecipe(Ingredient left, Ingredient right, IItemProvider output)
	{
		this(left, right, new ItemStack(output));
	}

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

	public static SoulforgeRecipe getMatchingRecipe(ItemStack left, ItemStack right)
	{
		for(SoulforgeRecipe recipe : RECIPES.values())
		{
			if(recipe.getLeft().test(left) && recipe.getRight().test(right))
				return recipe;
		}

		return null;
	}

	public static void register(SoulforgeRecipe recipe)
	{
		if(recipe.registryName == null)
			throw new IllegalStateException("Soulforge recipe cannot have null registry name!");
		else if(recipe.registryName.getNamespace().equals("minecraft"))
			throw new IllegalStateException("Soulforge recipe cannot be registered under the \"minecraft\" namespace!");
		else if(RECIPES.containsKey(recipe.registryName))
			throw new IllegalStateException(String.format("Soulforge recipe with registry name %s already exists!", recipe.registryName.toString()));
		else if((recipe.left == null || recipe.left.hasNoMatchingItems()))
			throw new IllegalStateException("Soulforge recipe's left input cannot be null and cannot have no matching stacks!");
		else if((recipe.right == null || recipe.right.hasNoMatchingItems()))
			throw new IllegalStateException("Soulforge recipe's right input cannot be null and cannot have no matching stacks!");
		else if(recipe.output == null || recipe.output.isEmpty())
			throw new IllegalStateException("Soulforge recipe's output cannot be null or empty!");

		RECIPES.put(recipe.getRegistryName(), recipe);
	}
}
