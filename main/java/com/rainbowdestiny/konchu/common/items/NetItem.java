package com.rainbowdestiny.konchu.common.items;

import com.rainbowdestiny.konchu.main.util.RandomMath;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.TieredItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class NetItem extends TieredItem{
	
	//Item Setup
	private int CatchChance;
	
	public NetItem(int catchProbability, IItemTier tier) {
        super(tier, new Properties());
        this.CatchChance = catchProbability;
	}

	//Left Click Behavior
	@Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        World world = player.level;
        Float randomNum = RandomMath.randomRange(0,100);
        	System.out.println(randomNum);
        	System.out.println(this.CatchChance);
        	
        	if(entity instanceof LivingEntity) {
        		if(randomNum <= this.CatchChance) {
        			if(!world.isClientSide) {
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
        					} else {

        					player.displayClientMessage(new StringTextComponent(TextFormatting.RED + "Net is full!"), true);
        					}
        				}
        			} else {
        				
        			player.displayClientMessage(new StringTextComponent(TextFormatting.RED + "You missed!"), true);
        			}
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

    //Extra Assignments
	public Object tab(ItemGroup tabTools) {
		return null;
	}
}