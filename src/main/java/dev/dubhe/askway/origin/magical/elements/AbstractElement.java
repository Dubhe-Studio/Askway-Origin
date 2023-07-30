package dev.dubhe.askway.origin.magical.elements;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.utils.Color;
import dev.dubhe.askway.origin.utils.CustomRegistry;

@SuppressWarnings("unused")
public abstract class AbstractElement { // 元素

    public static final CustomRegistry<AbstractElement> ELEMENT_CUSTOM_REGISTRY = new CustomRegistry<>(AskwayOrigin.of("element"));
    public static final AbstractElement METAL = ELEMENT_CUSTOM_REGISTRY.register("metal", new AbstractElement(Color.METAL_WHITE, false, true, true) {
    });
    public static final AbstractElement BOTANY = ELEMENT_CUSTOM_REGISTRY.register("botany", new AbstractElement(Color.BOTANY_GREEN, true, false, false) {
    });
    public static final AbstractElement WATER = ELEMENT_CUSTOM_REGISTRY.register("water", new AbstractElement(Color.WATER_BLUE, false, true, false) {
    });
    public static final AbstractElement FIRE = ELEMENT_CUSTOM_REGISTRY.register("fire", new AbstractElement(Color.FIRE_RED, true, false, true) {
    });
    public static final AbstractElement EARTH = ELEMENT_CUSTOM_REGISTRY.register("earth", new AbstractElement(Color.EARTH_BROWN, false, false, false) {
    });
    public static final AbstractElement THUNDER = ELEMENT_CUSTOM_REGISTRY.register("thunder", new AbstractElement(Color.THUNDER_PURPLE, false, false, true) {
    });
    public static final AbstractElement FREEZE = ELEMENT_CUSTOM_REGISTRY.register("freeze", new AbstractElement(Color.FREEZE_BLUE, true, true, true) {
    });
    public static final AbstractElement WIND = ELEMENT_CUSTOM_REGISTRY.register("wind", new AbstractElement(Color.WIND_BLUE, true, true, false) {
    });

    private final boolean[] code;
    private final Color color;

    /**
     * 抽象元素
     *
     * @param color 颜色
     * @param code  编码
     */
    protected AbstractElement(Color color, boolean... code) {
        this.code = code;
        this.color = color;
    }

    /**
     * @return 元素编码
     */
    public boolean[] getCode() {
        return code;
    }

    /**
     * @return 元素颜色
     */
    public Color getColor() {
        return color;
    }
}
