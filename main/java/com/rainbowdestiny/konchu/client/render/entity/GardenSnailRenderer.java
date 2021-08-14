package com.rainbowdestiny.konchu.client.render.entity;

import com.rainbowdestiny.konchu.client.render.model.GardenSnailModel;
import com.rainbowdestiny.konchu.common.entities.GardenSnailEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GardenSnailRenderer extends GeoEntityRenderer<GardenSnailEntity>
{
    public GardenSnailRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new GardenSnailModel());
    }
}