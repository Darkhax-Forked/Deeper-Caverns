package teamhollow.deepercaverns.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.reg.BlockRegistrar;
import teamhollow.deepercaverns.reg.ItemRegistrar;

public class BrightforgeRecipe
{
	public static final Map<ResourceLocation,BrightforgeRecipe> RECIPES = new HashMap<>();

	static {
		register(new BrightforgeRecipe(BlockRegistrar.BRIMSTONE, ItemRegistrar.BRIMSTONE_POWDER).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "brimstone_powder")));
		register(new BrightforgeRecipe(Items.GLOWSTONE, ItemRegistrar.GLOWSTONE_CRYSTAL).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "glowstone_crystal")));
		register(new BrightforgeRecipe(BlockRegistrar.ONYX_ORE, ItemRegistrar.ONYX_GEM).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "onyx_gem")));
		register(new BrightforgeRecipe(Blocks.SOUL_SAND, BlockRegistrar.SOULGLASS).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "soulglass")));
		register(new BrightforgeRecipe(BlockRegistrar.SOULSTONE, ItemRegistrar.SOUL_INGOT).setRegistryName(new ResourceLocation(DeeperCaverns.MODID, "soul_ingot")));
	}

	private ResourceLocation registryName;
	private Ingredient input;
	private final Ingredient fuel = Ingredient.fromItems(Blocks.GLOWSTONE);
	private ItemStack output;

	public BrightforgeRecipe(IItemProvider input, IItemProvider output)
	{
		this(Ingredient.fromItems(input), new ItemStack(output));
	}

	public BrightforgeRecipe(IItemProvider input, ItemStack output)
	{
		this(Ingredient.fromItems(input), output);
	}

	public BrightforgeRecipe(Ingredient input, IItemProvider output)
	{
		this(input, new ItemStack(output));
	}

	public BrightforgeRecipe(Ingredient input, ItemStack output)
	{
		this.input = input;
		this.output = output;
	}

	public BrightforgeRecipe setRegistryName(ResourceLocation registryName)
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

	public Ingredient getFuel()
	{
		return fuel;
	}

	public ItemStack getOutput()
	{
		return output;
	}

	public static BrightforgeRecipe getMatchingRecipe(ItemStack input)
	{
		for(BrightforgeRecipe recipe : RECIPES.values())
		{
			if(recipe.getInput().test(input))
				return recipe;
		}

		return null;
	}

	public static void register(BrightforgeRecipe recipe)
	{
		if(recipe.registryName == null)
			throw new IllegalStateException("Brightforge recipe cannot have null registry name!");
		else if(recipe.registryName.getNamespace().equals("minecraft"))
			throw new IllegalStateException("Brightforge recipe cannot be registered under the \"minecraft\" namespace!");
		else if(RECIPES.containsKey(recipe.registryName))
			throw new IllegalStateException(String.format("Brightforge recipe with registry name %s already exists!", recipe.registryName.toString()));
		else if((recipe.input == null || recipe.input.hasNoMatchingItems()))
			throw new IllegalStateException("Brightforge recipe's input cannot be null and cannot have no matching stacks!");
		else if(recipe.output == null || recipe.output.isEmpty())
			throw new IllegalStateException("Brightforge recipe's output cannot be null or empty!");

		RECIPES.put(recipe.getRegistryName(), recipe);
	}
}
