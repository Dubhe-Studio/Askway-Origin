package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.items.ElementRuneItem;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class AskwayModClientInit {
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        for (ElementRuneItem elem : AskwayModItems.elems()) {
            event.register((pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            }, elem);
        }
    }
}
