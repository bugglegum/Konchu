package com.rainbowdestiny.konchu.common.items;

import java.util.logging.Level;

import com.rainbowdestiny.konchu.main.util.ItemUtils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class MagnifierItem extends Item {

	public static final int USE_DURATION = 1200;
	public static final float ZOOM_FOV_MODIFIER = 0.2F;

	public MagnifierItem(Item.Properties properties, ItemGroup itemGroup) {
		super(properties.tab(itemGroup));
	}

	public int getUseDuration(ItemStack p_151222_) {
		return 1200;
	}

	public ActionResult<ItemStack> use(Level level, PlayerEntity player, Hand hand) {
		player.awardStat(Stats.ITEM_USED.get(this));
		return ItemUtils.startUsingInstantly(level, player, hand);
	}
}