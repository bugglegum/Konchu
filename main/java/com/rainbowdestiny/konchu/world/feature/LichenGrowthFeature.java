package com.rainbowdestiny.konchu.world.feature;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class LichenGrowthFeature extends TreeDecorator {	
    public static final Codec<LichenGrowthFeature> CODEC;
    public static final LichenGrowthFeature DECORATOR = new LichenGrowthFeature();
    
    protected TreeDecoratorType<?> type() {
        return KonchuFeatures.LICHEN_GROWTH.get();
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }


	@Override
    public void place(ISeedReader world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> updatedBlocks, MutableBoundingBox bounds) {
    /*
    	for (BlockPos pos : logs) {
            if (rand.nextInt(25) == 0) {
                Vector3i dir = Direction.(rand.nextInt(4));
                if (world.getBlockState(pos.offset(dir)).isAir()) {
                    world.setBlockState(pos.offset(dir), KonchuBlocks.LICHEN_GROWTH.get().defaultBlockState().with(LichenGrowthBlock.FACING, dir).with(LichenGrowthBlock.AGE, 2), 3);
                }
            }
        }
    */
    }

}