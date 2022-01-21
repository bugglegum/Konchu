package com.rainbowdestiny.konchu.client.render.model;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.common.entities.FrogEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrogModel extends AnimatedGeoModel<FrogEntity> {

	//Texture Assignment
	protected static final ResourceLocation GREEN = new ResourceLocation(Konchu.MOD_ID, "textures/entity/frog/green_frog.png");
	protected static final ResourceLocation BROWN = new ResourceLocation(Konchu.MOD_ID, "textures/entity/frog/brown_frog.png");
	protected static final ResourceLocation KEROPPI = new ResourceLocation(Konchu.MOD_ID, "textures/entity/frog/keroppi.png");
	protected static final ResourceLocation RANA = new ResourceLocation(Konchu.MOD_ID, "textures/entity/frog/rana.png");
	@Override
	public ResourceLocation getTextureLocation(FrogEntity entity) {
		switch(entity.getName().getString()) {
		case "Keroppi": return KEROPPI;
		case "keroppi": return KEROPPI;
		case "KEROPPI": return KEROPPI;
		case "Rana": return RANA;
		case "rana": return RANA;
		case "RANA": return RANA;
		default: return getEntityTypeLocation(entity);
		}
	}

	public ResourceLocation getEntityTypeLocation(FrogEntity entity) {
		switch(entity.getFrogType()) {
		case 1: return GREEN;
		case 2: return BROWN;
		default: return GREEN;
		}
	}

	//Model Assignment
	@Override
	public ResourceLocation getModelLocation(FrogEntity object) {
		return new ResourceLocation(Konchu.MOD_ID, "geo/frog.geo.json");
	}

	//Animation Assignment
	@Override
	public ResourceLocation getAnimationFileLocation(FrogEntity object) {
		if (!object.isInWater()) {
			if (object.isOnGround()) {
				if (object.isMoving()) {
					return new ResourceLocation(Konchu.MOD_ID, "animations/animation.frog.walk.json");
				} else {
					return new ResourceLocation(Konchu.MOD_ID, "animations/animation.frog.idle.json");
				} 
			} else {
				return new ResourceLocation(Konchu.MOD_ID, "animations/animation.frog.jump.json");
			}
		} else {
			return new ResourceLocation(Konchu.MOD_ID, "animations/animation.frog.swim.json");
		}
	}
}
