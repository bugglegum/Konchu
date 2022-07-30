package com.rainbowdestiny.konchu.world.feature;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.rainbowdestiny.konchu.block.KonchuBlocks;
import com.rainbowdestiny.konchu.block.LichenGrowthBlock;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class LichenGrowthFeature extends Feature<NoFeatureConfig> {
	private static final Direction[] DIRECTIONS = Direction.values();

	public LichenGrowthFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		BlockPos.Mutable blockpos$mutable = pos.mutable();
		for(int i = 64; i < 256; ++i) {
			blockpos$mutable.set(pos);
			blockpos$mutable.move(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
			blockpos$mutable.setY(i);
			if (reader.isEmptyBlock(blockpos$mutable)) {
				for(Direction direction : DIRECTIONS) {
					if (direction != Direction.DOWN && LichenGrowthBlock.isAcceptableNeighbour(reader, blockpos$mutable, direction)) {
						reader.setBlock(blockpos$mutable, KonchuBlocks.LICHEN_GROWTH.get().defaultBlockState().setValue(LichenGrowthBlock.getPropertyForFace(direction), Boolean.valueOf(true)), 2);
						break;
					}
				}
			}
		}
		return false;
	}
}
