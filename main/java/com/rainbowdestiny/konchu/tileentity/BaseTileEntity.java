package com.rainbowdestiny.konchu.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class BaseTileEntity extends TileEntity {

	public BaseTileEntity(TileEntityType<?> tileType, BlockPos blockPos, BlockState blockState) {
		super(tileType);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		return this.save(new CompoundNBT());
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbt = new CompoundNBT();
		this.save(nbt);
		return new SUpdateTileEntityPacket(this.getBlockPos(), 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		load(level.getBlockState(getBlockPos()), pkt.getTag());
	}

	public void sendUpdates() {
		level.setBlocksDirty(getBlockPos(), level.getBlockState(getBlockPos()), level.getBlockState(getBlockPos()));
		level.sendBlockUpdated(getBlockPos(), level.getBlockState(getBlockPos()), level.getBlockState(getBlockPos()), 3);
		setChanged();
	}
}

