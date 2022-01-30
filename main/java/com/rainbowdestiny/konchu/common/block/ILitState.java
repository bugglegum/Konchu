package com.rainbowdestiny.konchu.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.world.World;

public interface ILitState {
	public BlockState getLitState(World world, BlockState state);
}
