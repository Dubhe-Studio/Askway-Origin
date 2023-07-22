package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.AskwayOrigin;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class AskwayModConfiguredFeature {
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, AskwayOrigin.of("willow"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> PEACH = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, AskwayOrigin.of("peach"));

}
