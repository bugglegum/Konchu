package com.rainbowdestiny.konchu.common.block;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StairsBlock extends Block implements IWaterLoggable {
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
	public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape TOP_AABB = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape BOTTOM_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	protected static final VoxelShape OCTET_NNN = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
	protected static final VoxelShape OCTET_NNP = Block.box(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
	protected static final VoxelShape OCTET_NPN = Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
	protected static final VoxelShape OCTET_NPP = Block.box(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
	protected static final VoxelShape OCTET_PNN = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
	protected static final VoxelShape OCTET_PNP = Block.box(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
	protected static final VoxelShape OCTET_PPN = Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
	protected static final VoxelShape OCTET_PPP = Block.box(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape[] TOP_SHAPES = makeShapes(TOP_AABB, OCTET_NNN, OCTET_PNN, OCTET_NNP, OCTET_PNP);
	protected static final VoxelShape[] BOTTOM_SHAPES = makeShapes(BOTTOM_AABB, OCTET_NPN, OCTET_PPN, OCTET_NPP, OCTET_PPP);
	private static final int[] SHAPE_BY_STATE = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};
	private StairGetter stair;

	private static VoxelShape[] makeShapes(VoxelShape voxelShape1, VoxelShape voxelShape2, VoxelShape voxelShape3, VoxelShape voxelShape4, VoxelShape voxelShape5) {
		return IntStream.range(0, 16).mapToObj((map) -> {
			return makeStairShape(map, voxelShape1, voxelShape2, voxelShape3, voxelShape4, voxelShape5);
		}).toArray((p_199778_0_) -> {
			return new VoxelShape[p_199778_0_];
		});
	}

	private static VoxelShape makeStairShape(int i, VoxelShape shape1, VoxelShape shape2, VoxelShape shape3, VoxelShape shape4, VoxelShape shape5) {
		VoxelShape voxelshape = shape1;
		if ((i & 1) != 0) {
			voxelshape = VoxelShapes.or(shape1, shape2);
		}

		if ((i & 2) != 0) {
			voxelshape = VoxelShapes.or(voxelshape, shape3);
		}

		if ((i & 4) != 0) {
			voxelshape = VoxelShapes.or(voxelshape, shape4);
		}

		if ((i & 8) != 0) {
			voxelshape = VoxelShapes.or(voxelshape, shape5);
		}

		return voxelshape;
	}

	public StairsBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM).setValue(SHAPE, StairsShape.STRAIGHT).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return (state.getValue(HALF) == Half.TOP ? TOP_SHAPES : BOTTOM_SHAPES)[SHAPE_BY_STATE[this.getShapeIndex(state)]];
	}

	private int getShapeIndex(BlockState state) {
		return state.getValue(SHAPE).ordinal() * 4 + state.getValue(FACING).get2DDataValue();
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		this.getStair().getBlock().get().animateTick(state, world, pos, rand);
	}

	public void attack(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
		this.getStair().getBlock().get().defaultBlockState().attack(world, pos, entity);
	}

	public void destroy(IWorld world, BlockPos pos, BlockState state) {
		this.getStair().getBlock().get().destroy(world, pos, state);
	}

	@SuppressWarnings("deprecation")
	public float getExplosionResistance() {
		return this.getStair().getBlock().get().getExplosionResistance();
	}

	@SuppressWarnings("deprecation")
	public void onPlace(BlockState state1, World world, BlockPos pos, BlockState state2, boolean bool) {
		if (!state1.is(state1.getBlock())) {
			this.getStair().getBlock().get().defaultBlockState().neighborChanged(world, pos, Blocks.AIR, pos, false);
			this.getStair().getBlock().get().onPlace(this.getStair().getBlock().get().defaultBlockState(), world, pos, state2, false);
		}
	}

	public void onRemove(BlockState state1, World world, BlockPos pos, BlockState state2, boolean bool) {
		if (!state1.is(state2.getBlock())) {
			this.getStair().getBlock().get().defaultBlockState().onRemove(world, pos, state2, bool);
		}
	}

	public void stepOn(World world, BlockPos pos, Entity state) {
		this.getStair().getBlock().get().stepOn(world, pos, state);
	}

	public boolean isRandomlyTicking(BlockState state) {
		return this.getStair().getBlock().get().isRandomlyTicking(state);
	}

	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		this.getStair().getBlock().get().randomTick(state, world, pos, rand);
	}

	@SuppressWarnings("deprecation")
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		this.getStair().getBlock().get().tick(state, world, pos, rand);
	}

	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult result) {
		return this.getStair().getBlock().get().defaultBlockState().use(world, entity, hand, result);
	}

	public void wasExploded(World world, BlockPos pos, Explosion explosion) {
		this.getStair().getBlock().get().wasExploded(world, pos, explosion);
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getClickedFace();
		BlockPos blockpos = context.getClickedPos();
		FluidState fluidstate = context.getLevel().getFluidState(blockpos);
		BlockState blockstate = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection()).setValue(HALF, direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
		return blockstate.setValue(SHAPE, getStairsShape(blockstate, context.getLevel(), blockpos));
	}

	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state1, Direction dir, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2) {
		if (state1.getValue(WATERLOGGED)) {
			world.getLiquidTicks().scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return dir.getAxis().isHorizontal() ? state1.setValue(SHAPE, getStairsShape(state1, world, pos1)) : super.updateShape(state1, dir, state2, world, pos1, pos2);
	}

	private static StairsShape getStairsShape(BlockState state, IBlockReader reader, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockState blockstate = reader.getBlockState(pos.relative(direction));
		if (isStairs(blockstate) && state.getValue(HALF) == blockstate.getValue(HALF)) {
			Direction direction1 = blockstate.getValue(FACING);
			if (direction1.getAxis() != state.getValue(FACING).getAxis() && canTakeShape(state, reader, pos, direction1.getOpposite())) {
				if (direction1 == direction.getCounterClockWise()) {
					return StairsShape.OUTER_LEFT;
				}
				return StairsShape.OUTER_RIGHT;
			}
		}

		BlockState blockstate1 = reader.getBlockState(pos.relative(direction.getOpposite()));
		if (isStairs(blockstate1) && state.getValue(HALF) == blockstate1.getValue(HALF)) {
			Direction direction2 = blockstate1.getValue(FACING);
			if (direction2.getAxis() != state.getValue(FACING).getAxis() && canTakeShape(state, reader, pos, direction2)) {
				if (direction2 == direction.getCounterClockWise()) {
					return StairsShape.INNER_LEFT;
				}
				return StairsShape.INNER_RIGHT;
			}
		}
		return StairsShape.STRAIGHT;
	}

	private static boolean canTakeShape(BlockState state, IBlockReader reader, BlockPos pos, Direction dir) {
		BlockState blockstate = reader.getBlockState(pos.relative(dir));
		return !isStairs(blockstate) || blockstate.getValue(FACING) != state.getValue(FACING) || blockstate.getValue(HALF) != state.getValue(HALF);
	}

	public static boolean isStairs(BlockState state) {
		return state.getBlock() instanceof StairsBlock;
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings({ "deprecation", "incomplete-switch" })
	public BlockState mirror(BlockState state, Mirror mirror) {
		Direction direction = state.getValue(FACING);
		StairsShape stairsshape = state.getValue(SHAPE);
		switch(mirror) {
		case LEFT_RIGHT:
			if (direction.getAxis() == Direction.Axis.Z) {
				switch(stairsshape) {
				case INNER_LEFT:	return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
				case INNER_RIGHT:	return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
				case OUTER_LEFT:	return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
				case OUTER_RIGHT:	return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
				default: 			return state.rotate(Rotation.CLOCKWISE_180);
				}
			} break;
		case FRONT_BACK:
			if (direction.getAxis() == Direction.Axis.X) {
				switch(stairsshape) {
				case INNER_LEFT:	return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
				case INNER_RIGHT:	return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
				case OUTER_LEFT:	return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
				case OUTER_RIGHT:	return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
				case STRAIGHT:		return state.rotate(Rotation.CLOCKWISE_180);
				}
			}
		}
		return super.mirror(state, mirror);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
		state.add(FACING, HALF, SHAPE, WATERLOGGED);
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public boolean isPathfindable(BlockState state, IBlockReader reader, BlockPos pos, PathType type) {
		return false;
	}

	public StairGetter getStair() {
		return stair;
	}

	public Block setStair(StairGetter stair) {
		this.stair = stair;
		return this;
	}

	public abstract interface StairGetter{
		Supplier<Block> getBlock();
	}


}

