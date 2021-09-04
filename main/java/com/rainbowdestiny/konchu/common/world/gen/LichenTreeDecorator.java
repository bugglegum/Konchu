package com.rainbowdestiny.konchu.common.world.gen;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.rainbowdestiny.konchu.main.init.KonchuBlocks;
import com.rainbowdestiny.konchu.main.init.KonchuFeatures;

import io.netty.handler.codec.http2.Http2FrameLogger.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class LichenTreeDecorator extends TreeDecorator{
    public static final Codec<LichenTreeDecorator> CODEC;
    public static final LichenTreeDecorator DECORATOR = new LichenTreeDecorator();

    protected TreeDecoratorType<?> treeDecorator() {
        return KonchuFeatures.LICHEN_GROWTH.get();
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }

    @Override
    public void place(ISeedReader world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> updatedBlocks, MutableBoundingBox bounds) {

        for (BlockPos pos : logs) {
            if (rand.nextInt(25) == 0) {
                Direction dir = Direction.byHorizontalIndex(rand.nextInt(4));
                if (world.getBlockState(pos.offset(dir)).isAir()) {
                    world.setBlockState(pos.offset(dir), KonchuBlocks.LICHEN_GROWTH.get().getDefaultState().with(LichenGrowthBlock.FACING, dir).with(LichenGrowthBlock.AGE, 2), 3);
                }
            }
        }
    }
}