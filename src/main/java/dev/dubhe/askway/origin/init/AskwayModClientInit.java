package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.entities.renderer.SpiritRenderer;
import dev.dubhe.askway.origin.items.ElementRuneItem;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.util.RandomSource;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class AskwayModClientInit {
    public static final RandomSource RANDOM = RandomSource.create();

    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        for (ElementRuneItem elem : AskwayModItems.elems()) {
            event.register((pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            }, elem);
        }
    }

    public static void registerEntityRenderer() {
        AskwayModEntities.SPIRIT_BUILDER.renderer(() -> (pContext) -> new SpiritRenderer(pContext, RANDOM.nextBoolean()));
        AskwayModEntities.MAGIC_BUILDER.renderer(() -> ThrownItemRenderer::new);
    }
}
