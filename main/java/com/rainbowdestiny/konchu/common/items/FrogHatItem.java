package com.rainbowdestiny.konchu.common.items;

import java.util.List;

import javax.annotation.Nullable;

import com.rainbowdestiny.konchu.main.util.ItemUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FrogHatItem extends ModeledItem {
	private EquipmentSlotType slot;
	String description;

	public FrogHatItem(EquipmentSlotType slot, String description, ItemGroup tab) {
		super(new Item.Properties().tab(tab));
		this.slot = slot;
		this.description = description;
	}

	public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
		return slot;
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		ItemUtils.addText(tooltip, description, TextFormatting.YELLOW);
	}
	
	@Override
	public RenderDimension handRendering() {
		return RenderDimension.FLAT;
	}

	@Override
	public RenderDimension hatRendering() {
		return RenderDimension.MODEL;
	}
}
