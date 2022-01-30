package com.rainbowdestiny.konchu.common.item;

import javax.annotation.Nullable;

import net.minecraft.item.Item;

public class ModeledItem extends Item{

	public ModeledItem(Properties properties) {
		super(properties);
	}

	public RenderDimension handRendering() {
		return RenderDimension.MODEL;
	}

	public RenderDimension inventoryRendering() {
		return RenderDimension.FLAT;
	}

	public RenderDimension hatRendering() {
		return RenderDimension.FLAT;
	}

	public RenderDimension itemEntityRendering() {
		return RenderDimension.FLAT;
	}

	public RenderDimension itemFrameRendering() {
		return RenderDimension.FLAT;
	}

	public enum RenderDimension { 
		FLAT(null),
		MODEL("model"); 

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
