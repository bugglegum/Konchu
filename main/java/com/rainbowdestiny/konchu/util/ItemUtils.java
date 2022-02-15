package com.rainbowdestiny.konchu.util;

import java.util.List;
import java.util.logging.Level;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ItemUtils {
    public static List<ITextComponent> addText(List<ITextComponent> tooltip, String text, TextFormatting colour) {
        tooltip.add(new StringTextComponent(colour + text));
        return tooltip;
    }

	public static ActionResult<ItemStack> startUsingInstantly(Level level, PlayerEntity player, Hand hand) {
	      player.startUsingItem(hand);
	      return ActionResult.consume(player.getItemInHand(hand));
	}
}


