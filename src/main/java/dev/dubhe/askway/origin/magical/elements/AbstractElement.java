package dev.dubhe.askway.origin.magical.elements;

import dev.dubhe.askway.origin.utils.Color;

public abstract class AbstractElement { // 元素
    public static final AbstractElement FIRE = new AbstractElement(Color.FIRE_RED, true, false, true) {
    };

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
