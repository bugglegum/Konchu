package com.rainbowdestiny.konchu.block;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class TatamiBlock extends Block {
	private static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
	public static final BooleanProperty PAIRED = BooleanProperty.create("paired");
	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

	public TatamiBlock(AbstractBlock.Properties properties) {
		super(properties);	
		registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(PAIRED, false));
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction face = context.getClickedFace();
		BlockPos targetPos = context.getClickedPos().relative(face.getOpposite());
		BlockState targetState = context.getLevel().getBlockState(targetPos);
		boolean pairing = false;

		if (context.getPlayer() != null && !context.getPlayer().isShiftKeyDown() && targetState.getBlock() == this && !targetState.getValue(PAIRED)) {
			pairing = true;
		}
		return this.defaultBlockState().setValue(FACING, context.getClickedFace().getOpposite()).setValue(PAIRED, pairing);
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(world, pos, state, placer, stack);
		if (!world.isClientSide) {
			if (placer != null && placer.isShiftKeyDown()) {
				return;
			}
			BlockPos facingPos = pos.relative(state.getValue(FACING));
			BlockState facingState = world.getBlockState(facingPos);
			if (facingState.getBlock() == this && !facingState.getValue(PAIRED)) {
				world.setBlock(facingPos, state.setValue(FACING, state.getValue(FACING).getOpposite()).setValue(PAIRED, true), 3);
				world.blockUpdated(pos, Blocks.AIR);
				state.updateNeighbourShapes(world, pos, 3);
			}
		}
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
		builder.add(PAIRED);
	}

	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state1, Direction dir, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2) {
		return !state1.canSurvive(world, pos1) ? Blocks.AIR.defaultBlockState() : super.updateShape(state1, dir, state2, world, pos1, pos2);
	}

	public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
		return !reader.isEmptyBlock(pos.below());
	}

	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}
}
