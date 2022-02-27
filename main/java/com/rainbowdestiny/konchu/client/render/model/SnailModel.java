package com.rainbowdestiny.konchu.client.render.model;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.entity.SnailEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SnailModel extends AnimatedGeoModel<SnailEntity> {

	//Texture Assignment
	protected static final ResourceLocation BROWN	= new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/brown_snail.png");
	protected static final ResourceLocation GREEN	= new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/green_snail.png");
	protected static final ResourceLocation GRAY	= new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/gray_snail.png");
	protected static final ResourceLocation GOLDEN	= new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/golden_snail.png");		
	protected static final ResourceLocation GARY	= new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/gary.png");
	protected static final ResourceLocation SHELDON = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/sheldon.png");

	@Override
	public ResourceLocation getTextureLocation(SnailEntity entity) {
		switch(entity.getName().getString()) {
		case "Gary": return GARY;
		case "gary": return GARY;
		case "GARY": return GARY;
		case "Sheldon": return SHELDON;
		case "sheldon": return SHELDON;
		case "SHELDON": return SHELDON;
		default: return getEntityTypeLocation(entity);
		}
	}

	public ResourceLocation getEntityTypeLocation(SnailEntity entity) {
		switch(entity.getSnailType()) {
		case 1: return BROWN;
		case 2: return GREEN;
		case 3: return GRAY;
		default: return BROWN;
		}
	}

	//Model Assignment
	@Override
	public ResourceLocation getModelLocation(SnailEntity object) {
		return new ResourceLocation(Konchu.MOD_ID, "geo/snail.geo.json");
	}

	//Animation Assignment
	@Override
	public ResourceLocation getAnimationFileLocation(SnailEntity object) {
		if (!object.isHiding()) {
			if (object.isMoving()) {
				return new ResourceLocation(Konchu.MOD_ID, "animations/snail_move.json");
			} else {
				return new ResourceLocation(Konchu.MOD_ID, "animations/snail_idle.json");
			}
		} else {
			return new ResourceLocation(Konchu.MOD_ID, "animations/snail_hiding.json");
		}
	}
}
