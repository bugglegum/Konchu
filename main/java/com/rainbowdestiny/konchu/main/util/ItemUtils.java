package com.rainbowdestiny.konchu.main.util;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ItemUtils {
    public static List<ITextComponent> addText(List<ITextComponent> tooltip, String text, TextFormatting colour) {
        tooltip.add(new StringTextComponent(colour + text));
        return tooltip;
    }
}


