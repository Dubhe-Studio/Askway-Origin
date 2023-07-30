package dev.dubhe.askway.origin.menu.screen;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.menu.TalismanTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class TalismanTableScreen extends AbstractContainerScreen<TalismanTableMenu> {
    private static final ResourceLocation TALISMAN_TABLE_LOCATION = AskwayOrigin.of("textures/gui/talisman_table.png");

    public TalismanTableScreen(TalismanTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageHeight = 184;
        this.titleLabelX = 8;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(TALISMAN_TABLE_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
