package com.rainbowdestiny.konchu.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.ToolType;

public class TileBlock extends Block implements IWaterLoggable {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public TileBlock(Material material) {
		super(Properties.of(material).harvestLevel(0).harvestTool(ToolType.PICKAXE));
	}

	public TileBlock(Material material,int lightLevel) {
		super(Properties.of(material).harvestLevel(0).harvestTool(ToolType.PICKAXE).lightLevel((state) -> {
			return lightLevel;
		}));
	}

	public TileBlock(Material material,SoundType sound) {
		super(Properties.of(material).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(sound));
	}

	public TileBlock(Properties properties) {
		super(properties);
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockPos blockpos = context.getClickedPos();
		FluidState fluidstate = context.getLevel().getFluidState(blockpos);
		return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state1, Direction dir, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2) {
		if (state1.getValue(WATERLOGGED)) {
			world.getLiquidTicks().scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state1, dir, state2, world, pos1, pos2);
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
		super.createBlockStateDefinition(state);
		state.add(WATERLOGGED);
	}
}



