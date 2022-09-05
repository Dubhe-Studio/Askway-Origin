package dev.dubhe.askway.origin.utils;

public class Color {
    public static final Color FIRE_RED = new Color((short) 194, (short) 31, (short) 48, (short) 255);
    public final short red;
    public final short green;
    public final short blue;
    public final short alpha;

    public Color(short red, short green, short blue, short alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
}
