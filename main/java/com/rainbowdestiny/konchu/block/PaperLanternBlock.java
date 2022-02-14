package com.rainbowdestiny.konchu.block;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class PaperLanternBlock extends Block implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	protected static final VoxelShape SHAPE = VoxelShapes.join(Block.box( 2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D), IBooleanFunction.OR);

	public PaperLanternBlock(AbstractBlock.Properties properties) {
		super(properties.lightLevel((light) -> {
			return 15;
		}));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
		state.add(FACING, WATERLOGGED);
	}

	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
		return Block.canSupportCenter(reader, pos.above(), Direction.UP);
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state1, Direction dir, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2) {
		if (dir.getOpposite() == state1.getValue(FACING) && !state1.canSurvive(world, pos1)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			if (state1.getValue(WATERLOGGED)) {
				world.getLiquidTicks().scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
			}
			return super.updateShape(state1, dir, state2, world, pos1, pos2);
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		if (!context.replacingClickedOnBlock()) {
			BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()));
			if (blockstate.is(this) && blockstate.getValue(FACING) == context.getClickedFace()) {
				return null;
			}
		}
		
		BlockState blockstate1 = this.defaultBlockState();
		IWorldReader iworldreader = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

		for(Direction direction : context.getNearestLookingDirections()) {
			if (direction.getAxis().isHorizontal()) {
				blockstate1 = blockstate1.setValue(FACING, direction.getOpposite());
				if (blockstate1.canSurvive(iworldreader, blockpos)) {
					return blockstate1.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
				}
			}
		}
		return null;
	}

	public BlockState rotate(BlockState state, Rotation rotate) {
		return state.setValue(FACING, rotate.rotate(state.getValue(FACING)));
	}
}
