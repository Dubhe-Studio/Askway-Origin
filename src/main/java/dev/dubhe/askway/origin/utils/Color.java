package dev.dubhe.askway.origin.utils;

import org.joml.Vector3f;

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

    public Vector3f getVec3f() {
        float red = (float) 1.0 / 255 * this.red;
        float green = (float) 1.0 / 255 * this.green;
        float blue = (float) 1.0 / 255 * this.blue;
        return new Vector3f(red, green, blue);
    }

    public float getAlphaF() {
        return (float) 1.0 / 255 * this.alpha;
    }
}
