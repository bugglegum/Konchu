package com.rainbowdestiny.konchu.block;

import com.rainbowdestiny.konchu.entity.ChairEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ChairBlock extends HorizontalBlock implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	float yOff;
	protected static final VoxelShape VOX_TOP_BACKBOARD_SOUTH 		= Block.box( 0.0D,  0.0D,  0.0D, 16.0D,  8.0D,  3.0D);
	protected static final VoxelShape VOX_TOP_BACKBOARD_NORTH 		= Block.box( 0.0D,  0.0D, 13.0D, 16.0D,  8.0D, 16.0D);
	protected static final VoxelShape VOX_TOP_BACKBOARD_WEST		= Block.box(13.0D,  0.0D,  0.0D, 16.0D,  8.0D, 16.0D);
	protected static final VoxelShape VOX_TOP_BACKBOARD_EAST		= Block.box( 0.0D,  0.0D,  0.0D,  3.0D,  8.0D, 16.0D);
	protected static final VoxelShape TOP_BACKBOARD_SOUTH 			= Block.box( 0.0D,  0.0D,  0.0D, 16.0D,  8.0D,  1.0D);
	protected static final VoxelShape TOP_BACKBOARD_NORTH 			= Block.box( 0.0D,  0.0D, 15.0D, 16.0D,  8.0D, 16.0D);
	protected static final VoxelShape TOP_BACKBOARD_WEST			= Block.box(15.0D,  0.0D,  0.0D, 16.0D,  8.0D, 16.0D);
	protected static final VoxelShape TOP_BACKBOARD_EAST			= Block.box( 0.0D,  0.0D,  0.0D,  1.0D,  8.0D, 16.0D);	
	protected static final VoxelShape VOX_BOTTOM_BACKBOARD_SOUTH 	= VoxelShapes.join(Block.box( 0.0D, 0.0D,  0.0D, 16.0D, 16.0D,  3.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), IBooleanFunction.OR);
	protected static final VoxelShape VOX_BOTTOM_BACKBOARD_NORTH 	= VoxelShapes.join(Block.box( 0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), IBooleanFunction.OR);
	protected static final VoxelShape VOX_BOTTOM_BACKBOARD_WEST		= VoxelShapes.join(Block.box(13.0D, 0.0D,  0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), IBooleanFunction.OR);
	protected static final VoxelShape VOX_BOTTOM_BACKBOARD_EAST		= VoxelShapes.join(Block.box( 0.0D, 0.0D,  0.0D,  3.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), IBooleanFunction.OR);
	protected static final VoxelShape BOTTOM_BACKBOARD_SOUTH 		= VoxelShapes.join(Block.box( 0.0D, 0.0D,  0.0D, 16.0D, 16.0D,  1.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), IBooleanFunction.OR);
	protected static final VoxelShape BOTTOM_BACKBOARD_NORTH 		= VoxelShapes.join(Block.box( 0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), IBooleanFunction.OR);
	protected static final VoxelShape BOTTOM_BACKBOARD_WEST			= VoxelShapes.join(Block.box(15.0D, 0.0D,  0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), IBooleanFunction.OR);
	protected static final VoxelShape BOTTOM_BACKBOARD_EAST			= VoxelShapes.join(Block.box( 0.0D, 0.0D,  0.0D,  1.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), IBooleanFunction.OR);
	
	public ChairBlock(Properties properties, float yOff) {
		super(properties);
		this.yOff = yOff;
		this.registerDefaultState(super.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		DoubleBlockHalf height = state.getValue(HALF);
		Direction facing = state.getValue(FACING);
		switch (height) {
		case UPPER: {
			switch(facing) {
			case SOUTH: return VOX_TOP_BACKBOARD_SOUTH;
			case WEST: return VOX_TOP_BACKBOARD_WEST;
			case NORTH: default: return VOX_TOP_BACKBOARD_NORTH;
			case EAST: return VOX_TOP_BACKBOARD_EAST;
			}
		}
		case LOWER: {
			switch(facing) {
			case SOUTH: return VOX_BOTTOM_BACKBOARD_SOUTH;
			case NORTH: default: return VOX_BOTTOM_BACKBOARD_NORTH;
			case WEST: return VOX_BOTTOM_BACKBOARD_WEST;
			case EAST: return VOX_BOTTOM_BACKBOARD_EAST;
			}
		}
		}
		return super.getShape(state, reader, pos, context);
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		DoubleBlockHalf height = state.getValue(HALF);
		Direction facing = state.getValue(FACING);
		switch (height) {
		case UPPER: {
			switch(facing) {
			case SOUTH: return TOP_BACKBOARD_SOUTH;
			case WEST: return TOP_BACKBOARD_WEST;
			case NORTH: default: return TOP_BACKBOARD_NORTH;
			case EAST: return TOP_BACKBOARD_EAST;
			}
		}
		case LOWER: {
			switch(facing) {
			case SOUTH: return BOTTOM_BACKBOARD_SOUTH;
			case NORTH: default: return BOTTOM_BACKBOARD_NORTH;
			case WEST: return BOTTOM_BACKBOARD_WEST;
			case EAST: return BOTTOM_BACKBOARD_EAST;
			}
		}
		}
		return super.getCollisionShape(state, reader, pos, context);	}
	
	
	@SuppressWarnings("deprecation")
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			ChairEntity.create(world, pos, 0.4, player);
		}
		return super.use(state, world, pos, player, hand, hit);
	}

	public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		FluidState fluidstate = world.getFluidState(pos.above());
		world.setBlock(pos.above(), state.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(HALF, DoubleBlockHalf.UPPER), 3);
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity entity) {
		if (!world.isClientSide) {
			if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
				world.destroyBlock(pos.above(), false);
			} else {
				world.destroyBlock(pos.below(), false);
			}
		}
		super.playerWillDestroy(world, pos, state, entity);
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF, WATERLOGGED);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getHorizontalDirection();
		BlockPos blockpos = context.getClickedPos();
		BlockPos blockpos1 = blockpos.relative(direction);
		return context.getLevel().getBlockState(blockpos1).canBeReplaced(context) ? this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(HALF, DoubleBlockHalf.LOWER) : null;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}
}
