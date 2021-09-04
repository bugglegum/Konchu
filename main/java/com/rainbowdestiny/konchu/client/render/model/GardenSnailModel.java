package com.rainbowdestiny.konchu.client.render.model;

import com.rainbowdestiny.konchu.Konchu;
import com.rainbowdestiny.konchu.common.entities.GardenSnailEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GardenSnailModel extends AnimatedGeoModel<GardenSnailEntity> {
    
	//Texture Assignment
    protected static final ResourceLocation BROWN = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/brown_garden_snail.png");
    protected static final ResourceLocation GREEN = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/green_garden_snail.png");
    protected static final ResourceLocation GRAY = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/gray_garden_snail.png");
    protected static final ResourceLocation GOLDEN = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/golden_garden_snail.png");		
    protected static final ResourceLocation GARY = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/gary.png");
    protected static final ResourceLocation SHELDON = new ResourceLocation(Konchu.MOD_ID, "textures/entity/snail/sheldon.png");

    @Override
    public ResourceLocation getTextureLocation(GardenSnailEntity entity) {
	switch(entity.getName().getString()) {
    	case "Gary": return GARY;
    	case "Sheldon": return SHELDON;
    	default: return getEntityTypeLocation(entity);
		}
    }

	public ResourceLocation getEntityTypeLocation(GardenSnailEntity entity) {
	    switch(entity.getSnailType()) {
	        case 1: return BROWN;
	        case 2: return GREEN;
	        case 3: return GRAY;
	        default: return BROWN;
	        }
	    }
	
	//Model Assignment
    @Override
    public ResourceLocation getModelLocation(GardenSnailEntity object) {
        return new ResourceLocation(Konchu.MOD_ID, "geo/snail.geo.json");
    }

    //Animation Assignment
	@Override
    public ResourceLocation getAnimationFileLocation(GardenSnailEntity object) {
		if (!object.isHiding()) {
			if (object.isMoving()) {
				return new ResourceLocation(Konchu.MOD_ID, "animations/animation.snail.move.json");
			} else {
				return new ResourceLocation(Konchu.MOD_ID, "animations/animation.snail.idle.json");
			}
			
		} else {
			return new ResourceLocation(Konchu.MOD_ID, "animations/animation.snail.hiding.json");
		}
		
	}
	
}
