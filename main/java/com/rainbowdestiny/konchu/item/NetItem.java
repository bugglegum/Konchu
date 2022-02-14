package com.rainbowdestiny.konchu.item;

import javax.annotation.Nullable;

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
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class NetItem extends TieredItem{
	private int CatchChance;

	public NetItem(int catchProbability, IItemTier tier, ItemGroup itemGroup) {
		super(tier, new Properties().tab(itemGroup).stacksTo(1));
		this.CatchChance = catchProbability;
	} 
	
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
						player.awardStat(Stats.ITEM_USED.get(this));
					} else {
						player.displayClientMessage(new StringTextComponent(TextFormatting.RED + "Net is full"), true);
					}
				}
			} else {
				player.displayClientMessage(new StringTextComponent(TextFormatting.RED + "Your net missed"), true);
			}
		} else {
			player.displayClientMessage(new StringTextComponent(TextFormatting.RED + "Creature cannot be caught"), true);
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

	public Object tab(ItemGroup tabTools) {
		return null;
	}
	
	public RenderDimension handRendering() {
		return RenderDimension.THREE;
	}

	public RenderDimension inventoryRendering() {
		return RenderDimension.TWO;
	}

	public RenderDimension hatRendering() {
		return RenderDimension.THREE;
	}

	public RenderDimension itemEntityRendering() {
		return RenderDimension.TWO;
	}

	public RenderDimension itemFrameRendering() {
		return RenderDimension.TWO;
	}

	public enum RenderDimension { 
		TWO(null),
		THREE("model"); 
		private String string;

		RenderDimension(@Nullable String string) {
			this.string = string;
		}

		public String toString() {
			if(string != null)
				return "_" + string;
			else return "";
		}
	};
}