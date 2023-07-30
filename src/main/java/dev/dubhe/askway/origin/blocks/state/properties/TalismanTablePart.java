package dev.dubhe.askway.origin.blocks.state.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum TalismanTablePart implements StringRepresentable {
    RIGHT("right"),
    MIDDLE("middle"),
    LEFT("left");
    private final String name;

    TalismanTablePart(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}
