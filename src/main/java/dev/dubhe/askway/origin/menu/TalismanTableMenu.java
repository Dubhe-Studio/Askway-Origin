package dev.dubhe.askway.origin.menu;

import dev.dubhe.askway.origin.init.AskwayModBlocks;
import dev.dubhe.askway.origin.init.AskwayModItems;
import dev.dubhe.askway.origin.init.AskwayModMenus;
import dev.dubhe.askway.origin.menu.inventory.LimitSlot;
import dev.dubhe.askway.origin.menu.inventory.TalismanResultSlot;
import dev.dubhe.askway.origin.menu.inventory.TalismanTableContainer;
import dev.dubhe.askway.origin.items.*;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TalismanTableMenu extends AbstractContainerMenu {
    private final TalismanTableContainer craftSlots = new TalismanTableContainer(this);
    private final ResultContainer resultSlots = new ResultContainer();
    private final Player player;
    private final ContainerLevelAccess access;

    public TalismanTableMenu(MenuType<?> type, int containerId, Inventory inventory) {
        this(type, containerId, inventory, ContainerLevelAccess.NULL);
    }

    public TalismanTableMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        this(AskwayModMenus.TALISMAN_TABLE.get(), containerId, inventory, access);
    }

    public TalismanTableMenu(MenuType<?> type, int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(type, containerId);
        this.player = inventory.player;
        this.access = access;
        this.addSlot(new TalismanResultSlot(player, this.craftSlots, this.resultSlots, 0, 89, 51));
        this.addSlot(new LimitSlot(this.craftSlots, 0, 8, 17, pStack -> pStack.is(AskwayModItems.TALISMAN_BRUSH.get()))); // 符笔
        this.addSlot(new LimitSlot(this.craftSlots, 1, 8, 44, pStack -> pStack.is(AskwayModItems.TALISMAN_INK.get()))); // 符墨
        this.addSlot(new LimitSlot(this.craftSlots, 2, 8, 71, pStack -> pStack.is(AskwayModItems.TALISMAN_PAPER.get()))); // 符纸
        for (int i = 0; i < 3; i++) { // 效果
            this.addSlot(new LimitSlot(this.craftSlots, i + 3, 45, 27 + i * 22, stack -> stack.getItem() instanceof EffectRuneItem));
        }
        for (int i = 0; i < 3; i++) { // 视效
            this.addSlot(new LimitSlot(this.craftSlots, i + 6, 133, 27 + i * 22, stack -> stack.getItem() instanceof VisualRuneItem));
        }
        this.addSlot(new LimitSlot(this.craftSlots, 9, 89, 16, stack -> stack.getItem() instanceof ElementRuneItem)); // 元素
        this.addSlot(new LimitSlot(this.craftSlots, 10, 67, 22, stack -> stack.getItem() instanceof GoalRuneItem)); // 目标
        this.addSlot(new LimitSlot(this.craftSlots, 11, 111, 22, stack -> stack.getItem() instanceof ModeRuneItem)); // 方式
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(inventory, i1 + k * 9 + 9, 8 + i1 * 18, 102 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 160));
        }
    }

    public boolean hasNotBrush() {
        return this.craftSlots.getItem(0).isEmpty();
    }

    public boolean hasNotInk() {
        return this.craftSlots.getItem(1).isEmpty();
    }

    public boolean hasNotPaper() {
        return this.craftSlots.getItem(2).isEmpty();
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu pMenu, Level pLevel, Player pPlayer, TalismanTableContainer pContainer, ResultContainer pResult) {
        if (pLevel.isClientSide) return;
        ServerPlayer serverplayer = (ServerPlayer) pPlayer;
        ItemStack itemstack = pContainer.getResult();
        pResult.setItem(0, itemstack);
        pMenu.setRemoteSlot(0, itemstack);
        serverplayer.connection.send(new ClientboundContainerSetSlotPacket(pMenu.containerId, pMenu.incrementStateId(), 0, itemstack));
    }

    @Override
    public void slotsChanged(@NotNull Container pInventory) {
        this.access.execute((p_39386_, p_39387_) -> slotChangedCraftingGrid(this, p_39386_, this.player, this.craftSlots, this.resultSlots));
    }

    @Override
    public void removed(@NotNull Player pPlayer) {
        super.removed(pPlayer);
        this.access.execute((p_39371_, p_39372_) -> this.clearContainer(pPlayer, this.craftSlots));
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex == 0) {
                this.access.execute((p_39378_, p_39379_) -> itemstack1.getItem().onCraftedBy(itemstack1, p_39378_, pPlayer));
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (pIndex >= 10 && pIndex < 46) {
                if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
                    if (pIndex < 37) {
                        if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
            if (pIndex == 0) {
                pPlayer.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    @Override
    public boolean canTakeItemForPickAll(@NotNull ItemStack pStack, Slot pSlot) {
        return pSlot.container != this.resultSlots && super.canTakeItemForPickAll(pStack, pSlot);
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(this.access, pPlayer, AskwayModBlocks.TALISMAN_TABLE.get());
    }
}
