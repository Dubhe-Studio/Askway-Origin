package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.entities.renderer.SpiritRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.util.RandomSource;

public class AskwayModClientInit {
    public static final RandomSource RANDOM = RandomSource.create();

    public static void registerEntityRenderer() {
        AskwayModEntities.SPIRIT_BUILDER.renderer(() -> (pContext) -> new SpiritRenderer(pContext, RANDOM.nextBoolean()));
        AskwayModEntities.MAGIC_BUILDER.renderer(() -> ThrownItemRenderer::new);
    }
}
