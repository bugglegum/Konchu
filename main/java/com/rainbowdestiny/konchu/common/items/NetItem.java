package com.rainbowdestiny.konchu.common.items;

import com.rainbowdestiny.konchu.main.util.RandomMath;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class NetItem extends Item{

	public NetItem(Properties properties) {
		super(properties);
	}
	
	@Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        World world = player.level;
        if(RandomMath.randomRange(1,20) == 2){
        		if(!world.isClientSide) {
        			if(entity instanceof LivingEntity) {
        				CompoundNBT tag = new CompoundNBT();

        				entity.stopRiding();
        				entity.ejectPassengers();
        				entity.saveAsPassenger(tag);

        				if(!stack.hasTag()) {
        					stack.setTag(new CompoundNBT());
        				}

        				if(!stack.getTag().contains("caught_data")) {
        					stack.getTag().put("caught_data", tag);
        					entity.remove();
        		        	player.displayClientMessage(new StringTextComponent(TextFormatting.GREEN + "You caught it!"), true);
        				}else {
        					player.displayClientMessage(new StringTextComponent(TextFormatting.RED + "Net is full!"), true);
        				}
        			}
        		}
        }else {
        	player.displayClientMessage(new StringTextComponent(TextFormatting.RED + "You missed!"), true);
        }
        return true;
    }
    
    @SuppressWarnings("resource")
	@Override
    public ActionResultType useOn(ItemUseContext context) {
        if(!context.getLevel().isClientSide) {
            ItemStack stack = context.getItemInHand();
            if(stack.hasTag() && stack.getTag().contains("caught_data")) {
                CompoundNBT tag = (CompoundNBT) stack.getTag().get("caught_data");
                if(tag != null) {
                    BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
                    
                    Entity e = EntityType.loadEntityRecursive(tag, context.getLevel(), (entity) -> {
                        entity.moveTo(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f, entity.yRot, entity.xRot);
                        return entity;
                    });

                    context.getLevel().addFreshEntity(e);
                    stack.getTag().remove("caught_data");
                }
            }
        }
        return super.useOn(context);
    }
}