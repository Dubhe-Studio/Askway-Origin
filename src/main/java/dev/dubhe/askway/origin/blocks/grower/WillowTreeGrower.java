package dev.dubhe.askway.origin.blocks.grower;

import dev.dubhe.askway.origin.init.AskwayModConfiguredFeature;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class WillowTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
        return AskwayModConfiguredFeature.WILLOW;
    }
}
