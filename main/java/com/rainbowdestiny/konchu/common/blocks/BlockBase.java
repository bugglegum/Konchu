package com.rainbowdestiny.konchu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockBase extends Block {

    public BlockBase(Material material) {
        super(Properties.of(material).harvestLevel(0).harvestTool(ToolType.PICKAXE));
    }
    
    public BlockBase(Material material,int lightLevel) {
        super(Properties.of(material).harvestLevel(0).harvestTool(ToolType.PICKAXE).lightLevel((state) -> {
			return lightLevel;
		}));
    }
    
    public BlockBase(Material material,SoundType sound) {
        super(Properties.of(material).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(sound));
    }

    public BlockBase(Properties properties) {
        super(properties);
    }

}



