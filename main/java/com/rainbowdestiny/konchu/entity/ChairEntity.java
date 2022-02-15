package com.rainbowdestiny.konchu.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ChairEntity extends Entity {
	int lazyTimer = 0;
    private BlockPos source;

	public ChairEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	public ChairEntity(World level, BlockPos pos, double yOffset) {
        this(KonchuEntityType.CHAIR.get(), level);
        this.source = pos;
        this.setPos(source.getX() + 0.5, source.getY() + -0.4, source.getZ() + 0.5);
	}

    public BlockPos getSource() {
        return source;
    }
    
	public void mountTo(PlayerEntity player) {
		player.xRot = this.xRot;
		player.yRot = this.yRot;
		player.startRiding(this);
	}

	@Override
	public void tick() {
		super.tick();
		if(lazyTimer < 40) {
			lazyTimer++;
		}
		if(getPassengers().size() < 1 && lazyTimer >= 40) {
			this.remove();
		}
		
	}

	protected void registerData() {
	}

	protected void readAdditional(CompoundNBT compound) {
	}

	protected void writeAdditional(CompoundNBT compound) {
	}

	public static ActionResultType create(World level, BlockPos pos, double yOffset, PlayerEntity player) {
        if(!level.isClientSide()) {
            List<ChairEntity> seats = level.getEntitiesOfClass(ChairEntity.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0));
            if(seats.isEmpty()) {
            	ChairEntity seat = new ChairEntity(level, pos, yOffset);
                level.addFreshEntity(seat);
                player.startRiding(seat, false);
            }
        }
		return ActionResultType.SUCCESS;
    }

	public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
	}

	@Override
	protected void defineSynchedData() {		
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {		
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {		
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
	}
}

