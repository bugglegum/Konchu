package com.rainbowdestiny.konchu.common.items;

import java.util.logging.Level;

import com.rainbowdestiny.konchu.main.util.ItemUtils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvents;

public class MagnifierItem extends Item {
	
	//THIS IS ALL JUST SPYGLASS CODE, IGNORE IT. I LEFT IT HERE TO LOOK AT FOR REFERENCE.
	   public static final int USE_DURATION = 1200;
	   public static final float ZOOM_FOV_MODIFIER = 0.1F;

	   public MagnifierItem(Item.Properties properties) {
	      super(properties);
	   }

	   public int getUseDuration(ItemStack p_151222_) {
	      return 1200;
	   }

	   public UseAnim getUseAnimation(ItemStack p_151224_) {
	      return UseAnim.SPYGLASS;
	   }

	   public InteractionResultHolder<ItemStack> use(Level p_151218_, Player p_151219_, InteractionHand p_151220_) {
	      p_151219_.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
	      p_151219_.awardStat(Stats.ITEM_USED.get(this));
	      return ItemUtils.startUsingInstantly(p_151218_, p_151219_, p_151220_);
	   }

	   public ItemStack finishUsingItem(ItemStack p_151209_, Level p_151210_, LivingEntity p_151211_) {
	      this.stopUsing(p_151211_);
	      return p_151209_;
	   }

	   public void releaseUsing(ItemStack p_151213_, Level p_151214_, LivingEntity p_151215_, int p_151216_) {
	      this.stopUsing(p_151215_);
	   }

	   private void stopUsing(LivingEntity p_151207_) {
	      p_151207_.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);
	   }
	}