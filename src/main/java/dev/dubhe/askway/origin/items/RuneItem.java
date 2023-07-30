package dev.dubhe.askway.origin.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class RuneItem<T> extends Item {
    private final T t;

    public RuneItem(Properties pProperties, T t) {
        super(pProperties);
        this.t = t;
    }

    @NotNull
    public T getData() {
        return this.t;
    }
}
