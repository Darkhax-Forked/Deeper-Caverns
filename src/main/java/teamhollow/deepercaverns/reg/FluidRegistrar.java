package teamhollow.deepercaverns.reg;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamhollow.deepercaverns.DeeperCaverns;
import teamhollow.deepercaverns.block.SoulEssenceFluidBlock;

public class FluidRegistrar
{
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, DeeperCaverns.MODID);
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, DeeperCaverns.MODID);
	public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, DeeperCaverns.MODID);
	public static RegistryObject<FlowingFluid> SOUL_ESSENCE = FLUIDS.register("soul_essence", () -> new ForgeFlowingFluid.Source(FluidRegistrar.SOUL_ESSENCE_PROPERTIES));
	public static RegistryObject<FlowingFluid> SOUL_ESSENCE_FLOWING = FLUIDS.register("soul_essence_flowing", () -> new ForgeFlowingFluid.Flowing(FluidRegistrar.SOUL_ESSENCE_PROPERTIES));
	public static RegistryObject<SoulEssenceFluidBlock> SOUL_ESSENCE_BLOCK = BLOCKS.register("soul_essence_fluid_block", () -> new SoulEssenceFluidBlock(SOUL_ESSENCE, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(1.0F).noDrops()));
	public static RegistryObject<Item> SOUL_ESSENCE_BUCKET = ITEMS.register("soul_essence_bucket", () -> new BucketItem(SOUL_ESSENCE, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(DeeperCaverns.ITEM_GROUP)));
	public static final Properties SOUL_ESSENCE_PROPERTIES = new Properties(SOUL_ESSENCE, SOUL_ESSENCE_FLOWING, FluidAttributes.builder(new ResourceLocation("minecraft:block/soul_sand"), new ResourceLocation("minecraft:block/soul_sand"))).bucket(SOUL_ESSENCE_BUCKET).block(SOUL_ESSENCE_BLOCK);

	public static void init()
	{
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		FLUIDS.register(modEventBus);
	}
}
