package com.rainbowdestiny.konchu.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class RenderingEvents {

    private static List<ItemRenderInfo> guiRenders = new ArrayList<>();

    public static ItemRenderInfo addItemRenderer(Item item) {
        ItemRenderInfo ir = new ItemRenderInfo(item);
        guiRenders.add(ir);
        return ir;
    }

    public static List<ItemRenderInfo> getGuiRenders() {
        return guiRenders;
    }

    public static class ItemRenderInfo{

        private Map<ItemCameraTransforms.TransformType, ItemModelMatch> transforms = new HashMap<>();

        private ResourceLocation defaultModelLocation;
        private Item item;

        public ItemRenderInfo(Item i) {
            this.item = i;
            ResourceLocation rlPath = i.getRegistryName();
            this.defaultModelLocation = new ModelResourceLocation(rlPath, "gui");
        }

        public void addTransformType(String s, ItemCameraTransforms.TransformType type){
            ModelLoader.addSpecialModel(new ModelResourceLocation(item.getRegistryName() + "_" + s, "gui"));
            transforms.put(type, new ItemModelMatch(this, s, type));
        }

        public Map<ItemCameraTransforms.TransformType, ItemModelMatch> getTransforms() {
            return transforms;
        }

        public Item getItem() {
            return item;
        }

        public ResourceLocation getDefaultModelLocation() {
            return defaultModelLocation;
        }

        public static class ItemModelMatch{
            private ItemCameraTransforms.TransformType type;
            private ResourceLocation modelLocation;
            private IBakedModel model;

            public ItemModelMatch(ItemRenderInfo iri, String s, ItemCameraTransforms.TransformType type) {
                this.modelLocation = new ModelResourceLocation(iri.getItem().getRegistryName() + "_" + s, "inventory");
                this.type = type;
            }

            public ResourceLocation getModelLocation() {
                return modelLocation;
            }

            public ItemCameraTransforms.TransformType getType() {
                return type;
            }

            public void setModel(IBakedModel model) {
                this.model = model;
            }

            public IBakedModel getModel() {
                return model;
            }
        }
    }
}
