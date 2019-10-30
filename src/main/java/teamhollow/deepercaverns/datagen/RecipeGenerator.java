package teamhollow.deepercaverns.datagen;

import java.util.function.Consumer;

import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.Tags;
import teamhollow.deepercaverns.DeeperCaverns;
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
		ShapedRecipeBuilder.shapedRecipe(BlockRegistrar.PALE_GLASS_PANE)
		.patternLine("GGG")
		.patternLine("GGG")
		.key('G', BlockRegistrar.PALE_GLASS)
		.addCriterion("has_pale_glass", hasItem(BlockRegistrar.PALE_GLASS))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(BlockRegistrar.SOULGLASS_PANE)
		.patternLine("GGG")
		.patternLine("GGG")
		.key('G', BlockRegistrar.SOULGLASS)
		.addCriterion("has_soulglass", hasItem(BlockRegistrar.SOULGLASS))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ItemRegistrar.SOULFUR)
		.patternLine("BSB")
		.patternLine("SBS")
		.patternLine("BSB")
		.key('B', ItemRegistrar.SULFUR_POWDER)
		.key('S', Ingredient.fromItems(BlockRegistrar.PALE_SAND, Blocks.SOUL_SAND))
		.addCriterion("has_sulfur_powder", hasItem(ItemRegistrar.SULFUR_POWDER))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(BlockRegistrar.SOULFORGE)
		.patternLine("SSS")
		.patternLine("S S")
		.patternLine("SSS")
		.key('S', BlockRegistrar.SOULFUR_BLOCK)
		.addCriterion("has_soulfur_block", hasItem(BlockRegistrar.SOULFUR_BLOCK))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(BlockRegistrar.SOULFUR_BLOCK)
		.patternLine("SSS")
		.patternLine("SSS")
		.patternLine("SSS")
		.key('S', ItemRegistrar.SOULFUR)
		.addCriterion("has_soulfur", hasItem(ItemRegistrar.SOULFUR))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ItemRegistrar.ONYX_ARROW, 4)
		.patternLine("O")
		.patternLine("S")
		.patternLine("F")
		.key('O', ItemRegistrar.ONYX_GEM)
		.key('S', Items.STICK)
		.key('F', Items.FEATHER)
		.addCriterion("has_onyx_gem", hasItem(ItemRegistrar.ONYX_GEM))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(ItemRegistrar.CHALONITE_SWORD_ONYX)
		.patternLine("C")
		.patternLine("C")
		.patternLine("S")
		.key('C', ItemRegistrar.CHALONITE_INGOT)
		.key('S', Items.STICK)
		.addCriterion("has_chalonite_ingot", hasItem(ItemRegistrar.CHALONITE_INGOT))
		.build(consumer);

		//standard tool recipes
		addStandardToolRecipes(consumer, ItemRegistrar.GLOWSTONE_CRYSTAL, ItemRegistrar.GLOWSTONE_CRYSTAL_AXE, ItemRegistrar.GLOWSTONE_CRYSTAL_HOE, ItemRegistrar.GLOWSTONE_CRYSTAL_PICKAXE, ItemRegistrar.GLOWSTONE_CRYSTAL_SHOVEL, ItemRegistrar.GLOWSTONE_CRYSTAL_SWORD);
		addStandardToolRecipes(consumer, ItemRegistrar.ONYX_GEM, ItemRegistrar.ONYX_AXE, ItemRegistrar.ONYX_HOE, ItemRegistrar.ONYX_PICKAXE, ItemRegistrar.ONYX_SHOVEL, ItemRegistrar.ONYX_SWORD);

		//standard armor recipes
		addStandardArmorRecipes(consumer, ItemRegistrar.GHOSTSOUL_INGOT, ItemRegistrar.GHOSTSOUL_HELMET, ItemRegistrar.GHOSTSOUL_CHESTPLATE, ItemRegistrar.GHOSTSOUL_LEGGINGS, ItemRegistrar.GHOSTSOUL_BOOTS);
		addStandardArmorRecipes(consumer, ItemRegistrar.GLOWSTONE_CRYSTAL, ItemRegistrar.GLOWSTONE_CRYSTAL_HELMET, ItemRegistrar.GLOWSTONE_CRYSTAL_CHESTPLATE, ItemRegistrar.GLOWSTONE_CRYSTAL_LEGGINGS, ItemRegistrar.GLOWSTONE_CRYSTAL_BOOTS);

		//stonecutting recipes
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(Blocks.OBSIDIAN), BlockRegistrar.CHISELED_OBSIDIAN)
		.addCriterion("has_obsidian", hasItem(Tags.Items.OBSIDIAN))
		.build(consumer, new ResourceLocation(DeeperCaverns.MODID, "chiseled_obsidian_from_obsidian_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(BlockRegistrar.CHISELED_OBSIDIAN), Blocks.OBSIDIAN)
		.addCriterion("has_obsidian", hasItem(Tags.Items.OBSIDIAN))
		.build(consumer, new ResourceLocation(DeeperCaverns.MODID, "obsidian_from_chiseled_obsidian_stonecutting"));
	}

	private void addStandardToolRecipes(Consumer<IFinishedRecipe> consumer, Item material, Item axe, Item hoe, Item pickaxe, Item shovel, Item sword)
	{
		ShapedRecipeBuilder.shapedRecipe(axe)
		.patternLine("MM")
		.patternLine("MS")
		.patternLine(" S")
		.key('M', material)
		.key('S', Items.STICK)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(hoe)
		.patternLine("MM")
		.patternLine(" S")
		.patternLine(" S")
		.key('M', material)
		.key('S', Items.STICK)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(pickaxe)
		.patternLine("MMM")
		.patternLine(" S ")
		.patternLine(" S ")
		.key('M', material)
		.key('S', Items.STICK)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(shovel)
		.patternLine("M")
		.patternLine("S")
		.patternLine("S")
		.key('M', material)
		.key('S', Items.STICK)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(sword)
		.patternLine("M")
		.patternLine("M")
		.patternLine("S")
		.key('M', material)
		.key('S', Items.STICK)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
	}

	private void addStandardArmorRecipes(Consumer<IFinishedRecipe> consumer, Item material, Item helmet, Item chestplate, Item leggings, Item boots)
	{
		ShapedRecipeBuilder.shapedRecipe(helmet)
		.patternLine("MMM")
		.patternLine("M M")
		.key('M', material)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(chestplate)
		.patternLine("M M")
		.patternLine("MMM")
		.patternLine("MMM")
		.key('M', material)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(leggings)
		.patternLine("MMM")
		.patternLine("M M")
		.patternLine("M M")
		.key('M', material)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
		ShapedRecipeBuilder.shapedRecipe(boots)
		.patternLine("M M")
		.patternLine("M M")
		.key('M', material)
		.addCriterion("has_" + material.getRegistryName().getPath(), hasItem(material))
		.build(consumer);
	}

	@Override
	public String getName()
	{
		return "DeeperCavernsRecipes";
	}
}
