package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.AskwayOrigin;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AskwayModConfiguredFeature {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> FEATURE_REGISTER = DeferredRegister.create(
            Registries.CONFIGURED_FEATURE, AskwayOrigin.MODID
    );



}
