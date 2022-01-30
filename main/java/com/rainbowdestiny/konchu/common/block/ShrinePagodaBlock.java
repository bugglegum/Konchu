package com.rainbowdestiny.konchu.common.block;

import net.minecraft.block.AbstractBlock;
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
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ShrinePagodaBlock extends Block implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public ShrinePagodaBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(super.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		if(state.getValue(HALF) == DoubleBlockHalf.UPPER) {
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);
		}
		return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).setValue(HALF, DoubleBlockHalf.LOWER);
	}

	public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		FluidState fluidstate = world.getFluidState(pos.above());
		world.setBlock(pos.above(), state.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER)).setValue(HALF, DoubleBlockHalf.UPPER), 3);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = world.getBlockState(blockpos);
		return !blockstate.isFaceSturdy(world, blockpos, Direction.DOWN);
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

	@Override
	public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
		if (!world.isClientSide) {
			if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
				world.destroyBlock(pos.above(), false);
			} else {
				world.destroyBlock(pos.below(), false);
			}
		}
		super.onBlockExploded(state, world, pos, explosion);
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
		state.add(HALF, FACING, WATERLOGGED);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}
}
