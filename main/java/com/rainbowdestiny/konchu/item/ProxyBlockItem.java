package com.rainbowdestiny.konchu.item;

import java.util.List;

import javax.annotation.Nullable;

import com.rainbowdestiny.konchu.main.util.ItemUtils;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ProxyBlockItem extends BlockItem {
    public String description = "";
    public ProxyBlockItem(Block blockIn, ItemGroup group) {
        super(blockIn, new Item.Properties().tab(group));
    }        
        
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(!description.equals("")) {
        	ItemUtils.addText(tooltip,description, TextFormatting.GREEN);

        }
    }
}
