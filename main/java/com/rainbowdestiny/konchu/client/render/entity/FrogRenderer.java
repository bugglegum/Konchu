package com.rainbowdestiny.konchu.client.render.entity;

import com.rainbowdestiny.konchu.client.render.model.FrogModel;
import com.rainbowdestiny.konchu.common.entities.FrogEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FrogRenderer extends GeoEntityRenderer<FrogEntity>
{
    public FrogRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new FrogModel());
    }
}