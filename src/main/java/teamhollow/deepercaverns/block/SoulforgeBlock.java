package teamhollow.deepercaverns.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import teamhollow.deepercaverns.tileentity.SoulforgeTileEntity;

public class SoulforgeBlock extends Block
{
	public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public SoulforgeBlock(Properties properties)
	{
		super(properties);

		setDefaultState(stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LIT, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		if(!world.isRemote)
		{
			TileEntity te = world.getTileEntity(pos);

			if(te instanceof SoulforgeTileEntity)
				NetworkHooks.openGui((ServerPlayerEntity)player, (SoulforgeTileEntity)te, te.getPos());
			else
				throw new IllegalStateException("Trying to open Soulforge container: Tile entity is missing/incorrect!");
		}

		return true;
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new SoulforgeTileEntity();
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(HORIZONTAL_FACING, LIT);
	}
}
