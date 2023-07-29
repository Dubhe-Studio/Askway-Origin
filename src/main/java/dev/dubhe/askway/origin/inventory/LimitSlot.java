package dev.dubhe.askway.origin.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LimitSlot extends Slot {
    private final ItemCheck mayPlace;

    public LimitSlot(Container pContainer, int pSlot, int pX, int pY, ItemCheck mayPlace) {
        super(pContainer, pSlot, pX, pY);
        this.mayPlace = mayPlace;
    }

    public LimitSlot(Container pContainer, int pSlot, int pX, int pY) {
        super(pContainer, pSlot, pX, pY);
        this.mayPlace = super::mayPlace;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack pStack) {
        return mayPlace.check(pStack);
    }

    @FunctionalInterface
    public interface ItemCheck {
        boolean check(@NotNull ItemStack pStack);
    }
}
