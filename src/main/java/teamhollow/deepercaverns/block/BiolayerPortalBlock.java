package teamhollow.deepercaverns.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.cache.LoadingCache;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamhollow.deepercaverns.misc.BiolayerPortalHelper;
import teamhollow.deepercaverns.misc.BiolayerTeleporter;
import teamhollow.deepercaverns.reg.BlockRegistrar;

public class BiolayerPortalBlock extends Block
{
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AXIS_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AXIS_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public BiolayerPortalBlock(Block.Properties properties)
	{
		super(properties);

		setDefaultState(stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		switch(state.get(AXIS))
		{
			case Z:
				return Z_AXIS_SHAPE;
			case X:
			default:
				return X_AXIS_SHAPE;
		}
	}

	public boolean trySpawnPortal(IWorld world, BlockPos pos)
	{
		BiolayerPortalBlock.Size size = isPortal(world, pos);

		if(size != null)
		{
			size.placePortalBlocks();
			return true;
		}
		else
			return false;
	}

	@Nullable
	public BiolayerPortalBlock.Size isPortal(IWorld world, BlockPos pos)
	{
		BiolayerPortalBlock.Size xSize = new BiolayerPortalBlock.Size(world, pos, Direction.Axis.X);

		if(xSize.isValid() && xSize.portalBlockCount == 0)
			return xSize;
		else
		{
			BiolayerPortalBlock.Size zSize = new BiolayerPortalBlock.Size(world, pos, Direction.Axis.Z);

			return zSize.isValid() && zSize.portalBlockCount == 0 ? zSize : null;
		}
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		Direction.Axis axis = facing.getAxis();
		Direction.Axis stateAxis = state.get(AXIS);
		boolean flag = stateAxis != axis && axis.isHorizontal();

		return !flag && facingState.getBlock() != this && !(new BiolayerPortalBlock.Size(world, currentPos, stateAxis)).func_208508_f() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if(!world.isRemote && !entity.isPassenger() && !entity.isBeingRidden() && entity instanceof ServerPlayerEntity && entity.isSneaking())
		{
			ServerPlayerEntity player = (ServerPlayerEntity)entity;
			ServerWorld destWorld = BiolayerTeleporter.teleport(entity);

			if(destWorld != null)
			{
				BiolayerPortalHelper helper = BiolayerPortalHelper.getOrCreate(destWorld);

				if(!helper.placeInPortal(player, player.rotationYaw))
				{
					helper.makePortal(player);
					helper.placeInPortal(player, player.rotationYaw);
				}
			}
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) //TODO: custom particle effects?
	{
		if(rand.nextInt(100) == 0)
			world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);

		for(int i = 0; i < 4; ++i)
		{
			double xPos = pos.getX() + rand.nextFloat();
			double yPos = pos.getY() + rand.nextFloat();
			double zPos = pos.getZ() + rand.nextFloat();
			double xSpeed = (rand.nextFloat() - 0.5D) * 0.5D;
			double ySpeed = (rand.nextFloat() - 0.5D) * 0.5D;
			double zSpeed = (rand.nextFloat() - 0.5D) * 0.5D;
			int randVal = rand.nextInt(2) * 2 - 1;

			if(world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this)
			{
				xPos = pos.getX() + 0.5D + 0.25D * randVal;
				xSpeed = rand.nextFloat() * 2.0F * randVal;
			}
			else
			{
				zPos = pos.getZ() + 0.5D + 0.25D * randVal;
				zSpeed = rand.nextFloat() * 2.0F * randVal;
			}

			world.addParticle(ParticleTypes.PORTAL, xPos, yPos, zPos, xSpeed, ySpeed, zSpeed);
		}
	}

	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot)
	{
		switch(rot)
		{
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch(state.get(AXIS))
				{
					case Z:
						return state.with(AXIS, Direction.Axis.X);
					case X:
						return state.with(AXIS, Direction.Axis.Z);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(AXIS);
	}

	public BlockPattern.PatternHelper createPatternHelper(IWorld world, BlockPos pos)
	{
		Direction.Axis axis = Direction.Axis.Z;
		BiolayerPortalBlock.Size size = new BiolayerPortalBlock.Size(world, pos, Direction.Axis.X);
		LoadingCache<BlockPos, CachedBlockInfo> loadingCache = BlockPattern.createLoadingCache(world, true);

		if (!size.isValid())
		{
			axis = Direction.Axis.X;
			size = new BiolayerPortalBlock.Size(world, pos, Direction.Axis.Z);
		}

		if(!size.isValid())
			return new BlockPattern.PatternHelper(pos, Direction.NORTH, Direction.UP, loadingCache, 1, 1, 1);
		else
		{
			int[] intArr = new int[Direction.AxisDirection.values().length];
			Direction direction = size.rightDir.rotateYCCW();
			BlockPos blockPos = size.bottomLeft.up(size.getHeight() - 1);

			for(Direction.AxisDirection direction$axisdirection : Direction.AxisDirection.values())
			{
				BlockPattern.PatternHelper patternHelper = new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection ? blockPos : blockPos.offset(size.rightDir, size.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection, axis), Direction.UP, loadingCache, size.getWidth(), size.getHeight(), 1);

				for(int i = 0; i < size.getWidth(); ++i)
				{
					for(int j = 0; j < size.getHeight(); ++j)
					{
						CachedBlockInfo cachedblockinfo = patternHelper.translateOffset(i, j, 1);

						if(!cachedblockinfo.getBlockState().isAir())
							++intArr[direction$axisdirection.ordinal()];
					}
				}
			}

			Direction.AxisDirection axisDirection = Direction.AxisDirection.POSITIVE;

			for(Direction.AxisDirection axisDir : Direction.AxisDirection.values())
			{
				if(intArr[axisDir.ordinal()] < intArr[axisDirection.ordinal()])
					axisDirection = axisDir;
			}

			return new BlockPattern.PatternHelper(direction.getAxisDirection() == axisDirection ? blockPos : blockPos.offset(size.rightDir, size.getWidth() - 1), Direction.getFacingFromAxis(axisDirection, axis), Direction.UP, loadingCache, size.getWidth(), size.getHeight(), 1);
		}
	}

	public static class Size
	{
		private final IWorld world;
		private final Direction.Axis axis;
		private final Direction rightDir;
		private final Direction leftDir;
		private int portalBlockCount;
		@Nullable
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(IWorld world, BlockPos pos, Direction.Axis axis)
		{
			this.world = world;
			this.axis = axis;

			if(axis == Direction.Axis.X)
			{
				leftDir = Direction.EAST;
				rightDir = Direction.WEST;
			}
			else
			{
				leftDir = Direction.NORTH;
				rightDir = Direction.SOUTH;
			}

			for(BlockPos blockPos = pos; pos.getY() > blockPos.getY() - 21 && pos.getY() > 0 && func_196900_a(world.getBlockState(pos.down())); pos = pos.down())
				;

			int distanceUntilEdge = getDistanceUntilEdge(pos, leftDir) - 1;

			if(distanceUntilEdge >= 0)
			{
				bottomLeft = pos.offset(leftDir, distanceUntilEdge);
				width = getDistanceUntilEdge(bottomLeft, rightDir);

				if(width < 2 || width > 21)
				{
					bottomLeft = null;
					width = 0;
				}
			}

			if(bottomLeft != null)
				height = calculatePortalHeight();
		}

		protected int getDistanceUntilEdge(BlockPos pos, Direction dir)
		{
			int i;

			for(i = 0; i < 22; ++i)
			{
				BlockPos offsetPos = pos.offset(dir, i);

				if(!func_196900_a(world.getBlockState(offsetPos)) || world.getBlockState(offsetPos.down()).getBlock() != BlockRegistrar.CHISELED_OBSIDIAN)
					break;
			}

			Block block = world.getBlockState(pos.offset(dir, i)).getBlock();
			return block == BlockRegistrar.CHISELED_OBSIDIAN ? i : 0;
		}

		public int getHeight()
		{
			return height;
		}

		public int getWidth()
		{
			return width;
		}

		protected int calculatePortalHeight()
		{
			{
				outerLoop:
					for(height = 0; height < 21; ++height)
					{
						for(int i = 0; i < width; ++i)
						{
							BlockPos pos = bottomLeft.offset(rightDir, i).up(height);
							BlockState state = world.getBlockState(pos);

							if(!func_196900_a(state))
								break outerLoop;

							Block block = state.getBlock();

							if(block == BlockRegistrar.BIOLAYER_PORTAL)
								++portalBlockCount;

							if(i == 0)
							{
								block = world.getBlockState(pos.offset(leftDir)).getBlock();

								if(block != BlockRegistrar.CHISELED_OBSIDIAN)
									break outerLoop;
							}
							else if (i == width - 1)
							{
								block = this.world.getBlockState(pos.offset(rightDir)).getBlock();

								if(block != BlockRegistrar.CHISELED_OBSIDIAN)
									break outerLoop;
							}
						}
					}
			}

			for(int j = 0; j < width; ++j)
			{
				if (world.getBlockState(bottomLeft.offset(rightDir, j).up(height)).getBlock() != BlockRegistrar.CHISELED_OBSIDIAN)
				{
					height = 0;
					break;
				}
			}

			if(height <= 21 && height >= 3)
				return height;
			else
			{
				bottomLeft = null;
				width = 0;
				height = 0;
				return 0;
			}
		}

		protected boolean func_196900_a(BlockState pos)
		{
			Block block = pos.getBlock();

			return pos.isAir() || block == Blocks.FIRE || block == BlockRegistrar.BIOLAYER_PORTAL;
		}

		public boolean isValid()
		{
			return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && height <= 21;
		}

		public void placePortalBlocks()
		{
			for(int i = 0; i < width; ++i)
			{
				BlockPos pos = bottomLeft.offset(rightDir, i);

				for(int j = 0; j < height; ++j)
				{
					world.setBlockState(pos.up(j), BlockRegistrar.BIOLAYER_PORTAL.getDefaultState().with(BiolayerPortalBlock.AXIS, axis), 18);
				}
			}

		}

		private boolean func_196899_f()
		{
			return portalBlockCount >= width * height;
		}

		public boolean func_208508_f()
		{
			return isValid() && func_196899_f();
		}
	}
}