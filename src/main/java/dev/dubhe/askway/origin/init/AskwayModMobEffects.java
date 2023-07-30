package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.effects.AstralVisionMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectCategory;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class AskwayModMobEffects {
    public static final RegistryEntry<AstralVisionMobEffect> ASTRAL_VISION = REGISTRATE
            .generic("astral_vision", Registries.MOB_EFFECT, () -> new AstralVisionMobEffect(MobEffectCategory.BENEFICIAL, 3402751))
            .register();

    public static void register() {
        REGISTRATE.addRawLang("effect.askway_origin.astral_vision", "Astral Vision");
    }
}
