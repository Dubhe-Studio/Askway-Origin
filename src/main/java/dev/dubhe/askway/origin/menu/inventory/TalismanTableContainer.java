package dev.dubhe.askway.origin.menu.inventory;

import dev.dubhe.askway.origin.items.*;
import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.modes.IMode;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TalismanTableContainer implements Container, StackedContentsCompatible {
    private final NonNullList<ItemStack> items;
    private final AbstractContainerMenu menu;

    public TalismanTableContainer(AbstractContainerMenu menu) {
        this.items = NonNullList.withSize(12, ItemStack.EMPTY);
        this.menu = menu;
    }

    @Override
    public int getContainerSize() {
        return 12;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public List<ItemStack> getItems() {
        return List.copyOf(this.items);
    }

    @Override
    public @NotNull ItemStack getItem(int pSlot) {
        return pSlot >= this.getContainerSize() ? ItemStack.EMPTY : this.items.get(pSlot);
    }

    @Override
    public @NotNull ItemStack removeItem(int pSlot, int pAmount) {
        ItemStack itemstack = ContainerHelper.removeItem(this.items, pSlot, pAmount);
        if (!itemstack.isEmpty()) {
            this.menu.slotsChanged(this);
        }

        return itemstack;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int pSlot) {
        return ContainerHelper.takeItem(this.items, pSlot);
    }

    @Override
    public void setItem(int pSlot, @NotNull ItemStack pStack) {
        this.items.set(pSlot, pStack);
        this.menu.slotsChanged(this);
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void fillStackedContents(@NotNull StackedContents pContents) {
        for (ItemStack itemstack : this.items) {
            pContents.accountSimpleStack(itemstack);
        }
    }

    public boolean canCraft() {
        return !this.items.get(0).isEmpty() && !this.items.get(1).isEmpty() && !this.items.get(2).isEmpty() && !this.items.get(9).isEmpty() && !this.items.get(10).isEmpty() && !this.items.get(11).isEmpty();
    }

    /**
     * 0符笔、1符墨、2符纸<p>
     * 3、4、5效果<p>
     * 6、7、8效果<p>
     * 9元素<p>
     * 10目标<p>
     * 11方式
     */
    @NotNull
    public ItemStack getResult() {
        if (!this.canCraft()) return ItemStack.EMPTY;
        int itemCount = 0;
        for (int i = 3; i < 12; i++) {
            itemCount = Math.min(this.items.get(i).getCount(), itemCount);
        }
        List<ItemStack> effectStacks = this.items.subList(3, 6);
        List<ItemStack> visualStacks = this.items.subList(6, 9);
        ItemStack elementStack = this.items.get(9);
        ItemStack goalStack = this.items.get(10);
        ItemStack modeStack = this.items.get(11);
        AbstractElement element = null;
        IGoal goal = null;
        IMode mode = null;
        if (elementStack.getItem() instanceof ElementRuneItem item) {
            element = item.getData();
        }
        if (goalStack.getItem() instanceof GoalRuneItem item) {
            goal = item.getData();
        }
        if (modeStack.getItem() instanceof ModeRuneItem item) {
            mode = item.getData();
        }
        if (element == null || goal == null || mode == null) return ItemStack.EMPTY;
        MagicGroup magic = new MagicGroup(element, 15);
        for (ItemStack stack : effectStacks) {
            if (!(stack.getItem() instanceof EffectRuneItem item)) continue;
            magic.addEffects(item.getData());
        }
        for (ItemStack stack : visualStacks) {
            if (!(stack.getItem() instanceof VisualRuneItem item)) continue;
            magic.addVisuals(item.getData());

        }
        return TalismanItem.create(magic, mode, goal);
    }
}
