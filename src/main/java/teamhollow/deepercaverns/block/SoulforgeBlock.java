package teamhollow.deepercaverns.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import teamhollow.deepercaverns.tileentity.SoulforgeTileEntity;

public class SoulforgeBlock extends Block
{
	public SoulforgeBlock(Properties properties)
	{
		super(properties);
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
}
