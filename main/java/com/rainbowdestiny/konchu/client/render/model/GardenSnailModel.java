package com.rainbowdestiny.konchu.client.render.model;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.common.entities.GardenSnailEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GardenSnailModel extends AnimatedGeoModel<GardenSnailEntity>
{

    protected static final ResourceLocation DEFAULT = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/brown_garden_snail.png");
    protected static final ResourceLocation GARY = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/gary.png");
    protected static final ResourceLocation SHELDON = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/sheldon.png");
  
    @Override
    public ResourceLocation getTextureLocation(GardenSnailEntity entity) {
	switch(entity.getName().getString())
	{
    	case "Gary": return GARY;
    	case "Sheldon": return SHELDON;
    	default: return DEFAULT;
		}
    }

    @Override
    public ResourceLocation getModelLocation(GardenSnailEntity object) {
        return new ResourceLocation(Konchu.MOD_ID, "geo/garden_snail.geo.json");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GardenSnailEntity object) {
        return new ResourceLocation(Konchu.MOD_ID, "animations/animation.snail.move.json");
    }
}
