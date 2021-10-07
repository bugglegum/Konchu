package com.rainbowdestiny.konchu.client.render.entity;

import com.rainbowdestiny.konchu.client.render.model.SnailModel;
import com.rainbowdestiny.konchu.common.entities.SnailEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SnailRenderer extends GeoEntityRenderer<SnailEntity>
{
    public SnailRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new SnailModel());
    }
}