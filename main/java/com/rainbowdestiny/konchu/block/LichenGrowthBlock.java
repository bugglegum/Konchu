package com.rainbowdestiny.konchu.block;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SixWayBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

@SuppressWarnings("deprecation")
public class LichenGrowthBlock extends Block implements IGrowable, net.minecraftforge.common.IForgeShearable {
	public static final BooleanProperty UP = SixWayBlock.UP;
	public static final BooleanProperty NORTH = SixWayBlock.NORTH;
	public static final BooleanProperty EAST = SixWayBlock.EAST;
	public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
	public static final BooleanProperty WEST = SixWayBlock.WEST;
	public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = SixWayBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((filter) -> {
		return filter.getKey() != Direction.DOWN;
	}).collect(Util.toMap());
	private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	private final Map<BlockState, VoxelShape> shapesCache;

	public LichenGrowthBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState());
	}

	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.below()).isAir();
	}

	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		worldIn.setBlockAndUpdate(pos.below(), this.defaultBlockState());
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState stateDown = worldIn.getBlockState(pos.below());
		return stateDown.() || stateDown.getBlock() == this || stateDown.is(BlockTags.LEAVES) || stateDown.getBlock() == KonchuBlocks.LICHEN_BLOCK.get();
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.isValidPosition(worldIn, currentPos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return this.shapesCache.get(state);
	}

	public boolean ticksRandomly(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (worldIn.random.nextInt(8) == 0 && worldIn.isAreaLoaded(pos, 4)) {
			if (worldIn.isEmptyBlock(pos.below()) && worldIn.getBlockState(pos.above(3)).getBlock() != this) {
				worldIn.setBlockAndUpdate(pos.below(), this.defaultBlockState());
			}
		}
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return false;
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pod, BlockState state, boolean b) {
		return false;
	}

	@Override
	public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
		return false;
	}

	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {		
	}
}

