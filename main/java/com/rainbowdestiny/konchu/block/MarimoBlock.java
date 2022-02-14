package com.rainbowdestiny.konchu.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MarimoBlock extends BushBlock implements IGrowable, IWaterLoggable {
	public static final IntegerProperty MOSS_BALL_SIZE = IntegerProperty.create("moss", 0, 9);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape SMALL = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
	protected static final VoxelShape MEDIUM = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	protected static final VoxelShape BIG = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	protected static final VoxelShape FULL = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	protected static final VoxelShape FULL_SINGLE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	protected static final VoxelShape FULL_DOUBLE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	protected static final VoxelShape FULL_TRIPLE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	protected static final VoxelShape FULL_QUAD = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	protected static final VoxelShape FULL_PENTA = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	
	public MarimoBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(MOSS_BALL_SIZE, Integer.valueOf(1)).setValue(WATERLOGGED, Boolean.valueOf(true)));
	}	
	
	   public boolean isRandomlyTicking(BlockState state) {
		      return state.getValue(MOSS_BALL_SIZE) < 8;
		   }

		   //public void randomTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
		      //if (true) {
		         //int i = state.getValue(MOSS_BALL_SIZE);
		         //if (i < 8 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, pos.random.nextInt(5) == 0)) {
		        	 //level.setBlock(pos, state.setValue(MOSS_BALL_SIZE, Integer.valueOf(i + 1)), 8);
		            //net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
		         //}
		         
		      //}

		   //}
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		if (blockstate.is(this)) {
			return blockstate.setValue(MOSS_BALL_SIZE, Integer.valueOf(Math.min(9, blockstate.getValue(MOSS_BALL_SIZE) + 1)));
		} else {
			FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
			boolean flag = fluidstate.getType() == Fluids.WATER;
			return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag));
		}
	}
	
	public static boolean isDead(BlockState state) {
		return !state.getValue(WATERLOGGED);
	}

	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
		BlockPos blockpos = pos.below();
		return this.mayPlaceOn(reader.getBlockState(blockpos), reader, blockpos);
	}

		public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, IWorld p_196271_4_, BlockPos pos, BlockPos blockPos) {
			if (!state.canSurvive(p_196271_4_, pos)) {
				return Blocks.AIR.defaultBlockState();
			} else {
				if (state.getValue(WATERLOGGED)) {
					p_196271_4_.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(p_196271_4_));
				}

				return super.updateShape(state, direction, blockState, p_196271_4_, pos, blockPos);
			}
		}

	@SuppressWarnings("deprecation")
	public boolean canBeReplaced(BlockState state, BlockItemUseContext itemUseContext) {
		if (state.getValue(MOSS_BALL_SIZE) > 8 ) {
			return itemUseContext.getItemInHand().getItem() == this.asItem() && state.getValue(MOSS_BALL_SIZE) < 9 ? true : super.canBeReplaced(state, itemUseContext);
		} else {
			return (state.getValue(MOSS_BALL_SIZE) != 9);
		}
	}
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
			switch(state.getValue(MOSS_BALL_SIZE)) {
			case 1: default: return SMALL;
			case 2: return MEDIUM;
			case 3: return BIG;
			case 4: return FULL;
			case 5: return FULL_SINGLE;
			case 6: return FULL_DOUBLE;
			case 7: return FULL_TRIPLE;
			case 8: return FULL_QUAD;
			case 9: return FULL_PENTA;
			}
		}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
		state.add(MOSS_BALL_SIZE, WATERLOGGED);
	}
	
	public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean type) {
		return false;
	}

	public boolean isBonemealSuccess(World level, Random random, BlockPos pos, BlockState state) {
		return false;
	}

	public void performBonemeal(ServerWorld level, Random random, BlockPos pos, BlockState state) {
		level.setBlock(pos, state.setValue(MOSS_BALL_SIZE, Integer.valueOf(9)), 2);
	}

	public boolean isPathfindable(BlockState state, IBlockReader reader, BlockPos pos, PathType type) {
		return false;
	}
}
