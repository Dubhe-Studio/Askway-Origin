package dev.dubhe.askway.origin.events.handler;

import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.visuals.IVisual;
import dev.dubhe.askway.origin.network.MagicalVisualNetworkImpl;

public class AskwayModNetworkHandler {
    public static void visual(MagicalVisualNetworkImpl.MagicalVisualPack pack) {
        IVisual.VISUAL_CUSTOM_REGISTRY.get(pack.id()).display(pack.caster(), pack.target(), AbstractElement.ELEMENT_CUSTOM_REGISTRY.get(pack.element()), pack.energy());
    }
}
